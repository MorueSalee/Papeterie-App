package fr.eni.papeterie;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.ihm.CatalogueController;
import fr.eni.papeterie.ihm.EcranPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    CatalogueController controller = new CatalogueController();
                    EcranPrincipal ecranPrincipal = new EcranPrincipal(controller);

                } catch (BLLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
