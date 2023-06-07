package fr.eni.papeterie.dal;

import fr.eni.papeterie.dal.jdbc.ArticleDaoJdbcImpl;

public class DAOFactory {
    public static ArticleDAO getArticleDAO() {
        return new ArticleDaoJdbcImpl();
    }
}
