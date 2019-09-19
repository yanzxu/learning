package com.designpattern.command;

public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl remoteControl = new SimpleRemoteControl();
        remoteControl.setCommand(new LightOnCommand(new Light()));
        remoteControl.buttonPressed();
    }
}

