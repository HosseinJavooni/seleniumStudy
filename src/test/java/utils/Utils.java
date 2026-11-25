package utils;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.ULocale;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class Utils {

    public static String getRandomNumber(int lowerLimit, int upperLimit){
        Random random = new Random();
        return String.valueOf(random.nextInt(lowerLimit, upperLimit));
    }

    public static void sleep(int seconds){
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String toPersianNumber(String input) {
        char[] persianDigits = {
                '۰', '۱', '۲', '۳', '۴',
                '۵', '۶', '۷', '۸', '۹'
        };

        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c >= '0' && c <= '9') {
                result.append(persianDigits[c - '0']);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static boolean listContains(List<String> list, String item) {

        for (String s : list) {
            if (normalizePersianCharacters(s).equalsIgnoreCase(normalizePersianCharacters(item))) {
                return true;
            }
        }
        return false;

    }

    public static String normalizePersianCharacters(String input) {
         return input.replace("ي", "ی");
    }

    public static boolean equalsWithNormalizedPersianCharacters(String firstEntry, String secondEntry) {
        return normalizePersianCharacters(firstEntry).equals(normalizePersianCharacters(secondEntry));
    }

    public static String getFirstPartAfterSplitString(String input, String splitPattern) {
        if (input != null && input.contains(splitPattern)) {
            return input.split(splitPattern)[0];
        }
        return input;
    }

    public static boolean determineStringContainsOrEqualsToAnotherString(String mainString, String inputString, boolean isEquals) {
        if (mainString == null || inputString == null) {
            return false;
        }
        if (isEquals) {
            return mainString.equals(inputString);
        }
        return mainString.contains(inputString);
    }
}
