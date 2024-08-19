package com.ayaashraf.cairo_metro_app;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class StationsUtil {
    private static SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LANGUAGE = "language";
    private static String languageCode;
    private static Context context;

    private static final Map<String, String[]> adjacentStationsMapEnglish = new HashMap<>();

    static {
        adjacentStationsMapEnglish.put("New El-Marg", new String[]{"El-Marg"});
        adjacentStationsMapEnglish.put("El-Marg", new String[]{"New El-Marg", "Ezbet El-Nakhl"});
        adjacentStationsMapEnglish.put("Ezbet El-Nakhl", new String[]{"El-Marg", "Ain Shams"});
        adjacentStationsMapEnglish.put("Ain Shams", new String[]{"Ezbet El-Nakhl", "El-Matareyya"});
        adjacentStationsMapEnglish.put("El-Matareyya", new String[]{"Ain Shams", "Helmeyet El-Zaitoun"});
        adjacentStationsMapEnglish.put("Helmeyet El-Zaitoun", new String[]{"El-Matareyya", "Hadayeq El-Zaitoun"});
        adjacentStationsMapEnglish.put("Hadayeq El-Zaitoun", new String[]{"Helmeyet El-Zaitoun", "Saray El-Qobba"});
        adjacentStationsMapEnglish.put("Saray El-Qobba", new String[]{"Hadayeq El-Zaitoun", "Hammamat El-Qobba"});
        adjacentStationsMapEnglish.put("Hammamat El-Qobba", new String[]{"Saray El-Qobba", "Kobri El-Qobba"});
        adjacentStationsMapEnglish.put("Kobri El-Qobba", new String[]{"Hammamat El-Qobba", "Manshiet El Sadr"});
        adjacentStationsMapEnglish.put("Manshiet El Sadr", new String[]{"Kobri El-Qobba", "EL-Demerdash"});
        adjacentStationsMapEnglish.put("EL-Demerdash", new String[]{"Manshiet El Sadr", "Ghamra"});
        adjacentStationsMapEnglish.put("Ghamra", new String[]{"EL-Demerdash", "Al-Shohadaa"});
        adjacentStationsMapEnglish.put("Al-Shohadaa", new String[]{"Ghamra", "Orabi", "Masaraa", "Attaba"});
        adjacentStationsMapEnglish.put("Orabi", new String[]{"Al-Shohadaa", "Nasser"});
        adjacentStationsMapEnglish.put("Nasser", new String[]{"Orabi", "Sadat", "Maspero", "Attaba"});
        adjacentStationsMapEnglish.put("Sadat", new String[]{"Nasser", "Saad Zaghloul", "Mohamed Naguib", "Opera"});
        adjacentStationsMapEnglish.put("Saad Zaghloul", new String[]{"Sadat", "Al-Sayeda Zeinab"});
        adjacentStationsMapEnglish.put("Al-Sayeda Zeinab", new String[]{"Saad Zaghloul", "El-Malek El-Saleh"});
        adjacentStationsMapEnglish.put("El-Malek El-Saleh", new String[]{"Al-Sayeda Zeinab", "Mar Girgis"});
        adjacentStationsMapEnglish.put("Mar Girgis", new String[]{"El-Malek El-Saleh", "El-Zahraa"});
        adjacentStationsMapEnglish.put("El-Zahraa", new String[]{"Mar Girgis", "Dar El-Salam"});
        adjacentStationsMapEnglish.put("Dar El-Salam", new String[]{"El-Zahraa", "Hadayek El-Maadi"});
        adjacentStationsMapEnglish.put("Hadayek El-Maadi", new String[]{"Dar El-Salam", "Maadi"});
        adjacentStationsMapEnglish.put("Maadi", new String[]{"Hadayek El-Maadi", "Sakanat El-Maadi"});
        adjacentStationsMapEnglish.put("Sakanat El-Maadi", new String[]{"Maadi", "Tora El-Balad"});
        adjacentStationsMapEnglish.put("Tora El-Balad", new String[]{"Sakanat El-Maadi", "Kozzika"});
        adjacentStationsMapEnglish.put("Kozzika", new String[]{"Tora El-Balad", "Tora El-Asmant"});
        adjacentStationsMapEnglish.put("Tora El-Asmant", new String[]{"Kozzika", "El-Maasara"});
        adjacentStationsMapEnglish.put("El-Maasara", new String[]{"Tora El-Asmant", "Hadayek Helwan"});
        adjacentStationsMapEnglish.put("Hadayek Helwan", new String[]{"El-Maasara", "Wadi Hof"});
        adjacentStationsMapEnglish.put("Wadi Hof", new String[]{"Hadayek Helwan", "Helwan University"});
        adjacentStationsMapEnglish.put("Helwan University", new String[]{"Wadi Hof", "Ain Helwan"});
        adjacentStationsMapEnglish.put("Ain Helwan", new String[]{"Helwan University", "Helwan"});
        adjacentStationsMapEnglish.put("Helwan", new String[]{"Ain Helwan"});
        adjacentStationsMapEnglish.put("Shubra El-Kheima", new String[]{"Kolleyyet El-Zeraa"});
        adjacentStationsMapEnglish.put("Kolleyyet El-Zeraa", new String[]{"Shubra El-Kheima", "Mezallat"});
        adjacentStationsMapEnglish.put("Mezallat", new String[]{"Kolleyyet El-Zeraa", "Khalafawy"});
        adjacentStationsMapEnglish.put("Khalafawy", new String[]{"Mezallat", "St. Teresa"});
        adjacentStationsMapEnglish.put("St. Teresa", new String[]{"Khalafawy", "Rod El-Farag"});
        adjacentStationsMapEnglish.put("Rod El-Farag", new String[]{"St. Teresa", "Masaraa"});
        adjacentStationsMapEnglish.put("Masaraa", new String[]{"Rod El-Farag", "Al-Shohadaa"});
        adjacentStationsMapEnglish.put("Attaba", new String[]{"Al-Shohadaa", "Mohamed Naguib", "Bab El Shaaria", "Nasser"});
        adjacentStationsMapEnglish.put("Mohamed Naguib", new String[]{"Attaba", "Sadat"});
        adjacentStationsMapEnglish.put("Opera", new String[]{"Sadat", "Dokki"});
        adjacentStationsMapEnglish.put("Dokki", new String[]{"Opera", "El Bohoth"});
        adjacentStationsMapEnglish.put("El Bohoth", new String[]{"Dokki", "Cairo University"});
        adjacentStationsMapEnglish.put("Cairo University", new String[]{"El Bohoth", "Faisal", "Bulaq Al-Dakrour"});
        adjacentStationsMapEnglish.put("Faisal", new String[]{"Cairo University", "Giza"});
        adjacentStationsMapEnglish.put("Giza", new String[]{"Faisal", "Omm El-Masryeen"});
        adjacentStationsMapEnglish.put("Omm El-Masryeen", new String[]{"Giza", "Sakiat Mekky"});
        adjacentStationsMapEnglish.put("Sakiat Mekky", new String[]{"Omm El-Masryeen", "El-Mounib"});
        adjacentStationsMapEnglish.put("El-Mounib", new String[]{"Sakiat Mekky"});
        adjacentStationsMapEnglish.put("Adly Mansour", new String[]{"El Haykestep"});
        adjacentStationsMapEnglish.put("El Haykestep", new String[]{"Adly Mansour", "Omar Ibn El-Khattab"});
        adjacentStationsMapEnglish.put("Omar Ibn El-Khattab", new String[]{"El Haykestep", "Qobaa"});
        adjacentStationsMapEnglish.put("Qobaa", new String[]{"Omar Ibn El-Khattab", "Hesham Barakat"});
        adjacentStationsMapEnglish.put("Hesham Barakat", new String[]{"Qobaa", "El-Nozha"});
        adjacentStationsMapEnglish.put("El-Nozha", new String[]{"Hesham Barakat", "Nadi El-Shams"});
        adjacentStationsMapEnglish.put("Nadi El-Shams", new String[]{"El-Nozha", "Alf Maskan"});
        adjacentStationsMapEnglish.put("Alf Maskan", new String[]{"Nadi El-Shams", "Heliopolis"});
        adjacentStationsMapEnglish.put("Heliopolis", new String[]{"Alf Maskan", "Haroun"});
        adjacentStationsMapEnglish.put("Haroun", new String[]{"Heliopolis", "Al-Ahram"});
        adjacentStationsMapEnglish.put("Al-Ahram", new String[]{"Haroun", "Koleyet El-Banat"});
        adjacentStationsMapEnglish.put("Koleyet El-Banat", new String[]{"Al-Ahram", "Stadium"});
        adjacentStationsMapEnglish.put("Stadium", new String[]{"Koleyet El-Banat", "Fair Zone"});
        adjacentStationsMapEnglish.put("Fair Zone", new String[]{"Stadium", "Abbassiya"});
        adjacentStationsMapEnglish.put("Abbassiya", new String[]{"Fair Zone", "Abdou Pasha"});
        adjacentStationsMapEnglish.put("Abdou Pasha", new String[]{"Abbassiya", "El-Geish"});
        adjacentStationsMapEnglish.put("El-Geish", new String[]{"Abdou Pasha", "Bab El Shaaria"});
        adjacentStationsMapEnglish.put("Bab El Shaaria", new String[]{"El-Geish", "Attaba"});
        adjacentStationsMapEnglish.put("Maspero", new String[]{"Nasser", "Safaa Hegazy"});
        adjacentStationsMapEnglish.put("Safaa Hegazy", new String[]{"Maspero", "Kit Kat"});
        adjacentStationsMapEnglish.put("Kit Kat", new String[]{"Safaa Hegazy", "Tawfikeya", "Sudan"});
        adjacentStationsMapEnglish.put("Imbaba", new String[]{"Sudan", "El-Bohy"});
        adjacentStationsMapEnglish.put("El-Bohy", new String[]{"Imbaba", "El-Kawmeya Al-Arabiya"});
        adjacentStationsMapEnglish.put("El-Kawmeya Al-Arabiya", new String[]{"El-Bohy", "Ring Road"});
        adjacentStationsMapEnglish.put("Ring Road", new String[]{"El-Kawmeya Al-Arabiya", "Rod El-Farag Axis"});
        adjacentStationsMapEnglish.put("Rod El-Farag Axis", new String[]{"Ring Road"});
        adjacentStationsMapEnglish.put("Sudan", new String[]{"Kit Kat", "Imbaba"});
        adjacentStationsMapEnglish.put("Tawfikeya", new String[]{"Kit Kat", "Wadi El-Nile"});
        adjacentStationsMapEnglish.put("Wadi El-Nile", new String[]{"Tawfikeya", "Gamaet El-Dowal Al-Arabiya"});
        adjacentStationsMapEnglish.put("Gamaet El-Dowal Al-Arabiya", new String[]{"Wadi El-Nile", "Bulaq Al-Dakrour"});
        adjacentStationsMapEnglish.put("Bulaq Al-Dakrour", new String[]{"Gamaet El-Dowal Al-Arabiya", "Cairo University"});
    }


    private static final Map<String, String[]> adjacentStationsMapArabic = new HashMap<>();

    static {
        adjacentStationsMapArabic.put("المرج الجديدة", new String[]{"المرج"});
        adjacentStationsMapArabic.put("المرج", new String[]{"المرج الجديدة", "عزبة النخل"});
        adjacentStationsMapArabic.put("عزبة النخل", new String[]{"المرج", "عين شمس"});
        adjacentStationsMapArabic.put("عين شمس", new String[]{"عزبة النخل", "المطرية"});
        adjacentStationsMapArabic.put("المطرية", new String[]{"عين شمس", "حلمية الزيتون"});
        adjacentStationsMapArabic.put("حلمية الزيتون", new String[]{"المطرية", "حدائق الزيتون"});
        adjacentStationsMapArabic.put("حدائق الزيتون", new String[]{"حلمية الزيتون", "سراي القبة"});
        adjacentStationsMapArabic.put("سراي القبة", new String[]{"حدائق الزيتون", "حمامات القبة"});
        adjacentStationsMapArabic.put("حمامات القبة", new String[]{"سراي القبة", "كوبري القبة"});
        adjacentStationsMapArabic.put("كوبري القبة", new String[]{"حمامات القبة", "منشية الصدر"});
        adjacentStationsMapArabic.put("منشية الصدر", new String[]{"كوبري القبة", "الدمرداش"});
        adjacentStationsMapArabic.put("الدمرداش", new String[]{"منشية الصدر", "غمرة"});
        adjacentStationsMapArabic.put("غمرة", new String[]{"الدمرداش", "الشهداء"});
        adjacentStationsMapArabic.put("الشهداء", new String[]{"غمرة", "عرابي", "المسرة", "العتبة"});
        adjacentStationsMapArabic.put("عرابي", new String[]{"الشهداء", "ناصر"});
        adjacentStationsMapArabic.put("ناصر", new String[]{"عرابي", "السادات", "ماسبيرو", "العتبة"});
        adjacentStationsMapArabic.put("السادات", new String[]{"ناصر", "سعد زغلول", "محمد نجيب", "الأوبرا"});
        adjacentStationsMapArabic.put("سعد زغلول", new String[]{"السادات", "السيدة زينب"});
        adjacentStationsMapArabic.put("السيدة زينب", new String[]{"سعد زغلول", "الملك الصالح"});
        adjacentStationsMapArabic.put("الملك الصالح", new String[]{"السيدة زينب", "مار جرجس"});
        adjacentStationsMapArabic.put("مار جرجس", new String[]{"الملك الصالح", "الزهراء"});
        adjacentStationsMapArabic.put("الزهراء", new String[]{"مار جرجس", "دار السلام"});
        adjacentStationsMapArabic.put("دار السلام", new String[]{"الزهراء", "حدائق المعادي"});
        adjacentStationsMapArabic.put("حدائق المعادي", new String[]{"دار السلام", "المعادي"});
        adjacentStationsMapArabic.put("المعادي", new String[]{"حدائق المعادي", "ثكنات المعادي"});
        adjacentStationsMapArabic.put("ثكنات المعادي", new String[]{"المعادي", "طرة البلد"});
        adjacentStationsMapArabic.put("طرة البلد", new String[]{"ثكنات المعادي", "كوتسيكا"});
        adjacentStationsMapArabic.put("كوتسيكا", new String[]{"طرة البلد", "طرة الأسمنت"});
        adjacentStationsMapArabic.put("طرة الأسمنت", new String[]{"كوتسيكا", "المعصرة"});
        adjacentStationsMapArabic.put("المعصرة", new String[]{"طرة الأسمنت", "حدائق حلوان"});
        adjacentStationsMapArabic.put("حدائق حلوان", new String[]{"المعصرة", "وادي حوف"});
        adjacentStationsMapArabic.put("وادي حوف", new String[]{"حدائق حلوان", "جامعة حلوان"});
        adjacentStationsMapArabic.put("جامعة حلوان", new String[]{"وادي حوف", "عين حلوان"});
        adjacentStationsMapArabic.put("عين حلوان", new String[]{"جامعة حلوان", "حلوان"});
        adjacentStationsMapArabic.put("حلوان", new String[]{"عين حلوان"});
        adjacentStationsMapArabic.put("شبرا الخيمة", new String[]{"كلية الزراعة"});
        adjacentStationsMapArabic.put("كلية الزراعة", new String[]{"شبرا الخيمة", "المظلات"});
        adjacentStationsMapArabic.put("المظلات", new String[]{"كلية الزراعة", "الخلفاوي"});
        adjacentStationsMapArabic.put("الخلفاوي", new String[]{"المظلات", "سانت تريزا"});
        adjacentStationsMapArabic.put("سانت تريزا", new String[]{"الخلفاوي", "روض الفرج"});
        adjacentStationsMapArabic.put("روض الفرج", new String[]{"سانت تريزا", "المسرة"});
        adjacentStationsMapArabic.put("المسرة", new String[]{"روض الفرج", "الشهداء"});
        adjacentStationsMapArabic.put("العتبة", new String[]{"الشهداء", "محمد نجيب", "باب الشعرية", "ناصر"});
        adjacentStationsMapArabic.put("محمد نجيب", new String[]{"العتبة", "السادات"});
        adjacentStationsMapArabic.put("الأوبرا", new String[]{"السادات", "الدقي"});
        adjacentStationsMapArabic.put("الدقي", new String[]{"الأوبرا", "البحوث"});
        adjacentStationsMapArabic.put("البحوث", new String[]{"الدقي", "جامعة القاهرة"});
        adjacentStationsMapArabic.put("جامعة القاهرة", new String[]{"البحوث", "فيصل", "بولاق الدكرور"});
        adjacentStationsMapArabic.put("فيصل", new String[]{"جامعة القاهرة", "الجيزة"});
        adjacentStationsMapArabic.put("الجيزة", new String[]{"فيصل", "أم المصريين"});
        adjacentStationsMapArabic.put("أم المصريين", new String[]{"الجيزة", "ساقية مكي"});
        adjacentStationsMapArabic.put("ساقية مكي", new String[]{"أم المصريين", "المنيب"});
        adjacentStationsMapArabic.put("المنيب", new String[]{"ساقية مكي"});
        adjacentStationsMapArabic.put("عدلي منصور", new String[]{"الهايكستب"});
        adjacentStationsMapArabic.put("الهايكستب", new String[]{"عدلي منصور", "عمر بن الخطاب"});
        adjacentStationsMapArabic.put("عمر بن الخطاب", new String[]{"الهايكستب", "قباء"});
        adjacentStationsMapArabic.put("قباء", new String[]{"عمر بن الخطاب", "هشام بركات"});
        adjacentStationsMapArabic.put("هشام بركات", new String[]{"قباء", "النزهة"});
        adjacentStationsMapArabic.put("النزهة", new String[]{"هشام بركات", "نادي الشمس"});
        adjacentStationsMapArabic.put("نادي الشمس", new String[]{"النزهة", "ألف مسكن"});
        adjacentStationsMapArabic.put("ألف مسكن", new String[]{"نادي الشمس", "هليوبوليس"});
        adjacentStationsMapArabic.put("هليوبوليس", new String[]{"ألف مسكن", "هارون"});
        adjacentStationsMapArabic.put("هارون", new String[]{"هليوبوليس", "الأهرام"});
        adjacentStationsMapArabic.put("الأهرام", new String[]{"هارون", "كلية البنات"});
        adjacentStationsMapArabic.put("كلية البنات", new String[]{"الأهرام", "ستاد القاهرة"});
        adjacentStationsMapArabic.put("ستاد القاهرة", new String[]{"كلية البنات", "أرض المعارض"});
        adjacentStationsMapArabic.put("أرض المعارض", new String[]{"ستاد القاهرة", "العباسية"});
        adjacentStationsMapArabic.put("العباسية", new String[]{"أرض المعارض", "عبده باشا"});
        adjacentStationsMapArabic.put("عبده باشا", new String[]{"العباسية", "الجيش"});
        adjacentStationsMapArabic.put("الجيش", new String[]{"عبده باشا", "باب الشعرية"});
        adjacentStationsMapArabic.put("باب الشعرية", new String[]{"الجيش", "العتبة"});
        adjacentStationsMapArabic.put("ماسبيرو", new String[]{"ناصر", "صفاء حجازي"});
        adjacentStationsMapArabic.put("صفاء حجازي", new String[]{"ماسبيرو", "الكيت كات"});
        adjacentStationsMapArabic.put("الكيت كات", new String[]{"صفاء حجازي", "التوفيقية", "السودان"});
        adjacentStationsMapArabic.put("السودان",new String[]{"الكيت كات","إمبابة"});
        adjacentStationsMapArabic.put("إمبابة", new String[]{"السودان", "البوهي"});
        adjacentStationsMapArabic.put("البوهي", new String[]{"إمبابة", "القومية العربية"});
        adjacentStationsMapArabic.put("القومية العربية", new String[]{"البوهي", "الطريق الدائري"});
        adjacentStationsMapArabic.put("الطريق الدائري", new String[]{"القومية العربية", "محور روض الفرج"});
        adjacentStationsMapArabic.put("محور روض الفرج", new String[]{"الطريق الدائري"});
        adjacentStationsMapArabic.put("التوفيقية", new String[]{"الكيت كات", "وادي النيل"});
        adjacentStationsMapArabic.put("وادي النيل", new String[]{"التوفيقية", "جامعة الدول"});
        adjacentStationsMapArabic.put("جامعة الدول", new String[]{"وادي النيل", "بولاق الدكرور"});
        adjacentStationsMapArabic.put("بولاق الدكرور", new String[]{"جامعة الدول", "جامعة القاهرة"});
    }


    public static void initialize(Context ctx) {
        context = ctx.getApplicationContext();
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        languageCode = sharedPreferences.getString(KEY_LANGUAGE, "ar");
    }

    public static String[] getAdjacentStations(String stationName) {
        String[] adjacentStations;
        if (languageCode.equals("en")) {
            adjacentStations = adjacentStationsMapEnglish.get(stationName);
        } else {
            adjacentStations = adjacentStationsMapArabic.get(stationName);
        }
        if (adjacentStations == null) {
            throw new IllegalArgumentException("Unknown station name: " + stationName);
        }
        return adjacentStations;
    }
}
