package fr.eni.papeterie.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

public class CatalogueModel extends AbstractTableModel{

	private CatalogueController controller;
	
	private String[] columns = new String[] {"Type", "Id", "Référence", 
												"Désignation", "Marque", "Prix Unitaire",
												"Qte stock", "Grammage", "Couleur"};
	
	private List<Article> catalogue = new ArrayList<>();
	
	private final static ImageIcon ICON_RAMETTE = new ImageIcon(CatalogueModel.class.getResource("images/paper.png"));
	
	private final static ImageIcon ICON_STYLO = new ImageIcon(CatalogueModel.class.getResource("images/pen.png"));
	
	public CatalogueModel(CatalogueController controller) {
		this.controller = controller;
		
		catalogue = controller.getCatalogue();
	}

	@Override
	public int getRowCount() {
		return catalogue.size();
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}


	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		//si première colonne -> ImageIcon.class, sinon les autres colonnes : String.class
		return columnIndex == 0 ? ImageIcon.class : String.class;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex >= 0 && rowIndex < catalogue.size()) {
			switch (columnIndex) {
			case 0:
				if(catalogue.get(rowIndex) instanceof Ramette) {
					return ICON_RAMETTE;
				} else {
					return ICON_STYLO;
				}
				
			case 1:
				return catalogue.get(rowIndex).getIdArticle();
			case 2:
				return catalogue.get(rowIndex).getReference();
			case 3:
				return catalogue.get(rowIndex).getDesignation();
			case 4:
				return catalogue.get(rowIndex).getMarque();
			case 5:
				return catalogue.get(rowIndex).getPrixUnitaire();
			case 6:
				return catalogue.get(rowIndex).getQteStock();
			case 7:
				if(catalogue.get(rowIndex) instanceof Ramette) {
					return ((Ramette)catalogue.get(rowIndex)).getGrammage();
				} else {
					return "-";
				}
			case 8:
				if(catalogue.get(rowIndex) instanceof Stylo) {
					return ((Stylo)catalogue.get(rowIndex)).getCouleur();
				} else {
					return "-";
				}
			}
		}
		return null;
	}
	
	public Article getValueAt(int rowIndex) {
		if(rowIndex >= 0 && rowIndex < catalogue.size()) {
			return catalogue.get(rowIndex);
		}
		return null;
	}

	public void updateRow(int index, Article a){
		catalogue.set(index, a);
	}
	public void deleteRow(int index){
		catalogue.remove(index);
	}
}
