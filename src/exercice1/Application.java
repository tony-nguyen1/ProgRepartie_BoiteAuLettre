package exercice1;

public class Application {
    public static void main (String[] args){
        BalSimple maBoiteAuLettre;
        Client client1;
        Serveur unServeur;

        maBoiteAuLettre = new BalSimple();
        client1 = new Client("kiki", maBoiteAuLettre);
        unServeur = new Serveur(maBoiteAuLettre);

        client1.start();
        unServeur.start();
    }
}
