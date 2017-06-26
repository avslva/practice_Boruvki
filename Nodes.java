package sample;

import java.util.ArrayList;

public class Nodes {
    private ArrayList<Node> nodes;

    public Nodes() {

        nodes = new ArrayList<Node>();
    }

    public Node click (double x, double y) {    //функция для добавления вершины в список, если ее нет
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).equals(x, y)) {
                return nodes.get(i);
            }
        }
        nodes.add(new Node(x, y));
        return null;
    }

    public ArrayList<Integer> findSelected() {  //функция для поиска выделенных вершин
        ArrayList<Integer> selectedList = new ArrayList<Integer>();
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).isSelect()) {
                selectedList.add(i);
            }
        }
        return selectedList;
    }

    public Node getNode(int i) {
        return nodes.get(i);
    }
}