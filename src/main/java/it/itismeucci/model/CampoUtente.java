package it.itismeucci.model;

import java.util.Random;

public class CampoUtente {

    private final int grandezzaGriglia = 10;
    private boolean[][] grigliaUtenteLogica;
    private Navi navi;

    public CampoUtente() {
        this.grigliaUtenteLogica = new boolean[grandezzaGriglia][grandezzaGriglia];
        this.navi = new Navi(4, 3, 3, 3, 2, 2, 2, 1, 1);
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
            grigliaUtenteLogica[x + i][y] = true;
        }
    }

    private boolean posizioneValida(int x, int y, int nave) {
        // controlla se la nave sta nella griglia
        if (x + nave > grandezzaGriglia || y >= grandezzaGriglia) {
            return false;
        }
        // controlla se ci sono altre navi
        for (int i = 0; i < nave; i++) {
            if (grigliaUtenteLogica[x + i][y]) {
                return false;
            }
        }
        return true;
    }

    public String sparare(int x, int y) {

        if (x >= 0 && x < grandezzaGriglia && y >= 0 && y < grandezzaGriglia) {
            if (grigliaUtenteLogica[x][y] == true) {
                grigliaUtenteLogica[x][y] = false; // segno che la nave è stata colpita impostando quella casella della
                                                   // griglia su false
                return "COLPITO";
            } else {
                return "ACQUA";
            }
        } else {
            return "";
        }
    }

}
