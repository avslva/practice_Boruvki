package sample;

public class Graph {
    private int[][] matrix;
    private int number;

    public Graph() {
        matrix = new int[100][3];
        number = 0;
    }

    public void add(int a, int b, int w) {
        matrix[number][0] = a;
        matrix[number][1] = b;
        matrix[number][2] = w;
        number++;
    }
}