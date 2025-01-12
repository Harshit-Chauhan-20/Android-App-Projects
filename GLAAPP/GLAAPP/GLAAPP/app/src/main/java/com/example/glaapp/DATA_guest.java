package com.example.glaapp;

public class DATA_guest {
    String Name, Phone_Number;
    public DATA_guest(){

    }
    public DATA_guest(String name, String phone_Number) {
        Name = name;
        Phone_Number = phone_Number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }
}
