package exercice1;

public class Application {
    public static void main (String[] args){
        BalSimple maBoiteAuLettre;
        Client client1, client2, client3;
        Serveur unServeur;

        maBoiteAuLettre = new BalSimple();
        client1 = new Client("kiki", maBoiteAuLettre);
        client2 = new Client("keke", maBoiteAuLettre);
        client3 = new Client("koko", maBoiteAuLettre);
        unServeur = new Serveur(maBoiteAuLettre);

        client1.start();
        client2.start();
        client3.start();
        unServeur.start();
    }
}
