package exercice1;

import java.util.concurrent.Semaphore;

public class BalSimple {
    private String message;
    private Semaphore sDepot, sRetrait;

    public BalSimple() {
        // initialisation des sémaphores
        sDepot = new Semaphore(1);
        sRetrait = new Semaphore(1);

        try {
            sRetrait.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * - attendre que la BAL soit vide
     * - d\’eposer le message dans la BAL
     * - indiquer que la BAL est pleine
     */
    public void deposeRequete(String mess) {
        try {
            //attend que le dépot soit disponible
            sDepot.acquire();

            //dépot du message
            message = mess; //section critique
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //indique que la boiteAuLettre est pleine et libère le droit de lecture
        sRetrait.release();
    }

    /**
     * - attendre que la BAL soit pleine
     * - lire le message
     * - indiquer que la BAL est vide
     * - renvoyer le message
     */
    public String retireRequete(){
        String theMessage = "";

        try {
            //attend que le dépot soit remplit
            sRetrait.acquire();

            //lecture du message
            theMessage = message;
            message = "";

            sDepot.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return theMessage;
    }

    public boolean isDone() {
        return sDepot.hasQueuedThreads();
    }
}
