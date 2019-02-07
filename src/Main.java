import java.util.Random;

class Main {

    public static void main(String[] args) {

        final int TEST = 11;
        String[] testNames = {
            "Serial, Small",
            "Transposed Serial, Small",
            "Serial, Medium",
            "Transposed Serial, Medium",
            "Serial, Large",
            "Transposed Serial, Large",
            "Many Threads, Small",
            "Row Threads, Small",
            "Many Threads, Medium",
            "Row Threads, Medium",
            "Many Threads, Large",
            "Row Threads, Large"
        };

        final int USER = 1;
        String[][] specList = {
            //----NAME----------CACHE SIZE
            {
                "Philippe Nadon", "384KiB"
            },
            {
                "Luke Rostad", "250KiB"
            },
            {
                "", ""
            }
        };

        int[][] randMatrixSizes = {
            {60, 70, 80},
            {1000, 2000, 3000}
        };

        double[][] fixedMatrix23 = {
            {1, 2, 3},
            {5, 7, 11}
        };

        double[][] fixedMatrix34 = {
            {1, 2, 3, 5},
            {7, 11, 13, 17},
            {19, 23, 29, 31}
        };

        //-----------------------------------------------------
        //                                        TEST SWITCH |
        //-----------------------------------------------------
        long timing = 0;
        switch (TEST) {

            case 0:
                timing = runSerial(fixedMatrix23, fixedMatrix34);
                break;

            case 1:
                timing = runSerialT(fixedMatrix23, fixedMatrix34);
                break;

            case 2:
                timing = runSerialR(randMatrixSizes[0]);
                break;

            case 3:
                timing = runSerialRT(randMatrixSizes[0]);
                break;

            case 4:
                timing = runSerialR(randMatrixSizes[1]);
                break;

            case 5:
                timing = runSerialRT(randMatrixSizes[1]);
                break;

            case 6:
                timing = runManyThreads(fixedMatrix23, fixedMatrix34);
                break;

            case 7:
                timing = runRowThreads(fixedMatrix23, fixedMatrix34);
                break;

            case 8:
                timing = runManyThreadsR(randMatrixSizes[0]);
                break;

            case 9:
                timing = runRowThreadsR(randMatrixSizes[0]);
                break;

            case 10:
                timing = runManyThreadsR(randMatrixSizes[1]);
                break;

            case 11:
                timing = runRowThreadsR(randMatrixSizes[1]);
                break;

            default:
                return;
        }

        printResult(testNames[TEST], specList[USER], timing);
    }

    //-----------------------------------------------------
    //                                    TEST METHODS P1 |
    //-----------------------------------------------------

    private static long runSerial(double[][] matr1, double[][] matr2) {
        int height = matr1.length;
        int width = matr2[0].length;
        int innerDim = matr2.length;
        double[][] res = new double[height][width];

        long startTime;
        long endTime;

        startTime = System.nanoTime();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < innerDim; k++) {
                    res[i][j] += matr1[i][k] * matr2[k][j];
                }
            }
        }
        endTime = System.nanoTime();

        printMatrix(matr1);
        print("------------ x ------------\n");
        printMatrix(matr2);
        print("------------ = ------------\n");
        printMatrix(res);

        return endTime - startTime;
    }

    private static long runSerialT(double[][] matr1, double[][] matr2) {
        int height = matr1.length;
        int width = matr2[0].length;
        int innerDim = matr2.length;
        double[][] res = new double[height][width];

        double[][] matr2T = transposeMatrix(matr2);

        long startTime;
        long endTime;

        startTime = System.nanoTime();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < innerDim; k++) {
                    res[i][j] += matr1[i][k] * matr2T[j][k];
                }
            }
        }
        endTime = System.nanoTime();

        printMatrix(matr1);
        print("------------ x ------------\n");
        printMatrix(matr2);
        print("------------ = ------------\n");
        printMatrix(res);

        return endTime - startTime;
    }

    private static long runSerialR(int[] sizes) {
        int height = sizes[0];
        int innerDim = sizes[1];
        int width = sizes[2];

        double[][] matr1 = makeRandMatrix( height, innerDim);
        double[][] matr2 = makeRandMatrix( innerDim, width);
        double[][] res = new double[height][width];

        long startTime;
        long endTime;

        startTime = System.nanoTime();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < innerDim; k++) {
                    res[i][j] += matr1[i][k] * matr2[k][j];
                }
            }
        }
        endTime = System.nanoTime();

        return endTime - startTime;
    }

    private static long runSerialRT(int[] sizes) {
        int height = sizes[0];
        int innerDim = sizes[1];
        int width = sizes[2];

        double[][] matr1 = makeRandMatrix( height, innerDim);
        double[][] matr2 = makeRandMatrix( innerDim, width);
        double[][] res = new double[height][width];

        double[][] matr2T = transposeMatrix(matr2);

        long startTime;
        long endTime;

        startTime = System.nanoTime();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < innerDim; k++) {
                    res[i][j] += matr1[i][k] * matr2T[j][k];
                }
            }
        }
        endTime = System.nanoTime();

        return endTime - startTime;
    }

    //-----------------------------------------------------
    //                                    TEST METHODS P2 |
    //-----------------------------------------------------

    private static long runManyThreads(double[][] matr1, double[][] matr2) {
        int height = matr1.length;
        int width = matr2[0].length;
        int innerDim = matr2.length;
        double[][] res = new double[height][width];

        long startTime;
        long endTime;

        startTime = System.nanoTime();
        endTime = System.nanoTime();

        return endTime - startTime;
    }

    private static long runManyThreadsR(int[] sizes) {
        long startTime;
        long endTime;

        startTime = System.nanoTime();
        endTime = System.nanoTime();

        return endTime - startTime;
    }

    private static long runRowThreads(double[][] matr1, double[][] matr2) {
        double[][] matr2T = transposeMatrix(matr2);
        int height = matr1.length;
        int width = matr2T[0].length;
        int innerDim = matr2T.length;
        double[][] res = new double[height][width];

        long startTime;
        long endTime;



        RowThread[] threads = new RowThread[height];

        startTime = System.nanoTime();

        for (int i=0; i < height; i++){
            threads[i] = new RowThread(matr1[i], matr2T);
            threads[i].start();
        }

        try {
            for (int i=0; i < height; i++) {
                threads[i].join();
                res[i] = threads[i].getResult();
            }
        }
        catch (InterruptedException e) {
            // fall through
        }

        endTime = System.nanoTime();

        printMatrix(matr1);
        print("------------ x ------------\n");
        printMatrix(matr2);
        print("------------ = ------------\n");
        printMatrix(res);


        return endTime - startTime;
    }

    private static long runRowThreadsR(int[] sizes) {
        double[][] matr1 = makeRandMatrix( sizes[0], sizes[1]);
        double[][] matr2 = makeRandMatrix( sizes[1], sizes[2]);
        double[][] matr2T = transposeMatrix(matr2);
        int height = matr1.length;
        int width = matr2T[0].length;
        int innerDim = matr2T.length;
        double[][] res = new double[height][width];

        long startTime;
        long endTime;



        RowThread[] threads = new RowThread[height];

        startTime = System.nanoTime();

        for (int i=0; i < height; i++){
            threads[i] = new RowThread(matr1[i], matr2T);
            threads[i].start();
        }

        try {
            for (int i=0; i < height; i++) {
                threads[i].join();
                res[i] = threads[i].getResult();
            }
        }
        catch (InterruptedException e) {
            // fall through
        }

        endTime = System.nanoTime();

        return endTime - startTime;
    }

    //-----------------------------------------------------
    //                                      OTHER METHODS |
    //-----------------------------------------------------

    private static double[][] transposeMatrix(double[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        double[][] res = new double[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                res[i][j] = matrix[j][i];
            }
        }

        return res;
    }

    private static double[][] makeRandMatrix( int width, int height) {
        double[][] res = new double[width][height];
        Random rand = new Random();
        for( int i = 0; i < width; i++){
            for( int j = 0; j < height; j++){
                res[i][j] = rand.nextDouble();
            }
        }
        return res;
    }

    private static void print(String msg) {
        System.out.print(msg);
    }

    private static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            print("|  ");
            for (int j = 0; j < matrix[0].length; j++) {
                print(String.format("%-6.1f", matrix[i][j]));
                print(" ");
            }
            print("|\n");
        }
    }

    private static void printResult(String test, String[] specs, long timing) {
        int numCores = Runtime.getRuntime().availableProcessors();
        double timeRes = (double)timing;
        print(
            "\n ______________________________________" +
            "\n|----------------RESULT----------------|" +
            "\n|  test run: " + String.format("%-26s", test)     + "|" +
            "\n|      user: " + String.format("%-26s", specs[0]) + "|" +
            "\n|  L1 cache: " + String.format("%-26s", specs[1]) + "|" +
            "\n|   # cores: " + String.format("%-26d", numCores) + "|" +
            "\n| time (ns): " + String.format("%-26.2e", timeRes)   + "|" +
            "\n\\--------------------------------------/");
    }

}
