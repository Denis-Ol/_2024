package advent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Part1RegEx {
    public static void main(String[] args) {
//        List<String> lines = Utils.readFileToListStream("Day3-test.txt");
        List<String> lines = Utils.readFileToListStream("Day3-input.txt");
//        List<String> lines = List.of("mul(2,6)mul(1,2)");
        int sumOfMuls = 0;

        String numRegEx = "([1-9][0-9]{0,2})";
        String regEx = "mul\\(" + numRegEx + "," + numRegEx + "\\)";

        Pattern pattern = Pattern.compile(regEx);
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                int num1 = Integer.parseInt(matcher.group(1));
                int num2 = Integer.parseInt(matcher.group(2));
                sumOfMuls += num1 * num2;
            }
        }
        System.out.println("sumOfMuls = " + sumOfMuls); //182619815
    }
}


