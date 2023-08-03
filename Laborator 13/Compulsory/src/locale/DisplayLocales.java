package locale;

import java.util.Locale;

public class DisplayLocales {
    public void execute() {
        Locale[] availableLocales = Locale.getAvailableLocales();
        for (Locale locale : availableLocales) {
            System.out.println(locale.toString());
        }
    }
}