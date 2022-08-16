package data.utils;

import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Date: 31/12/21
 * Time: 1:19 am
 * This file is project specific to visual-data-structures
 * Author: Pramod Khalkar
 */
public final class Helper {

    private static final Pattern NUM_CHECK_PATTERN = Pattern.compile("^[0-9]*$");

    public static boolean isWithDecimal(String numValue) {
        return numValue.contains(".");
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return NUM_CHECK_PATTERN.matcher(strNum).find();
    }

    public static String[] randomNumbers(int limit) {
        Random random = new Random();
        return IntStream.range(0, limit)
                .map(i -> random.nextInt(100))
                .distinct()
                .mapToObj(String::valueOf).toArray(String[]::new);
    }

    public static String[] randomNumbers() {
        return randomNumbers(15);
    }
}
