import 'package:flutter/material.dart';
import 'dart:convert';
import 'dart:async';
import 'package:lottie/lottie.dart';
import 'package:http/http.dart' as http;
import 'package:audioplayers/audioplayers.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Suno',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: const SplashScreen(),
    );
  }
}

class SplashScreen extends StatefulWidget {
  const SplashScreen({super.key});

  @override
  _SplashScreenState createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {
  @override
  void initState() {
    super.initState();

    Timer(const Duration(seconds: 3), () {
      Navigator.pushReplacement(
        context,
        MaterialPageRoute(builder: (context) => const TextToAudioScreen()),
      );
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Center(
        child: Lottie.network(
          "https://lottie.host/b769c988-beb7-4adf-80d3-d1665d928e06/70uD4YjDJu.json",
          width: 200,
          height: 200,
        ),
      ),
    );
  }
}

class TextToAudioScreen extends StatefulWidget {
  const TextToAudioScreen({super.key});

  @override
  _TextToAudioScreenState createState() => _TextToAudioScreenState();
}

class _TextToAudioScreenState extends State<TextToAudioScreen> {
  final TextEditingController _textController = TextEditingController();
  bool _isLoading = false;
  String? _errorMessage;
  final AudioPlayer _audioPlayer = AudioPlayer();
  bool _isPlaying = false;
  Duration _currentPosition = Duration.zero;
  Duration _totalDuration = Duration.zero;

  // API Details
  final String apiUrl =
      "https://api-inference.huggingface.co/models/facebook/musicgen-small";
  final String apiKey = "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";

  @override
  void initState() {
    super.initState();

    _audioPlayer.onPositionChanged.listen((position) {
      setState(() {
        _currentPosition = position;
      });
    });

    _audioPlayer.onPlayerStateChanged.listen((state) {
      if (state == PlayerState.completed) {
        setState(() {
          _isPlaying = false;
          _currentPosition = Duration.zero;
        });
      }
    });

    _audioPlayer.onDurationChanged.listen((duration) {
      setState(() {
        _totalDuration = duration;
      });
    });
  }

  Future<void> generateAudio(String text) async {
    setState(() {
      _isLoading = true;
      _errorMessage = null;
    });

    try {
      final response = await http.post(
        Uri.parse(apiUrl),
        headers: {
          "Authorization": "Bearer $apiKey",
          "Content-Type": "application/json",
        },
        body: jsonEncode({"inputs": text}),
      );

      if (response.statusCode == 200) {
        final audioBytes = response.bodyBytes;
        await _audioPlayer.play(BytesSource(audioBytes));
        setState(() {
          _isPlaying = true;
        });
      } else {
        setState(() {
          _errorMessage = "Error: ${response.reasonPhrase}";
        });
      }
    } catch (e) {
      setState(() {
        _errorMessage = "Failed to generate audio: $e";
      });
    } finally {
      setState(() {
        _isLoading = false;
      });
    }
  }

  Future<void> togglePlayPause() async {
    if (_isPlaying) {
      await _audioPlayer.pause();
      setState(() {
        _isPlaying = false;
      });
    } else {
      await _audioPlayer.resume();
      setState(() {
        _isPlaying = true;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Row(
          children: [
            Image.asset(
              'assets/logo.png', // Ensure you add your logo file in assets and declare it in `pubspec.yaml`
              height: 50,
            ),
            const SizedBox(width: 10),
            const Text('Listee HC',
                style: const TextStyle(fontWeight: FontWeight.bold)),
          ],
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            const Text(
              "Enter a description to generate music:",
              style: TextStyle(fontSize: 18),
            ),
            const SizedBox(height: 10),
            TextField(
              controller: _textController,
              decoration: InputDecoration(
                hintText: "e.g., atmospheric synths, airy sounds",
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(8),
                ),
              ),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: _isLoading
                  ? null
                  : () {
                      final text = _textController.text.trim();
                      if (text.isNotEmpty) {
                        generateAudio(text);
                      } else {
                        setState(() {
                          _errorMessage = "Input cannot be empty.";
                        });
                      }
                    },
              child: _isLoading
                  ? const CircularProgressIndicator(color: Colors.white)
                  : const Text("Generate Audio"),
            ),
            const SizedBox(height: 20),
            if (_errorMessage != null)
              Text(
                _errorMessage!,
                style: const TextStyle(color: Colors.red),
              ),
            if (_isPlaying || _currentPosition != Duration.zero)
              Column(
                children: [
                  const SizedBox(height: 20),
                  Slider(
                    min: 0.0,
                    max: _totalDuration.inSeconds.toDouble(),
                    value: _totalDuration.inSeconds > 0
                        ? _currentPosition.inSeconds.toDouble()
                        : 0.0,
                    onChanged: (value) async {
                      final position = Duration(seconds: value.toInt());
                      await _audioPlayer.seek(position);
                    },
                    activeColor: Colors.blue,
                    inactiveColor: Colors.grey,
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      IconButton(
                        icon: Icon(_isPlaying ? Icons.pause : Icons.play_arrow),
                        onPressed: togglePlayPause,
                      ),
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text(
                        "${_currentPosition.toString().split('.').first} / ${_totalDuration.toString().split('.').first}",
                        style: const TextStyle(fontSize: 16),
                      ),
                    ],
                  ),
                ],
              ),
          ],
        ),
      ),
    );
  }

  @override
  void dispose() {
    _textController.dispose();
    _audioPlayer.dispose();
    super.dispose();
  }
}
