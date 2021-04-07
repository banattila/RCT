package hu.banattila.kontrollerek;

import hu.banattila.Main;
import hu.banattila.enumok.JatekNevek;
import hu.banattila.enumok.ReklamNevek;
import hu.banattila.jatek.Jatek;
import hu.banattila.kivetelek.MaxSzemelyzetSzam;
import hu.banattila.mentes.Mentesek;
import hu.banattila.modellek.jatekok.Jatekok;
import hu.banattila.modellek.reklamok.Reklamok;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class JatekKontroller {

    public JatekKontroller() {
    }

    List<Label> reklamok = new ArrayList<>();
    List<Label> karbantartok = new ArrayList<>();
    DecimalFormat df = new DecimalFormat("###,###,###");

    @FXML
    private Label nev;

    @FXML
    private Label penz;

    @FXML
    private Label nap;

    @FXML
    private Label uzenetek;

    @FXML
    private Pane reklamPanel;

    @FXML
    private Label reklamAr;

    @FXML
    private Label latogatoNoveles;

    @FXML
    private Label hatasfok;

    @FXML
    private Label megrendelve;


    @FXML
    private void initReklamokLista() {
        reklamok.add(szorolap);
        reklamok.add(ujsaghirdetes);
        reklamok.add(oriasplakat);
        reklamok.add(tvReklam);
        reklamok.add(adSense);
    }

    private void initReklamok() {
        ujsaghirdetes.setText(ReklamNevek.UJSAGHIRDETES.getNev());
        szorolap.setText(ReklamNevek.SZOROLAPOZAS.getNev());
        oriasplakat.setText(ReklamNevek.ORIASPLAKAT.getNev());
        tvReklam.setText(ReklamNevek.TVREKLAM.getNev());
        adSense.setText(ReklamNevek.ADSENSE.getNev());

    }

    private void setReklamok() {
        initReklamok();
        for (Label reklam : reklamok) {
            reklam.setOnMouseClicked(event -> {
                Main.getJatek().jatekosReklamoz(reklam.getText());
                if (Main.getJatek().getUzenet().get(Main.getJatek().getUzenet().size() - 1).contains("Sikeresen megrendelted")
                        && Main.getJatek().getUzenet().get(Main.getJatek().getUzenet().size() - 1).contains(reklam.getText())) {
                    Main.getJatek().getJatekos().reklamoz(reklam.getText());
                    penz.setText("Pénzed: " + df.format(Main.getJatek().getJatekos().getPenz()) + " fabatka");
                    reklam.setStyle("-fx-fill: red");
                    setUzik();
                }
            });

            reklam.setOnMouseEntered(event -> {
                Reklamok rm = Main.getJatek().getJatekos().getReklamok().stream()
                        .filter(it -> it.getNev().equals(reklam.getText())).findFirst().get();
                reklamAr.setText("Költsége: " + df.format(rm.getKoltseg()));
                latogatoNoveles.setText(rm.getUjLatogatokNaponta() + " fő");
                hatasfok.setText(String.format("%.2f", rm.getHatasfok()) + "%");
                megrendelve.setText((rm.isMegrendelve()) ? "Meg van rendelve" : "Nincs megrendelve");
                reklamPanel.setVisible(true);
            });
            reklam.setOnMouseExited(event -> reklamPanel.setVisible(false));
        }
    }

    private void reklamok() {
        initReklamokLista();
        setReklamok();
    }


    @FXML
    private Label latogatok;

    private void setLatogatok() {
        latogatok.setText("Napi látogatók: " + df.format(Jatek.getNapiLatogatok()) + " fő");
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
    private CheckBox konyvelo;

    private boolean vanKonyvelo(){
        return Main.getJatek().getJatekos().getKonyvelo() != null;
    }

    private void setKonyvelo(){
        if (vanKonyvelo()){
            konyvelo.setSelected(true);
            konyvelo.setText(Main.getJatek().getJatekos().getKonyvelo().getNev());
        }
    }

    private void konyveloSetup() {
        konyvelo.setOnAction(event -> {
            if (konyvelo.isSelected()) {
                Main.getJatek().getJatekos().konyvelotAlkalmaz();
                konyvelo.setText(Main.getJatek().getJatekos().getKonyvelo().getNev());

            } else if (!konyvelo.isSelected()) {
                konyvelo.setText("Nincs alkalmazásban");
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


    private void setKarbantartoList() {
        karbantartok.add(karbantarto1);
        karbantartok.add(karbantarto2);
        karbantartok.add(karbantarto3);
        karbantartok.add(karbantarto4);
        karbantartok.add(karbantarto5);
        var szam = new Object() {
            int szamlalo = 0;
        };
        Main.getJatek().getJatekos().getKarbantartok().stream()
                .filter(it -> it != null)
                .forEach(it -> {
                    karbantartok.get(szam.szamlalo).setText(it.getNev());
                    szam.szamlalo++;
                });
    }

    private void karbantartoAlkalmazasok() {
        setKarbantartoList();
        disableAlkalmaz();
    }

    @FXML
    private TextField kitAlkalmaz;

    @FXML
    private TextField kitRugKi;

    @FXML
    private Button kirug;


    private void karbantartotKirug() {
        kirug.setOnMouseClicked(event -> {
            for (Label karbantarto : karbantartok) {
                if (karbantarto.getText().equals(kitRugKi.getText())) {
                    Main.getJatek().getJatekos().karbantartotKirug(kitRugKi.getText());
                    karbantarto.setText("Nincs alkalmazásban");
                } else {
                    Jatek.addUzenet("Nincs " + kitRugKi.getText() + " nevű alkalmazottad.");
                    setUzik();
                }
            }
            kitRugKi.setText("");
        });
    }

    private void disableAlkalmaz() {
        alkalmaz.setOnAction(event -> {
            if (!kitAlkalmaz.getText().isEmpty()) {
                try {
                    Main.getJatek().getJatekos().karbantartotFelvesz(kitAlkalmaz.getText());
                } catch (MaxSzemelyzetSzam e) {
                    Jatek.addUzenet(e.getMessage());
                    setUzik();
                }
                for (int i = 0; i < karbantartok.size(); i++) {
                    if (Main.getJatek().getJatekos().getKarbantartok().size() > i) {
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
    private Pane panel;

    private void infoPanelNotVisible() {
        panel.setVisible(false);
        reklamPanel.setVisible(false);
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
        int counter = 0;
        for (int i = Main.getJatek().getUzenet().size() - 1; i >= 0; i--) {
            uzenet.append(Main.getJatek().getUzenet().get(i)).append("\n");
            counter++;
            if (counter == 5) {
                break;
            }
        }
        uzenetek.setText(uzenet.toString());
    }

    private void setTexts() {
        nev.setText("" + Main.getJatek().getJatekos().getNev());
        penz.setText("Pénzed: " + df.format(Main.getJatek().getJatekos().getPenz()) + " fabatka");
        nap.setText(Main.getJatek().getElteltNapok() + ". nap");
        setUzik();

    }

    @FXML
    private CheckBox autoSave;

    private void setNapVege() {
        napVege.setOnAction(event -> {
            Main.getJatek().napVege();
            System.out.println(Main.getJatek());
            if (Main.getJatek().getElteltNapok() <= 100 && Main.getJatek().getJatekos().getPenz() > 0) {
                setTexts();
            } else {
                try {
                    Main.setRoot("fxml/vidampark");
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            if (autoSave.isSelected()) {
                Mentesek.mentes(Main.getJatek());
            }
            setLatogatok();
        });
    }

    @FXML
    private Label jatekNev;

    @FXML
    private Label szint;

    @FXML
    private Label ktg;

    @FXML
    private Label bevetel;

    @FXML
    private Button csonakazo;

    private void setJatekEvent(Button btn, String mit) {
        btn.setOnAction(actionEvent -> {
            Main.getJatek().jatekosFejleszt(mit);
            penz.setText("Pénzed: " + df.format(Main.getJatek().getJatekos().getPenz()) + " fabatka");
            setUzik();
        });

        btn.setOnMouseEntered(event -> {
            panel.setVisible(true);
            Jatekok jatek = Main.getJatek().getJatekos().getJatekok()
                    .stream().
                            filter(it -> it.getId().equals(btn.getId())).findFirst().get();
            jatekNev.setText(jatek.getNev());
            szint.setText("Jelenlegi szint: " + jatek.getSzint());
            ktg.setText(df.format(jatek.getFejlesztesKoltseg()) + " fabatka");
            bevetel.setText("A napi bevételt noveli: " + df.format(jatek.getNyeresegLatogatonkent()) + " fabatka/fő");
        });

        btn.setOnMouseExited(event -> panel.setVisible(false));
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
        setKonyvelo();
        setJatekok();
        reklamok();
        setLatogatok();
        konyveloSetup();
        karbantartoAlkalmazasok();
        karbantartotKirug();
        infoPanelNotVisible();
    }
}
