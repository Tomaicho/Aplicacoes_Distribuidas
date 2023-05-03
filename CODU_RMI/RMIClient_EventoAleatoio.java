package CODU_RMI;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.time.LocalDate;

public class RMIClient_EventoAleatoio {
    public static void main(String[] args) {
        try {
            GestorInterface gi = (GestorInterface) Naming.lookup("rmi://localhost:50001/G");

            System.out.println();
            System.out.println("Criar Posto, Meios e Técnico");
            String desc_posto = gi.criaPosto("Hospital de Braga", 200, 100);
            String desc_posto2 = gi.criaPosto("Hospital do Porto", 100, 100);
            int id_amb = gi.criaAmbulancia(desc_posto);
            int id_mot = gi.criaMoto(desc_posto);
            int id_vei = gi.criaVeiculo(desc_posto);
            int id_heli = gi.criaHelicoptero(desc_posto);
            gi.alteraPosto(id_heli,desc_posto2);
            int id_tec = gi.criaTecnico("Hugo");
            LocalDate dn = LocalDate.of(2000,10, 2);
            gi.editDNTecnico(id_tec,dn);
            for (int tecnico: gi.idTecnicos()){
                System.out.println(gi.imprimeTecnico(tecnico));
            }
            for (String posto: gi.descricaoPostos()){
                System.out.println(gi.imprimePosto(posto));
            }
            for (int meio: gi.idMeios()){
                System.out.println(gi.imprimeMeio(meio));
            }
            for (int evento: gi.idEventos()){
                System.out.println(gi.imprimeEvento(evento));
            }

            System.out.println();
            System.out.println("Procuras");
            System.out.println("ID das ambulâncias livres: " + gi.procuraMeioTipoLivre("ambulancia"));
            System.out.println("ID dos meios livres no posto: " + gi.procuraMeioPostoLivre("Hospital"));
            System.out.println("ID dos helicóptros livres no posto: " + gi.procuraMeioLivrePostoTipo("Hospital", "helicoptero"));

            System.out.println();
            System.out.println("Cria Evento Aleatório");
            int id_ev = gi.novoEventoAleatorio();
            gi.adicionaInfoEvento(id_ev, "José Alves", 45);
            System.out.println(gi.imprimeEvento(id_ev));

            System.out.println();
            System.out.println("Ver Posto, Ambulancia e Técnico");
            for (int tecnico: gi.idTecnicos()){
                System.out.println(gi.imprimeTecnico(tecnico));
            }
            for (String posto: gi.descricaoPostos()){
                System.out.println(gi.imprimePosto(posto));
            }
            for (int meio: gi.idMeios()){
                System.out.println(gi.imprimeMeio(meio));
            }
            for (int evento: gi.idEventos()){
                System.out.println(gi.imprimeEvento(evento));
            }

            System.out.println();
            System.out.println("Procuras");
            System.out.println("ID das ambulâncias livres: " + gi.procuraMeioTipoLivre("ambulancia"));
            System.out.println("ID dos meios livres no posto: " + gi.procuraMeioPostoLivre("Hospital"));
            System.out.println("ID dos helicóptros livres no posto: " + gi.procuraMeioLivrePostoTipo("Hospital", "helicoptero"));

            System.out.println();
            System.out.println("Procura por técnicos com 'go' no nome" + gi.procuraTecnico("go"));
            System.out.println("Procura por postos com 'Hospital' na descrição" + gi.procuraPosto("Hospital"));
            System.out.println("Procura por ambulancias em postos com 'Hospital' na descrição" + gi.procuraMeioPostoTipo("Hospital", "ambulancia"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JaExisteException | NaoExisteException | NotBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
