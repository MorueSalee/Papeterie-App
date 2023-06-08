package fr.eni.papeterie.ihm;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import fr.eni.papeterie.bo.Article;

public class EcranCatalogue extends JFrame implements CatalogueController.CatalogueListener {

    private final CatalogueController controller;

    private JTable table;

    private CatalogueModel model;

    private JScrollPane panel;

    public EcranCatalogue(CatalogueController controller) {
        super("Papeterie - vue Catalogue");
        setSize(500, 500);
        setLocation(950, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        this.controller = controller;

        setContentPane(getPanel());
    }

    public JScrollPane getPanel() {
        if (panel == null) {
            panel = new JScrollPane();

            panel.setViewportView(getTable());
        }
        return panel;
    }

    public JTable getTable() {
        if (table == null) {
            model = new CatalogueModel(controller);
        }
        return getTable(model);
    }

    public JTable getTable(CatalogueModel model) {
        table = new JTable(model);
        table.setRowHeight(40);
        //setSelectionMode(...) -> permet de ne sélectionner qu'une seule ligne
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.getSelectionModel().addListSelectionListener(e -> {
            // !! Chaque clic génère 2 ListSelectionEvent, il ne faut en prendre qu'un seul !
            if (!e.getValueIsAdjusting()) {
                int ligneSelectionnee = table.getSelectedRow();
                if (model.getRowCount() > 0) {
                    controller.selectArticle(ligneSelectionnee);
                }
            }
        });
        if (model.getRowCount() > 0) {
            table.setRowSelectionInterval(0, 0);
        }
        return table;
    }

    public void selectRow(int index) {
        if (index > 0) {
            table.setRowSelectionInterval(index, index);
        }
    }

    @Override
    public void onArticleSelected(int index) {
        selectRow(index);
    }

    @Override
    public void onCatalogueSelected(Article a) {

    }

    @Override
    public void onArticleUpdated(int index) {
        panel.setViewportView(getTable(model));
        selectRow(index);
    }

    @Override
    public void onArticleUpdated(int index, Article a) {
        model.updateRow(index, a);
        panel.setViewportView(getTable(model));
        selectRow(index);
    }

    @Override
    public void onArticleDeleted(int index) {
        model.deleteRow(index);
        panel.setViewportView(getTable(model));
        if (model.getRowCount() > 0) {
            selectRow(index - 1);
        }
    }
}
