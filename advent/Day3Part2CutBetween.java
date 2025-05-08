package advent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Part2CutBetween {
    public static void main(String[] args) {
        String inputString = Utils.readFileToStringWithNewline("Day3-input.txt");
//        String inputString = Utils.readFileToString("Day3-input-small.txt");//7092082
//        String inputString = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
        inputString = "do()" + inputString + "don't()";
        int sumOfMuls = 0;

        String numRegEx = "([1-9][0-9]{0,2})";
        String regEx = "mul\\(" + numRegEx + "," + numRegEx + "\\)";

        String enableRegex = "do\\(\\)(.*?)don't\\(\\)";

        Pattern patternMul = Pattern.compile(regEx);
        Pattern patternEnable = Pattern.compile(enableRegex, Pattern.DOTALL);
//        Pattern patternEnable = Pattern.compile(enableRegex); // -> 65M instead of 80M

        Matcher matcherEnable = patternEnable.matcher(inputString);

        while (matcherEnable.find()) {
            Matcher matcherMul = patternMul.matcher(matcherEnable.group());
            while (matcherMul.find()) {
                int num1 = Integer.parseInt(matcherMul.group(1));
                int num2 = Integer.parseInt(matcherMul.group(2));
                sumOfMuls += num1 * num2;
            }
        }
        System.out.println("sumOfMuls = " + sumOfMuls); //80,747,545, but got 65,949,847
    }
}


