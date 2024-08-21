package com.ayaashraf.cairo_metro_app;

import java.util.ArrayList;
import java.util.Arrays;

public class Arabic implements Language
{
    private static Arabic instance;
    private Arabic()
    {

    }

    public static Arabic getInstance()
    {
        if(instance == null)
        {
            instance = new Arabic();
        }
        return instance;
    }
    @Override
    public ArrayList<String> getLine1() {
        return new ArrayList<>(Arrays.asList("المرج الجديدة", "المرج", "عزبة النخل", "عين شمس", "المطرية", "حلمية الزيتون", "حدائق الزيتون", "سراي القبة", "حمامات القبة", "كوبري القبة", "منشية الصدر", "الدمرداش", "غمرة", "الشهداء", "عرابي", "ناصر", "السادات", "سعد زغلول", "السيدة زينب", "الملك الصالح", "مار جرجس", "الزهراء", "دار السلام", "حدائق المعادي", "المعادي", "ثكنات المعادي", "طرة البلد", "كوتسيكا", "طرة الأسمنت", "المعصرة", "حدائق حلوان", "وادي حوف", "جامعة حلوان", "عين حلوان", "حلوان"));
    }

    @Override
    public ArrayList<String> getLine2() {
        return new ArrayList<>(Arrays.asList("شبرا الخيمة", "كلية الزراعة", "المظلات", "الخلفاوي", "سانت تريزا", "روض الفرج", "المسرة", "الشهداء", "العتبة", "محمد نجيب", "السادات", "الأوبرا", "الدقي", "البحوث", "جامعة القاهرة", "فيصل", "الجيزة", "أم المصريين", "ساقية مكي", "المنيب"));
    }

    @Override
    public ArrayList<String> getLine3() {
        return new ArrayList<>(Arrays.asList("عدلي منصور", "الهايكستب", "عمر بن الخطاب", "قباء", "هشام بركات", "النزهة", "نادي الشمس", "ألف مسكن", "هليوبوليس", "هارون", "الأهرام", "كلية البنات", "ستاد القاهرة", "أرض المعارض", "العباسية", "عبده باشا", "الجيش", "باب الشعرية", "العتبة", "ناصر", "ماسبيرو", "صفاء حجازي", "الكيت كات", "السودان", "إمبابة", "البوهي", "القومية العربية", "الطريق الدائري", "محور روض الفرج"));
    }

    @Override
    public ArrayList<String> getLine3part2() {
        return new ArrayList<>(Arrays.asList("عدلي منصور","الكيت كات","التوفيقية", "وادي النيل", "جامعة الدول", "بولاق الدكرور", "جامعة القاهرة"));
    }

    @Override
    public ArrayList<String> getTranstionStations() {
        return new ArrayList<>(Arrays.asList("السادات", "ناصر", "العتبة", "الشهداء", "جامعة القاهرة", "الكيت كات"));
    }

    @Override
    public ArrayList<String> getStations() {
        return new ArrayList<>(Arrays.asList("المرج الجديدة", "المرج", "عزبة النخل", "عين شمس", "المطرية", "حلمية الزيتون", "حدائق الزيتون", "سراي القبة", "حمامات القبة", "كوبري القبة", "منشية الصدر", "الدمرداش", "غمرة", "الشهداء", "عرابي", "ناصر", "السادات", "سعد زغلول", "السيدة زينب", "الملك الصالح", "مار جرجس", "الزهراء", "دار السلام", "حدائق المعادي", "المعادي", "ثكنات المعادي", "طرة البلد", "كوتسيكا", "طرة الأسمنت", "المعصرة", "حدائق حلوان", "وادي حوف", "جامعة حلوان", "عين حلوان", "حلوان", "شبرا الخيمة", "كلية الزراعة", "المظلات", "الخلفاوي", "سانت تريزا", "روض الفرج", "المسرة", "الشهداء", "العتبة", "محمد نجيب", "السادات", "الأوبرا", "الدقي", "البحوث", "جامعة القاهرة", "فيصل", "الجيزة", "أم المصريين", "ساقية مكي", "المنيب", "عدلي منصور", "الهايكستب", "عمر بن الخطاب", "قباء", "هشام بركات", "النزهة", "نادي الشمس", "ألف مسكن", "هليوبوليس", "هارون", "الأهرام", "كلية البنات", "ستاد القاهرة", "أرض المعارض", "العباسية", "عبده باشا", "الجيش", "باب الشعرية", "العتبة", "ناصر", "ماسبيرو", "صفاء حجازي", "الكيت كات", "السودان", "إمبابة","التوفيقية", "وادي النيل", "جامعة الدول", "بولاق الدكرور", "البوهي", "القومية العربية", "الطريق الدائري", "محور روض الفرج"));
    }

    @Override
    public ArrayList<String> getPrefer() {
        return new ArrayList<>(Arrays.asList("أسرع طريق","أقل تحويلات"));
    }

    @Override
    public ArrayList<String> getWords() {
        return new ArrayList<>(Arrays.asList("دقيقة","ساعة","ثم حول عند محطة","اتجاه","محطات","جنيه"));
    }

    @Override
    public String getWelcomeTextView() {
        return "مرحبا بك في مترو القاهرة";
    }

    @Override
    public String getEntryStationTextView() {
        return "محطة البداية";
    }

    @Override
    public String getPreferTextView() {
        return "ايهما تفضل الوقت ام التحويلات";
    }

    @Override
    public String getExitStationTextView() {
        return "محطة الوصول";
    }

    public String getCheckStationTextView() {
        return "من فضلك ادخل مكان";
    }

    @Override
    public String getAllRoutesTextView() {
        return "كل الطرق";
    }

    @Override
    public String getSettingsTextView() {
        return "الإعدادات";
    }


    @Override
    public String getHint() {
        return "ادخل وجهتك";
    }

    @Override
    public String getLanguageTextView() {
        return "اللغة";
    }

    @Override
    public String getEntrySpinner() {
        return "اكتب للبحث";
    }

    @Override
    public String getExitSpinner() {
        return "اكتب للبحث";
    }

    @Override
    public String getTimeOrTransitionSpinner() {
        return "اكتب للبحث";
    }

    @Override
    public String getConfirmButton() {
        return "تأكيد";
    }

    @Override
    public String getCheckButton() {
        return "تفقد";
    }

    @Override
    public String getResetButton() {
        return "إعادة ضبط";
    }

    @Override
    public String getSaveButton() {
        return "حفظ";
    }

    @Override
    public String getDevolpedBy() {
        return "تعرف على المطورين";
    }

    @Override
    public String getDevolpedByHere() {
        return "سوف تجد رابط المطورين عندما تضغط هنا";
    }

    public String getDestinationTextView() {
        return "من فضلك ادخل وجهتك (اختياري)";
    }

}
