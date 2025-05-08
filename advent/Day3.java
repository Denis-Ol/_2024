package advent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {

//        List<String> lines = Utils.readFileToListStream("Day3-test.txt");
        List<String> lines = Utils.readFileToListStream("Day3-input.txt");
//        List<String> lines = List.of("mul(2,6)mul(1,2)");
        int sumOfMuls = 0;

        String numRegEx = "([1-9][0-9]{0,2})";
//        String numRegEx = "(\\d+)";
        String regEx = "mul\\(" + numRegEx + "," + numRegEx + "\\)";

        Pattern pattern = Pattern.compile(regEx);
        System.out.println("Number Regex: " + numRegEx);
        System.out.println("Main Regex: " + regEx);
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()){
                System.out.println("  - Full match: " + matcher.group());
                System.out.println("  - First number (group 1): " + matcher.group(1));
                System.out.println("  - Second number (group 2): " + matcher.group(2));

                int num1 = Integer.parseInt(matcher.group(1));
                int num2 = Integer.parseInt(matcher.group(2));
                sumOfMuls += num1 * num2;
            }
//            while (line.contains("mul(")) {
//                String candidate = getCandidate(line);
//                sumOfMuls += getMul(candidate);
//                int index = line.indexOf("mul(");
//                line = line.substring(index + 4);
//            }
        }
        System.out.println("sumOfMuls = " + sumOfMuls);
    }

    private static String getCandidate(String line) {
        int startIndex = line.indexOf("mul(");
        if (startIndex == -1) {
            return "";
        }
        int end = Math.min(line.length(), startIndex + 12);
        return line.substring(startIndex, end);
    }

    private static int strToInt(String numStr) {
        int length = numStr.length();
        if (length == 0 || length > 3) {
            return 0;
        }
        int num;
        try {
            num = Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            return 0;
        }
        return num;
    }

    private static int getMul(String candidateStr) {
        String trimmedCandidateStr = candidateStr.substring(4);
        int closingParenthesisIndex = trimmedCandidateStr.indexOf(')');
        if (closingParenthesisIndex == -1) {
            return 0;
        }
        String numsAndCommaStr = trimmedCandidateStr.substring(0, closingParenthesisIndex);
        if (numsAndCommaStr.isEmpty()) {
            return 0;
        }
        int commaIndex = numsAndCommaStr.indexOf(',');
        if (commaIndex == -1) {
            return 0;
        }
        String num1Str = numsAndCommaStr.substring(0, commaIndex);
        int num1 = strToInt(num1Str);
        String num2Str = numsAndCommaStr.substring(commaIndex + 1);
        int num2 = strToInt(num2Str);
        return num1 * num2;
    }
}


