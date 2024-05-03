package it.itismeucci.model;

public class Navi {

    private int corazzata1;
    private int sottomarino1;
    private int sottomarino2;
    private int sottomarino3;
    private int corvetta1;
    private int corvetta2;
    private int corvetta3;
    private int lancia1;
    private int lancia2;

    public Navi(int corazzata1, int sottomarino1, int sottomarino2, int sottomarino3, int corvetta1, int corvetta2,
            int corvetta3, int lancia1, int lancia2) {
        this.corazzata1 = corazzata1;
        this.sottomarino1 = sottomarino1;
        this.sottomarino2 = sottomarino2;
        this.sottomarino3 = sottomarino3;
        this.corvetta1 = corvetta1;
        this.corvetta2 = corvetta2;
        this.corvetta3 = corvetta3;
        this.lancia1 = lancia1;
        this.lancia2 = lancia2;
    }

    public int getCorazzata1() {
        return corazzata1;
    }

    public int getSottomarino1() {
        return sottomarino1;
    }

    public int getSottomarino2() {
        return sottomarino2;
    }

    public int getSottomarino3() {
        return sottomarino3;
    }

    public int getCorvetta1() {
        return corvetta1;
    }

    public int getCorvetta2() {
        return corvetta2;
    }

    public int getCorvetta3() {
        return corvetta3;
    }

    public int getLancia1() {
        return lancia1;
    }

    public int getLancia2() {
        return lancia2;
    }

    public int getNumeroNavi() {
        return 9;
    }

}
