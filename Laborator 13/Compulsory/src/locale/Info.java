package locale;

import java.text.DateFormatSymbols;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    public void execute(String localeTag) {
        Locale locale = Locale.forLanguageTag(localeTag);
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages");
        String message = messages.getString("info");
        System.out.println(message.replace("{0}", localeTag));

        System.out.println("Country: " + locale.getDisplayCountry(locale));
        System.out.println("Language: " + locale.getDisplayLanguage(locale));
        System.out.println("Currency: " + Currency.getInstance(locale).getCurrencyCode() +
                " (" + Currency.getInstance(locale).getDisplayName() + ")");
        System.out.println("Week Days: " + String.join(", ", DateFormatSymbols.getInstance(locale).getWeekdays()));
        System.out.println("Months: " + String.join(", ", DateFormatSymbols.getInstance(locale).getMonths()));
        System.out.println("Today: " + DateFormatSymbols.getInstance(locale).getMonths()[5] +
                " 8, 2016 (" + DateFormatSymbols.getInstance(locale).getMonths()[4] + " 8, 2016)");
    }
}
