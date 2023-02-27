public class Main {
    public static void main(String[] args) {
        long start =  System.currentTimeMillis();
        if(args.length == 0) {
            System.out.println("Provide an integer as a command line argument.");
            System.exit(-1);
        }
        int n = Integer.parseInt(args[0]);
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            matrix[0][i] = i + 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = matrix[i-1][(j+1) % n];
            }
        }
        if(n<30000) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("The new matrix as a string: ");
            for (int i = 0; i < n; i++) {
                String s = "";
                for (int j = 0; j < n; j++) {
                    s += matrix[i][j];
                }
                System.out.println(s);
            }
        }
        long end =  System.currentTimeMillis();
        System.out.println("The running time of the application: " + (end-start) + " ms");
    }
}