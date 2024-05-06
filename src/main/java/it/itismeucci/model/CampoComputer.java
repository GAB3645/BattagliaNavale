package it.itismeucci.model;

import java.util.Random;

public class CampoComputer {

    private final int grandezzaGriglia = 10;
    private boolean[][] grigliaComputerLogica;
    private Navi navi;

    public CampoComputer(Navi navi) {
        this.grigliaComputerLogica = new boolean[grandezzaGriglia][grandezzaGriglia];
        this.navi = navi;
    }

    public void piazzaNaviCasuali() {
        generaPosizioniCasualiNavi(navi.getCorazzata1());
        generaPosizioniCasualiNavi(navi.getSottomarino1());
        generaPosizioniCasualiNavi(navi.getSottomarino2());
        generaPosizioniCasualiNavi(navi.getSottomarino3());
        generaPosizioniCasualiNavi(navi.getCorvetta1());
        generaPosizioniCasualiNavi(navi.getCorvetta2());
        generaPosizioniCasualiNavi(navi.getCorvetta3());
        generaPosizioniCasualiNavi(navi.getLancia1());
        generaPosizioniCasualiNavi(navi.getLancia2());
    }

    private void generaPosizioniCasualiNavi(int nave) {
        Random numRandom = new Random();
        int x, y;

        do {
            x = numRandom.nextInt(grandezzaGriglia);
            y = numRandom.nextInt(grandezzaGriglia);
        } while (!posizioneValida(x, y, nave));
        // finche la posizione non è valida non piazza la nave
        for (int i = 0; i < nave; i++) {
            grigliaComputerLogica[x + i][y] = true;
        }
    }

    private boolean posizioneValida(int x, int y, int nave) {
        // controlla se la nave sta nella griglia
        if (x + nave > grandezzaGriglia || y >= grandezzaGriglia) {
            return false;
        }

        // controlla se ci sono altre navi
        for (int i = 0; i < nave; i++) {
            if (grigliaComputerLogica[x + i][y]) {
                return false;
            }
        }
        return true;
    }


    public String sparare(int x, int y) {
        if (x >= 0 && x < grandezzaGriglia && y >= 0 && y < grandezzaGriglia) {
            if (grigliaComputerLogica[x][y]) {
                grigliaComputerLogica[x][y] = false;  // segno che la nave è stata colpita impostando quella casella della griglia su false
                if (!naveRimaste()) {
                    return "GIOCO FINITO"; 
                }
                return "COLPITO";
            } else {
                return "ACQUA";
            }
        } else {
            return "";
        }
    }


    private boolean naveRimaste() {
        for (int i = 0; i < grandezzaGriglia; i++) {
            for (int j = 0; j < grandezzaGriglia; j++) {
                if (grigliaComputerLogica[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

}


