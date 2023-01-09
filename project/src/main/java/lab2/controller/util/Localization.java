package lab2.controller.util;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import lab2.model.enums.Language;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class Localization {
    @Getter
    @Setter
    private static Language currentLanguage = Language.ua;
    private static final Locale engLocale = new Locale("en", "US");
    private static final Locale uaLocale = new Locale("ua", "UA");

    public static String getString(@NotNull String tag) {
        return getStringByLang(tag, currentLanguage);
    }

    public static String getUaString(@NotNull String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("MessagesBundle_ua_UA", uaLocale);
        return new String(resourceBundle.getString(key).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    public static String getEnString(@NotNull String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("MessagesBundle_en_US", engLocale);
        return resourceBundle.getString(key);
    }

    /**
     * Returns localized String by key and language from bundle.
     *
     * @param key      - key
     * @param language - language
     * @return null if language is not supported by method
     */
    public static String getStringByLang(@NotNull String key, @NotNull Language language) {
        if (language == Language.en) {
            return getEnString(key);
        } else if (language == Language.ua) {
            return getUaString(key);
        }
        return null;
    }

    public static void changeLocale(@NotNull HttpServletRequest request) {
        try {
            String param = request.getParameter("lang");
            if (param != null) {
                Language language = Language.valueOf(param);
                Localization.setCurrentLanguage(language);
            }
        } catch (Exception e) {
            System.out.println("Error: impossible to get language parameter and set locale");
        }
    }

    private Localization() {
    }
}