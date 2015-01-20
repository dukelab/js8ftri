package dukelab.js8ftri.ch6;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class Exercise09 {

    private static class Matrix {

        private final int[][] m;

        private Matrix(int[][] m) {
            this.m = m;
        }

        public Matrix multiple(Matrix other) {
            int[][] result = new int[2][2];
            result[0][0] =  m[0][0] * other.m[0][0] + m[0][1] * other.m[1][0];
            result[0][1] =  m[0][0] * other.m[0][1] + m[0][1] * other.m[1][1];
            result[1][0] =  m[1][0] * other.m[0][0] + m[1][1] * other.m[1][0];
            result[1][1] =  m[1][0] * other.m[0][1] + m[1][1] * other.m[1][1];
            return new Matrix(result);
        }

        public int get(int x, int y) {
            return m[x][y];
        }

    }

    @Test
    public void test() {
        int[][] m = { { 1, 1 }, { 1, 0 } };
        Matrix[] matrix = new Matrix[10];
        Arrays.parallelSetAll(matrix, i -> new Matrix(m));
        Arrays.parallelPrefix(matrix, (m1, m2) -> m1.multiple(m2));

        int[] actuals = new int[10];
        Arrays.parallelSetAll(actuals, i -> matrix[i].get(0, 0));

        int[] expecteds = { 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };
        Assert.assertArrayEquals(expecteds, actuals);
    }

}
