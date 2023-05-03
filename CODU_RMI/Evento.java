package CODU_RMI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Evento {
    private int id;
    private String nomeDoente;
    private int idadeDoente;
    private LocalDateTime timestampAtiv;
    private int tecnico;
    private Local localOcorrencia;
    private Grau grau;
    private HashMap<Integer, Integer> meios_mob; // id do Meio de Socorro, distância entre localOcorrencia e localOrigem
    private LocalDateTime timestampConcl;


    public Evento(int id, LocalDateTime timestampAtiv, int tecnico, Local localOcorrencia, Grau grau) {
        this.id = id;
        this.timestampAtiv = timestampAtiv;
        this.tecnico = tecnico;
        this.localOcorrencia = localOcorrencia;
        this.grau = grau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDoente() {
        return nomeDoente;
    }

    public void setNomeDoente(String nomeDoente) {
        this.nomeDoente = nomeDoente;
    }

    public int getIdadeDoente() {
        return idadeDoente;
    }

    public void setIdadeDoente(int idadeDoente) {
        this.idadeDoente = idadeDoente;
    }

    public LocalDateTime getTimestampAtiv() {
        return timestampAtiv;
    }

    public void setTimestampAtiv(LocalDateTime timestampAtiv) {
        this.timestampAtiv = timestampAtiv;
    }

    public Local getLocalOcorrencia() {
        return localOcorrencia;
    }

    public void setLocalOcorrencia(Local localOcorrencia) {
        this.localOcorrencia = localOcorrencia;
    }

    public int getTecnico() {
        return tecnico;
    }

    public void setTecnico(int tecnico) {
        this.tecnico = tecnico;
    }

    public HashMap<Integer, Integer> getMeios_mob() {
        return meios_mob;
    }

    public void setMeios_mob(HashMap<Integer, Integer> meios_mob) {
        this.meios_mob = meios_mob;
    }

    public LocalDateTime getTimestampConcl() {
        return timestampConcl;
    }

    public void setTimestampConcl(LocalDateTime timestampConcl) {
        this.timestampConcl = timestampConcl;
    }

    public Grau getGrau() {
        return grau;
    }

    public void setGrau(Grau grau) {
        this.grau = grau;
    }

    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        if (timestampConcl != null) {
            return "Evento{" +
                    "id=" + id +
                    ", nomeDoente='" + nomeDoente + '\'' +
                    ", idadeDoente=" + idadeDoente +
                    ", timestampAtiv=" + dtf.format(timestampAtiv) +
                    ", tecnico=" + tecnico +
                    ", localOcorrencia=" + localOcorrencia +
                    ", grau=" + grau +
                    ", meios_mob=" + meios_mob +
                    ", timestampConcl=" + dtf.format(timestampConcl) +
                    '}';
        } else {
            return "Evento{" +
                    "id=" + id +
                    ", nomeDoente='" + nomeDoente + '\'' +
                    ", idadeDoente=" + idadeDoente +
                    ", timestampAtiv=" + dtf.format(timestampAtiv) +
                    ", tecnico=" + tecnico +
                    ", localOcorrencia=" + localOcorrencia +
                    ", grau=" + grau +
                    ", meios_mob=" + meios_mob +
                    ", timestampConcl= (still active)" +
                    '}';
        }

    }
}