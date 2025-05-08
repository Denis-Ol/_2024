package advent;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Day11Part1Upgr {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
//        String line = "0 1 10 99 999";
//        String line = "125 17";
        String line = "9759 0 256219 60 1175776 113 6 92833";

        Map<Long, String> map = createMap(line);

        System.out.println("Original: " + map);
        System.out.println("map.size() = " + map.size());
        for (byte blink = 1; blink <= 36; blink++) {

            long size = map.size();
            Map<Long, String> extraMap = new HashMap<>();
            long extraIndex = 0;

            for (Map.Entry<Long, String> entry : map.entrySet()) {
                long key = entry.getKey();
                String value = entry.getValue();
                if (value.equals("0")) {
                    map.put(key, "1");
                } else if (value.length() % 2 == 0) {
                    map.put(key, splitString(value)[0]);
                    extraMap.put(size + extraIndex, splitString(value)[1]);
                    extraIndex++;
                } else {
                    map.put(key, String.valueOf(new BigInteger(value).multiply(BigInteger.valueOf(2024))));
                }
            }
//            System.out.println("Map " + map);
//            System.out.println("Extra Map " + extraMap);
            map.putAll(extraMap);
//            System.out.println(map.size() + " stones after " + blink + " blink: " + map);
            long endTime = System.nanoTime();
            long durationInNanoseconds = endTime - startTime;
            double durationInSeconds = (double) durationInNanoseconds / 1000000000;

            System.out.println(map.size()+" stones after "+blink+ " blinks" + " time = "+durationInSeconds+ " seconds");

        }
        System.out.println("last stones number = " + map.size());

//        String checkLine = "2097446912 14168 4048 2 0 2 4 40 48 2024 40 48 80 96 2 8 6 7 6 0 3 2";
//        Map<Long, String> checkMap = createMap(checkLine);
//        System.out.println("identical? "+ compareValueCounts(map,checkMap));


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
    public static boolean compareValueCounts(Map<Long, String> map1, Map<Long, String> map2) {
        Map<String, Integer> freqMap1 = new HashMap<>();
        Map<String, Integer> freqMap2 = new HashMap<>();

        // Count frequencies for map1
        for (String value : map1.values()) {
            freqMap1.put(value, freqMap1.getOrDefault(value, 0) + 1);
        }

        // Count frequencies for map2
        for (String value : map2.values()) {
            freqMap2.put(value, freqMap2.getOrDefault(value, 0) + 1);
        }

        // Compare the frequency maps
        return freqMap1.equals(freqMap2);
    }

}


