package logging.format.color;

import java.util.Map;

public class Ansi {
    private String code;

    private static final Map<Color, String> COLOR_TO_ANSI = Map.ofEntries(
        Map.entry(Color.BLACK, "\033[30m"),
        Map.entry(Color.RED, "\033[31m"),
        Map.entry(Color.GREEN, "\033[32m"),
        Map.entry(Color.YELLOW, "\033[33m"),
        Map.entry(Color.BLUE, "\033[34m"),
        Map.entry(Color.MAGENTA, "\033[35m"),
        Map.entry(Color.CYAN, "\033[36m"),
        Map.entry(Color.WHITE, "\033[37m"),
        Map.entry(Color.BRIGHT_BLACK, "\033[90m"),
        Map.entry(Color.GRAY, "\033[90m"),
        Map.entry(Color.GREY, "\033[90m"),
        Map.entry(Color.BRIGHT_RED, "\033[91m"),
        Map.entry(Color.BRIGHT_GREEN, "\033[92m"),
        Map.entry(Color.BRIGHT_YELLOW, "\033[93m"),
        Map.entry(Color.BRIGHT_BLUE, "\033[94m"),
        Map.entry(Color.BRIGHT_MAGENTA, "\033[95m"),
        Map.entry(Color.BRIGHT_CYAN, "\033[96m"),
        Map.entry(Color.BRIGHT_WHITE, "\033[97m")
    );

    /**
     * Get the ANSI code of this Ansi object.
     *
     * @return ANSI code
     */
    public String getCode() {
        return code;
    }

    /**
     * Convert a Color to a 24-bit ANSI code.
     *
     * @param color - Color
     * @return ANSI code
     */
    public static Ansi fromColor(Color color) {
        return new Ansi(COLOR_TO_ANSI.get(color));
    }

    /**
     * Convert an RGB value to a 24-bit ANSI code.
     * Invalid r, g, and b values will be clamped.
     *
     * @param r - Red
     * @param g - Green
     * @param b - Blue
     * @return ANSI code
     */
    public static Ansi fromRgb(int r, int g, int b) {
        r = clampRgb(r);
        g = clampRgb(g);
        b = clampRgb(b);

        return new Ansi(String.format("\033[38;2;%d;%d;%dm", r, g, b));
    }

    /**
     * Initialize an Ansi object with an ANSI code.
     * This constructor is private because I don't feel like
     * implementing a regex checker for ANSI codes...
     *
     * @param code - ANSI code
     */
    private Ansi(String code) {
        this.code = code;
    }

    /**
     * Clamp an RGB value to be between 0 and 255.
     *
     * @param value - RGB value
     * @return Clamped value
     */
    private static int clampRgb(int value) {
        return Math.min(Math.max(value, 0), 255);
    }
}
