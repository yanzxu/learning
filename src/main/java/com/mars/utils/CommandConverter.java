package com.mars.utils;

import com.mars.enums.Command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommandConverter {
    private CommandConverter(){}

    public static List<Command> convertToCommands(String input){
       return IntStream.range(0,input.length()-1).mapToObj(input::charAt).map(Command::from).collect(Collectors.toList());
    }

}
