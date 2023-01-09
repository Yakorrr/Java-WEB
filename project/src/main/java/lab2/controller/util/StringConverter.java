package lab2.controller.util;

import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class StringConverter {
    private static final Logger logger = Logger.getLogger(StringConverter.class);

    public static Optional<Integer> strToInt(@NotNull String str) {
        Optional<Integer> result = Optional.empty();

        try {
            result = Optional.of(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            logger.error("Illegal argument: trying to convert " + str + " to " + Integer.class + ". Fail");
        }

        return result;
    }

    public static String decodeParameter(@NotNull String parameter) throws UnsupportedEncodingException {
        return new String(parameter.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    private StringConverter() {
    }
}
