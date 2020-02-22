package exercice2;

import java.util.concurrent.Semaphore;

public class BalQR {
    private String message;
    private Semaphore sDepotClient, sRetraitClient, sDepotServeur, sRetraitServeur;

    public BalQR() {
        // initialisation des sémaphores
        sDepotClient = new Semaphore(1);
        sRetraitClient = new Semaphore(1);
        sDepotServeur = new Semaphore(1);
        sRetraitServeur = new Semaphore(1);

        try {
            sRetraitClient.acquire();
            sDepotServeur.acquire();
            sRetraitServeur.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Le client dépose une lettre.
     *
     * - attendre que la bal soit dispo
     * - dépose le message dans la bal
     * - indiquer que la bal est pleine et doinne le droit de lire au client
     *
     * Sémaphore au début de la fonction
     * sDepotClient released
     * sRetraitClient acquired
     * sDepotServeur acquired
     * sRetraitServeur acquired
     */
    public void deposeRequete(String mess) {
        try {
            //attente du droit d'écriture du client
            sDepotClient.acquire();

            //dépot du message
            message = mess; //section critique
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //indique que la boiteAuLettre est pleine et libère le droit de lecture
        sRetraitClient.release();
    }

    /**
     * Le serveur lis un message du client.
     *
     * - attendre que la BAL soit pleine
     * - lire le message
     * indique que c'est au tour du serveur de déposer un message
     *
     * Sémaphore au début de la fonction
     * sDepotClient acquired
     * sRetraitClient released
     * sDepotServeur acquired
     * sRetraitServeur acquired
     */
    @SuppressWarnings("Duplicates")
    public String retireRequete(){
        String theMessage = "";

        try {
            //attend que le client dépose un message
            sRetraitClient.acquire();

            //lecture du message
            theMessage = message;
            message = "";

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //donne le droit au serveur de déposer qql chose
        sDepotServeur.release();

        return theMessage;
    }

    /**
     * Le serveur répond au message du client.
     *
     * attend la fin de la lecture du message du client par le serveur
     * dépose le message dans la bal
     * indique que le client peux lire la réponse du serveur
     *
     * Sémaphore au début de la fonction
     * sDepotClient acquired
     * sRetraitClient acquired
     * sDepotServeur released
     * sRetraitServeur acquired
     */
    public void deposeReponse(String mess) {
        try {
            //attend que le serveur lis le message
            sDepotServeur.acquire();

            //dépot du message
            message = mess;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //indique que la lecture de la réponse est disponible
        sRetraitServeur.release();
    }

    /**
     * Le client lis la réponse du serveur
     *
     * attend que le serveur lui réponde
     * lis la réponse
     * libère la bal
     *
     * Sémaphore au début de la fonction
     * sDepotClient acquired
     * sRetraitClient acquired
     * sDepotServeur acquired
     * sRetraitServeur released
     */
    public String retireReponse() {
        String theMessage = "";

        try {
            //attente de la réponse du serveur
            sRetraitServeur.acquire();

            //lecture de la réponse
            theMessage = message;
            message = "";

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //libère la boite au lettre
        sDepotClient.release();

        return theMessage;
    }
}
