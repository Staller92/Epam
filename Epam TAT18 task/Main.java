
import java.util.List;

/**
 * Created by LIKHTAROVICH on 02.10.2017.
 */

public class Main {

    public static void main(String[] args) {

        String originalText = Reader.input();
        Sentence sentence = new Sentence(originalText);
        List<Word> words = sentence.getListOfWords();
        System.out.println("Text after fixing: ");
        for (Word word : words) {
            System.out.print(word + " ");
        }
    }
}