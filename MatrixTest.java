import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    void testMatrixConstructorRowAndColumnOnly() {
        Matrix m = new Matrix(4, 5);
        assertEquals(4, m.getRows());
        assertEquals(5, m.getCols());
    }

    @Test
    void testMatrixConstructorWithValuesFull() {
        Matrix m = new Matrix(3, 3, 1, 2, 3, 4, 5, 6, 7 ,8 ,9);
        int counter = 1;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                assertEquals(counter++, m.getValue(i, j));
            }
        }
    }

    @Test
    void testMatrixConstructorWithValuesNotFull() {
        Matrix m = new Matrix(3, 3, 1, 2, 3);
        for(int i = 1; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                assertEquals(0.0, m.getValue(i, j));
            }
        }
    }

    @Test
    void testMatrixConstructorRowAndColumnNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            Matrix m = new Matrix(-3, 3);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Matrix m = new Matrix(3, -3);
        });
    }

    @Test
    void testMatrixConstructorTooManyValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            Matrix m = new Matrix(1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        });
    }

    @Test
    void testMatrixConstructorNegativeRowsColumnsWithValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            Matrix m = new Matrix(-2, 2, 1, 2, 3);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Matrix m = new Matrix(2, -2, 1, 2, 3);
        });
    }

    @Test
    void testSetValue() {
        Matrix m = new Matrix(3, 3);
        m.setValue(0, 0, 1.0);
        assertEquals(1.0, m.getValue(0, 0), 0.0001);
    }

    @Test
    void testSetValueNegativeIndex() {
        Matrix m = new Matrix(3, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            m.setValue(-1, 0, 1.0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            m.setValue(0, -1, 1.0);
        });
    }

    @Test
    void testSetValueOutOfBoundIndex() {
        Matrix m = new Matrix(1, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            m.setValue(20, 0, 1.0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            m.setValue(0, 20, 1.0);
        });
    }

    @Test
    void testGetValueNegativeColAndRow() {
        Matrix m = new Matrix(3, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            m.getValue(1, -1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            m.getValue(-1, 1);
        });
    }

    @Test
    void testGetValueAtIndexGreaterThanColAndRow() {
        Matrix m = new Matrix(3, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            m.getValue(1, 5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            m.getValue(5, 1);
        });
    }

    @Test
    void testGetValueAtIndex() {
        Matrix m = new Matrix(2, 2, 1, 2, 3, 4);
        assertEquals(1.0, m.getValue(0, 0), 0.0001);
    }

    @Test
    void testGetValueAtIndexArrayNegativeColAndRow() {
        Matrix m = new Matrix(1, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            m.getValue(-1);
        });
    }

    @Test
    void testGetValueAtIndexArrayOutOfBoundColAndRow() {
        Matrix m = new Matrix(1, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            m.getValue(20);
        });
    }

    @Test
    void testGetValueArrayReal() {
        Matrix m = new Matrix(2, 2, 1, 2, 3, 4);
        assertEquals(1.0, m.getValue(0), 0.0001);
    }

    @Test
    void testContainValue() {
        Matrix m = new Matrix(2, 2, 1, 2, 3, 4);
        assertTrue(m.containsValue(1.0));
        assertFalse(m.containsValue(0.0));
    }

    @Test
    void testToString() {
        String check = "[1.0, 2.0] \n[3.0, 4.0] \n";
        Matrix m = new Matrix(2, 2, 1, 2, 3, 4);
        assertEquals(check, m.toString());
    }
}
