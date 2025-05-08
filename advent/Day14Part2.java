package advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day14Part2 {
//    static String input = """
//            p=0,4 v=3,-3
//            p=6,3 v=-1,-3
//            p=10,3 v=-1,2
//            p=2,0 v=2,-1
//            p=0,0 v=1,3
//            p=3,0 v=-2,-2
//            p=7,6 v=-1,-3
//            p=3,0 v=-1,-2
//            p=9,3 v=2,3
//            p=7,3 v=-1,2
//            p=2,4 v=2,-3
//            p=9,5 v=-3,-3""";
//    static byte xDim = 11;
//    static byte yDim = 7;

    static byte xDim = 101;
    static byte yDim = 103;

    public static String stringFromFile(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public static class Point {
        int x, y, vx, vy;
        public Point(int x, int y, int vx, int vy) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }


        public void move100() {
            x = (x + 100 * vx) % xDim;
            if (x < 0) {
                x += xDim;
            }
            y = (y + 100 * vy) % yDim;
            if (y < 0) {
                y += yDim;
            }
        }

        public Point move(int times) {
            x = (x + times * vx) % xDim;
            if (x < 0) {
                x += xDim;
            }
            y = (y + times * vy) % yDim;
            if (y < 0) {
                y += yDim;
            }
            return this;
        }
    }

    public static List<Point> parseInput(String input) {
        List<Point> points = new ArrayList<>();
        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] parts = line.split(" ");
            String[] p = parts[0].split("=")[1].split(",");
            String[] v = parts[1].split("=")[1].split(",");
            int x = Integer.parseInt(p[0]);
            int y = Integer.parseInt(p[1]);
            int vx = Integer.parseInt(v[0]);
            int vy = Integer.parseInt(v[1]);
            points.add(new Point(x, y, vx, vy));
        }
        return points;
    }

    public static void main(String[] args) {

//        List<Point> points = parseInput(input);
//        List<Point> points = parseInput(stringFromFile("advent/data/Day14-test.txt"));
        List<Point> points = parseInput(stringFromFile("advent/data/Day14Input.txt"));

//        System.out.println("points.size() = " + points.size());
//        System.out.println("points = " + points);
//        for (int i = 0; i < points.size(); i++) {
//            System.out.println("Point " + i);
//            System.out.println("Position (" + points.get(i).getX() + ", " + points.get(i).getY() + ")");
//            System.out.println("Velocity (" + points.get(i).getVx() + ", " + points.get(i).getVy() + ")\n");
//        }

//        points.get(0)
//                .move(1)
//                .move(1);

        points.forEach(Point::move100);
//
        System.out.println("100 moves");
        int[][] area = new int[yDim][xDim];
        System.out.println("area.length = " + area.length);
        System.out.println("area[0].length = " + area[0].length);
        for (int[] value : area) {
            Arrays.fill(value, 0);
        }

        for (Point point : points) {
            int x = point.getX();
            int y = point.getY();
//            System.out.println("Point " + i + ": Position (" + x + ", " + y + ")");
            area[y][x]++;
        }
//        System.out.println();
//        System.out.println("area:");
//        printArea(area);
        int[][] quadrantTopLeft = getSubArray(area, 0, 0, (yDim - 1) / 2, (xDim - 1) / 2);
//        System.out.println("/nquadrantTopLeft:");
//        printArea(quadrantTopLeft);
        int sumOfQuadrant1 = sumOfQuadrant(quadrantTopLeft);
        System.out.println("sumOfQuadrant1 = " + sumOfQuadrant1);

        int[][] quadrantTopRight = getSubArray(area, 0, (xDim + 1) / 2, (yDim - 1) / 2, (xDim - 1) / 2);
//        System.out.println("/nquadrantTopRight:");
//        printArea(quadrantTopRight);
        int sumOfQuadrant2 = sumOfQuadrant(quadrantTopRight);
        System.out.println("sumOfQuadrant2 = " + sumOfQuadrant2);

        int[][] quadrantBottomLeft = getSubArray(area, (yDim + 1) / 2, 0, (yDim - 1) / 2, (xDim - 1) / 2);
//        System.out.println("/nquadrantBottomLeft:");
//        printArea(quadrantBottomLeft);
        int sumOfQuadrant3 = sumOfQuadrant(quadrantBottomLeft);
        System.out.println("sumOfQuadrant3 = " + sumOfQuadrant3);

        int[][] quadrantBottomRight = getSubArray(area, (yDim + 1) / 2, (xDim + 1) / 2, (yDim - 1) / 2, (xDim - 1) / 2);
//        System.out.println("/nquadrantBottomRight:");
//        printArea(quadrantBottomRight);
        int sumOfQuadrant4 = sumOfQuadrant(quadrantBottomRight);
        System.out.println("sumOfQuadrant4 = " + sumOfQuadrant4);

        int safetyFactor = sumOfQuadrant1*sumOfQuadrant2*sumOfQuadrant3*sumOfQuadrant4;
        System.out.println("safetyFactor = " + safetyFactor);

    }

    private static void printArea(int[][] area) {
        for (int[] ints : area) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public static int[][] getSubArray(int[][] originalArray, int startRow, int startCol, int rows, int cols) {
        int[][] subArray = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(originalArray[startRow + i], startCol, subArray[i], 0, cols);
        }
        return subArray;
    }

    public static int sumOfQuadrant(int[][] array) {
        int sum = 0;
        for (int[] rows : array) {
            for (int element : rows) {
                sum += element;
            }
        }
        return sum;
    }

}
