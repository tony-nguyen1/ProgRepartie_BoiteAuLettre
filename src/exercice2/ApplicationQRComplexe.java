package exercice2;

public class ApplicationQRComplexe {

    public static void main (String[] args){
        BalQR maBoiteAuLettre;
        ClientQR client1, client2, client3;
        ServeurQR unServeur, deuxServeur;

        maBoiteAuLettre = new BalQR();
        client1 = new ClientQR("kiki", maBoiteAuLettre);
        client2 = new ClientQR("titi", maBoiteAuLettre);
        client3 = new ClientQR("bobo", maBoiteAuLettre);
        unServeur = new ServeurQR(maBoiteAuLettre);
        deuxServeur = new ServeurQR(maBoiteAuLettre);

        client1.start();
        client2.start();
        client3.start();
        unServeur.start();
        deuxServeur.start();
    }
}
