package app;

import locale.DisplayLocales;
import locale.Info;
import locale.SetLocale;

import java.util.Scanner;

public class LocaleExplore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DisplayLocales displayLocales = new DisplayLocales();
        SetLocale setLocale = new SetLocale();
        Info info = new Info();

        while (true) {
            System.out.println("Enter a command: (display-locales / set-locale / info / exit)");
            String command = scanner.nextLine();

            if (command.equals("display-locales")) {
                displayLocales.execute();
            } else if (command.equals("set-locale")) {
                System.out.print("Enter the locale tag (e.g., en-US, ro-RO): ");
                String localeTag = scanner.nextLine();
                setLocale.execute(localeTag);
            } else if (command.equals("info")) {
                System.out.print("Enter the locale tag (e.g., en-US, ro-RO): ");
                String localeTag = scanner.nextLine();
                info.execute(localeTag);
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("Unknown command");
            }

            System.out.println();
        }
    }
}
