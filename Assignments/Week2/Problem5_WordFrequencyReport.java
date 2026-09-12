import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Problem5_WordFrequencyReport {

    static void printFilteredWordFrequency(String feedback) {

        String[] stopWords = {
            "the", "was", "and", "a", "is", "of", "in"
        };

        feedback = feedback.toLowerCase();

        feedback = feedback.replace(".", "");
        feedback = feedback.replace(",", "");

        String[] words = feedback.split("\\s+");

        HashMap<String, Integer> frequency = new HashMap<>();

        for (int i = 0; i < words.length; i++) {

            boolean isStopWord = false;

            for (int j = 0; j < stopWords.length; j++) {

                if (words[i].equals(stopWords[j])) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord) {

                if (frequency.containsKey(words[i])) {
                    frequency.put(
                        words[i],
                        frequency.get(words[i]) + 1
                    );
                } else {
                    frequency.put(words[i], 1);
                }
            }
        }

        List<Map.Entry<String, Integer>> entries =
                new ArrayList<>(frequency.entrySet());

        entries.sort(
            Comparator.comparing(
                Map.Entry<String, Integer>::getValue
            ).reversed()
        );

        for (Map.Entry<String, Integer> entry : entries) {

            System.out.println(
                entry.getKey() + ": " + entry.getValue()
            );
        }
    }

    public static void main(String[] args) {

        String feedback =
                "The mentor was great, the session was great and clear.";

        printFilteredWordFrequency(feedback);
    }
}