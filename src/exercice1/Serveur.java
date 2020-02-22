package exercice1;

import java.util.Random;

public class Serveur extends Thread {
    private int id;
    private BalSimple boiteAuLettre;
    private static int cpt = 1;

    public Serveur(BalSimple boiteAuLettre) {
        this.id = cpt;
        this.boiteAuLettre = boiteAuLettre;
        cpt++;
    }

    @Override
    public void run() {
        String messageRecu;
        Random random = new Random();
        boolean bool = true;

        while (bool) {
            messageRecu = boiteAuLettre.retireRequete();
            System.out.println(new StringBuilder().append("exercice1.Serveur : ").append(id).append(" - message : ").append(messageRecu).toString());

            try {
                Thread.sleep(random.nextInt(200) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
