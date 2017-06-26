package sample;

public class Node {
    private double x;
    private double y;
    private boolean isSelect;
    public static final double NodeSize = 40;

    public Node (double x, double y) {
        this.x = x;
        this.y = y;
        isSelect = false;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean equals(double x, double y) { //фенкция для определения "равенства" вершин - определяет кликнули мышью по вершине или она - новая
        return  (Math.abs(this.x - x) < NodeSize && Math.abs(this.y - y) < NodeSize);
    }
}