package advent;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day11Part2 {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        String line = "9759 0 256219 60 1175776 113 6 92833";
//        String line = "125 17";
        Map<Long, String> map = createMap(line);
        for (byte blink = 1; blink <= 25; blink++) {
            long size = map.size();
            Map<Long, String> extraMap = new HashMap<>();
            long extraIndex = 0;

            for (Map.Entry<Long, String> entry : map.entrySet()) {
                long key = entry.getKey();
                String value = entry.getValue();
                if (value.equals("0")) {
                    extraMap.put(key, "1");
                } else if (value.length() % 2 == 0) {
                    extraMap.put(key, splitString(value)[0]);
                    extraMap.put(size + extraIndex, splitString(value)[1]);
                    extraIndex++;
                } else {
                    extraMap.put(key,
                            String.valueOf(new BigInteger(value).multiply(BigInteger.valueOf(2024))));
                }
            }
//            map.putAll(extraMap);
            map = new HashMap<>(extraMap);
            long endTime = System.nanoTime();
            long durationInNanoseconds = endTime - startTime;
            double durationInSeconds = (double) durationInNanoseconds / 1000000000;
            System.out.println(map.size() + " stones after " + blink + " blinks" +
                    " time = " + durationInSeconds+ " seconds");
//            System.out.println(map.size() + " stones after " + blink + " blink: " + map);
        }
        System.out.println("last stones number = " + map.size());

        long endTime = System.nanoTime();
        long durationInNanoseconds = endTime - startTime;
        double durationInSeconds = (double) durationInNanoseconds / 1000000000;
        System.out.println("Execution time: " + durationInSeconds + " seconds");
    }

    public static Map<Long, String> createMap(String numberString) {
        String[] numbers = numberString.split(" ");
        Map<Long, String> numberMap = new HashMap<>();
        Long position = 0L;
        for (String number : numbers) {
            numberMap.put(position, number);
            position++;
        }
        return numberMap;
    }

    public static String[] splitString(String value) {

        int mid = value.length() / 2;

        String left = String.valueOf(new BigInteger(value.substring(0, mid)));
        String right = String.valueOf(new BigInteger(value.substring(mid)));

        return new String[]{left, right};
    }
}


