package fr.pgah.java.unbrco.model;

import java.awt.Point;
import java.awt.*;

import fr.pgah.java.son.MidiSynth;

public class FormeOvale extends Forme {

    public FormeOvale(Point hautGauche, MidiSynth midiSynth, int instrument) {
        super(hautGauche, midiSynth, instrument);
    }

    @Override
    public void dessiner(Graphics g) {
        Color saveCouleur = g.getColor();
        if (estSelectionnee) {
            g.setColor(COULEUR_LIGNE_JOUEE);
        } else {
            g.setColor(Color.white);
        }
        g.fillOval(x, y, longueur, hauteur);
        g.setColor(saveCouleur);
        g.drawOval(x, y, longueur, hauteur);

        if (colonneJouee > 0 && colonneJouee < longueur) {
            g.setColor(Color.red);
            g.drawLine(x + colonneJouee, y, x + colonneJouee, y + hauteur);
            g.setColor(saveCouleur);
        }
    }
}
