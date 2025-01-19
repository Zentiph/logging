package tests;

import logging.LogLevel;
import logging.Logger;
import logging.format.ColorFormatter;
import logging.format.color.Ansi;
import logging.format.color.Color;

public class Sandbox {
    public static void main(String[] args) {
        Logger root = Logger.basicLogger("root");

        root.setUp();
        root.setFormatter(new ColorFormatter());

        root.info("Hello there");

        ColorFormatter formatter = root.getFormatterAs(ColorFormatter.class);
        formatter.setDebugColor(Ansi.fromColor(Color.BRIGHT_MAGENTA));

        root.setBaseLevel(LogLevel.DEBUG);
        root.debug("Hello again");
    }
}
