package CheckingWords;

import java.util.regex.Pattern;

public class CheckWord
{
    public static final int ERROR_WRONG_SYMBOLS = 1;
    public static final int NOT_ERROR = 0;

    private static final Pattern WORD_PATTERN = Pattern.compile("([a-zA-Zа-яА-Я]){1,}");

    public static int checkText(String text) {
        if (!WORD_PATTERN.matcher(text).matches())
            return ERROR_WRONG_SYMBOLS;

        return NOT_ERROR;
    }


}
