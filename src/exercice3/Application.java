package exercice3;

public class Application {
    public static void main (String[] args){
        BalTable maBoiteAuLettre;
        Client client1, client2, client3;
        Serveur unServeur;

        maBoiteAuLettre = new BalTable(20);
        client1 = new Client("kiki", maBoiteAuLettre);
        client2 = new Client("titi", maBoiteAuLettre);
        client3 = new Client("bobo", maBoiteAuLettre);
        unServeur = new Serveur(maBoiteAuLettre);

        client1.start();
        client2.start();
        client3.start();
        unServeur.start();
    }
}
