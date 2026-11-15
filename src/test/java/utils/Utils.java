package utils;

import java.util.Random;

public class Utils {

    public static String getRandomNumber(int limit){
        Random random = new Random();
        return String.valueOf(random.nextInt(limit));
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
}
