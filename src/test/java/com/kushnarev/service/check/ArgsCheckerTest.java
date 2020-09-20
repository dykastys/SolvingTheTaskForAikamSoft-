package com.kushnarev.service.check;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ArgsCheckerTest {

    private String arg1 = "search";
    private String arg2 = "testSearch.json";
    private String arg3 = "testResults/testResult.json";

    @Before
    public void setTestDirectory() {
        ArgsChecker.inDirectory = "src/test/java/testResources/";
    }

    @After
    public void returnDirectory() {
        ArgsChecker.inDirectory = "JsonInputData/";
    }

    @Test
    public void test_checkArgs_incorrectArgumentQuantity() {
        try {
            ArgsChecker.checkArgs(arg1);
            throw new RuntimeException();
        } catch (IOException | IllegalArgumentException exception) {
            assertThat(exception.getClass().getSimpleName(), is("IllegalArgumentException"));
            assertThat(exception.getMessage(), is("incorrect argument quantity"));
        }
    }

    @Test
    public void test_checkArgs_incorrectFirstArgument() {
        try {
            arg1 = "qwe";
            ArgsChecker.checkArgs(arg1, arg2, arg3);
            throw new RuntimeException();
        } catch (IOException | IllegalArgumentException exception) {
            assertThat(exception.getClass().getSimpleName(), is("IllegalArgumentException"));
            assertThat(exception.getMessage(), is(String.format("incorrect first argument - '%s'", arg1)));
        }
    }

    @Test
    public void test_checkArgs_incorrectSecondArgument() {
        try {
            arg2 = "qwe";
            ArgsChecker.checkArgs(arg1, arg2, arg3);
            throw new RuntimeException();
        } catch (IOException | IllegalArgumentException exception) {
            assertThat(exception.getClass().getSimpleName(), is("IllegalArgumentException"));
            assertThat(exception.getMessage(), is(String.format("incorrect path or name of second argument - '%s'", arg2)));
        }
    }

    @Test
    public void test_checkArgs_inFileNotExist() {
        arg2 = "dd.json";
        try {
            ArgsChecker.checkArgs(arg1, arg2, arg3);
            throw new RuntimeException();
        } catch (IOException | IllegalArgumentException exception) {
            assertThat(exception.getClass().getSimpleName(), is("FileNotFoundException"));
            assertThat(exception.getMessage(), is(String.format("json file '%s' not exist", arg2)));
        }
    }

    @Test
    public void test_checkArgs_nameOutFileIsIncorrect() {
        ArgsChecker.inDirectory = "src/test/java/testResources/";
        arg3= "ddd.jso";
        try {
            ArgsChecker.checkArgs(arg1, arg2, arg3);
            throw new RuntimeException();
        } catch (IOException | IllegalArgumentException exception) {
            assertThat(exception.getClass().getSimpleName(), is("IllegalArgumentException"));
            assertThat(exception.getMessage(), is(String.format("incorrect path or name of third argument - '%s'", arg3)));
        }
    }

    @Test
    public void test_ok() throws IOException {
        ArgsChecker.inDirectory = "src/test/java/testResources/";
        ArgsChecker.checkArgs(arg1, arg2, arg3);
    }
}