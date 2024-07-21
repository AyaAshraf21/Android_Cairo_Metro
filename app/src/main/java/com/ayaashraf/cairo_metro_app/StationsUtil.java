package com.ayaashraf.cairo_metro_app;

public class StationsUtil {

    public static final String[] getAdjacentStations(String stationName) {

        switch (stationName) {

            case "New El-Marg":
                return new String[]{"El-Marg"};
            case "El-Marg":
                return new String[]{"New El-Marg", "Ezbet El-Nakhl"};
            case "Ezbet El-Nakhl":
                return new String[]{"El-Marg", "Ain Shams"};
            case "Ain Shams":
                return new String[]{"Ezbet El-Nakhl", "El-Matareyya"};
            case "El-Matareyya":
                return new String[]{"Ain Shams", "Helmeyet El-Zaitoun"};
            case "Helmeyet El-Zaitoun":
                return new String[]{"El-Matareyya", "Hadayeq El-Zaitoun"};
            case "Hadayeq El-Zaitoun":
                return new String[]{"Helmeyet El-Zaitoun", "Saray El-Qobba"};
            case "Saray El-Qobba":
                return new String[]{"Hadayeq El-Zaitoun", "Hammamat El-Qobba"};
            case "Hammamat El-Qobba":
                return new String[]{"Saray El-Qobba", "Kobri El-Qobba"};
            case "Kobri El-Qobba":
                return new String[]{"Hammamat El-Qobba", "Manshiet El Sadr"};
            case "Manshiet El Sadr":
                return new String[]{"Kobri El-Qobba", "EL-Demerdash"};
            case "EL-Demerdash":
                return new String[]{"Manshiet El Sadr", "Ghamra"};
            case "Ghamra":
                return new String[]{"EL-Demerdash", "Al-Shohadaa"};
            case "Al-Shohadaa":
                return new String[]{"Ghamra", "Orabi", "Masaraa", "Attaba"};
            case "Orabi":
                return new String[]{"Al-Shohadaa", "Nasser"};
            case "Nasser":
                return new String[]{"Orabi", "Sadat","Maspero","Attaba"};
            case "Sadat":
                return new String[]{"Nasser", "Saad Zaghloul", "Mohamed Naguib", "Opera"};
            case "Saad Zaghloul":
                return new String[]{"Sadat", "Al-Sayeda Zeinab"};
            case "Al-Sayeda Zeinab":
                return new String[]{"Saad Zaghloul", "El-Malek El-Saleh"};
            case "El-Malek El-Saleh":
                return new String[]{"Al-Sayeda Zeinab", "Mar Girgis"};
            case "Mar Girgis":
                return new String[]{"El-Malek El-Saleh", "El-Zahraa"};
            case "El-Zahraa":
                return new String[]{"Mar Girgis", "Dar El-Salam"};
            case "Dar El-Salam":
                return new String[]{"El-Zahraa", "Hadayek El-Maadi"};
            case "Hadayek El-Maadi":
                return new String[]{"Dar El-Salam", "Maadi"};
            case "Maadi":
                return new String[]{"Hadayek El-Maadi", "Sakanat El-Maadi"};
            case "Sakanat El-Maadi":
                return new String[]{"Maadi", "Tora El-Balad"};
            case "Tora El-Balad":
                return new String[]{"Sakanat El-Maadi", "Kozzika"};
            case "Kozzika":
                return new String[]{"Tora El-Balad", "Tora El-Asmant"};
            case "Tora El-Asmant":
                return new String[]{"Kozzika", "El-Maasara"};
            case "El-Maasara":
                return new String[]{"Tora El-Asmant", "Hadayek Helwan"};
            case "Hadayek Helwan":
                return new String[]{"El-Maasara", "Wadi Hof"};
            case "Wadi Hof":
                return new String[]{"Hadayek Helwan", "Helwan University"};
            case "Helwan University":
                return new String[]{"Wadi Hof", "Ain Helwan"};
            case "Ain Helwan":
                return new String[]{"Helwan University", "Helwan"};
            case "Helwan":
                return new String[]{"Ain Helwan"};
            case "Shubra El-Kheima":
                return new String[]{"Kolleyyet El-Zeraa"};
            case "Kolleyyet El-Zeraa":
                return new String[]{"Shubra El-Kheima", "Mezallat"};
            case "Mezallat":
                return new String[]{"Kolleyyet El-Zeraa", "Khalafawy"};
            case "Khalafawy":
                return new String[]{"Mezallat", "St. Teresa"};
            case "St. Teresa":
                return new String[]{"Khalafawy", "Rod El-Farag"};
            case "Rod El-Farag":
                return new String[]{"St. Teresa", "Masaraa"};
            case "Masaraa":
                return new String[]{"Rod El-Farag", "Al-Shohadaa"};
            case "Attaba":
                return new String[]{"Al-Shohadaa", "Mohamed Naguib", "Bab El Shaaria","Nasser"};
            case "Mohamed Naguib":
                return new String[]{"Attaba", "Sadat"};
            case "Opera":
                return new String[]{"Sadat", "Dokki"};
            case "Dokki":
                return new String[]{"Opera", "El Bohoth"};
            case "El Bohoth":
                return new String[]{"Dokki", "Cairo University"};
            case "Cairo University":
                return new String[]{"El Bohoth", "Faisal","Bulaq Al-Dakrour"};
            case "Faisal":
                return new String[]{"Cairo University", "Giza"};
            case "Giza":
                return new String[]{"Faisal", "Omm El-Masryeen"};
            case "Omm El-Masryeen":
                return new String[]{"Giza", "Sakiat Mekky"};
            case "Sakiat Mekky":
                return new String[]{"Omm El-Masryeen", "El-Mounib"};
            case "El-Mounib":
                return new String[]{"Sakiat Mekky"};
            case "Adly Mansour":
                return new String[]{"El Haykestep"};
            case "El Haykestep":
                return new String[]{"Adly Mansour", "Omar Ibn El-Khattab"};
            case "Omar Ibn El-Khattab":
                return new String[]{"El Haykestep", "Qobaa"};
            case "Qobaa":
                return new String[]{"Omar Ibn El-Khattab", "Hesham Barakat"};
            case "Hesham Barakat":
                return new String[]{"Qobaa", "El-Nozha"};
            case "El-Nozha":
                return new String[]{"Hesham Barakat", "Nadi El-Shams"};
            case "Nadi El-Shams":
                return new String[]{"El-Nozha", "Alf Maskan"};
            case "Alf Maskan":
                return new String[]{"Nadi El-Shams", "Heliopolis"};
            case "Heliopolis":
                return new String[]{"Alf Maskan", "Haroun"};
            case "Haroun":
                return new String[]{"Heliopolis", "Al-Ahram"};
            case "Al-Ahram":
                return new String[]{"Haroun", "Koleyet El-Banat"};
            case "Koleyet El-Banat":
                return new String[]{"Al-Ahram", "Stadium"};
            case "Stadium":
                return new String[]{"Koleyet El-Banat", "Fair Zone"};
            case "Fair Zone":
                return new String[]{"Stadium", "Abbassiya"};
            case "Abbassiya":
                return new String[]{"Fair Zone", "Abdou Pasha"};
            case "Abdou Pasha":
                return new String[]{"Abbassiya", "El-Geish"};
            case "El-Geish":
                return new String[]{"Abdou Pasha", "Bab El Shaaria"};
            case "Bab El Shaaria":
                return new String[]{"El-Geish", "Attaba"};
            case "Maspero":
                return new String[]{"Nasser", "Safaa Hegazy"};
            case "Safaa Hegazy":
                return new String[]{"Maspero", "Kit Kat"};
            case "Kit Kat":
                return new String[]{"Safaa Hegazy", "Tawfikeya", "Sudan"};
            case "Imbaba":
                return new String[]{"Sudan", "El-Bohy"};
            case "El-Bohy":
                return new String[]{"Imbaba", "El-Kawmeya Al-Arabiya"};
            case "El-Kawmeya Al-Arabiya":
                return new String[]{"El-Bohy", "Ring Road"};
            case "Ring Road":
                return new String[]{"El-Kawmeya Al-Arabiya", "Rod El-Farag Axis"};
            case "Rod El-Farag Axis":
                return new String[]{"Ring Road"};
            case "Sudan":
                return new String[]{"Kit Kat", "Imbaba"};
            case "Tawfikeya":
                return new String[]{"Kit Kat", "Wadi El-Nile"};
            case "Wadi El-Nile":
                return new String[]{"Tawfikeya", "Gamaet El-Dowal Al-Arabiya"};
            case "Gamaet El-Dowal Al-Arabiya":
                return new String[]{"Wadi El-Nile", "Bulaq Al-Dakrour"};
            case "Bulaq Al-Dakrour":
                return new String[]{"Gamaet El-Dowal Al-Arabiya", "Cairo University"};
            default:
                throw new IllegalArgumentException("Unknown station name: " + stationName);
        }
    }

}
