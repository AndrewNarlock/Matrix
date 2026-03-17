import java.util.Arrays;

public class Matrix {
    final private int ROWS;
    final private int COLS;
    final private double[] MATRIX;

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

    public double getValueAtIndex(final int theIndex) {
        if(theIndex < 0 || theIndex >= MATRIX.length) {
            throw new IndexOutOfBoundsException("The index is out of bounds.");
        }
        return MATRIX[theIndex];
    }

    public double getValue(final int theRow, final int theCol) {
        if (theRow < 0 || theCol < 0 || theCol >= COLS || theRow >= ROWS) {
            throw new IndexOutOfBoundsException("The row or column number is out of bounds.");
        }
        return MATRIX[theRow * COLS + theCol];
    }

    public void setValue(final int theRow, final int theCol, final double theValue) {
        if (theRow < 0 || theCol < 0 || theCol >= COLS || theRow >= ROWS) {
            throw new IndexOutOfBoundsException("The row or column number is out of bounds.");
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

    public Matrix add(Matrix theMatrix) {
        if (theMatrix.getRows() != ROWS || theMatrix.getCols() != COLS) {
            throw new IllegalArgumentException("Matrices must have same columns and rows.");
        }
        Matrix result = new Matrix(ROWS, COLS);
        for (int i = 0; i < MATRIX.length; i++) {
            result.MATRIX[i] = MATRIX[i] + theMatrix.getValueAtIndex(i);
        }
        return result;
    }

    public Matrix subtract(Matrix theMatrix) {
        if (theMatrix.getRows() != ROWS || theMatrix.getCols() != COLS) {
            throw new IllegalArgumentException("Matrices must have same columns and rows.");
        }
        Matrix result = new Matrix(ROWS, COLS);
        for (int i = 0; i < MATRIX.length; i++) {
            result.MATRIX[i] = MATRIX[i] - theMatrix.getValueAtIndex(i);
        }
        return result;
    }

    public Matrix multiply(int theScalar) {
        Matrix result = new Matrix(ROWS, COLS);
        for (int i = 0; i < MATRIX.length; i++) {
            result.MATRIX[i] = MATRIX[i] * theScalar;
        }
        return result;
    }

    public Matrix multiply(double theScalar) {
        Matrix result = new Matrix(ROWS, COLS);
        for (int i = 0; i < MATRIX.length; i++) {
            result.MATRIX[i] = MATRIX[i] * theScalar;
        }
        return result;
    }

    public Matrix multiply(Matrix theMatrix) {
        if (COLS != theMatrix.getRows()) {
            throw new IllegalArgumentException("Number of columns in matrix A must be equal to number of rows in matrix B.");
        }
        Matrix result = new Matrix(ROWS, theMatrix.getCols());
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < theMatrix.getCols(); j++) {
                double sum = 0;
                for (int k = 0; k < COLS; k++) {
                    double a = MATRIX[i * COLS + k];
                    double b = theMatrix.MATRIX[k * theMatrix.getCols() + j];

                    sum += a * b;
                }
                result.MATRIX[i * theMatrix.getCols() + j] = sum;
            }
        }
        return result;
    }

    public Matrix transpose() {
        Matrix result = new Matrix(COLS, ROWS);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                result.setValue(j, i, getValue(i, j));
            }
        }
        return result;
    }

    @Override
    public String toString() {
        int counter = 0;
        StringBuilder finalString = new StringBuilder();

        for (int i = 0; i < ROWS; i++) {
            finalString.append("[");
            for (int j = 0; j < COLS; j++) {
                if (j == COLS - 1) {
                    finalString.append(String.format("%.2f" ,MATRIX[counter++])).append("] ");
                } else {
                    finalString.append(String.format("%.2f" ,MATRIX[counter++])).append(", ");
                }
            }
            finalString.append("\n");
        }
        return finalString.toString();
    }

    @Override
    public boolean equals(Object theObject) {
        if (this == theObject) return true;
        if (theObject == null || getClass() != theObject.getClass()) return false;
        Matrix other = (Matrix) theObject;
        if (ROWS != other.ROWS || COLS != other.COLS) return false;
        return Arrays.equals(MATRIX, other.MATRIX);
    }

    @Override
    public int hashCode() {
        return 31 * (31 * ROWS + COLS) + Arrays.hashCode(MATRIX);
    }
}
