package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    public static List<int[]> getReports(String filePath) {
        try (Stream<String> lines = Files.lines(Path.of("advent", "data", filePath))) {
            return lines.map(line -> Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray()).toList();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return List.of();
        }
    }

    public static List<String> readFileToStringList(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(Path.of("advent", "data", fileName).toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
        return lines;
    }

    public static List<String> readFileToListStream(String filePath) {
        try (Stream<String> lines = Files.lines(Path.of("advent", "data", filePath))) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public static String readFileToString(String filePath) {
        try (Stream<String> lines = Files.lines(Path.of("advent", "data", filePath))) {
            return lines.collect(Collectors.joining(""));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public static String readFileToStringWithNewline(String filePath) {
        try (Stream<String> lines = Files.lines(Path.of("advent", "data", filePath))) {
            return lines.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }

    public static String readFileToString2(String filePath) throws IOException {
        Path path = Paths.get("advent", "data", filePath);
        return Files.readString(path, StandardCharsets.UTF_8);
    }

    public static String readFileToString3(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get("advent", "data", filePath).toString()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        // Remove the last line separator if the file is not empty
        if (content.length() > 0) {
            content.delete(content.length() - System.lineSeparator().length(), content.length());
        }
        return content.toString();
    }
}
