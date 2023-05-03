package CODU_RMI;

import java.util.Random;

public enum Grau {
    // SBV = Suporte Básico de Vida; SBV_U = SBV urgente; SAV = Suporte Avançado de Vida; SAV_U = SAV urgente;
    SBV(1), SBV_U(2), SAV(3), SAV_U(4);

    private int numVal;
    private static final Random r = new Random();

    Grau(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

    public static Grau randomGrau()  {
        Grau[] grau = values();
        return grau[r.nextInt(grau.length)];
    }

}
