package CODU_RMI;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Tecnico implements Serializable {


    private String nome;
    private int id;
    private LocalDate dataNascimento;
    private ArrayList<Integer> atendimentosEfetuados;
    private HashMap<String, Integer> totalMeiosEnviados; // <tipo de meio (almbulância, moto...), nº de mobilizações desse tipo>

    public Tecnico(String nome, int id) {
        this.nome = nome;
        this.id = id;
        HashMap<String, Integer> meios = new HashMap<>();
        meios.put("ambulancia", 0);
        meios.put("moto", 0);
        meios.put("veiculo", 0);
        meios.put("helicoptero", 0);
        this.totalMeiosEnviados = meios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<Integer> getAtendimentosEfetuados() {
        return atendimentosEfetuados;
    }

    public void setAtendimentosEfetuados(ArrayList<Integer> atendimentosEfetuados) {
        this.atendimentosEfetuados = atendimentosEfetuados;
    }

    public void addAtendimento(int id) {
        if(atendimentosEfetuados!=null){
            this.atendimentosEfetuados.add(id);
        } else {
            ArrayList<Integer> l = new ArrayList<>();
            l.add(id);
            this.atendimentosEfetuados = l;
        }
    }

    public HashMap<String, Integer> getTotalMeiosEnviados() {
        return totalMeiosEnviados;
    }

    public void setTotalMeiosEnviados(HashMap<String, Integer> totalMeiosEnviados) {
        this.totalMeiosEnviados = totalMeiosEnviados;
    }

    public void addTotalMeiosEnviados(ArrayList<String> meiosEnviados) {
        for (String meio: meiosEnviados) {
            if (Objects.equals(meio, "ambulancia")) {
                totalMeiosEnviados.put("ambulancia", totalMeiosEnviados.get("ambulancia")+1);
            } else if (Objects.equals(meio, "moto")) {
                 totalMeiosEnviados.put("moto", totalMeiosEnviados.get("moto")+1);
            } else if (Objects.equals(meio, "veiculo")) {
                totalMeiosEnviados.put("veiculo", totalMeiosEnviados.get("veiculo")+1);
            } else {
                totalMeiosEnviados.put("helicoptero", totalMeiosEnviados.get("helicoptero")+1);
            }
        }
    }

    public String toString() {
        return "Tecnico{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", dataNascimento=" + dataNascimento +
                ", atendimentosEfetuados=" + atendimentosEfetuados +
                ", totalMeiosEnviados=" + totalMeiosEnviados +
                '}';
    }
}
