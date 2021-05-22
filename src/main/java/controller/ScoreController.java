package controller;

import database.Eredmeny;
import database.EredmenyDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.Date;
import java.util.List;

@Log4j2
public class ScoreController {

    @FXML
    private TableView<Eredmeny> scoreTable;

    @FXML
    private TableColumn<Eredmeny, Long> id;

    @FXML
    private TableColumn<Eredmeny, String> jatekos;

    @FXML
    private TableColumn<Eredmeny, Boolean> win;

    @FXML
    private TableColumn<Eredmeny, Integer> lepes;

    @FXML
    private TableColumn<Eredmeny, Date> date;

    EredmenyDao dao;
    List<Eredmeny> pontok;

    @FXML
    public void initialize(){

        try {
            Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
            jdbi.installPlugin(new SqlObjectPlugin());
            Handle handle = jdbi.open();
            dao = handle.attach(EredmenyDao.class);
            dao.ujtabla();
        } catch (Exception e){
            System.out.println("Database problem!");
        }

        pontok = dao.list();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        jatekos.setCellValueFactory(new PropertyValueFactory<>("jatekos"));
        win.setCellValueFactory(new PropertyValueFactory<>("win"));
        lepes.setCellValueFactory(new PropertyValueFactory<>("lepes"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        ObservableList<Eredmeny> observableResult = FXCollections.observableArrayList();
        observableResult.addAll(pontok);
        scoreTable.setItems(observableResult);
        log.info("Játékosok kilistázása az eredménytáblára.");
        pontok.forEach(System.out::println);
    }

    public void fomenu(MouseEvent mouseEvent) throws Exception{
        log.info("Főmenü gomb megnyomva.");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
