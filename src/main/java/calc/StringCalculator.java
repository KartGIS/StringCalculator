package calc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class StringCalculator {

    public static final String DEFAULT_DELIMITER = ",|\\n";

    public static int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        String delimiter = DEFAULT_DELIMITER;

        if (input.startsWith("//")) {
            delimiter = input.substring(2, input.indexOf("\n"));
            input = input.substring(input.indexOf("\n") + 1);
        }

        return splitAndSumByDelimiter(input, delimiter);
    }

    private static int splitAndSumByDelimiter(String input, String regex) {
        List<String> splitted = Arrays.asList(input.split(regex.isEmpty() ? DEFAULT_DELIMITER : regex));
        List<Integer> parsedInts = splitted
                .stream()
                .map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> negativeInts = parsedInts.stream().filter(i -> i < 0).collect(Collectors.toList());
        if (!negativeInts.isEmpty()) {
            throw new IllegalArgumentException("negatives are not allowed: " + negativeInts.toString());
        }
        return parsedInts.stream()
                .reduce(0, Integer::sum);
    }


}
