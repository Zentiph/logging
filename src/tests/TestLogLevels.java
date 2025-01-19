package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import logging.LogLevel;
import logging.LogLevels;

public class TestLogLevels {
    @Test
    public void testToString() {
        String expected = "DEBUG";
        String got = LogLevels.toString(LogLevel.DEBUG);
        assertEquals(expected, got);
        expected = "INFO";
        got = LogLevels.toString(LogLevel.INFO);
        assertEquals(expected, got);
        expected = "WARNING";
        got = LogLevels.toString(LogLevel.WARNING);
        assertEquals(expected, got);
        expected = "WARN";
        got = LogLevels.toString(LogLevel.WARN);
        assertEquals(expected, got);
        expected = "ERROR";
        got = LogLevels.toString(LogLevel.ERROR);
        assertEquals(expected, got);
        expected = "FATAL";
        got = LogLevels.toString(LogLevel.FATAL);
        assertEquals(expected, got);
        expected = "CRITICAL";
        got = LogLevels.toString(LogLevel.CRITICAL);
        assertEquals(expected, got);
    }

    @Test
    public void testToInt() {
        int expected = 0;
        int got = LogLevels.toInt(LogLevel.DEBUG);
        assertEquals(expected, got);
        expected = 1;
        got = LogLevels.toInt(LogLevel.INFO);
        assertEquals(expected, got);
        expected = 2;
        got = LogLevels.toInt(LogLevel.WARNING);
        assertEquals(expected, got);
        expected = 2;
        got = LogLevels.toInt(LogLevel.WARN);
        assertEquals(expected, got);
        expected = 3;
        got = LogLevels.toInt(LogLevel.ERROR);
        assertEquals(expected, got);
        expected = 4;
        got = LogLevels.toInt(LogLevel.FATAL);
        assertEquals(expected, got);
        expected = 4;
        got = LogLevels.toInt(LogLevel.CRITICAL);
        assertEquals(expected, got);
    }

    @Test
    public void testFromInt() {
        LogLevel expected = LogLevel.DEBUG;
        LogLevel got = LogLevels.fromInt(0);
        assertEquals(expected, got);
        expected = LogLevel.INFO;
        got = LogLevels.fromInt(1);
        assertEquals(expected, got);
        expected = LogLevel.WARN;
        got = LogLevels.fromInt(2);
        assertEquals(expected, got);
        expected = LogLevel.ERROR;
        got = LogLevels.fromInt(3);
        assertEquals(expected, got);
        expected = LogLevel.FATAL;
        got = LogLevels.fromInt(4);
        assertEquals(expected, got);
    }

    @Test
    public void testFromString() {
        LogLevel expected = LogLevel.DEBUG;
        LogLevel got = LogLevels.fromString("DEBUG");
        assertEquals(expected, got);
        expected = LogLevel.INFO;
        got = LogLevels.fromString("INFO");
        assertEquals(expected, got);
        expected = LogLevel.WARN;
        got = LogLevels.fromString("WARN");
        assertEquals(expected, got);
        expected = LogLevel.WARNING;
        got = LogLevels.fromString("WARNING");
        assertEquals(expected, got);
        expected = LogLevel.ERROR;
        got = LogLevels.fromString("ERROR");
        assertEquals(expected, got);
        expected = LogLevel.FATAL;
        got = LogLevels.fromString("FATAL");
        assertEquals(expected, got);
        expected = LogLevel.CRITICAL;
        got = LogLevels.fromString("CRITICAL");
        assertEquals(expected, got);
    }
}
