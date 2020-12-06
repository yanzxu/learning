package com.mars.enums;

import com.mars.exceptions.CommandException;

import java.util.Arrays;

public enum Command {
    L('L'),
    R('R'),
    M('M');

    char value;

     Command(char value) {
        this.value = value;
    }

    public static Command from(char input) {
        return Arrays.stream(Command.values()).filter(comm -> comm.value == input).findFirst().orElseThrow(CommandException::new);
    }

}
