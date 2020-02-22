package exercice3;

import java.util.Random;

public class Client extends Thread {
    private String id;
    private BalTable boiteAuLettre;


    public Client(String aId, BalTable uneBoiteAuLettre) {
        id = aId;
        boiteAuLettre = uneBoiteAuLettre;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            boiteAuLettre.deposeRequete(id);

            try {
                Thread.sleep(random.nextInt(100) + 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
