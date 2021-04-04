package hu.banattila.kontrollerek;

import hu.banattila.Main;
import hu.banattila.enumok.JatekNevek;
import hu.banattila.enumok.ReklamNevek;
import hu.banattila.modellek.reklamok.Reklamok;
import hu.banattila.modellek.reklamok.UjsagHirdetes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JatekKontroller {

    public JatekKontroller() {
    }

    ObservableList<String> uzenetek2 = FXCollections.observableArrayList(Main.getJatek().getUzenet());
    List<CheckBox> checkBoxes = new ArrayList<>();

    @FXML
    private Label nev;

    @FXML
    private Label penz;

    @FXML
    private Label nap;

    @FXML
    private Label uzenetek;

    @FXML
    private Label szorolapAr;

    @FXML
    private Label ujsaghirdetesAr;

    @FXML
    private Label oriasplakatAr;

    @FXML
    private Label tvReklamAr;

    @FXML
    private Label adSenseAr;

    private void setTvReklamAr(String ar){
        szorolapAr.setText(ar);
    }

    @FXML
    private Button napVege;

    @FXML
    private CheckBox szorolap;

    @FXML
    private CheckBox ujsaghirdetes;

    @FXML
    private CheckBox oriasplakat;

    @FXML
    private CheckBox tvReklam;

    @FXML
    private CheckBox adSense;

    @FXML
    private void initCheckBoxLista() {
        checkBoxes.add(szorolap);
        checkBoxes.add(ujsaghirdetes);
        checkBoxes.add(oriasplakat);
        checkBoxes.add(tvReklam);
        checkBoxes.add(adSense);
    }

    private void initReklamok(){
        ujsaghirdetes.setText(ReklamNevek.UJSAGHIRDETES.getNev());
        szorolap.setText(ReklamNevek.SZOROLAPOZAS.getNev());
        oriasplakat.setText(ReklamNevek.ORIASPLAKAT.getNev());
        tvReklam.setText(ReklamNevek.TVREKLAM.getNev());
        adSense.setText(ReklamNevek.ADSENSE.getNev());

    }

    private void setReklamok() {
        initReklamok();
        for (CheckBox chB : checkBoxes) {
            chB.setOnAction(event -> {
                Main.getJatek().jatekosReklamoz(chB.getText());
                if (Main.getJatek().getUzenet().get(Main.getJatek().getUzenet().size() - 1).contains("Sikeresen megrendelted")
                        && Main.getJatek().getUzenet().get(Main.getJatek().getUzenet().size() - 1).contains(chB.getText())) {
                    Main.getJatek().getJatekos().reklamoz(chB.getText());
                    penz.setText("Pénzed: " + Main.getJatek().getJatekos().getPenz());
                    chB.setDisable(true);
                    setUzik();
                }
            });
        }
    }

    private void reklamLejar() {
        for (CheckBox chB : checkBoxes) {
            List<Reklamok>lejartak = Main.getJatek().getJatekos().getReklamok().stream().filter(
                    it -> !it.isMegrendelve() && it.getNev().equals(chB.getText())
            ).collect(Collectors.toList());
            lejartak.stream().forEach(it -> {
                if (it.getNev().equals(chB.getText())){
                    chB.setDisable(false);
                }
            });
        }
    }

    private void reklamok() {
        initCheckBoxLista();
        setReklamok();
        reklamLejar();
    }

    private void setJatekok() {
        setJatekEvent(csonakazo, JatekNevek.CSONAKAZO.getNev());
        setJatekEvent(hullamvasut, JatekNevek.HULLAMVASUT.getNev());
        setJatekEvent(szellemvasut, JatekNevek.SZELLEMVASUT.getNev());
        setJatekEvent(vattacukros, JatekNevek.VATTACUKROS.getNev());
        setJatekEvent(korhinta, JatekNevek.KORHINTA.getNev());
    }

    private void setUzik() {
        String uzenet = "";
        for (String uzi : Main.getJatek().getUzenet()) {
            uzenet += uzi + "\n";
        }
        uzenetek.setText(uzenet);
    }

    private void setTexts() {
        nev.setText("Név " + Main.getJatek().getJatekos().getNev());
        penz.setText("Pénzed: " + Main.getJatek().getJatekos().getPenz());
        nap.setText(Main.getJatek().getElteltNapok() + ". nap");
        setUzik();
    }

    private void setNapVege() {
        napVege.setOnAction(event -> {
            Main.getJatek().napVege();
            if (Main.getJatek().getElteltNapok() <= 100 && Main.getJatek().getJatekos().getPenz() > 0) {
                setTexts();
            } else {
                try {
                    Main.setRoot("fxml/vidampark");
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            reklamLejar();
        });
    }

    @FXML
    private Button csonakazo;

    private void setJatekEvent(Button btn, String mit) {
        btn.setOnAction(actionEvent -> {
            Main.getJatek().jatekosFejleszt(mit);
            penz.setText("Pénzed: " + Main.getJatek().getJatekos().getPenz());
            setUzik();
        });
    }

    @FXML
    private Button hullamvasut;

    @FXML
    private Button szellemvasut;

    @FXML
    private Button korhinta;

    @FXML
    private Button vattacukros;

    @FXML
    private void initialize() {
        setTexts();
        setNapVege();
        setJatekok();
        reklamok();


    }
}
