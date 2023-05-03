package CODU_RMI;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class GestorCODU extends UnicastRemoteObject implements GestorInterface, Serializable {

    private Map<Integer, Tecnico> tecnicos;
    private Map<Integer, MeioSocorro> meios;
    private Map<Integer, Evento> eventos;
    private Map<String, Local.Posto> postos;
    private int idTecnico=1;
    private int idMeio=1;
    private int idEvento=1;

    public GestorCODU() throws RemoteException {
        super();
        this.tecnicos = new HashMap<>();
        this.meios = new HashMap<>();
        this.eventos = new HashMap<>();
        this.postos = new HashMap<>();
    }


    // --- Métodos para o Técnico --------------------------------------------------------------------------------------


    synchronized public int criaTecnico(String nome) throws RemoteException {
        int id=idTecnico;
        idTecnico += 1;
        Tecnico tecnico = new Tecnico(nome, id);
        this.tecnicos.put(id, tecnico);
        return id;
    }

    synchronized public List<Integer> procuraTecnico(String nome) {
        List<Integer> result = new ArrayList<>();
        for (int chave: this.tecnicos.keySet()) {
            Tecnico tecnico = this.tecnicos.get(chave);
            if (tecnico.getNome().contains(nome)) {
                result.add(chave);
            }
        }
        return result;
    }

    synchronized public void editDNTecnico(int id, LocalDate dn) throws NaoExisteException {
        if(! this.tecnicos.containsKey(id)){
            throw new NaoExisteException();
        }
        Tecnico tecnico = tecnicos.get(id);
        tecnico.setDataNascimento(dn);
    }

    synchronized public String imprimeTecnico(int id) throws NaoExisteException {
        if(! this.tecnicos.containsKey(id)){
            throw new NaoExisteException();
        }
        Tecnico tecnico = tecnicos.get(id);
        return tecnico.toString();
    }

    synchronized public List<Integer> idTecnicos() {
        return new ArrayList<>(tecnicos.keySet());
    }


    // --- Métodos para o Local / Posto --------------------------------------------------------------------------------


    public Local localAletatorio() {
        Random rand = new Random();
        int xbound = 1000;
        int ybound = 1000;
        int x = rand.nextInt(xbound);
        int y = rand.nextInt(ybound);
        Local local_aleatorio = new Local();
        local_aleatorio.setX(x);
        local_aleatorio.setY(y);
        return local_aleatorio;
    }

    synchronized public String criaPosto(String descricao, int x, int y) throws JaExisteException {
        if(postos.containsKey(descricao)){
            throw new JaExisteException();
        }
        Local.Posto posto = new Local.Posto(descricao, x, y);
        this.postos.put(descricao, posto);
        return descricao;
    }

    synchronized public String imprimePosto(String descricao) throws NaoExisteException {
        if(! this.postos.containsKey(descricao)){
            throw new NaoExisteException();
        }
        Local.Posto posto = postos.get(descricao);
        return posto.toString();
    }

    synchronized public ArrayList<String> procuraPosto(String descricao) throws RemoteException {
        ArrayList<String> resultado = new ArrayList<>();
        for(String chave: this.postos.keySet()){
            if(chave.contains(descricao)){
                resultado.add(chave);
            }
        }
        return resultado;
    }

    synchronized public List<String> descricaoPostos() {
        return new ArrayList<>(postos.keySet());
    }

    synchronized public int distancia(Local l1, Local l2){
        int dx = l1.getX() - l2.getX();
        int dy = l1.getY() - l2.getY();
        return (int) Math.round(Math.sqrt(dx*dx+dy*dy));
    }


    // --- Métodos para o Meio de Socorro ------------------------------------------------------------------------------


    synchronized public int criaAmbulancia(String descricao) throws RemoteException, NaoExisteException {
        if(! this.postos.containsKey(descricao)){
            throw new NaoExisteException();
        }
        Local.Posto localOrigem = this.postos.get(descricao);
        int id = idMeio;
        idMeio += 1;
        MeioSocorro.Ambulancia ambulancia = new MeioSocorro.Ambulancia(id, localOrigem);
        this.meios.put(id, ambulancia);
        return id;
    }

    synchronized public int criaMoto(String descricao) throws RemoteException, NaoExisteException {
        if(! this.postos.containsKey(descricao)){
            throw new NaoExisteException();
        }
        Local.Posto localOrigem = this.postos.get(descricao);
        int id = idMeio;
        idMeio += 1;
        MeioSocorro.Moto moto = new MeioSocorro.Moto(id, localOrigem);
        this.meios.put(id, moto);
        return id;
    }

    synchronized public int criaVeiculo(String descricao) throws RemoteException, NaoExisteException {
        if(! this.postos.containsKey(descricao)){
            throw new NaoExisteException();
        }
        Local.Posto localOrigem = this.postos.get(descricao);
        int id = idMeio;
        idMeio += 1;
        MeioSocorro.Veiculo veiculo = new MeioSocorro.Veiculo(id, localOrigem);
        this.meios.put(id, veiculo);
        return id;
    }

    synchronized public int criaHelicoptero(String descricao) throws RemoteException, NaoExisteException {
        if(! this.postos.containsKey(descricao)){
            throw new NaoExisteException();
        }
        Local.Posto localOrigem = this.postos.get(descricao);
        int id = idMeio;
        idMeio += 1;
        MeioSocorro.Helicoptero helicoptero = new MeioSocorro.Helicoptero(id, localOrigem);
        this.meios.put(id, helicoptero);
        return id;
    }

    synchronized public void alteraPosto(int id, String descricao) throws NaoExisteException {
        if(! this.postos.containsKey(descricao) || ! this.meios.containsKey(id)){
            throw new NaoExisteException();
        }
        Local.Posto localOrigem = this.postos.get(descricao);
        MeioSocorro meio = meios.get(id);
        meio.setLocalOrigem(localOrigem);
    }

    synchronized public ArrayList<Integer> procuraMeioPosto(String descricao) {
        ArrayList<Integer> resposta = new ArrayList<>();
        for(int chave: this.meios.keySet()) {
            MeioSocorro meio = this.meios.get(chave);
            if (meio.getLocalOrigem().getDescricao().contains(descricao)) {
                resposta.add(chave);
            }
        }
        return resposta;
    }

    synchronized public ArrayList<Integer> procuraMeioTipo(String tipo) throws NaoExisteException {
        ArrayList<Integer> resposta = new ArrayList<>();
        for(int chave: this.meios.keySet()) {
            MeioSocorro meio = this.meios.get(chave);
            if (Objects.equals(tipo, "ambulancia")) {
                if (MeioSocorro.Ambulancia.class.isAssignableFrom(meio.getClass())) {
                    resposta.add(chave);
                }
            } else if (Objects.equals(tipo, "moto")) {
                if (MeioSocorro.Moto.class.isAssignableFrom(meio.getClass())) {
                    resposta.add(chave);
                }
            } else if (Objects.equals(tipo, "veiculo")) {
                if (MeioSocorro.Veiculo.class.isAssignableFrom(meio.getClass())) {
                    resposta.add(chave);
                }
            } else if (Objects.equals(tipo, "helicoptero")) {
                if (MeioSocorro.Helicoptero.class.isAssignableFrom(meio.getClass())) {
                    resposta.add(chave);
                }
            } else {
                throw new NaoExisteException();
            }
        }
        return resposta;
    }

    synchronized public ArrayList<Integer> procuraMeioLivre() {
        ArrayList<Integer> resposta = new ArrayList<>();
        for(int id: meios.keySet()){
            if(this.meios.get(id).getEstado()){
                resposta.add(id);
            }
        }
        return resposta;
    }

    synchronized public ArrayList<Integer> procuraMeioTipoLivre(String tipo) throws NaoExisteException {
        ArrayList<Integer> resposta = new ArrayList<>();
        ArrayList<Integer> tipomeio = procuraMeioTipo(tipo);
        ArrayList<Integer> meiolivre = procuraMeioLivre();
        for(int id: meiolivre){
            if(tipomeio.contains(id)){
                resposta.add(id);
            }
        }
        return resposta;
    }

    synchronized public ArrayList<Integer> procuraMeioPostoTipo(String descricao, String tipo) throws NaoExisteException {
        ArrayList<Integer> resposta = new ArrayList<>();
        ArrayList<Integer> tipomeio = procuraMeioTipo(tipo);
        ArrayList<Integer> meioposto = procuraMeioPosto(descricao);
        for(int id: meioposto){
            if(tipomeio.contains(id)){
                resposta.add(id);
            }
        }
        return resposta;
    }

    synchronized public ArrayList<Integer> procuraMeioPostoLivre(String descricao) {
        ArrayList<Integer> resposta = new ArrayList<>();
        ArrayList<Integer> meiolivre = procuraMeioLivre();
        ArrayList<Integer> meioposto = procuraMeioPosto(descricao);
        for(int id: meiolivre){
            if(meioposto.contains(id)){
                resposta.add(id);
            }
        }
        return resposta;
    }

    synchronized public ArrayList<Integer> procuraMeioLivrePostoTipo(String descricao, String tipo) throws NaoExisteException {
        ArrayList<Integer> resposta = new ArrayList<>();
        ArrayList<Integer> tipomeio = procuraMeioTipo(tipo);
        ArrayList<Integer> meioposto = procuraMeioPosto(descricao);
        ArrayList<Integer> meiolivre = procuraMeioLivre();
        for(int id: meiolivre){
            if(tipomeio.contains(id) && meioposto.contains(id)){
                resposta.add(id);
            }
        }
        return resposta;
    }

    synchronized public String imprimeMeio(int id) throws NaoExisteException {
        if(! this.meios.containsKey(id)){
            throw new NaoExisteException();
        }
        MeioSocorro m = meios.get(id);
        return m.toString();
    }

    synchronized public List<Integer> idMeios() {
        return new ArrayList<>(meios.keySet());
    }


    // --- Métodos para o Evento ---------------------------------------------------------------------------------------


    synchronized public int novoEvento(int tecnico, int x, int y, Grau grau) throws  NaoExisteException {
        if(! this.tecnicos.containsKey(tecnico)){
            throw new NaoExisteException();
        }

        // calcula id do evento e adiciona-o aos atendimentos feitos pelo técnico
        int id_evento = this.idEvento;
        this.idEvento += 1;
        this.tecnicos.get(tecnico).addAtendimento(id_evento);

        // guarda o tempo de início num objeito da classe LocalDateTime
        LocalDateTime timestampAtiv = LocalDateTime.now();

        // guarda o local da ocorrência num objeto da classe Local
        Local localOcorrencia = new Local();
        localOcorrencia.setX(x);
        localOcorrencia.setY(y);

        // cria o evento com os objetos fornecidos (id do técnico e grau) e os acima criados
        Evento evento = new Evento(id_evento, timestampAtiv, tecnico, localOcorrencia, grau);
        this.eventos.put(id_evento, evento);

        // calcula os meios necessários disponíveis mais próximos do local de ocorrência
        // e a distância entre o posto dos meios e o local da ocorrência — HashMap<id meio, distancia>
        HashMap<Integer,Integer> meiosDisponiveisProximos = procuraMeiosDisponiveis(id_evento);

        // mobiliza os meios disponíveis mais próximos e atualiza as variáveis de estatísticas respetivas
        mobilizaMeios(id_evento, meiosDisponiveisProximos);

        return id_evento;
    }

    synchronized public int novoEventoAleatorio() throws NaoExisteException {
        // sorteia-se o técnico que responde ao evento
        Random r = new Random();
        int tecnico = this.tecnicos.keySet().stream().toList().get(r.nextInt(this.tecnicos.size()));

        // calcula-se um local de ocorrência aleatório
        Local localOcorrencia = localAletatorio();

        // sorteia-se um grau
        Grau grau = Grau.randomGrau();

        // a partir dos dados sorteados, cria-se um evento novo, a partir do método novoEvento
        return novoEvento(tecnico, localOcorrencia.getX(), localOcorrencia.getY(), grau);
    }

    synchronized public void adicionaInfoEvento(int id, String nome, int idade) throws NaoExisteException {
        if(! this.eventos.containsKey(id)){
            throw new NaoExisteException();
        }
        Evento e = this.eventos.get(id);
        e.setNomeDoente(nome);
        e.setIdadeDoente(idade);
    }

    synchronized public HashMap<Integer,Integer> procuraMeiosDisponiveis(int id) throws NaoExisteException {
        // HashMap<id meio, distância>
        HashMap<Integer,Integer> meios_distancia = new HashMap<>();

        // cria uma lista para cada tipo de meio disponível (livre)
        ArrayList<Integer> ambulancias = procuraMeioTipoLivre("ambulancia");
        ArrayList<Integer> motos = procuraMeioTipoLivre("moto");
        ArrayList<Integer> veiculos = procuraMeioTipoLivre("veiculo");
        ArrayList<Integer> helicopteros = procuraMeioTipoLivre("helicoptero");

        // dos meios livres, vai escolher o mais apropriado para o grau do evento, e caso não tenha disponíveis,
        // opta por opções menos ideais
        Evento evento = this.eventos.get(id);

        if(evento.getGrau().getNumVal()==1){ // caso o grau seja 1 (SBV)
            if(ambulancias.size()!=0){ // idealmente, envia uma ambulância
                ArrayList<Integer> a = meioMaisProximo(id, ambulancias); // procura a ambulância mais próxima e a distância
                meios_distancia.put(a.get(0), a.get(1)); // guarda a lista resultante do meioMaisProximo no HashMap meios_distancia
            } else if (helicopteros.size()!=0) { // se não tiver ambulâncias disponíveis, envia um helicóptero
                ArrayList<Integer> h = meioMaisProximo(id, helicopteros);
                meios_distancia.put(h.get(0), h.get(1));
            } else {
                throw new NaoExisteException();
            }

        } else if (evento.getGrau().getNumVal()==2) { // caso o grau seja 2 (SBV_U)
            if(ambulancias.size()!=0 && motos.size()!=0){ // idealmente, envia uma ambulância e uma moto
                ArrayList<Integer> a = meioMaisProximo(id, ambulancias);
                meios_distancia.put(a.get(0), a.get(1));
                ArrayList<Integer> mo = meioMaisProximo(id, motos);
                meios_distancia.put(mo.get(0), mo.get(1));
            } else if (ambulancias.size()!=0) { // se não há motos disponíveis, apenas envia uma ambulância
                ArrayList<Integer> a = meioMaisProximo(id, ambulancias);
                meios_distancia.put(a.get(0), a.get(1));
            } else if (helicopteros.size()!=0) { // caso não haja ambulância, envia só um helicóptero (mesmo que tenha motos)
                ArrayList<Integer> h = meioMaisProximo(id, helicopteros);
                meios_distancia.put(h.get(0), h.get(1));
            } else {
                throw new NaoExisteException();
            }

        } else if (evento.getGrau().getNumVal()==3) { // caso o grau seja 3 (SAV)
            if (ambulancias.size() != 0 && veiculos.size() != 0) { // idealmente envia uma ambulância e um veículo
                ArrayList<Integer> a = meioMaisProximo(id, ambulancias);
                meios_distancia.put(a.get(0), a.get(1));
                ArrayList<Integer> v = meioMaisProximo(id, veiculos);
                meios_distancia.put(v.get(0), v.get(1));
            } else if (helicopteros.size() != 0) { // se não tiver um dos dois (ambulâcia ou veículo), envia apenas um helicóptero
                ArrayList<Integer> h = meioMaisProximo(id, helicopteros);
                meios_distancia.put(h.get(0), h.get(1));
            } else if (ambulancias.size() != 0) { // se nao tiver helicópteros, mas tiver ambulâncias, envia uma ambulância
                ArrayList<Integer> a = meioMaisProximo(id, ambulancias);
                meios_distancia.put(a.get(0), a.get(1));
            } else {
                throw new NaoExisteException();
            }

        } else if (evento.getGrau().getNumVal()==4) { // caso o grau seja 4 (SAV_U)
            if (helicopteros.size() != 0) { // idealmente envia um helicóptero
                ArrayList<Integer> h = meioMaisProximo(id, helicopteros);
                meios_distancia.put(h.get(0), h.get(1));
            } else if (ambulancias.size() != 0 && veiculos.size() != 0) { // se não tiver, envia uma ambulância e um veículo
                ArrayList<Integer> a = meioMaisProximo(id, ambulancias);
                meios_distancia.put(a.get(0), a.get(1));
                ArrayList<Integer> v = meioMaisProximo(id, veiculos);
                meios_distancia.put(v.get(0), v.get(1));
            } else if (ambulancias.size() != 0) { // se não tiver veículo, envia apenas a ambulância
                ArrayList<Integer> a = meioMaisProximo(id, ambulancias);
                meios_distancia.put(a.get(0), a.get(1));
            } else {
                throw new NaoExisteException();
            }
        }
        return meios_distancia;
    }

    synchronized public ArrayList<Integer> meioMaisProximo(int id, ArrayList<Integer> lista){
        // cria uma lista para guardar o id do meio mais próximo (primeiro elemento)
        // e a sua distância (segundo elemento)
        ArrayList<Integer> lista_meio_distancia = new ArrayList<>();
        Evento evento = this.eventos.get(id);
        int meio_mais_proximo = 0; // id=0 não corresponde a nenhum evento, pois o id destes começam em 1
        int menor_distancia = Integer.MAX_VALUE; // MAX_VALUE para que qualquer valor de distância calculado seja menor
        for (int meio: lista){ // percorre todos os meios da lista fornecida
            int distancia = distancia(evento.getLocalOcorrencia(), this.meios.get(meio).getLocalOrigem());
            if (distancia < menor_distancia){ // compara a sua distância com o valor da menor_distancia
                menor_distancia=distancia; // caso a distância do meio a calcular seja menor que a menor_distancia,
                meio_mais_proximo=meio;    // guarda-se essa distância e o seu meio das variaveis menor_distancia e meio_mais_proximo
            }
        }
        lista_meio_distancia.add(meio_mais_proximo); // meio_mais_proximo como primeiro elemento
        lista_meio_distancia.add(menor_distancia); // distancia do meio_mais_proximo como segundo elemento
        return lista_meio_distancia;
    }

    synchronized public void mobilizaMeios(int id_evento, HashMap<Integer,Integer> meiosDisponiveisProximos) {
        for (int id: meiosDisponiveisProximos.keySet()) {      // para cada meio no HashMap
            MeioSocorro meio = this.meios.get(id);
            meio.setEstado(false);                             // estado = false
            meio.add_mobilizacao();                            // nº de mobilizações do meio += 1
            meio.getLocalOrigem().addTotal_mob();              // nº de meios mobilizados do posto +=1
            meio.setEvento_atual(id_evento);                   // atualiza o evento atual do meio
            meio.addEventosAtendidos(id_evento);               // atualiza a lista de eventos atendidos pelo meio
        }

        Evento evento = this.eventos.get(id_evento);
        evento.setMeios_mob(meiosDisponiveisProximos);  // atualiza o HashMap dos meios mobilizados para o evento

        ArrayList<String> meiosmob = new ArrayList<>();
        for (int id_m: meiosDisponiveisProximos.keySet()) {     // para cada meio mobilizado
            MeioSocorro meio = meios.get(id_m);
            if (MeioSocorro.Ambulancia.class.isAssignableFrom(meio.getClass())) {   // adiciona o seu tipo a uma lista
                meiosmob.add("ambulancia");
            } else if (MeioSocorro.Moto.class.isAssignableFrom(meio.getClass())) {
                meiosmob.add("moto");
            } else if (MeioSocorro.Veiculo.class.isAssignableFrom(meio.getClass())) {
                meiosmob.add("veiculo");
            } else {
                meiosmob.add("helicoptero");
            }
        }
        Tecnico t = tecnicos.get(evento.getTecnico());
        t.addTotalMeiosEnviados(meiosmob);   // e atualiza o HashMap<tipo de meio, nº de mobilizacoes += 1> do técnico
    }

    synchronized public void desmobilizaMeio(int id) throws NaoExisteException {
        MeioSocorro meio = this.meios.get(id);     // o meio indica que chegou de volta ao local de origem
        if (meio.getEvento_atual()==0) {
            throw new NaoExisteException();
        }

        Evento evento = eventos.get(meio.getEvento_atual());
        int distancia_total = 2*evento.getMeios_mob().get(id);
        meio.add_distancia(distancia_total); // atualiza a distancia total percorrida pelo meio

        meio.setEstado(true);       // estado = true, volta a estar livre
        meio.setEvento_atual(0);    // o meio fica sem evento atual

        if(MeioSocorro.Ambulancia.class.isAssignableFrom(meio.getClass()) || MeioSocorro.Helicoptero.class.isAssignableFrom(meio.getClass())) {
            evento.setTimestampConcl(LocalDateTime.now()); // caso o meio seja de transporte (ambulância ou helicóptero),
        }                                                  // a vítima chegou e o evento dá-se por concluido
    }

    synchronized public String imprimeEvento(int id) throws NaoExisteException{
        if(! this.eventos.containsKey(id)){
            throw new NaoExisteException();
        }
        Evento e = eventos.get(id);
        return e.toString();
    }

    synchronized public List<Integer> idEventos(){
        return new ArrayList<>(eventos.keySet());
    }

}
