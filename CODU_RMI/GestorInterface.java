package CODU_RMI;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface GestorInterface extends Remote {


    // --- Métodos para o Técnico --------------------------------------------------------------------------------------


    int criaTecnico(String nome)
            throws JaExisteException, IOException;

    List<Integer> procuraTecnico(String nome)
            throws RemoteException;

    void editDNTecnico(int id, LocalDate dn)
            throws NaoExisteException, IOException;

    String imprimeTecnico(int id)
            throws NaoExisteException, IOException;

    List<Integer> idTecnicos()
            throws  RemoteException;


    // --- Métodos para o Local / Posto --------------------------------------------------------------------------------


    Local localAletatorio()
            throws RemoteException;

    String criaPosto(String descricao, int x, int y)
            throws JaExisteException, IOException;

    String imprimePosto(String descricao)
            throws NaoExisteException, IOException;

    ArrayList<String> procuraPosto(String descricao)
            throws RemoteException;

    List<String> descricaoPostos()
            throws RemoteException;

    int distancia(Local l1, Local l2)
            throws RemoteException;


    // --- Métodos para o Meio de Socorro ------------------------------------------------------------------------------


    int criaAmbulancia(String descricao)
            throws JaExisteException, IOException, NaoExisteException;

    int criaMoto(String descricao)
            throws JaExisteException, IOException, NaoExisteException;

    int criaVeiculo(String descricao)
            throws JaExisteException, IOException, NaoExisteException;

    int criaHelicoptero(String descricao)
            throws JaExisteException, IOException, NaoExisteException;

    void alteraPosto(int id, String descricao)
            throws NaoExisteException, IOException;

    ArrayList<Integer> procuraMeioPosto(String descricao)
            throws RemoteException;

    ArrayList<Integer> procuraMeioTipo(String tipo)
            throws NaoExisteException, IOException;

    ArrayList<Integer> procuraMeioLivre()
            throws RemoteException;

    ArrayList<Integer> procuraMeioTipoLivre(String tipo)
            throws NaoExisteException, IOException;

    ArrayList<Integer> procuraMeioPostoTipo(String descricao, String tipo)
            throws NaoExisteException, IOException;

    ArrayList<Integer> procuraMeioPostoLivre(String descricao)
            throws NaoExisteException, RemoteException;

    ArrayList<Integer> procuraMeioLivrePostoTipo(String descricao, String tipo)
            throws NaoExisteException, RemoteException;

    String imprimeMeio(int id)
            throws NaoExisteException, IOException;

    List<Integer> idMeios()
            throws RemoteException;


    // --- Métodos para o Evento ---------------------------------------------------------------------------------------


    ArrayList<Integer> meioMaisProximo(int id, ArrayList<Integer> lista)
            throws RemoteException;

    HashMap<Integer,Integer> procuraMeiosDisponiveis(int id)
            throws NaoExisteException, IOException;

    void mobilizaMeios(int id_evento, HashMap<Integer,Integer> meiosproximos)
            throws RemoteException;

    void desmobilizaMeio(int id)
            throws RemoteException, NaoExisteException;

    int novoEventoAleatorio()
            throws RemoteException, NaoExisteException;

    int novoEvento(int tecnico, int x, int y, Grau grau)
            throws NaoExisteException, IOException;

    void adicionaInfoEvento(int id, String nome, int idade)
            throws NaoExisteException, IOException;

    String imprimeEvento(int id) throws NaoExisteException, RemoteException;

    List<Integer> idEventos()
            throws RemoteException;

}
