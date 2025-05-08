package advent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Part2 {
    public static void main(String[] args) {
        String inputString = Utils.readFileToString("Day3-input.txt");
//        String inputString = Utils.readFileToString("Day3-input-small.txt");
//        7092082
//        9014623
//        19681692
//        15087905
//        16852785
//        18041735

//        String inputString =
//                "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
        int sumOfMuls = 0;

        String numRegEx = "([1-9][0-9]{0,2})";
        String regEx = "mul\\(" + numRegEx + "," + numRegEx + "\\)";
        String enableRegex = "do\\(\\)";
        String disableRegex = "don't\\(\\)";

        Pattern patternMul = Pattern.compile(regEx);
        Pattern patternEnable = Pattern.compile(enableRegex);
        Pattern patternDisable = Pattern.compile(disableRegex);

        Matcher matcherMul = patternMul.matcher(inputString);
        Matcher matcherEnable = patternEnable.matcher(inputString);
        Matcher matcherDisable = patternDisable.matcher(inputString);

        int startPosition = 0;
        boolean isDisabled = false;

        while (matcherMul.find()) {
            int endPosition = matcherMul.start();

            matcherDisable.region(startPosition, endPosition);
            int lastDisablePosition = startPosition;
            while (matcherDisable.find()) {
                lastDisablePosition = matcherDisable.end();
            }

            matcherEnable.region(startPosition, endPosition);
            int lastEnablePosition = startPosition;
            while (matcherEnable.find()) {
                lastEnablePosition = matcherEnable.end();
            }

            if (lastDisablePosition < lastEnablePosition) {
                isDisabled = false;
            } else if (lastEnablePosition < lastDisablePosition) {
                isDisabled = true;
            }

            if (!isDisabled) {
                int num1 = Integer.parseInt(matcherMul.group(1));
                int num2 = Integer.parseInt(matcherMul.group(2));
                sumOfMuls += num1 * num2;
            }
            startPosition = matcherMul.end();
        }
        System.out.println("sumOfMuls = " + sumOfMuls); //80747545
    }
}


