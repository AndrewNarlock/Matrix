import java.util.Arrays;

public class Matrix {
    final private int ROWS;
    final private int COLS;
    final double[] MATRIX;

    public Matrix(final int theRows, final int theCols) {
        if (theRows < 0 || theCols < 0) {
            throw new IllegalArgumentException("The number of columns and rows must be greater than or equal to zero.");
        }

        ROWS = theRows;
        COLS = theCols;
        MATRIX = new double[theRows * theCols];
        Arrays.fill(MATRIX, 0.0);
    }

    public Matrix(final int theRows, final int theCols, final double... theValues) {
        if (theRows < 0 || theCols < 0) {
            throw new IllegalArgumentException("The number of columns and rows must be greater than or equal to zero.");
        }
        if (theCols * theRows < theValues.length) {
            throw new IllegalArgumentException("The number of values must be less than or equal to amount of spaces in the matrix.");
        }

        ROWS = theRows;
        COLS = theCols;
        MATRIX = new double[theRows * theCols];
        System.arraycopy(theValues, 0, MATRIX, 0, theValues.length);
    }

    public int getRows() {
        return ROWS;
    }
    public int getCols() {
        return COLS;
    }

    public double getValue(final int theIndex) {
        if(theIndex < 0 || theIndex >= MATRIX.length) {
            throw new IllegalArgumentException("The index is out of bounds.");
        }
        return MATRIX[theIndex];
    }

    public double getValue(final int theRow, final int theCol) {
        if (theRow < 0 || theCol < 0 || theCol >= COLS || theRow >= ROWS) {
            throw new IllegalArgumentException("The row or column number is out of bounds.");
        }
        return MATRIX[theRow * COLS + theCol];
    }

    public void setValue(final int theRow, final int theCol, final double theValue) {
        if (theRow < 0 || theCol < 0 || theCol >= COLS || theRow >= ROWS) {
            throw new IllegalArgumentException("The row or column number is out of bounds.");
        }
        MATRIX[theRow * COLS + theCol] = theValue;
    }

    public boolean containsValue(final double theValue) {
        return checkValueIndex(theValue) != -1;
    }

    public int checkValueIndex(final double theValue) {
        int index = -1;
        for (int i = 0; i < MATRIX.length; i++) {
            if (MATRIX[i] == theValue) {
                index = i;
            }
        }
        return index;
    }

    public String toString() {
        int counter = 0;
        StringBuilder finalString = new StringBuilder();

        for (int i = 0; i < ROWS; i++) {
            finalString.append("[");
            for (int j = 0; j < COLS; j++) {
                if (j == COLS - 1) {
                    finalString.append(MATRIX[counter++]).append("] ");
                } else {
                    finalString.append(MATRIX[counter++]).append(", ");
                }
            }
            finalString.append("\n");
        }
        return finalString.toString();
    }
}
