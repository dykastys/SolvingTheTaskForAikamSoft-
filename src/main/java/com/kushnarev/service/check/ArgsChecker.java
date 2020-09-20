package com.kushnarev.service.check;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArgsChecker {

    static String inDirectory = "JsonInputData/";
    static String outDirectory = "JsonResult/";

    public static void checkArgs(String...args) throws IOException {
        checkLength(args);
        checkFirstArgument(args[0]);
        checkSecondArgument(args[1]);
        checkThirdArgument(args[2]);
    }

    private static void checkLength(String[] args) {
        if(args.length != 3) {
            throw new IllegalArgumentException("incorrect argument quantity");
        }
    }

    private static void checkFirstArgument(String firstArg) {
        if(!firstArg.equals("search") && !firstArg.equals("stat")) {
            throw new IllegalArgumentException(String.format("incorrect first argument - '%s'", firstArg));
        }
    }

    private static void checkSecondArgument(String secondArg) throws FileNotFoundException {
        if(secondArg.length()<=5 || !secondArg.endsWith(".json")) {
            throw new IllegalArgumentException(String.format("incorrect path or name of second argument - '%s'", secondArg));
        }
        Path path = Paths.get(inDirectory + secondArg);
        if(Files.notExists(path)) {
            throw new FileNotFoundException(String.format("json file '%s' not exist", secondArg));
        }
    }

    private static void checkThirdArgument(String thirdArg) throws IOException {
        if(thirdArg.length()<=5 || !thirdArg.endsWith(".json")) {
            throw new IllegalArgumentException(String.format("incorrect path or name of third argument - '%s'", thirdArg));
        }
        Path path = Paths.get(outDirectory + thirdArg);
        if(Files.notExists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException ioException) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
        }
    }
}
