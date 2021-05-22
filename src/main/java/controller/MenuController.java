package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MenuController {

    @FXML
    private TextField nameField;

    @FXML
    private Label errorLabel;

    private static String nev;

    public void initialize(){

        nameField.setText(nev);
    }

    public void kezdesKatt(MouseEvent mouseEvent) throws Exception {
        if (nameField.getText()==null || nameField.getText().equals("")) {
            errorLabel.setText("Kérlek add meg a játékos neved!");
        } else {
            nev=nameField.getText();
            log.info("A játékos név beállítva: {}, és kezdés gomb megnyomva.", nameField.getText());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<GameController>getController().initdata(nev);
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void eredmenyKatt(MouseEvent mouseEvent) throws Exception{
        log.info("Scoreboard button is clicked.");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/score.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
