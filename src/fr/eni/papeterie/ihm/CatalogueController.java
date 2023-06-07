package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;

import java.util.List;

public class CatalogueController {
    List<Article> catalogue;
    Integer index = 0;

    CatalogueManager manager = CatalogueManager.getInstance();

    public CatalogueController() throws BLLException {
        if (catalogue == null) {
            catalogue = manager.getCatalogue();
        }
    }

    public Article getFirstItem() throws BLLException {
        if (catalogue.size() != 0){
            return catalogue.get(0);
        }
        return null;
    }

    public Article getPrev() {
        if (index > 0) {
            index --;
            return catalogue.get(index);
        }
        return null;
    }

    public Article getNext() {
        if (index < catalogue.size() - 1) {
            index ++;
            return catalogue.get(index);
        }
        return null;
    }

    public void save(Article article) throws BLLException {
        manager.addArticle(article);
        catalogue.add(article);
        index = catalogue.size() - 1;
    }

    public Article delete() throws BLLException {
        Article article = null;
        manager.removeArticle(catalogue.get(index));
        catalogue.remove(article);
//        System.out.println(catalogue);
        catalogue = manager.getCatalogue();

        if (index < catalogue.size() && catalogue.size() != 0){
            article = catalogue.get(index);
        }else if (index >= catalogue.size() && catalogue.size() != 0){
            index--;
            article = catalogue.get(index);
        }
        return article;
    }

    public void update(Article article) throws BLLException {
        manager.updateArticle(article);
    }
}
