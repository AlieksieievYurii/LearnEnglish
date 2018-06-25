package CheckingWords;

public class ConvertStringToStandart
{
    public static String[] convertToStandart(String[] word_translation)
    {
        for(int i = 0; i < word_translation.length; i++)
            word_translation[i] =
                    word_translation[i].substring(0,1).toUpperCase()
                            + word_translation[i].substring(1).toLowerCase();

        return word_translation;
    }
}
