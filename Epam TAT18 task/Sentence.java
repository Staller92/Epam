

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LIKHTAROVICH on 02.10.2017.
 */
public class Sentence extends Word {
    private static final String SPACE = "\\s+";

    private List<Word> listOfWords = new ArrayList<>();

    public List<Word> getListOfWords() {
        return listOfWords;
    }

    public Sentence(String originalText) {
        this.word = originalText;
        parse();
    }

    @Override
    protected void parse() {
        String[] words = word.split(SPACE);
        for (String word : words) {
            listOfWords.add(new Word(word));
        }
    }
}
