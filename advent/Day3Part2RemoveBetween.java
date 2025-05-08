package advent;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Part2RemoveBetween {
    public static void main(String[] args) throws IOException {
//        String inputString = Utils.readFileToString("Day3-input.txt");
        String inputString = Utils.readFileToStringWithNewline("Day3-input.txt"); //-> 98M, but 80M is correct, with no pattern change
//        String inputString = Utils.readFileToString2("Day3-input.txt");
//        String inputString = Utils.readFileToString3("Day3-input.txt");
//        String inputString = Utils.readFileToString("Day3-input-small.txt");//7092082
//        String inputString = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
//        inputString = "do()" + inputString + "don't()";
        int sumOfMuls = 0;

        String numRegEx = "([1-9][0-9]{0,2})";
        String regEx = "mul\\(" + numRegEx + "," + numRegEx + "\\)";

//        String removeRegex = "don't\\(\\).*?(?:do\\(\\)|\\z)";
        String removeRegex = "(?s)don't\\(\\).*?(?:do\\(\\)|\\z)"; //fixes \n
        String cutInput = inputString.replaceAll(removeRegex, "");

        Pattern patternMul = Pattern.compile(regEx);

        Matcher matcherMul = patternMul.matcher(cutInput);
        while (matcherMul.find()) {
            int num1 = Integer.parseInt(matcherMul.group(1));
            int num2 = Integer.parseInt(matcherMul.group(2));
            sumOfMuls += num1 * num2;
        }
        System.out.println("sumOfMuls = " + sumOfMuls); //80,747,545 but yield 98,632,444
    }
}


