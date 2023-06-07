package fr.eni.papeterie.dal.jdbc;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoJdbcImpl implements ArticleDAO {
    final String SELECT_ALL = "SELECT * FROM Articles;";
    final String SELECT_BY_MARQUE = "SELECT * FROM Articles WHERE marque=?;";
    final String SELECT_BY_MOT_CLE = "SELECT * FROM Articles WHERE designation LIKE ?;";
    final String SELECT_BY_ID = "SELECT * FROM Articles WHERE idArticle=?;";
    final String INSERT = "INSERT INTO Articles(" +
            "reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
    final String UPDATE = "UPDATE Articles SET reference = ?, marque = ?, designation = ?, prixUnitaire = ?, " +
            "qteStock = ?, grammage = ?, couleur = ?, type = ? WHERE idArticle = ?";
    final String DELETE = "DELETE FROM Articles WHERE idArticle = ?";

    @Override
    public List<Article> selectAll() throws DALException {
        List<Article> articles = new ArrayList<>();


        try (Connection cnx = JdbcTools.getConnection()) {
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL);
            while (rs.next()) {
                Article a = getResult(rs);
                articles.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException((e.getMessage()));
        }

        return articles;
    }

    @Override
    public List<Article> selectByMarque(String marque) throws DALException {

        List<Article> articles = new ArrayList<>();

        try (Connection cnx = JdbcTools.getConnection()) {
            PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_MARQUE);
            pStmt.setString(1, marque);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                if (getResult(rs) != null) {
                    articles.add(getResult(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException((e.getMessage()));
        }
        return articles;
    }

    @Override
    public List<Article> selectByMotCle(String motCle) throws DALException {

        List<Article> articles = new ArrayList<>();

        try (Connection cnx = JdbcTools.getConnection()) {
            PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_MOT_CLE);
            pStmt.setString(1, '%' + motCle + '%');
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                if (getResult(rs) != null) {
                    articles.add(getResult(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException((e.getMessage()));
        }
        return articles;
    }

    @Override
    public Article selectById(int id) throws DALException {
        Article article = null;

        try (Connection cnx = JdbcTools.getConnection()) {
            PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_ID);
            pStmt.setInt(1, id);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                article = getResult(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException((e.getMessage()));
        }

        return article;
    }

    @Override
    public void insert(Article article) throws DALException {


        try (Connection cnx = JdbcTools.getConnection()) {
            PreparedStatement pStmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            setResult(article, pStmt);

            pStmt.executeUpdate();
            ResultSet rs = pStmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                article.setIdArticle(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException((e.getMessage()));
        }
    }

    @Override
    public void update(Article article) throws DALException {

        try (Connection cnx = JdbcTools.getConnection()) {
            PreparedStatement pStmt = cnx.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);
            setResult(article, pStmt);
            pStmt.setInt(9, article.getIdArticle());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException((e.getMessage()));
        }
    }

    @Override
    public void delete(int id) throws DALException {

        try (Connection cnx = JdbcTools.getConnection()) {
            PreparedStatement pStmt = cnx.prepareStatement(DELETE, PreparedStatement.RETURN_GENERATED_KEYS);
            pStmt.setInt(1, id);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException((e.getMessage()));
        }
    }

    private static void setResult(Article article, PreparedStatement pStmt) throws SQLException {
        pStmt.setString(1, article.getReference());
        pStmt.setString(2, article.getMarque());
        pStmt.setString(3, article.getDesignation());
        pStmt.setFloat(4, article.getPrixUnitaire());
        pStmt.setInt(5, article.getQteStock());

        if (article instanceof Stylo) {
            pStmt.setNull(6, Types.INTEGER);
            pStmt.setString(7, ((Stylo) article).getCouleur());
            pStmt.setString(8, "Stylo");
        }
        if (article instanceof Ramette) {
            pStmt.setInt(6, ((Ramette) article).getGrammage());
            pStmt.setNull(7, Types.INTEGER);
            pStmt.setString(8, "Ramette");
        }
    }

    private Article getResult(ResultSet rs) throws SQLException {
        Integer idArticle = rs.getInt("idArticle");
        String reference = rs.getString("reference");
        String marque = rs.getString("marque");
        String designation = rs.getString("designation");
        Float prixUnitaire = rs.getFloat("prixUnitaire");
        Integer qteStock = rs.getInt("qteStock");
        String type = rs.getString("type").trim();

        Article a = null;
        if (type.equals("Ramette")) {
            int grammage = rs.getInt("grammage");
            a = new Ramette(idArticle, marque, reference, designation, prixUnitaire, qteStock, grammage);
        }
        if (type.equals("Stylo")) {
            String couleur = rs.getString("couleur");
            a = new Stylo(idArticle, marque, reference, designation, prixUnitaire, qteStock, couleur);
        }
        return a;
    }
}
