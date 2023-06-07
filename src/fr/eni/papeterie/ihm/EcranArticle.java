package fr.eni.papeterie.ihm;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

import javax.swing.*;
import java.awt.*;

public class EcranArticle extends JFrame implements CatalogueController.CatalogueListener {
    private JSplitPane splitPane;

    private JPanel panelChamps;
    private JPanel panelBoutons;

    private JLabel lblReference;
    private JTextField txtReference;

    private JLabel lblDesignation;
    private JTextField txtDesignation;

    private JLabel lblMarque;
    private JTextField txtMarque;

    private JLabel lblStock;
    private JTextField txtStock;

    private JLabel lblPrix;
    private JTextField txtPrix;

    private JLabel lblType;
    private JRadioButton radioBtnType1;
    private JRadioButton radioBtnType2;

    private JLabel lblGrammage;
    private JCheckBox checkBtnGrammage1;
    private JCheckBox checkBtnGrammage2;

    private JLabel lblCouleur;
    private JComboBox<String> comboBoxCouleur;

    private JButton btnPrev;
    private JButton btnNew;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnNext;

    private CatalogueController controller;

    private Article currentArticle = null;

    public EcranArticle(CatalogueController controller) throws BLLException {
        super("Papeterie DunderMiflin & Co");
        setSize(500, 500);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        add(getPanelChamps());
        add(getPanelBoutons());
        pack();

        this.controller = controller;
        setChamps(controller.getFirstItem());
    }

    public JPanel getPanelChamps() {
        if (panelChamps == null) {
            panelChamps = new JPanel(new GridBagLayout());

            addComponentToPanel(getLblReference(), panelChamps, 0, 0);
            addComponentToPanel(getTxtReference(), panelChamps, 0, 1);

            addComponentToPanel(getLblDesignation(), panelChamps, 1, 0);
            addComponentToPanel(getTxtDesignation(), panelChamps, 1, 1);

            addComponentToPanel(getLblMarque(), panelChamps, 2, 0);
            addComponentToPanel(getTxtMarque(), panelChamps, 2, 1);

            addComponentToPanel(getLblStock(), panelChamps, 3, 0);
            addComponentToPanel(getTxtStock(), panelChamps, 3, 1);

            addComponentToPanel(getLblPrix(), panelChamps, 4, 0);
            addComponentToPanel(getTxtPrix(), panelChamps, 4, 1);

            addComponentToPanel(getLblType(), panelChamps, 5, 0, 1, 2);
            addComponentToPanel(getRadioBtnType1(), panelChamps, 5, 1);
            addComponentToPanel(getRadioBtnType2(), panelChamps, 6, 1);

            ButtonGroup groupRadio = new ButtonGroup();
            groupRadio.add(radioBtnType1);
            groupRadio.add(radioBtnType2);

            addComponentToPanel(getLblGrammage(), panelChamps, 7, 0, 1, 2);
            addComponentToPanel(getCheckBtnGrammage1(), panelChamps, 7, 1);
            addComponentToPanel(getCheckBtnGrammage2(), panelChamps, 8, 1);

            ButtonGroup groupCheck = new ButtonGroup();
            groupCheck.add(checkBtnGrammage1);
            groupCheck.add(checkBtnGrammage2);

            addComponentToPanel(getLblCouleur(), panelChamps, 9, 0);
            addComponentToPanel(getComboBoxCouleur(), panelChamps, 9, 1);

            initRadioAndCheck(true);
        }
        return panelChamps;
    }

    public JPanel getPanelBoutons() {
        if (panelBoutons == null) {
            panelBoutons = new JPanel(new GridBagLayout());

            addComponentToPanel(getBtnPrev(), panelBoutons, 0, 0);
            addComponentToPanel(getBtnNew(), panelBoutons, 0, 1);
            addComponentToPanel(getBtnSave(), panelBoutons, 0, 2);
            addComponentToPanel(getBtnDelete(), panelBoutons, 0, 3);
            addComponentToPanel(getBtnNext(), panelBoutons, 0, 4);
        }
        return panelBoutons;
    }

    private void addComponentToPanel(JComponent component, JPanel panel, int ligne, int colonne) {
        addComponentToPanel(component, panel, ligne, colonne, 1, 1);
    }

    /**
     * Cette fonction ajoute un composant dans un panel à la position ligne, colonne.
     * Le composant prend le nombre de colonne indiqué dans la variable colspan.
     *
     * @param component
     * @param panel
     * @param ligne
     * @param colonne
     * @param colSpan
     */
    private void addComponentToPanel(JComponent component, JPanel panel, int ligne, int colonne, int colSpan,
                                     int ligneSpan) {
        //GridBagConstraints -> contraintes permettant de placer un composant dans le GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = ligne;
        gbc.gridx = colonne;
        gbc.gridwidth = colSpan;
        gbc.gridheight = ligneSpan;
        panel.add(component, gbc);
    }

    public JLabel getLblReference() {
        if (lblReference == null) {
            lblReference = new JLabel("Référence :");
        }
        return lblReference;
    }

    public JTextField getTxtReference() {
        if (txtReference == null) {
            txtReference = new JTextField(20);
        }
        return txtReference;
    }

    public JLabel getLblDesignation() {
        if (lblDesignation == null) {
            lblDesignation = new JLabel("Désignation :");
        }
        return lblDesignation;
    }

    public JTextField getTxtDesignation() {
        if (txtDesignation == null) {
            txtDesignation = new JTextField(20);
        }
        return txtDesignation;
    }

    public JLabel getLblMarque() {
        if (lblMarque == null) {
            lblMarque = new JLabel("Marque :");
        }
        return lblMarque;
    }

    public JTextField getTxtMarque() {
        if (txtMarque == null) {
            txtMarque = new JTextField(20);
        }
        return txtMarque;
    }

    public JLabel getLblStock() {
        if (lblStock == null) {
            lblStock = new JLabel("Stock :");
        }
        return lblStock;
    }

    public JTextField getTxtStock() {
        if (txtStock == null) {
            txtStock = new JTextField(20);
        }
        return txtStock;
    }

    public JLabel getLblPrix() {
        if (lblPrix == null) {
            lblPrix = new JLabel("Prix :");
        }
        return lblPrix;
    }

    public JTextField getTxtPrix() {
        if (txtPrix == null) {
            txtPrix = new JTextField(20);
        }
        return txtPrix;
    }

    public JLabel getLblType() {
        if (lblType == null) {
            lblType = new JLabel("Type :");
        }
        return lblType;
    }

    public JRadioButton getRadioBtnType1() {
        if (radioBtnType1 == null) {
            radioBtnType1 = new JRadioButton("Ramette", true);
        }
        radioBtnType1.addActionListener((e) -> {
            initRadioAndCheck(true);
        });
        return radioBtnType1;
    }

    public JRadioButton getRadioBtnType2() {
        if (radioBtnType2 == null) {
            radioBtnType2 = new JRadioButton("Stylo");
        }
        radioBtnType2.addActionListener((e) -> {
            initRadioAndCheck(false);
        });
        return radioBtnType2;
    }

    public JLabel getLblGrammage() {
        if (lblGrammage == null) {
            lblGrammage = new JLabel("Grammage :");
        }
        return lblGrammage;
    }

    public JCheckBox getCheckBtnGrammage1() {
        if (checkBtnGrammage1 == null) {
            checkBtnGrammage1 = new JCheckBox("80 grammes");
        }
        return checkBtnGrammage1;
    }

    public JCheckBox getCheckBtnGrammage2() {
        if (checkBtnGrammage2 == null) {
            checkBtnGrammage2 = new JCheckBox("100 grammes");
        }
        return checkBtnGrammage2;
    }

    public JLabel getLblCouleur() {
        if (lblCouleur == null) {
            lblCouleur = new JLabel("Couleur :");
        }
        return lblCouleur;
    }

    public JComboBox getComboBoxCouleur() {
        if (comboBoxCouleur == null) {
            String[] listCouleurs = {"noir", "bleu", "orange", "vert", "rouge", "violet", "jaune"};
            comboBoxCouleur = new JComboBox(listCouleurs);
        }
        return comboBoxCouleur;
    }

    public JButton getBtnPrev() {
        if (btnPrev == null) {
            ImageIcon icon = new ImageIcon(getClass().getResource("images/Back24.gif"));
            btnPrev = new JButton(icon);

            btnPrev.addActionListener((e) -> {
                setChamps(controller.getPrev());
            });
        }
        return btnPrev;
    }

    public JButton getBtnNew() {
        if (btnNew == null) {
            ImageIcon icon = new ImageIcon(getClass().getResource("images/New24.gif"));
            btnNew = new JButton(icon);
        }

        btnNew.addActionListener((e) -> {
            setNewItem();
        });
        return btnNew;
    }

    public JButton getBtnSave() {
        if (btnSave == null) {
            ImageIcon icon = new ImageIcon(getClass().getResource("images/Save24.gif"));
            btnSave = new JButton(icon);
        }
        btnSave.addActionListener((e) -> {
            try {
                saveArticle();
            } catch (BLLException ex) {
                throw new RuntimeException(ex);
            }
        });
        return btnSave;
    }

    public JButton getBtnDelete() {
        if (btnDelete == null) {
            ImageIcon icon = new ImageIcon(getClass().getResource("images/Delete24.gif"));
            btnDelete = new JButton(icon);
        }
        btnDelete.addActionListener((e) -> {
            try {
                Article article = controller.delete();
                if (article == null) {
                    setNewItem();
                } else {
                    setChamps(article);
                }
            } catch (BLLException ex) {
                throw new RuntimeException(ex);
            }
        });
        return btnDelete;
    }

    public JButton getBtnNext() {
        if (btnNext == null) {
            ImageIcon icon = new ImageIcon(getClass().getResource("images/Forward24.gif"));
            btnNext = new JButton(icon);
        }
        btnNext.addActionListener((e) -> {
            setChamps(controller.getNext());
        });
        return btnNext;
    }

    public void setChamps(Article a) {
        if (a != null) {
            txtReference.setText(a.getReference().trim());
            txtDesignation.setText(a.getDesignation().trim());
            txtMarque.setText(a.getMarque().trim());
            txtStock.setText(String.valueOf(a.getQteStock()).trim());
            txtPrix.setText(String.valueOf(a.getPrixUnitaire()).trim());

            getRadioBtnType1().setEnabled(false);
            getRadioBtnType2().setEnabled(false);

            if (a instanceof Ramette) {
                initRadioAndCheck(true);
                if (((Ramette) a).getGrammage() == 80) {
                    checkBtnGrammage1.setSelected(true);
                } else {
                    checkBtnGrammage2.setSelected(true);
                }
                comboBoxCouleur.setEnabled(false);
            } else {
                initRadioAndCheck(false);
                comboBoxCouleur.setSelectedItem(((Stylo) a).getCouleur());
            }
        }
        currentArticle = a;
    }

    private void initRadioAndCheck(boolean isRamette) {

        getRadioBtnType1().setSelected(isRamette);
        getCheckBtnGrammage1().setEnabled(isRamette);
        getCheckBtnGrammage2().setEnabled(isRamette);

        if(isRamette) {
            getCheckBtnGrammage1().setSelected(true);
        }

        getRadioBtnType2().setSelected(!isRamette);
        getComboBoxCouleur().setEnabled(!isRamette);

    }

    private void setNewItem() {
        currentArticle = null;
        txtReference.setText("");
        txtDesignation.setText("");
        txtMarque.setText("");
        txtStock.setText("");
        txtPrix.setText("");
        initRadioAndCheck(true);
        getRadioBtnType1().setEnabled(true);
        getRadioBtnType2().setEnabled(true);
    }

    private void saveArticle() throws BLLException {
        Article article = getArticleFromChamps();

        radioBtnType1.setEnabled(false);
        radioBtnType2.setEnabled(false);

        if (currentArticle == null) {
            controller.save(article);
            currentArticle = article;

        }else{
            article.setIdArticle(currentArticle.getIdArticle());
            controller.update(article);
        }
    }

    private Article getArticleFromChamps() {
        Article a = null;

        String marque = txtMarque.getText();
        String reference = txtReference.getText();
        String designation = txtDesignation.getText();

        float prixUnitaire = 0;
        try {
            prixUnitaire = Float.parseFloat(getTxtPrix().getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            //TODO gestion erreurs
        }

        int qteStock = 0;
        try {
            qteStock = Integer.parseInt(getTxtStock().getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            //TODO gestion erreurs
        }

        boolean typeRamette = getRadioBtnType1().isSelected();

        if (typeRamette) {
            //le radiobouton Ramette est sélectionné
            int grammage = getCheckBtnGrammage1().isSelected() ? 80 : 100;
            a = new Ramette(marque, reference, designation, prixUnitaire, qteStock, grammage);
        } else {
            String couleur = (String) getComboBoxCouleur().getSelectedItem();
            a = new Stylo(marque, reference, designation, prixUnitaire, qteStock, couleur);
        }

        return a;
    }

    @Override
    public void onArticleSelected(int index) {
    }

    @Override
    public void onCatalogueSelected(Article a) {
        setChamps(a);
    }

    @Override
    public void onArticleUpdated(int index) {

    }

    @Override
    public void onArticleUpdated(int index, Article a) {

    }

    @Override
    public void onArticleDeleted(int index) {

    }
}