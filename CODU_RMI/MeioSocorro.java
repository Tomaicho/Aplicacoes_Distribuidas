package CODU_RMI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeioSocorro {

    private int id;
    private Local.Posto localOrigem;
    private boolean estado;  // true = livre; false = ocupado
    private ArrayList<Integer> eventosAtendidos;
    private int evento_atual;
    private ArrayList<Grau> grau;
    private int total_mobilizacoes;
    private int total_distancia;

    public MeioSocorro(int id, Local.Posto localOrigem, ArrayList<Grau> grau) {
        this.id = id;
        this.localOrigem = localOrigem;
        this.estado = true;
        this.grau = grau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Local.Posto getLocalOrigem() {
        return localOrigem;
    }

    public void setLocalOrigem(Local.Posto localOrigem) {
        this.localOrigem = localOrigem;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ArrayList<Integer> getEventosAtendidos() {
        return eventosAtendidos;
    }

    public void setEventosAtendidos(ArrayList<Integer> eventosAtendidos) {
        this.eventosAtendidos = eventosAtendidos;
    }

    public void addEventosAtendidos(int id){
        if(eventosAtendidos!=null){
            this.eventosAtendidos.add(id);
        } else {
            ArrayList<Integer> l = new ArrayList<>();
            l.add(id);
            this.eventosAtendidos = l;
        }
    }

    public int getEvento_atual() {
        return evento_atual;
    }

    public void setEvento_atual(int evento_atual) {
        this.evento_atual = evento_atual;
    }

    public ArrayList<Grau> getGrau() {
        return grau;
    }

    public void setGrau(ArrayList<Grau> grau) {
        this.grau = grau;
    }

    public int getTotal_mobilizacoes() {
        return total_mobilizacoes;
    }

    public void setTotal_mobilizacoes(int total_mobilizacoes) {
        this.total_mobilizacoes = total_mobilizacoes;
    }

    public void add_mobilizacao() {
        this.total_mobilizacoes += 1;
    }

    public int getTotal_distancia() {
        return total_distancia;
    }

    public void setTotal_distancia(int total_distancia) {
        this.total_distancia = total_distancia;
    }

    public void add_distancia(int distancia) {
        this.total_distancia += distancia;
    }

    public String toString() {
        return "MeioSocorro{" +
                "id=" + id +
                ", localOrigem=" + localOrigem +
                ", estado=" + estado +
                ", eventosAtendidos=" + eventosAtendidos +
                ", evento_atual=" + evento_atual +
                ", grau=" + grau +
                ", total_mobilizacoes=" + total_mobilizacoes +
                ", total_distancia=" + total_distancia +
                '}';
    }

    public static class Ambulancia extends MeioSocorro {
        private static final ArrayList<Grau> g = new ArrayList<>(Arrays.asList(Grau.SBV,Grau.SBV_U,Grau.SAV));

        public Ambulancia(int id, Local.Posto localOrigem) {
            super(id, localOrigem, g);
        }

        public String toString() {
            return "Ambulancia{" +
                    super.toString() +
                    "}";
        }
    }

    public static class Moto extends MeioSocorro {
        private static final ArrayList<Grau> g = new ArrayList<>(List.of(Grau.SBV_U));

        public Moto(int id, Local.Posto localOrigem) {
            super(id, localOrigem, g);
        }

        public String toString() {
            return "Moto{" +
                    super.toString() +
                    "}";
        }
    }

    public static class Veiculo extends MeioSocorro {
        private static final ArrayList<Grau> g = new ArrayList<>(List.of(Grau.SAV));

        public Veiculo(int id, Local.Posto localOrigem) {
            super(id, localOrigem, g);
        }

        public String toString() {
            return "Veiculo{" +
                    super.toString() +
                    "}";
        }
    }

    public static class Helicoptero extends MeioSocorro {
        private static final ArrayList<Grau> g = new ArrayList<>(List.of(Grau.SAV_U));

        public Helicoptero(int id, Local.Posto localOrigem) {
            super(id, localOrigem, g);
        }

        public String toString() {
            return "Helicoptero{" +
                    super.toString() +
                    "}";
        }
    }
}
