package exercice3;

import java.util.concurrent.Semaphore;

public class BalTable {

    /** désigne la case du tableau dans laquelle on placera la prochaine requête */
    private int indiceDepot;

    /** désigne la position dans le tableau où se trouve la requête la plus ancienne à traiter */
    private int indiceRetrait;

    /** Le nombre de lettre max qu'on mémorise */
    private int nb;

    /** Le tableau qui va stockée nos lettres */
    private String message [];

    /** Sémaphore */
    private Semaphore sDepot, sRetrait;

    public BalTable(int nbcases) {
        this.nb = nbcases;
        this.message = new String[this.nb]; // tableau de nb String
        this.indiceDepot = 0; // initialisation des indices
        this.indiceRetrait = 0;

        sDepot = new Semaphore(nb);
        sRetrait = new Semaphore(nb);

        sRetrait.drainPermits(); //acquire all the permits
    }

    public void deposeRequete(String mess) {
        try {
            sDepot.acquire();

            message[indiceDepot] = mess;
            indiceDepot = (indiceDepot + 1) % nb;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sRetrait.release();
    }

    public String retireRequete() {
        String theMessage = "";

        try {
            sRetrait.acquire();

            theMessage = message[indiceRetrait];
            message[indiceRetrait] = "";

            indiceRetrait = (indiceRetrait + 1) % nb;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sDepot.release();

        return theMessage;
    }
}
