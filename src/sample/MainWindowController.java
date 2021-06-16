package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

public class MainWindowController {
    @FXML private Pane titlePane;
    @FXML private ImageView btnMinimize, btnClose;
    @FXML private Label lbResult;

    private double x, y;
    private double num1 = 0;
    private String operator = "+";

    public void init(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getX();
            y = mouseEvent.getY();
        });

        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getSceneX() - x);
            stage.setY(mouseEvent.getY() - y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }
    @FXML
    void onNumberClicked(MouseEvent event) {
        int value = Integer.parseInt(((Pane)event.getSource()).getId().replace("btn", ""));
        lbResult.setText(Double.parseDouble(lbResult.getText()) == 0 ? String.valueOf((double) value) :
                String.valueOf(Double.parseDouble(lbResult.getText()) * 10 + value));
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {
        String symbol = ((Pane)event.getSource()).getId().replace("btn", "");
        if (symbol.equals("Equal")) {
            double num2 = Double.parseDouble(lbResult.getText());
            switch (operator) {
                case "+" -> lbResult.setText((num1 + num2) + "");
                case "-" -> lbResult.setText((num1 - num2) + "");
                case "*" -> lbResult.setText((num1 * num2) + "");
                case "/" -> lbResult.setText((num1 / num2) + "");
            }
            operator = ".";
        } else if (symbol.equals("Close")) {
            lbResult.setText(String.valueOf(0.0));
            operator = ".";
        } else {
            switch (symbol) {
                case "Plus" -> operator = "+";
                case "Minus" -> operator = "-";
                case "Times" -> operator = "*";
                case "Divide" -> operator = "/";
            }
            num1 = Double.parseDouble(lbResult.getText());
            lbResult.setText(String.valueOf(0.0));
        }
    }

}
