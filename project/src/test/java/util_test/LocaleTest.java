package util_test;

import org.junit.Test;
import lab2.controller.util.Localization;
import lab2.model.enums.Language;

public class LocaleTest {
    @Test
    public void getTexts() {
        assert (Localization.getUaString("greeting").equals("Привіт."));
        assert (Localization.getEnString("greeting").equals("Hello."));

        assert (Localization.getStringByLang("greeting", Language.ua).equals("Привіт."));
        assert (Localization.getStringByLang("greeting", Language.en).equals("Hello."));
    }
}
