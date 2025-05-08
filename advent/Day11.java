package advent;

import java.math.BigInteger;
import java.util.ArrayList;

public class Day11 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();





//        String line = "0 1 10 99 999";
//        String line = "125 17";
        String line = "9759 0 256219 60 1175776 113 6 92833";

        ArrayList<BigInteger> numbersList = getList(line);

        System.out.println("Original: " + numbersList);

        for (int blink = 1; blink <= 30; blink++) {


            for (int i = 0; i < numbersList.size(); i++) {
                BigInteger value = numbersList.get(i);
                if (value.equals(BigInteger.ZERO)) {
                    numbersList.set(i, BigInteger.ONE);
                } else if (numberOfDigits(value) % 2 == 0) {
                    numbersList.set(i, splitString(value)[0]);
                    numbersList.add(i + 1, splitString(value)[1]);
                    i++;
                } else {
                    numbersList.set(i, value.multiply(BigInteger.valueOf(2024)));
                }
            }
//            System.out.println(numbersList.size()+" stones after "+blink+ " blink: " + numbersList);
            System.out.println(numbersList.size()+" stones after "+blink+ " blinks");
        }
        System.out.println("last stones number = " + numbersList.size());
        long endTime = System.nanoTime();
        long durationInNanoseconds = endTime - startTime;
        double durationInSeconds = (double) durationInNanoseconds / 1000000000;

        System.out.println("Execution time: " + durationInSeconds + " seconds");

    }

    private static ArrayList<BigInteger> getList(String line) {
        String[] numbers = line.split(" ");
        ArrayList<BigInteger> numbersList = new ArrayList<>();
        for (String number : numbers) {
            numbersList.add(new BigInteger(number));
        }
        return numbersList;
    }

    public static boolean isEvenNumberOfDigits(BigInteger value) {
        String valueString = value.toString();
        return valueString.length() % 2 == 0;
    }

    public static int numberOfDigits(BigInteger value) {
        return value.toString().length();
    }

    public static BigInteger[] splitString(BigInteger value) {

        String valueString = value.toString();
        int mid = valueString.length() / 2;

        BigInteger left = new BigInteger(valueString.substring(0, mid));
        BigInteger right = new BigInteger(valueString.substring(mid));

        return new BigInteger[]{left, right};
    }


}
