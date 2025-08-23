package ch.shai.cs3.utils.math;

import java.util.ArrayList;
import java.util.List;

public class MathUtils {

    /**
     * Finds indexes representing the step's position on the total
     * @param total The amount
     * @param step The percentages
     * @return List of indexes
     */
    public static List<Integer> getStepIndexes(int total, int step) {
        List<Integer> indices = new ArrayList<>();
        for (int i = step; i <= 100; i += step) {
            indices.add((int) Math.ceil(i / 100.0 * total));
        }
        return indices;
    }
}
