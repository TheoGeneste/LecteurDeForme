package fr.pgah.java.unbrco.ui.outils;

import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.event.MouseEvent;

import fr.pgah.java.unbrco.model.FormeRectangle;
import fr.pgah.java.unbrco.ui.EditeurDeFormes;

public class OutilFormeRectangle extends OutilForme {

    public OutilFormeRectangle(EditeurDeFormes editeur, JComponent parent) {
        super(editeur, parent);
    }

    @Override
    protected void creerBouton(JComponent parent) {
        bouton = new JButton("Forme : Rectangle");
        bouton = customiserButton(bouton);
    }

    @Override
    public void pressDansZoneDessin(MouseEvent e) {
        forme = new FormeRectangle(e.getPoint(), editeur.getMidiSynth(), 1);
        forme.selectionnerEtJouer();
        forme.setLimites(e.getPoint());
        editeur.ajouterAuDessin(forme);
    }
}
