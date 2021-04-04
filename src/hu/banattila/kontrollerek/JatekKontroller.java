package hu.banattila.kontrollerek;

import hu.banattila.Main;
import hu.banattila.enumok.JatekNevek;
import hu.banattila.enumok.ReklamNevek;
import hu.banattila.jatek.Jatek;
import hu.banattila.kivetelek.MaxSzemelyzetSzam;
import hu.banattila.modellek.reklamok.Reklamok;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JatekKontroller {

    public JatekKontroller() {
    }

    List<Label> reklamok = new ArrayList<>();
    List<Label> karbantartok = new ArrayList<>();

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

    private String reklamAra(String melyike){
        Reklamok reklam = Main.getJatek().getJatekos().getReklamok().stream().filter(
                it -> it.getNev().equals(melyike)
        ).findFirst().get();
        return reklam.getKoltseg() + "";
    }

    private void setReklamokAra(){
        szorolapAr.setText(reklamAra(ReklamNevek.SZOROLAPOZAS.getNev()));
        ujsaghirdetesAr.setText(reklamAra(ReklamNevek.UJSAGHIRDETES.getNev()));
        oriasplakatAr.setText(reklamAra(ReklamNevek.ORIASPLAKAT.getNev()));
        tvReklamAr.setText(reklamAra(ReklamNevek.TVREKLAM.getNev()));
        adSenseAr.setText(reklamAra(ReklamNevek.ADSENSE.getNev()));
    }

    @FXML
    private Label latogatok;

    private void setLatogatok(){
        latogatok.setText("Napi látogatók: " + Jatek.getNapiLatogatok());
    }

    @FXML
    private Button napVege;

    @FXML
    private Label szorolap;

    @FXML
    private Label ujsaghirdetes;

    @FXML
    private Label oriasplakat;

    @FXML
    private Label tvReklam;

    @FXML
    private Label adSense;

    @FXML
    private CheckBox lali;

    private void setKonyvelo(){
        lali.setOnAction(event -> {
            if (lali.isSelected()){
                Main.getJatek().getJatekos().konyvelotAlkalmaz();
                lali.setText(Main.getJatek().getJatekos().getKonyvelo().getNev());

            } else if(!lali.isSelected()){
                lali.setText("Nincs alkalmazásban");
                Main.getJatek().getJatekos().konyvelotKirug();
            }
            setUzik();
        });
    }

    @FXML
    private Label karbantarto1;

    @FXML
    private Label karbantarto2;

    @FXML
    private Label karbantarto3;

    @FXML
    private Label karbantarto4;

    @FXML
    private Label karbantarto5;

    @FXML
    private Label kirug1;

    @FXML
    private Label kirug2;

    @FXML
    private Label kirug3;

    @FXML
    private Label kirug4;

    @FXML
    private Label kirug5;


    private void setKarbantartoList(){
        karbantartok.add(karbantarto1);
        karbantartok.add(karbantarto2);
        karbantartok.add(karbantarto3);
        karbantartok.add(karbantarto4);
        karbantartok.add(karbantarto5);
    }

    private void karbantartoAlkalmazasok(){
        setKarbantartoList();
        disableAlkalmaz();
    }

    @FXML
    private TextField kitAlkalmaz;

    private void disableAlkalmaz(){
        alkalmaz.setOnAction( event -> {
            if (!kitAlkalmaz.getText().isEmpty()){
                try {
                    Main.getJatek().getJatekos().karbantartotFelvesz(kitAlkalmaz.getText());
                } catch (MaxSzemelyzetSzam e) {
                    Jatek.addUzenet(e.getMessage());
                    setUzik();
                }
                for (int i = 0; i < karbantartok.size(); i++){
                    if (Main.getJatek().getJatekos().getKarbantartok().size() > i){
                        karbantartok.get(i).setText(Main.getJatek().getJatekos().getKarbantartok().get(i).getNev());
                    } else {
                        karbantartok.get(i).setText("Nincs alkalmazásban");
                    }
                }
            } else {
                Jatek.addUzenet("Írd be hogy kit alkalmazol");
                setUzik();
            }
            kitAlkalmaz.setText("");
        });
    }

    @FXML
    private Button alkalmaz;

    @FXML
    private void initCheckBoxLista() {
        reklamok.add(szorolap);
        reklamok.add(ujsaghirdetes);
        reklamok.add(oriasplakat);
        reklamok.add(tvReklam);
        reklamok.add(adSense);
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
        for (Label chB : reklamok) {
            chB.setOnMouseClicked( event ->{
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
        for (Label chB : reklamok) {
            List<Reklamok>lejartak = Main.getJatek().getJatekos().getReklamok().stream().filter(
                    it -> !it.isMegrendelve() && it.getNev().equals(chB.getText())
            ).collect(Collectors.toList());
            lejartak.forEach(it -> {
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
        StringBuilder uzenet = new StringBuilder();
        for (String uzi : Main.getJatek().getUzenet()) {
            uzenet.append(uzi).append("\n");
        }
        uzenetek.setText(uzenet.toString());
    }

    private void setTexts() {
        nev.setText("" + Main.getJatek().getJatekos().getNev());
        penz.setText("Pénzed: " + Main.getJatek().getJatekos().getPenz());
        nap.setText(Main.getJatek().getElteltNapok() + ". nap");
        setUzik();
        setReklamokAra();
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
            setLatogatok();
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
        setLatogatok();
        setKonyvelo();
        karbantartoAlkalmazasok();
    }
}
