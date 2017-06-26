package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GraphFormController extends Canvas {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Canvas graphicPane;
    @FXML
    private Label messageText;
    @FXML
    private TextField userText;
    @FXML
    public static GraphicsContext gc;

    private Nodes nodes;
    private Image nodeImg;
    private Image selectNodeImg;
    private Graph graph;

    private int numberOfNodes;
    private int inputedNumberOfNodes;
    private boolean firstStep;
    private boolean canSelect;
    private boolean canCreate;
    private int selectedOne;
    private int selectedTwo;
    private int numberOfSelectNodes;



    @FXML
    private void initialize() { //функция для инициализации нач. данных
        firstStep = true;
        canSelect = false;
        canCreate = false;
        gc = graphicPane.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 600, 400);
        nodes = new Nodes();
        nodeImg = new Image("1.png");
        selectNodeImg = new Image("2.png");
        messageText.setText("Введите количество вершин");
        graph = new Graph();
        numberOfSelectNodes = 0;

        setMouseFunction();
    }

    private void setMouseFunction() {   //функция для определения клика мыши по активному полю
        graphicPane.setOnMousePressed((MouseEvent) -> {
            Node clickNode = nodes.click(MouseEvent.getX(), MouseEvent.getY());
            if (canCreate) {
                if (clickNode == null) {
                    gc.drawImage(nodeImg, MouseEvent.getX()-20, MouseEvent.getY()-20);  //рисуем неотмеченную вершину на поле в координатах клика мыши
                    inputedNumberOfNodes--;
                    if (inputedNumberOfNodes == 0) {
                        messageText.setText("Вы можете создать ребро, выделив 2 вершины");
                        canCreate = false;
                        canSelect = true;
                    }
                }
            }
            if (canSelect) {
                if (clickNode != null) {
                    messageText.setText("Вы можете создать ребро, выделив 2 вершины");
                    if (clickNode.isSelect()) {
                        gc.drawImage(nodeImg, clickNode.getX()-20, clickNode.getY()-20);    //рисуем неотмеченную вершину на поле в координатах вершины
                        clickNode.setSelect(false);
                        numberOfSelectNodes = numberOfSelectNodes - 1;
                    } else {
                        gc.drawImage(selectNodeImg, clickNode.getX()-20, clickNode.getY()-20);  //рисуем отмеченную вершину на поле в координатах вершины
                        clickNode.setSelect(true);
                        numberOfSelectNodes = numberOfSelectNodes + 1;
                        if (numberOfSelectNodes == 2) {
                            selectTwoNodes();
                        }
                    }
                }
            }

        });
    }

    private void selectTwoNodes() { //функция для рисования ребра(если 2 вершины выделены)
        messageText.setText("Введите вес ребра");
        canSelect = false;
        ArrayList<Integer> selectedList = nodes.findSelected();
        selectedOne = selectedList.get(0);
        selectedTwo = selectedList.get(1);
        gc.strokeLine(nodes.getNode(selectedOne).getX()+15, nodes.getNode(selectedOne).getY()+15, nodes.getNode(selectedTwo).getX()+15, nodes.getNode(selectedTwo).getY()+15);

    }

    public void cancelFunction(ActionEvent actionEvent) {   //кнопка Отмена

        System.out.println("Button 1 pressed!");
    }

    public void deleteFunction(ActionEvent actionEvent) {   //кнопка Удалить

        initialize();
    }

    public void resultFunction(ActionEvent actionEvent) {   //кнопка Результат

        System.out.println("Button 2 pressed!");
    }

    public void nextStepFunction(ActionEvent actionEvent) { //кнопка След.шаг

        System.out.println("Button 3 pressed!");
    }

    public void okButtonFunction(ActionEvent actionEvent) { //кнопка Готово
        if (firstStep) {    //случай ввода количества вершин
            inputedNumberOfNodes = Integer.parseInt(userText.getText());
            userText.clear();
            numberOfNodes = inputedNumberOfNodes;
            canCreate = true;
            firstStep = false;
            messageText.setText("Создайте вершины, кликнув левой кнопкой мыши в активном поле");
        } else {
            if (!canSelect) {   //случай для ввода веса ребра
                messageText.setText("Введите вес ребра");
                int weight = Integer.parseInt(userText.getText());
                String weightStr = userText.getText();
                graph.add(selectedOne, selectedTwo, weight);
                gc.drawImage(nodeImg, nodes.getNode(selectedOne).getX()-20, nodes.getNode(selectedOne).getY()-20);
                nodes.getNode(selectedOne).setSelect(false);
                gc.drawImage(nodeImg, nodes.getNode(selectedTwo).getX()-20, nodes.getNode(selectedTwo).getY()-20);
                nodes.getNode(selectedTwo).setSelect(false);
                final Label weightText = new Label();
                weightText.setText(weightStr);
                weightText.setLayoutX(Math.abs(nodes.getNode(selectedOne).getX()+ nodes.getNode(selectedTwo).getX())/2);
                weightText.setLayoutY(Math.abs(nodes.getNode(selectedOne).getY()+ nodes.getNode(selectedTwo).getY())/2);
                mainPane.getChildren().add( weightText);
                messageText.setText("Вы можете создать ребро, выделив 2 вершины");
                numberOfSelectNodes = 0;
                canSelect = true;
                userText.clear();
            } else {
                canSelect = false;
                // TODO: продолжение следует...
            }
        }
    }
}
