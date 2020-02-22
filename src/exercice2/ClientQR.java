package exercice2;

import java.util.Random;

public class ClientQR extends Thread {
    private String id;
    private  BalQR boiteAuLettre;


    public ClientQR(String aId, BalQR uneBoiteAuLettre) {
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

            System.out.println(boiteAuLettre.retireReponse());
        }
    }
}
