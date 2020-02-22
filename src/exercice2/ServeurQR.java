package exercice2;

import java.util.Random;

public class ServeurQR extends Thread {
    private int id;
    private  BalQR boiteAuLettre;
    private static int cpt = 1;

    public ServeurQR(BalQR boiteAuLettre) {
        this.id = cpt;
        this.boiteAuLettre = boiteAuLettre;
        cpt++;
    }

    @Override
    public void run() {
        String messageRecu, prefix;
        Random random = new Random();

        prefix = "Serveur " + id + ", Réponse:";

        while (true) {
            messageRecu = boiteAuLettre.retireRequete();
            System.out.println(new StringBuilder().append("Serveur : ").append(id).append(" - message du client : ").append(messageRecu).toString());

            try {
                Thread.sleep(random.nextInt(200) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boiteAuLettre.deposeReponse(prefix + "Hi, i'm me");
        }
    }
}
