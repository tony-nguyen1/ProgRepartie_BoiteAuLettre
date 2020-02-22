package exercice2;

public class ApplicationQR {
    public static void main (String[] args){
        BalQR maBoiteAuLettre;
        ClientQR client1;
        ServeurQR unServeur;

        maBoiteAuLettre = new BalQR();
        client1 = new ClientQR("kiki", maBoiteAuLettre);
        unServeur = new ServeurQR(maBoiteAuLettre);

        client1.start();
        unServeur.start();
    }
}
