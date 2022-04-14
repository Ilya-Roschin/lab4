package com.java.app.lab4;

public class Service {

    public String findMatrix(String string) {
        try {
        Service service = new Service();
        StringBuilder matrix = new StringBuilder();
        for (float[] element : service.inversion(service.StringToArray(string), 3)) {
            for (float number : element) {
                matrix.append(number).append(" ");
            }
            matrix.append("\n");
        }
           return matrix.toString();
        } catch (Exception e) {

        }
        return "Wrong matrix";
    }

    public double [][] StringToArray(String string) {
        String[] subStr = string.split(" ");
        double [][] array = new double[3][3];
        int n = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = (double) Double.parseDouble(subStr[n]);
                n++;
            }

        }
        return array;
    }


    public float[][] inversion(double[][] A, int N) {
        double temp;

        float[][] E = new float[N][N];


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                E[i][j] = 0f;
                if (i == j)
                    E[i][j] = 1f;
            }
        }

        for (int k = 0; k < N; k++) {
            temp = A[k][k];
            for (int j = 0; j < N; j++) {
                A[k][j] /= temp;
                E[k][j] /= temp;
            }

            for (int i = k + 1; i < N; i++) {
                temp = A[i][k];
                for (int j = 0; j < N; j++) {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        for (int k = N - 1; k > 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                temp = A[i][k];
                for (int j = 0; j < N; j++) {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = E[i][j];
            }
        }

        return E;
    }


}
