package fr.eni.papeterie.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private float montant;
    private List<Ligne> list = new ArrayList<Ligne>();

    public Panier() {
    }

    public float getMontant() {
        return montant;
    }

    public Ligne getLigne(int index) {
        return list.get(index);
    }

    public List<Ligne> getLignesPanier() {
        return list;
    }

    public void addLigne(Article article, int qte) {
        Ligne ligne = new Ligne(article, qte);
        list.add(ligne);
    }

    public void updateLigne(int index, int newQte) {
        list.get(index).setQte(newQte);
    }

    public void removeLigne(int index) {
        list.remove(index);
    }

    @Override
    public String toString() {
        int i = 0;
        String response = "";
        for (Ligne l : list) {
            response += "ligne " + i + " : " + l + "\n";
            i++;
        }
        return "Panier :\n\n" +
                response +
                "\nValeur du panier : " + montant
                ;
    }
}
