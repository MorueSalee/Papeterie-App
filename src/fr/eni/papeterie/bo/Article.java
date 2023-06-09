package fr.eni.papeterie.bo;

public class Article {
    private Integer idArticle;
    private String marque;
    private String reference;
    private String designation;
    private float prixUnitaire;
    private int qteStock;

    public Article(Integer idArticle, String marque, String reference, String designation, float prixUnitaire, int qteStock) {
        this.idArticle = idArticle;
        this.marque = marque;
        this.reference = reference;
        this.designation = designation;
        this.prixUnitaire = prixUnitaire;
        this.qteStock = qteStock;
    }

    public Article(String marque, String reference, String designation, float prixUnitaire, int qteStock) {
        this.marque = marque;
        this.reference = reference;
        this.designation = designation;
        this.prixUnitaire = prixUnitaire;
        this.qteStock = qteStock;
    }
    public Article()
    {

    }

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getQteStock() {
        return qteStock;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }

//    @Override
    public String toString() {
        return "Article{" +
                "idArticle=" + idArticle +
                ", marque='" + marque + '\'' +
                ", reference='" + reference + '\'' +
                ", designation='" + designation + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", qteStock=" + qteStock +
                '}';
    }
}
