package fr.eni.papeterie.bll;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAOFactory;

import java.util.List;

public class CatalogueManager {
    private static CatalogueManager instance;

    private CatalogueManager() {
    }

    public static CatalogueManager getInstance() throws BLLException {
        if (instance == null) {
            instance = new CatalogueManager();
        }
        return instance;
    }

    public List<Article> getCatalogue() throws BLLException {
        try {
            return DAOFactory.getArticleDAO().selectAll();
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException(e.getMessage());
        }

    }

    public void addArticle(Article a) throws BLLException {
        validerArticle(a);
        try {
            DAOFactory.getArticleDAO().insert(a);
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException(e.getMessage());
        }
    }

    public void updateArticle(Article a) throws BLLException {
        validerArticle(a);
        validerIndex(a.getIdArticle());
        try {
            DAOFactory.getArticleDAO().update(a);
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException(e.getMessage());
        }
    }

    public void removeArticle(Article a) throws BLLException {
        validerIndex(a.getIdArticle());
        try {
            DAOFactory.getArticleDAO().delete(a.getIdArticle());
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException(e.getMessage());
        }
    }

    public Article getArticle(int index) throws BLLException {
        validerIndex(index);
        try {
            return DAOFactory.getArticleDAO().selectById(index);
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException(e.getMessage());
        }
    }

    public void validerArticle(Article a) throws BLLException {
        validationReference(a.getReference());
        validationMarque(a.getMarque());
        validationDesignation(a.getDesignation());
        validationPrixUnitaire(a.getPrixUnitaire());
        validationQteStock(a.getQteStock());
        if (a instanceof Ramette) {
            validationGrammage(((Ramette) a).getGrammage());
        }
        if (a instanceof Stylo) {
            validationCouleur(((Stylo) a).getCouleur());
        }
    }

    private void validationCouleur(String couleur) throws BLLException {
        if (couleur == null || couleur.isBlank() || couleur.length() > 50) {
            throw new BLLException("La couleur est obligatoire et inférieure à 50 caractères.");
        }
    }

    public void validationReference(String reference) throws BLLException {
        if (reference == null || reference.isBlank() || reference.length() > 10) {
            throw new BLLException("La référence est obligatoire et inférieure à 10 caractères.");
        }
    }

    public void validationMarque(String marque) throws BLLException {
        if (marque == null || marque.isBlank() || marque.length() > 200) {
            throw new BLLException("La marque est obligatoire et inférieure à 200 caractères.");
        }
    }

    public void validationDesignation(String designation) throws BLLException {
        if (designation == null || designation.isBlank() || designation.length() > 250) {
            throw new BLLException("La désignation est obligatoire et inférieure à 250 caractères.");
        }
    }

    public void validationPrixUnitaire(float prixUnitaire) throws BLLException {
        if (prixUnitaire < 0) {
            throw new BLLException("Le prix unitaire doit être supérieur à zéro.");
        }
    }

    public void validationQteStock(int qteStock) throws BLLException {
        if (qteStock < 0) {
            throw new BLLException("La quantité en stock doit être positive.");
        }
    }

    public void validationGrammage(int grammage) throws BLLException {
        if (grammage <= 0) {
            throw new BLLException("Le grammage doit être positive.");
        }
    }

    private void validerIndex(Integer index) throws BLLException {
        if (index == null || index < 0) {
            throw new BLLException("L'index doit être positif.");
        }
    }
}
