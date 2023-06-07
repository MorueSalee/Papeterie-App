package fr.eni.papeterie;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.ihm.CatalogueController;
import fr.eni.papeterie.ihm.EcranArticle;
import fr.eni.papeterie.ihm.EcranCatalogue;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    CatalogueController controller = new CatalogueController();
                    EcranArticle ecranArticle = new EcranArticle(controller);
                    EcranCatalogue ecranCatalogue = new EcranCatalogue(controller);

                    controller.addListener(ecranArticle);
                    controller.addListener(ecranCatalogue);

                } catch (BLLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
