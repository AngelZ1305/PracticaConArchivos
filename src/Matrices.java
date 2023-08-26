import java.io.*;

public class Matrices {
    public static void main(String[] args) {

        try{
            FileInputStream FileaMat = new FileInputStream("src/a.mat");
            FileInputStream FilebMat = new FileInputStream("src/b.mat");


            DataInputStream aMat = new DataInputStream(FileaMat);
            DataInputStream bMat = new DataInputStream(FilebMat);

            int filasA = aMat.readByte();
            int columnasA = aMat.readByte();
            int filasB = bMat.readByte();
            int columnasB = bMat.readByte();

            if (columnasA != filasB){
                System.out.println("Las matrices no se pueden multiplicar");
                return;
            }
            double [][] matrizA = new double[filasA][columnasA];
            double [][] matrizB = new double[filasB][columnasB];

            for (int i = 0; i < filasA; i++) {
                for (int j = 0; j < columnasA; j++) {
                    matrizA [i][j] = aMat.readDouble();
                }
            }
            for (int i = 0; i < filasB; i++) {
                for (int j = 0; j < columnasB; j++) {
                    matrizB [i][j] = bMat.readDouble();
                }
            }
            aMat.close();
            bMat.close();

            double [][] matrizC = new double[filasA][columnasB];

            for (int i = 0; i < filasA; i++) {
                for (int j = 0; j < columnasB; j++) {
                    double sum = 0;
                    for (int k = 0; k < columnasA; k++) {
                        sum += matrizA[i][k] * matrizB[k][j];
                    }
                    matrizC[i][j] = sum;
                }
            }
            System.out.println("Matriz A: ");
            printMatriz(matrizA);
            System.out.println("Matriz B: ");
            printMatriz(matrizB);
            System.out.println("Matriz C: ");
            printMatriz(matrizC);

            DataOutputStream matrixCStream = new DataOutputStream(new FileOutputStream("c.mat"));
            matrixCStream.writeByte(filasA);
            matrixCStream.writeByte(columnasB);

            for (int i = 0; i < filasA; i++) {
                for (int j = 0; j < columnasB; j++) {
                    matrixCStream.writeDouble(matrizC[i][j]);
                }
            }
            System.out.println("Se ha guardado la matriz en c.mat");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void printMatriz(double[][] matriz) {
        for (double[] row : matriz) {
            for (double value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}
