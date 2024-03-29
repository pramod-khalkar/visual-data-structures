package data.utils;

import static java.util.Optional.empty;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;
import javax.swing.JTextField;

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

    public static Long[] randomNumbers(int limit) {
        Random random = new Random();
        return IntStream.range(0, limit)
                .map(i -> random.nextInt(100))
                .distinct()
                .mapToObj(Long::new)
                .toArray(Long[]::new);
    }

    public static Long[] randomNumbers() {
        return randomNumbers(15);
    }

    public static BufferedImage loadImageIcon(String path) {
        try {
            return ImageIO.read(ClassLoader.getSystemResource(path));
        } catch (IOException e) {
            //failed silently
        }
        return null;
    }

    public static boolean isValidInput(String text) {
        if (text != null && !text.equals("") && isNumeric(text)) {
            return true;
        }
        return false;
    }

    public static Optional<Long> checkAndGetValidInput(JTextField inputField) {
        if (isValidInput(inputField.getText())) {
            return Optional.of(Long.parseLong(inputField.getText().trim()));
        }
        return empty();
    }
}
