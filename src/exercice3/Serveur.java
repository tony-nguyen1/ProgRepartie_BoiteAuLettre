package exercice3;

import java.util.Random;

public class Serveur extends Thread {
    private int id;
    private BalTable boiteAuLettre;
    private static int cpt = 1;

    public Serveur(BalTable boiteAuLettre) {
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
            System.out.println(new StringBuilder().append("Serveur : ").append(id).append(" - message : ").append(messageRecu).toString());

            try {
                Thread.sleep(random.nextInt(200) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
