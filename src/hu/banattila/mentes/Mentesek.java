package hu.banattila.mentes;

import hu.banattila.enumok.JatekSzintek;
import hu.banattila.jatek.Jatek;
import hu.banattila.kivetelek.MaximumJatekSzint;
import hu.banattila.modellek.emberek.Karbantarto;
import hu.banattila.modellek.jatekok.Jatekok;
import hu.banattila.modellek.reklamok.Reklamok;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Mentesek {

    private String jatekosNev;
    private String jatekSzint;

    public Mentesek(String jatekosNev, String jatekSzint){
        this.jatekosNev = jatekosNev;
        this.jatekSzint = jatekSzint;
    }

    public static String mentes(Jatek jatek){
        String eredmeny = "Sikertelen mentés!";
        BufferedWriter bw = null;

        try {
            String fileName = ".mentesek/" + jatek.getJatekos().getNev() + "-"
                    + jatek.getSzint().getName() + "_nap.txt";
            File f = new File(".mentesek");
            if (!f.exists() && !f.isDirectory()){
                f.mkdir();
            }
            bw = new BufferedWriter(new FileWriter(fileName));
            bw.write("Játéknap:" + jatek.getElteltNapok() + "\n");
            bw.write("Játékszint:" + jatek.getSzint().getName() + "\n");
            bw.write("NapiLlátogatők:" + Jatek.getNapiLatogatok() + "\n");
            bw.write("Játékos név:" + jatek.getJatekos().getNev() + "\n");
            bw.write("Játékos pénze:" + jatek.getJatekos().getPenz() + "\n");
            bw.write("Könyvelő:" + ((jatek.getJatekos().getKonyvelo() == null)?"nincs":"van") + "\n");
            StringBuilder sb = new StringBuilder();
            sb.append("Karbantartók:");
            int szamlalo = 0;
            for (Karbantarto karbantarto: jatek.getJatekos().getKarbantartok()){
                szamlalo++;
                sb.append(karbantarto.getNev());
                if (szamlalo < jatek.getJatekos().getKarbantartok().size()){
                    sb.append(",");
                }
            }
            sb.append("\n");
            bw.write(sb.toString());
            sb.delete(0, sb.length());
            szamlalo = 0;
            sb.append("Aktív reklámok:");
            int megrendeltek = 0;
            for (Reklamok reklam: jatek.getJatekos().getReklamok()){
                if (reklam.isMegrendelve()){
                    megrendeltek++;
                }
            }
            for (Reklamok reklam: jatek.getJatekos().getReklamok()){
                if (reklam.isMegrendelve()){
                    sb.append(reklam.getNev() + "," + reklam.getHanyadikNapja() + "," + reklam.getHatasfok() + "," + reklam.getUjLatogatokNaponta());
                    szamlalo++;

                    if (szamlalo < megrendeltek){
                        sb.append(";");
                    }
                }

            }
            sb.append("\n");
            bw.write(sb.toString());

            sb.delete(0, sb.length());
            sb.append("Játékok:");
            szamlalo = 0;
            for (Jatekok j: jatek.getJatekos().getJatekok()){
                sb.append(j.getId());
                sb.append(",");
                sb.append(j.getSzint());
                sb.append(",");
                sb.append(j.getNyeresegLatogatonkent());
                sb.append(",");
                sb.append(j.getFejlesztesKoltseg());

                if (szamlalo < jatek.getJatekos().getJatekok().size()){
                    sb.append(";");
                }
            }
            sb.append("\n");
            bw.write(sb.toString());


        } catch (IOException e){
            eredmeny = e.getMessage();
        } finally {
            if (bw != null){
                try {
                    bw.close();
                } catch (IOException e){
                    eredmeny = e.getMessage();
                }
            }
        }
        return eredmeny;
    }

    public static List<String> osszesMentes(){

        File file = new File(".mentesek");
        ArrayList<String> list = new ArrayList<>();

        if (file.isDirectory()){
            Arrays.stream(file.list()).forEach( it -> {
                list.add(it.substring(0, it.length() - 4));
            });
        }
        return list;
    }

    public static Jatek betoltes(String fileNev){
        Jatek jatek = null;
        BufferedReader br = null;
        String sor;
        int elteltnap = 0;
        String jatekosnev = "";
        long jatekosPenze = 0;
        String vanKonyvelo = "";
        JatekSzintek szint;
        int latogatok = 0;

        try {
            br = new BufferedReader(new FileReader(".mentesek/" + fileNev));
            elteltnap = Integer.parseInt(splitter(br));
            szint = szint(splitter(br));
            latogatok = Integer.parseInt(splitter(br));
            jatekosnev = splitter(br);
            jatek = new Jatek(jatekosnev, szint);
            jatek.setElteltNapok(elteltnap);
            Jatek.setNapiLatogatok(latogatok);
            jatekosPenze = Integer.parseInt(splitter(br));
            jatek.getJatekos().setPenz(jatekosPenze);

            vanKonyvelo = splitter(br);
            if (vanKonyvelo.equals("van")){
                jatek.getJatekos().setKonyvelo();
            }
            jatek.getJatekos().setKarbantartok(karbantartok(br));
            reklamok(br, jatek.getJatekos().getReklamok());
            try {
                setEpuletek(br, jatek.getJatekos().getJatekok());
            } catch (MaximumJatekSzint e){
                Jatek.addUzenet(e.getMessage());
            }
            System.out.println(jatek);

        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return jatek;
    }

    private static String splitter(BufferedReader br) throws IOException{
        return br.readLine().split(":")[1];
    }

    private static List<Karbantarto> karbantartok(BufferedReader br) throws IOException{
        List<Karbantarto> karbantartok = new ArrayList<>();
        String[] karbantartoSor = br.readLine().split(":");
        if (karbantartoSor.length > 1){
            String[] karbantartoAdatok = karbantartoSor[1].split(",");
            for (String karbantarto: karbantartoAdatok){
                karbantartok.add(new Karbantarto(karbantarto));
            }
        }

        return karbantartok;
    }

    private static void reklamok(BufferedReader br, Set<Reklamok> reklamok) throws IOException{
        String egeszSor = br.readLine();
        String[] sor = egeszSor.split(":");

        if (sor.length > 1){
            String egyReklam[] = sor[1].split(";");
            for (int i  = 0; i < egyReklam.length; i++){
                String[] rek = egyReklam[i].split(",");
                for (Reklamok reklam: reklamok){
                    if (reklam.getNev().equals(rek[0])){
                        reklam.setHanyadikNapja(Integer.parseInt(rek[1]));
                        reklam.setHatasfok(Double.parseDouble(rek[2]));
                        reklam.setUjLatogatokNaponta(Integer.parseInt(rek[3]));
                        reklam.setMegrendelve(true);
                    }
                }
            }
        }
    }

    private static void setEpuletek(BufferedReader br, Set<Jatekok> jatekok) throws IOException, MaximumJatekSzint {
        String egeszSor = br.readLine();
        String[] sor = egeszSor.split(":");
        if (sor.length > 1){
            String[] egyEpulet = sor[1].split(";");
            for (int index = 0; index < egyEpulet.length; index++){
                String[] epuletTulajdonsagok = egyEpulet[index].split(",");
                for (Jatekok jatek: jatekok){
                    if (jatek.getId().equals(epuletTulajdonsagok[0])){
                        jatek.setSzint(Integer.parseInt(epuletTulajdonsagok[1]));
                        jatek.setNyeresegLatogatonkent(Integer.parseInt(epuletTulajdonsagok[2]));
                        jatek.setFejlesztesKoltseg(Integer.parseInt(epuletTulajdonsagok[3]));

                    }
                }
            }
        }

    }

    private static JatekSzintek szint(String neve){
        JatekSzintek szint = null;
        switch (neve){
            case "Könnyű": szint = JatekSzintek.KONNYU ;break;
            case "Közepes": szint = JatekSzintek.KOZEPES ;break;
            case "Nehéz": szint = JatekSzintek.NEHEZ;break;
            default:
                System.err.println("Hiba történt a beolvasás közben.");;
        }
        return szint;
    }

    public String getJatekosNev() {
        return jatekosNev;
    }

    public String getJatekSzint() {
        return jatekSzint;
    }

    @Override
    public String toString(){
        return getJatekosNev() + "-" + getJatekSzint();
    }
}
