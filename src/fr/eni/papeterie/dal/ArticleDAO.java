package fr.eni.papeterie.dal;

import fr.eni.papeterie.bo.Article;

import java.util.List;

public interface ArticleDAO extends DAO<Article> {
    List<Article> selectByMarque(String marque) throws DALException;

    List<Article> selectByMotCle(String motCle) throws DALException;
}
