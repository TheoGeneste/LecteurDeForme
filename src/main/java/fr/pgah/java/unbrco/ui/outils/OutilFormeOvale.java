package fr.pgah.java.unbrco.ui.outils;

import javax.swing.JButton;
import javax.swing.JComponent;

import fr.pgah.java.unbrco.model.FormeOvale;
import fr.pgah.java.unbrco.ui.EditeurDeFormes;
import java.awt.event.MouseEvent;

public class OutilFormeOvale extends OutilForme {

    public OutilFormeOvale(EditeurDeFormes editeur, JComponent parent) {
        super(editeur, parent);
    }

    @Override
    protected void creerBouton(JComponent parent) {
        bouton = new JButton("Forme : Ovale");
        bouton = customiserButton(bouton);
    }

    @Override
    public void pressDansZoneDessin(MouseEvent e) {
        forme = new FormeOvale(e.getPoint(), editeur.getMidiSynth(), 124);
        forme.selectionnerEtJouer();
        forme.setLimites(e.getPoint());
        editeur.ajouterAuDessin(forme);
    }

}
