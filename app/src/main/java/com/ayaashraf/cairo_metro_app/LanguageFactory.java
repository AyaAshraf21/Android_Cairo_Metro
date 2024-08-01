package com.ayaashraf.cairo_metro_app;

public class LanguageFactory
{
    public static Language getLanguage(String languageCode) {
        if (languageCode.equals("en")) {
            return new English();
        } else if (languageCode.equals("ar")) {
            return new Arabic();
        }
        return null;
    }
}
