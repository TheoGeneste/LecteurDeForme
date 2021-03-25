package fr.pgah.java.unbrco.lecteurs;

import fr.pgah.java.unbrco.model.Dessin;
import fr.pgah.java.unbrco.model.Forme;
import fr.pgah.java.unbrco.ui.EditeurDeFormes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LecteurDeDessin implements ActionListener {
  private Dessin dessin;
  private Timer timer;
  private int colonneEnCours;

  private List<Forme> dansLaColonnePrecedente;
  private List<Forme> dansLaColonneCourante;

  public LecteurDeDessin(Dessin dessin, Timer timer) {
    dansLaColonneCourante = new ArrayList<Forme>();
    dansLaColonnePrecedente = new ArrayList<Forme>();
    this.dessin = dessin;
    this.timer = timer;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    selectionnerEtJouerLesFormes();
    dessinerLigneProgression();
    incrementerColonne();
    arreterSiDessinFini();
  }

  private void incrementerColonne() {
    colonneEnCours += 1;
    dansLaColonnePrecedente = dansLaColonneCourante;
  }

  private void dessinerLigneProgression() {
    dessin.setColonneCourante(colonneEnCours);
    dessin.repaint();
  }

  private void arreterSiDessinFini() {
    if (colonneEnCours > EditeurDeFormes.LONGUEUR) {
      timer.stop();
    }
  }

  private void selectionnerEtJouerLesFormes() {
    dansLaColonneCourante = dessin.formesSurLaColonne(colonneEnCours);

    for (Forme colonnePrecedente : dansLaColonnePrecedente) {
      if (!dansLaColonneCourante.contains(colonnePrecedente)) {
        colonnePrecedente.deselectionnerEtStopper();
      }
    }

    for (Forme colonneCourante : dansLaColonneCourante) {
      if (dansLaColonnePrecedente.contains(colonneCourante)) {
        colonneCourante.selectionnerEtJouer();
      }
    }
  }

}
