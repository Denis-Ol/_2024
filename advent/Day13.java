package advent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Day13 {
    public static void main(String[] args) {
        Map<Byte, Integer> costs = new HashMap();
        Map<Byte, Byte> solutions = new HashMap();
        for (byte a = 0; a < 100; a++) {
            for (byte b = 0; b < 100; b++) {
                if ((94 * a + 22 * b == 8400) && (34 * a + 67 * b == 5400)) {
                    solutions.put(a, b);
                    costs.put(a, 3 * a + b);
                }
            }
        }
        if (solutions.isEmpty()) {
            System.out.println("No solution");
        } else {
            for (Map.Entry<Byte, Byte> entry : solutions.entrySet()) {
                byte a = entry.getKey();
                byte b = entry.getValue();
                System.out.println("Solution is: A = " + a + " B = " + b + ", cost = " + costs.get(a));
            }
            int minCost = Collections.min(costs.values());
            byte a = 0;
            for (Map.Entry<Byte, Integer> entry : costs.entrySet()) {
                if (entry.getValue().equals(minCost)) {
                    a = entry.getKey();
                    break; // Assuming unique values, break after finding the first match
                }
            }
            System.out.println("Min cost = " + minCost);
            System.out.println("For solution A = " + a + ", B = " + solutions.get(a));
        }

    }
}
