package controller;

import database.Eredmeny;
import database.EredmenyDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import state.GameState;

import java.io.IOException;
import java.time.LocalDateTime;

@Log4j2
public class GameController {

    private String nev;
    private GameState allas;
    private static int legjobblepes=0;
    EredmenyDao dao;
    boolean stop=false;

    @FXML
    private GridPane racs;

    @FXML
    private Label legjobb;

    @FXML
    private Label lepes;

    @FXML
    private Label vege;

    @FXML
    private Button eredmenytabla;

    @FXML
    private Label azon_22;

    @FXML
    private Label azon_21;

    @FXML
    private Label azon_12;

    @FXML
    public void initialize() {
        ujjatekKatt();
        try {
            Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
            jdbi.installPlugin(new SqlObjectPlugin());
            Handle handle = jdbi.open();
            dao = handle.attach(EredmenyDao.class);
            dao.ujtabla();
        } catch (Exception e){
            System.out.println("Adatbázis probléma!");
        }
    }

    public void initdata(String nev) {
        this.nev = nev;
    }

    public void rajzol(){
        lepes.setText("Lépésszám: " + allas.getlepes());
        Label cimke;
        String azon;
        int[][] allas = this.allas.getallas();
        if (!this.allas.win()){
            tisztit();
        }
        // Ez egy kis hack, hogy azonnal nyerjek.
        /*
        if (!stop) {
            this.allas.gyoztespoz();
            stop=true;
        }

         */
        if (!this.allas.win()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (allas[i][j] != 0) {
                        azon = "azon_" + i + j;
                        String fazon = azon;
                        cimke = (Label) racs.getChildren().stream()
                                .filter(e -> e.getId() != null)
                                .filter(e -> e.getId().equals(fazon))
                                .findFirst()
                                .get();
                        cimke.setText(String.valueOf(allas[i][j]));
                    }
                }
            }
        } else{
            azon_21.setText("8");
            azon_22.setText("");
            azon_12.setText("6");
            vege.setVisible(true);
            eredmenytabla.setVisible(true);
            alliteredmeny();
            log.info("Kiraktad, vége a játéknak.");
        }
    }

    public void frissit(KeyEvent keyEvent) {
        if (!allas.win()){
            KeyCode code = keyEvent.getCode();
            allas.mozgatas(code);
            rajzol();
            log.info("{} billentyű lenyomva. Lépésszám: {}",code, allas.getlepes());
        }
    }

    public void ujjatekKatt(){
        vege.setVisible(false);
        eredmenytabla.setVisible(false);
        log.info("Új játék elkezdődött.");
        legjobb.setText("Legjobb eredmény: " + legjobblepes);
        log.info("Legjobb eredmény beállítva.");
        allas = new GameState();
        rajzol();
    }

    public void fomenuKatt(MouseEvent mouseEvent) throws IOException {
        alliteredmeny();
        log.info("Főmenü gomb megnyomva.");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void eredmenyKatt(MouseEvent mouseEvent) throws IOException {
        log.info("Eredménytábla gomb megnyomva.");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/score.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void allitlegjobb(){
        if (legjobblepes> allas.getlepes() || legjobblepes==0){
            legjobblepes= allas.getlepes();
        }
    }

    private void tisztit() {
        for (Node label : racs.getChildren()) {
            ((Label) label).setText("");
        }
    }

    public void alliteredmeny() {

        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();

        String timenow = year + "-" +
                (10>month ? "0" + month : month) + "-" +
                (10>day ? "0" + day : day) + "     " +
                (10>hour ? "0" + hour : hour) + ":" +
                (10>minute ? "0" + minute : minute) + ":" +
                (10>second ? "0" + second : second);

        Eredmeny ered = Eredmeny.builder()
                .jatekos(nev)
                .win((allas.win()))
                .lepes(allas.getlepes())
                .date(timenow).build();

        Long idd = dao.beszur(ered);

        if (allas.win()){
            allitlegjobb();
        }
        log.info("Új sor a táblába felvéve: " + dao.keresid(idd));
    }
}
