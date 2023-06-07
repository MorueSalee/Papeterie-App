package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;

import java.util.ArrayList;
import java.util.List;

public class CatalogueController {
    List<Article> catalogue;
    Integer index = 0;

    CatalogueManager manager = CatalogueManager.getInstance();

    public interface CatalogueListener {
        void onArticleSelected(int index);
        void onCatalogueSelected(Article a);
        void onArticleUpdated(int index);
        void onArticleUpdated(int index, Article a);
        void onArticleDeleted(int index);
    }

    private List<CatalogueListener> catalogueListeners = new ArrayList<>();

    public void addListener(CatalogueListener l) {
        catalogueListeners.add(l);
    }

    public CatalogueController() throws BLLException {
        if (catalogue == null) {
            catalogue = manager.getCatalogue();
        }
    }

    public Article getFirstItem() throws BLLException {
        if (catalogue.size() != 0) {
            return catalogue.get(0);
        }
        return null;
    }

    public Article getPrev() {
        if (index > 0) {
            index--;
            for (CatalogueListener l : catalogueListeners) {
                l.onArticleSelected(index);
            }
            return catalogue.get(index);
        }
        return null;
    }

    public Article getNext() {
        if (index < catalogue.size() - 1) {
            index++;
            for (CatalogueListener l : catalogueListeners) {
                l.onArticleSelected(index);
            }
            return catalogue.get(index);
        }
        return null;
    }

    public void save(Article article) throws BLLException {
        manager.addArticle(article);
        catalogue.add(article);
        index = catalogue.size() - 1;
        for (CatalogueListener l : catalogueListeners) {
            l.onArticleUpdated(index);
        }
    }

    public Article delete() throws BLLException {
        Article article = null;
        manager.removeArticle(catalogue.get(index));
        catalogue.remove(article);
//        System.out.println(catalogue);
//        catalogue = manager.getCatalogue();
        for (CatalogueListener l : catalogueListeners) {
            l.onArticleDeleted(index);
        }
        if (index < catalogue.size() && catalogue.size() != 0) {
            article = catalogue.get(index);
        } else if (index >= catalogue.size() && catalogue.size() != 0) {
            index--;
            article = catalogue.get(index);
        }

        return article;
    }

    public void update(Article article) throws BLLException {
        manager.updateArticle(article);
        for (CatalogueListener l : catalogueListeners) {
            l.onArticleUpdated(index, article);
        }
    }

    public List<Article> getCatalogue() {
        return catalogue;
    }

    public Article getArticleFromId(int index){
        return catalogue.get(index);
    }

    public void selectArticle(int ligneSelectionnee) {
        index = ligneSelectionnee;
        for (CatalogueListener l : catalogueListeners) {
            l.onCatalogueSelected(getArticleFromId(index));
        }
    }
}
