package word;

public class Word
{
    private String word;
    private String translation;

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public Word(String word, String translation) {

        this.word = word;
        this.translation = translation;
    }
}
