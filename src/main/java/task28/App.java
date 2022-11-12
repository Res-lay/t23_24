package task28;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] words;
        List<String> allwords = new ArrayList<>();
        System.out.println("Enter string: ");
        String input = in.nextLine().toLowerCase();
        words = input.split("[\\p{Punct}\\s]+");
        for (String word : words) {
            allwords.add(word);
        }

        Map<String, Long> wordCount = new HashMap<>(10);
        wordCount = allwords.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e2, LinkedHashMap::new));

        wordCount.forEach((k, v) -> System.out.println(k));
    }
}
