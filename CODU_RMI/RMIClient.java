package CODU_RMI;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.time.LocalDate;

public class RMIClient {
    public static void main(String[] args) {
        try {
            GestorInterface gi = (GestorInterface) Naming.lookup("rmi://localhost:50001/G");

            System.out.println();
            System.out.println("Cria Postos, Meios e Técnicos");

            String desc_postoB = gi.criaPosto("Hospital de Braga", 200, 200);
            int id_amb1 = gi.criaAmbulancia(desc_postoB);
            int id_mot1 = gi.criaMoto(desc_postoB);
            int id_vei1 = gi.criaVeiculo(desc_postoB);
            int id_heli1 = gi.criaHelicoptero(desc_postoB);

            String desc_postoP = gi.criaPosto("Hospital do Porto", 100, 100);
            int amb2 = gi.criaAmbulancia(desc_postoP);
            int amb3 = gi.criaAmbulancia(desc_postoP);

            int id_tec1 = gi.criaTecnico("Hugo");
            LocalDate dn1 = LocalDate.of(2000,10, 2);
            gi.editDNTecnico(id_tec1,dn1);
            int id_tec2 = gi.criaTecnico("Tomás");
            LocalDate dn2 = LocalDate.of(2001,2,7);
            gi.editDNTecnico(id_tec2,dn2);

            for (String posto: gi.descricaoPostos()){
                System.out.println(gi.imprimePosto(posto));
            }
            for (int meio: gi.idMeios()){
                System.out.println(gi.imprimeMeio(meio));
            }
            for (int tecnico: gi.idTecnicos()){
                System.out.println(gi.imprimeTecnico(tecnico));
            }


            System.out.println();
            System.out.println("Cria Eventos");
            int id_ev1 = gi.novoEvento(id_tec1,300,250,Grau.SBV);
            int id_ev2 = gi.novoEvento(id_tec2,250,150,Grau.SBV_U);
            int id_ev3 = gi.novoEvento(id_tec1,50,150,Grau.SAV_U);
            int id_ev4 = gi.novoEvento(id_tec2,150,200,Grau.SAV_U);
            for (int evento: gi.idEventos()){
                System.out.println(gi.imprimeEvento(evento));
            }

            System.out.println();
            System.out.println("Ver Postos, Meios e Técnicos");
            for (String posto: gi.descricaoPostos()){
                System.out.println(gi.imprimePosto(posto));
            }
            for (int meio: gi.idMeios()){
                System.out.println(gi.imprimeMeio(meio));
            }
            for (int tecnico: gi.idTecnicos()){
                System.out.println(gi.imprimeTecnico(tecnico));
            }

            System.out.println();
            System.out.println("Desmobilizacao da Ambulância 1, Moto 1 e Ambulância 3");
            System.out.println("(2 segundos)");
            Thread.sleep(2000);
            gi.desmobilizaMeio(id_amb1);
            System.out.println("Ambulância 1 desmobilizada");
            System.out.println("(+3 segundos)");
            Thread.sleep(3000);
            gi.desmobilizaMeio(id_mot1);
            System.out.println("Moto 1 desmobilizada");
            gi.desmobilizaMeio(amb3);
            System.out.println("Ambulância 3 desmobilizada");

            System.out.println();
            System.out.println("Ver Postos, Meios, Técnicos e Eventos");
            for (String posto: gi.descricaoPostos()){
                System.out.println(gi.imprimePosto(posto));
            }
            for (int meio: gi.idMeios()){
                System.out.println(gi.imprimeMeio(meio));
            }
            for (int tecnico: gi.idTecnicos()){
                System.out.println(gi.imprimeTecnico(tecnico));
            }
            for (int evento: gi.idEventos()){
                System.out.println(gi.imprimeEvento(evento));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JaExisteException | NaoExisteException | NotBoundException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
