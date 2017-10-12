

/**
 * Created by LIKHTAROVICH on 02.10.2017.
 */
public class Word {
    private static final String PATTERN = "[^a-zA-Zа-яА-Я_ёЁ\\s]+";
    private static final String EMPTY_STRING = "";

    protected String word;

    public Word() {
    }

    public Word(String word) {
        this.word = word;
        parse();
    }

    @Override
    public String toString() {
        return word;
    }

    protected void parse() {
        this.word = word.replaceAll(PATTERN, EMPTY_STRING);
    }

}
