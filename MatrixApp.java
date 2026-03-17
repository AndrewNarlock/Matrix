import java.util.Scanner;

public class MatrixApp {

    public static void main(String[] args) {
        Matrix m = new Matrix(3, 3, 1, 2, 3, 4, 5, 6, 7, 8 , 9);
        System.out.println(m.toString());
        Matrix n = new Matrix(5, 7, 1, 2, 3, 4, 5);
        System.out.println(n.toString());

        Matrix l = new Matrix(5, 7, 1, 2, 3, 4, 5);
        //m.add(n);
        Matrix result = n.add(l);

        System.out.println(result.toString());

        Matrix sub = new Matrix(5, 7, 2, 4, 6, 8, 10);
        Matrix result2 = result.subtract(sub);

        System.out.println(result2.toString());

        Matrix mult = sub.multiply(3);
        System.out.println(mult.toString());

        Matrix doubmult = sub.multiply(3.2);
        System.out.println(doubmult.toString());

        Matrix A = new Matrix(3, 2, 1, 2, 3, 4, 5, 6);
        Matrix B = new Matrix(2, 3, 1, 2, 3, 4, 5, 6);

        Matrix C = A.multiply(B);

        System.out.println(C.toString());

    }


}
