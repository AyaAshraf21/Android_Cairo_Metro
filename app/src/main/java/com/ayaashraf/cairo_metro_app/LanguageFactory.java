package com.ayaashraf.cairo_metro_app;

public class LanguageFactory
{
    private static LanguageFactory instance;

    private LanguageFactory()
    {

    }

    public static LanguageFactory getInstance()
    {
        if (instance == null)
        {
            instance =  new LanguageFactory();
        }
        return instance;
    }
    public Language getLanguage(String languageCode) {
        if (languageCode.equals("en")) {
            return English.getInstance();
        } else if (languageCode.equals("ar")) {
            return Arabic.getInstance();
        }
        return null;
    }
}
