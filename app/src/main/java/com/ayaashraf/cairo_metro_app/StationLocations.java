package com.ayaashraf.cairo_metro_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class StationLocations {

    private static SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LANGUAGE = "language";
    private static String languageCode;
    private static Context context;
    private static final double EARTH_RADIUS = 6371.0;
    public static final Map<String, double[]> cordanatorEn = new HashMap<>();

    static {
        cordanatorEn.put("New El-Marg", new double[]{30.163687417465443, 31.33836445250712});
        cordanatorEn.put("El-Marg", new double[]{30.15207714128871, 31.335683021718527});
        cordanatorEn.put("Ezbet El-Nakhl", new double[]{30.139429315185044, 31.324422194971998});
        cordanatorEn.put("Ain Shams", new double[]{30.131090572449327, 31.31910726431132});
        cordanatorEn.put("El-Matareyya", new double[]{30.121368436674942, 31.313708054821966});
        cordanatorEn.put("Helmeyet El-Zaitoun", new double[]{30.11333830215214, 31.313964694970696});
        cordanatorEn.put("Hadayeq El-Zaitoun", new double[]{30.105991671394083, 31.310461837298014});
        cordanatorEn.put("Saray El-Qobba", new double[]{30.097766644470713, 31.304563094969808});
        cordanatorEn.put("Hammamat El-Qobba", new double[]{30.09153318905702, 31.298857927383782});
        cordanatorEn.put("Kobri El-Qobba", new double[]{30.087196974797383, 31.29410409496931});
        cordanatorEn.put("Manshiet El Sadr", new double[]{30.082004447642586, 31.287511612949583});
        cordanatorEn.put("EL-Demerdash", new double[]{30.07731603912765, 31.277791287465718});
        cordanatorEn.put("Ghamra", new double[]{30.069018707671813, 31.264606226488922});
        cordanatorEn.put("Al-Shohadaa", new double[]{30.061061823104865, 31.246033662768014});
        cordanatorEn.put("Orabi", new double[]{30.056688464050122, 31.242052947390007});
        cordanatorEn.put("Nasser", new double[]{30.05350546363506, 31.23873139342649});
        cordanatorEn.put("Sadat", new double[]{30.044133489304492, 31.23440633662588});
        cordanatorEn.put("Saad Zaghloul", new double[]{30.037019393330276, 31.238356079150318});
        cordanatorEn.put("Al-Sayeda Zeinab", new double[]{30.029273578013974, 31.235418546626892});
        cordanatorEn.put("El-Malek El-Saleh", new double[]{30.017689700519316, 31.23120165781196});
        cordanatorEn.put("Mar Girgis", new double[]{30.0061001398057, 31.229611836562803});
        cordanatorEn.put("El-Zahraa", new double[]{29.99547471287979, 31.231167589610198});
        cordanatorEn.put("Dar El-Salam", new double[]{29.982078499767873, 31.242166934291753});
        cordanatorEn.put("Hadayek El-Maadi", new double[]{29.970137801844807, 31.250590522067426});
        cordanatorEn.put("Maadi", new double[]{29.960294167159432, 31.257640201091924});
        cordanatorEn.put("Sakanat El-Maadi", new double[]{29.95330739825872, 31.262947411995547});
        cordanatorEn.put("Tora El-Balad", new double[]{29.94677651550211, 31.27297281172739});
        cordanatorEn.put("Kozzika", new double[]{29.936253846994905, 31.281813134266983});
        cordanatorEn.put("Tora El-Asmant", new double[]{29.925960543106633, 31.28753729101867});
        cordanatorEn.put("El-Maasara", new double[]{29.90608081356438, 31.299508418575552});
        cordanatorEn.put("Hadayek Helwan", new double[]{29.897140855908066, 31.30396205557997});
        cordanatorEn.put("Wadi Hof", new double[]{29.879081048289322, 31.313572989167383});
        cordanatorEn.put("Helwan University", new double[]{29.869443794450458, 31.320056055691822});
        cordanatorEn.put("Ain Helwan", new double[]{29.86261031567134, 31.32486845721002});
        cordanatorEn.put("Helwan", new double[]{29.848985830828955, 31.334226452670784});
        cordanatorEn.put("Shubra El-Kheima", new double[]{30.122437013783863, 31.244535607337426});
        cordanatorEn.put("Kolleyyet El-Zeraa", new double[]{30.113682656234165, 31.24865801883198});
        cordanatorEn.put("Mezallat", new double[]{30.104175734837547, 31.245647593898806});
        cordanatorEn.put("Khalafawy", new double[]{30.097884660571072, 31.245390531684933});
        cordanatorEn.put("St. Teresa", new double[]{30.087952258814255, 31.245475796965952});
        cordanatorEn.put("Rod El-Farag", new double[]{30.080589244675185, 31.24540237635897});
        cordanatorEn.put("Masaraa", new double[]{30.070884174110354, 31.2450973524905});
        cordanatorEn.put("Attaba", new double[]{30.05234532014751, 31.246801227984676});
        cordanatorEn.put("Mohamed Naguib", new double[]{30.045321746151014, 31.2441603444723});
        cordanatorEn.put("Opera", new double[]{30.041941370144535, 31.22497492348373});
        cordanatorEn.put("Dokki", new double[]{30.038434268395182, 31.212230138794776});
        cordanatorEn.put("El Bohoth", new double[]{30.035782894984603, 31.200160771733053});
        cordanatorEn.put("Cairo University", new double[]{30.02601123462684, 31.201154249181485});
        cordanatorEn.put("Faisal", new double[]{30.01736183205528, 31.20392927586734});
        cordanatorEn.put("Giza", new double[]{30.010658240356626, 31.20707722486442});
        cordanatorEn.put("Omm El-Masryeen", new double[]{30.005649068048818, 31.208113985135153});
        cordanatorEn.put("Sakiat Mekky", new double[]{29.995483255523187, 31.208643410316917});
        cordanatorEn.put("El-Mounib", new double[]{29.981093777265393, 31.21231632203813});
        cordanatorEn.put("Adly Mansour", new double[]{30.146460891056062, 31.42132009501648});
        cordanatorEn.put("El Haykestep", new double[]{30.14384675550377, 31.4046911598909});
        cordanatorEn.put("Omar Ibn El-Khattab", new double[]{30.140374683852777, 31.394337389936844});
        cordanatorEn.put("Qobaa", new double[]{30.13481905601565, 31.383747990314497});
        cordanatorEn.put("Hesham Barakat", new double[]{30.13083182413351, 31.37293384310862});
        cordanatorEn.put("El-Nozha", new double[]{30.12798718978646, 31.360166001286885});
        cordanatorEn.put("Nadi El-Shams", new double[]{30.125482406939103, 31.348876784170976});
        cordanatorEn.put("Alf Maskan", new double[]{30.118998064870205, 31.340184811724036});
        cordanatorEn.put("Heliopolis", new double[]{30.108419533101188, 31.33830315431158});
        cordanatorEn.put("Haroun", new double[]{30.101360769314265, 31.332969259154336});
        cordanatorEn.put("Al-Ahram", new double[]{30.09171267348972, 31.326312489483023});
        cordanatorEn.put("Koleyet El-Banat", new double[]{30.084035190321604, 31.329014883953445});
        cordanatorEn.put("Stadium", new double[]{30.07290068192294, 31.317103060148366});
        cordanatorEn.put("Fair Zone", new double[]{30.07325713229277, 31.300981814209575});
        cordanatorEn.put("Abbassiya", new double[]{30.071983536625847, 31.28337426851981});
        cordanatorEn.put("Abdou Pasha", new double[]{30.06477439471832, 31.274743278100342});
        cordanatorEn.put("El-Geish", new double[]{30.061748439319054, 31.26687659582453});
        cordanatorEn.put("Bab El Shaaria", new double[]{30.054134586563745, 31.25587055384179});
        cordanatorEn.put("Maspero", new double[]{30.055712204662488, 31.232108390232284});
        cordanatorEn.put("Safaa Hegazy", new double[]{30.062275908223246, 31.223278234503947});
        cordanatorEn.put("Kit Kat", new double[]{30.066548852550714, 31.213018104587128});
        cordanatorEn.put("Imbaba", new double[]{30.075833574700226, 31.207464172450546});
        cordanatorEn.put("El-Bohy", new double[]{30.082122893443948, 31.2105304121635});
        cordanatorEn.put("El-Kawmeya Al-Arabiya", new double[]{30.093223310814984, 31.209014963943794});
        cordanatorEn.put("Ring Road", new double[]{30.096409582519108, 31.199577197718867});
        cordanatorEn.put("Rod El-Farag Axis", new double[]{30.101907125405603, 31.1844206020801});
        cordanatorEn.put("Sudan", new double[]{30.070053521084258, 31.204732322317977});
        cordanatorEn.put("Tawfikeya", new double[]{30.065169841541913, 31.202693087507942});
        cordanatorEn.put("Wadi El-Nile", new double[]{30.058465248538035, 31.20103979496784});
        cordanatorEn.put("Gamaet El-Dowal Al-Arabiya", new double[]{30.05017140138583, 31.1989324693425});
        cordanatorEn.put("Bulaq Al-Dakrour", new double[]{30.037549412549918, 31.195547731930066});
    }

    public static final Map<String, double[]> cordanatorAr = new HashMap<>();

    static {
        cordanatorAr.put("المرج الجديدة", new double[]{30.163687417465443, 31.33836445250712});
        cordanatorAr.put("المرج", new double[]{30.15207714128871, 31.335683021718527});
        cordanatorAr.put("عزبة النخل", new double[]{30.139429315185044, 31.324422194971998});
        cordanatorAr.put("عين شمس", new double[]{30.131026205563288, 31.31909243883623});
        cordanatorAr.put("المطرية", new double[]{30.121368436674942, 31.313708054821966});
        cordanatorAr.put("حلمية الزيتون", new double[]{30.11333830215214, 31.313964694970696});
        cordanatorAr.put("حدائق الزيتون", new double[]{30.105991671394083, 31.310461837298014});
        cordanatorAr.put("سراي القبة", new double[]{30.097766644470713, 31.304563094969808});
        cordanatorAr.put("حمامات القبة", new double[]{30.09153318905702, 31.298857927383782});
        cordanatorAr.put("كوبري القبة", new double[]{30.087196974797383, 31.29410409496931});
        cordanatorAr.put("منشية الصدر", new double[]{30.082004447642586, 31.287511612949583});
        cordanatorAr.put("الدمرداش", new double[]{30.07731603912765, 31.277791287465718});
        cordanatorAr.put("غمرة", new double[]{30.069018707671813, 31.264606226488922});
        cordanatorAr.put("الشهداء", new double[]{30.061061823104865, 31.246033662768014});
        cordanatorAr.put("عرابي", new double[]{30.056688464050122, 31.242052947390007});
        cordanatorAr.put("ناصر", new double[]{30.05350546363506, 31.23873139342649});
        cordanatorAr.put("السادات", new double[]{30.044133489304492, 31.23440633662588});
        cordanatorAr.put("سعد زغلول", new double[]{30.037019393330276, 31.238356079150318});
        cordanatorAr.put("السيدة زينب", new double[]{30.029273578013974, 31.235418546626892});
        cordanatorAr.put("الملك الصالح", new double[]{30.017689700519316, 31.23120165781196});
        cordanatorAr.put("مار جرجس", new double[]{30.0061001398057, 31.229611836562803});
        cordanatorAr.put("الزهراء", new double[]{29.99547471287979, 31.231167589610198});
        cordanatorAr.put("دار السلام", new double[]{29.982078499767873, 31.242166934291753});
        cordanatorAr.put("حدائق المعادي", new double[]{29.970137801844807, 31.250590522067426});
        cordanatorAr.put("المعادي", new double[]{29.960294167159432, 31.257640201091924});
        cordanatorAr.put("ثكنات المعادي", new double[]{29.95330739825872, 31.262947411995547});
        cordanatorAr.put("طرة البلد", new double[]{29.94677651550211, 31.27297281172739});
        cordanatorAr.put("كوتسيكا", new double[]{29.936253846994905, 31.281813134266983});
        cordanatorAr.put("طرة الأسمنت", new double[]{29.925960543106633, 31.28753729101867});
        cordanatorAr.put("المعصرة", new double[]{29.90608081356438, 31.299508418575552});
        cordanatorAr.put("حدائق حلوان", new double[]{29.897140855908066, 31.30396205557997});
        cordanatorAr.put("وادي حوف", new double[]{29.879081048289322, 31.313572989167383});
        cordanatorAr.put("جامعة حلوان", new double[]{29.869443794450458, 31.320056055691822});
        cordanatorAr.put("عين حلوان", new double[]{29.86261031567134, 31.32486845721002});
        cordanatorAr.put("حلوان", new double[]{29.848985830828955, 31.334226452670784});
        cordanatorAr.put("شبرا الخيمة", new double[]{30.122437013783863, 31.244535607337426});
        cordanatorAr.put("كلية الزراعة", new double[]{30.113682656234165, 31.24865801883198});
        cordanatorAr.put("المظلات", new double[]{30.104175734837547, 31.245647593898806});
        cordanatorAr.put("الخلفاوي", new double[]{30.097884660571072, 31.245390531684933});
        cordanatorAr.put("سانت تريزا", new double[]{30.087952258814255, 31.245475796965952});
        cordanatorAr.put("روض الفرج", new double[]{30.080589244675185, 31.24540237635897});
        cordanatorAr.put("المسرة", new double[]{30.070884174110354, 31.2450973524905});
        cordanatorAr.put("العتبة", new double[]{30.05234532014751, 31.246801227984676});
        cordanatorAr.put("محمد نجيب", new double[]{30.045321746151014, 31.2441603444723});
        cordanatorAr.put("الأوبرا", new double[]{30.041941370144535, 31.22497492348373});
        cordanatorAr.put("الدقي", new double[]{30.038434268395182, 31.212230138794776});
        cordanatorAr.put("البحوث", new double[]{30.035782894984603, 31.200160771733053});
        cordanatorAr.put("جامعة القاهرة", new double[]{30.02601123462684, 31.201154249181485});
        cordanatorAr.put("فيصل", new double[]{30.01736183205528, 31.20392927586734});
        cordanatorAr.put("الجيزة", new double[]{30.010658240356626, 31.20707722486442});
        cordanatorAr.put("أم المصريين", new double[]{30.005649068048818, 31.208113985135153});
        cordanatorAr.put("ساقية مكي", new double[]{29.995483255523187, 31.208643410316917});
        cordanatorAr.put("المنيب", new double[]{29.981093777265393, 31.21231632203813});
        cordanatorAr.put("عدلي منصور", new double[]{30.146460891056062, 31.42132009501648});
        cordanatorAr.put("الهايكستب", new double[]{30.14384675550377, 31.4046911598909});
        cordanatorAr.put("عمر بن الخطاب", new double[]{30.140374683852777, 31.394337389936844});
        cordanatorAr.put("قباء", new double[]{30.13481905601565, 31.383747990314497});
        cordanatorAr.put("هشام بركات", new double[]{30.13083182413351, 31.37293384310862});
        cordanatorAr.put("النزهة", new double[]{30.12798718978646, 31.360166001286885});
        cordanatorAr.put("نادي الشمس", new double[]{30.125482406939103, 31.348876784170976});
        cordanatorAr.put("ألف مسكن", new double[]{30.118998064870205, 31.340184811724036});
        cordanatorAr.put("هليوبوليس", new double[]{30.108419533101188, 31.33830315431158});
        cordanatorAr.put("هارون", new double[]{30.101360769314265, 31.332969259154336});
        cordanatorAr.put("الأهرام", new double[]{30.09171267348972, 31.326312489483023});
        cordanatorAr.put("كلية البنات", new double[]{30.084035190321604, 31.329014883953445});
        cordanatorAr.put("ستاد القاهرة", new double[]{30.07290068192294, 31.317103060148366});
        cordanatorAr.put("أرض المعارض", new double[]{30.07325713229277, 31.300981814209575});
        cordanatorAr.put("العباسية", new double[]{30.071983536625847, 31.28337426851981});
        cordanatorAr.put("عبده باشا", new double[]{30.06477439471832, 31.274743278100342});
        cordanatorAr.put("الجيش", new double[]{30.061748439319054, 31.26687659582453});
        cordanatorAr.put("باب الشعرية", new double[]{30.054134586563745, 31.25587055384179});
        cordanatorAr.put("ماسبيرو", new double[]{30.055712204662488, 31.232108390232284});
        cordanatorAr.put("صفاء حجازي", new double[]{30.062275908223246, 31.223278234503947});
        cordanatorAr.put("الكيت كات", new double[]{30.066548852550714, 31.213018104587128});
        cordanatorAr.put("السودان",new double[]{30.070053521084258, 31.204732322317977});
        cordanatorAr.put("إمبابة", new double[]{30.075833574700226, 31.207464172450546});
        cordanatorAr.put("البوهي", new double[]{30.082122893443948, 31.2105304121635});
        cordanatorAr.put("القومية العربية", new double[]{30.093223310814984, 31.209014963943794});
        cordanatorAr.put("الطريق الدائري", new double[]{30.096409582519108, 31.199577197718867});
        cordanatorAr.put("محور روض الفرج", new double[]{30.101907125405603, 31.1844206020801});
        cordanatorAr.put("التوفيقية", new double[]{30.065169841541913, 31.202693087507942});
        cordanatorAr.put("وادي النيل", new double[]{30.058465248538035, 31.20103979496784});
        cordanatorAr.put("جامعة الدول", new double[]{30.05017140138583, 31.1989324693425});
        cordanatorAr.put("بولاق الدكرور", new double[]{30.037549412549918, 31.195547731930066});
    }
    public static void initialize(Context ctx) {
        context = ctx.getApplicationContext();
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        languageCode = sharedPreferences.getString(KEY_LANGUAGE, "ar");
    }

    public static double[] cordinations(String stationName) {
        double[] cordinations;
        if (languageCode.equals("en")) {
            cordinations = cordanatorEn.get(stationName);
        } else {
            cordinations = cordanatorAr.get(stationName);
        }
        if (cordinations == null) {
            if (languageCode.equals("en")) {
                Toast.makeText(context, "Place not found. Please enter a valid place.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "من فضلك ادخل مكان صحيح", Toast.LENGTH_SHORT).show();
            }
            return new double[] {0.0, 0.0};
        }
     return cordinations;
    }
}
