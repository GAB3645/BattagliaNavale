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

        //visualizzaGriglia();

        if (x >= 0 && x < grandezzaGriglia && y >= 0 && y < grandezzaGriglia) {
            if (grigliaComputerLogica[x][y] == true) {
                grigliaComputerLogica[x][y] = false; // segno che la nave è stata colpita impostando quella casella della griglia su false
                return "COLPITO";
            } else {
                return "ACQUA";
            }
        } else {
            return "";
        }

        /*
         * if(!naveRimaste()) {
         * System.out.println("\nHai Vinto, non ci sono più navi");
         * }
         */
        // } while (naveRimaste()); //continuo a mettere coordinate finchè non ci sono più navi da colpire

    }


    private boolean naveRimaste() {
        // scorre tutta la griglia per trovare se ci sono ancora navi
        for (int i = 0; i < grandezzaGriglia; i++) {
            for (int j = 0; j < grandezzaGriglia; j++) {
                if (grigliaComputerLogica[i][j] == true) {
                    return true; // se trova una nave restituisce true
                }
            }
        }
        return false; // se non trova più navi restituisce false
    }
}




    /* 
    public void visualizzaGriglia() {

        // stampa i numeri delle colonne sopra la griglia
        System.out.print("  ");
        for (int k = 0; k < grandezzaGriglia; k++) {
            System.out.print(k + " ");
        }
        System.out.println(); // Va a capo per iniziare la stampa delle righe della griglia

        // stampa i numeri delle righe a sinistra
        for (int i = 0; i < grandezzaGriglia; i++) {
            System.out.print(i + " ");

            // stampa la griglia
            for (int j = 0; j < grandezzaGriglia; j++) {
                if (grigliaComputerLogica[i][j] == true) { // se la cella è occupata da una nave, stampa la N, sennò
                                                           // stampa il mare
                    System.out.print("N ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
    */
