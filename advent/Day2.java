package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day2 {
    public static List<int[]> getReports(String filePath) {
        try (Stream<String> lines = Files.lines(Path.of("advent", "data", filePath))) {
            return lines.map(line -> Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray()).toList();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return List.of();
        }
    }

    public static boolean isReportSafe(int[] levels) {
        int startDiff = levels[1] - levels[0];
        if (startDiff == 0 || Math.abs(startDiff) > 3) {
            return false;
        }
        for (int i = 2; i < levels.length; i++) {
            int diff = levels[i] - levels[i - 1];
            if (diff == 0 || Math.abs(diff) > 3 || diff * startDiff < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTolerantReportSafe(int[] levels) {
        List<int[]> listOfCandidates = getArrayFamily2(levels);
        for (int[] candidateArray : listOfCandidates) {
            if (isReportSafe(candidateArray)) {
                return true;
            }
        }
        return false;
    }

    public static int safeCount(List<int[]> reports) {
        int count = 0;
        for (int[] report : reports) {
            if (isReportSafe(report)) count++;
        }
        return count;
    }

    public static int safeCountWithDampener(List<int[]> reports) {
        int count = 0;
        for (int[] report : reports) {
            if (isReportSafe(report)) count++;
            else if (isTolerantReportSafe(report)) count++;
        }
        return count;
    }

    public static List<int[]> getArrayFamily(int[] levels) {
        List<int[]> listArrays = new ArrayList<>(levels.length);
        for (int i = 0; i < levels.length; i++) {
            int[] temp = new int[levels.length - 1];
            int tempIndex = 0;
            for (int j = 0; j < levels.length; j++) {
                if (j != i) temp[tempIndex++] = levels[j];
            }
            listArrays.add(temp);
        }
        return listArrays;
    }

    public static List<int[]> getArrayFamily2(int[] levels) {
        List<int[]> listArrays = new ArrayList<>(levels.length);
        for (int i = 0; i < levels.length; i++) {
            int[] temp = new int[levels.length - 1];
            System.arraycopy(levels, 0, temp, 0, i);
            System.arraycopy(levels, i + 1, temp, i, levels.length - 1 - i);
            listArrays.add(temp);
        }
        return listArrays;
    }

    public static List<int[]> getArrayFamily3(int[] levels) {
        return IntStream.range(0, levels.length)
                .mapToObj(i -> IntStream.range(0, levels.length)
                        .filter(j -> i != j)
                        .map(j -> levels[j])
                        .toArray())
                .toList();
    }

    public static void main(String[] args) {
        System.out.println("Input safe count = " + safeCount(getReports("Day2-input.txt")));
        System.out.println("Input safe tolerant count = " + safeCountWithDampener(getReports("Day2-input.txt")));
    }
}
