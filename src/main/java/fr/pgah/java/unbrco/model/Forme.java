package fr.pgah.java.unbrco.model;

import fr.pgah.java.son.MidiSynth;
import java.awt.*;

public class Forme {

  protected static final Color COULEUR_LIGNE_JOUEE = new Color(230, 158, 60);

  protected int x;
  protected int y;
  protected int longueur;
  protected int hauteur;
  protected boolean estSelectionnee;
  private MidiSynth midiSynth;
  private int instrument;
  protected int colonneJouee;

  public Forme(Point hautGauche, MidiSynth midiSynth, int instrument) {
    this((int) hautGauche.getX(), (int) hautGauche.getY(), 0, 0);
    estSelectionnee = false;
    this.midiSynth = midiSynth;
    this.instrument = instrument;
    colonneJouee = 0;
  }

  public Forme(int hautGaucheX, int hautGaucheY, int longueur, int hauteur) {
    this.x = hautGaucheX;
    this.y = hautGaucheY;
    this.longueur = longueur;
    this.hauteur = hauteur;

  }

  public boolean contientX(int x) {
    int positionFinale = this.x + longueur;
    if (x >= this.x && x <= positionFinale) {
      return true;
    }
    return false;
  }

  public boolean contientY(int y) {
    int positionFinale = this.y + hauteur;
    if (y >= this.y && y <= positionFinale) {
      return true;
    }
    return false;
  }

  public boolean contient(Point pt) {

    if (this.contientX(pt.x) && this.contientY(pt.y)) {
      return true;
    }
    return false;
  }

  public void setLimites(Point basDroite) {
    longueur = basDroite.x - x;
    hauteur = basDroite.y - y;
  }

  public void dessiner(Graphics g) {
    Color saveCouleur = g.getColor();
    if (estSelectionnee) {
      g.setColor(COULEUR_LIGNE_JOUEE);
    } else {
      g.setColor(Color.white);
    }
    g.fillRect(x, y, longueur, hauteur);
    g.setColor(saveCouleur);
    g.drawRect(x, y, longueur, hauteur);

    if (colonneJouee > 0 && colonneJouee < longueur) {
      g.setColor(Color.red);
      g.drawLine(x + colonneJouee, y, x + colonneJouee, y + hauteur);
      g.setColor(saveCouleur);
    }
  }

  public void deplacer(int dx, int dy) {
    boolean changementNote;
    changementNote = (convertirCoordVersNote(y) != convertirCoordVersNote(y + dy));
    if (changementNote) {
      stopper();
    }
    x += dx;
    y += dy;
    if (changementNote) {
      jouer();
    }
  }

  public void selectionnerEtJouer() {
    if (!estSelectionnee) {
      estSelectionnee = true;
      jouer();
    }
  }

  public void deselectionnerEtStopper() {
    if (estSelectionnee) {
      estSelectionnee = false;
      stopper();
    }
  }

  private void jouer() {
    int volume = convertirZoneVersVelocite(longueur * hauteur);
    midiSynth.play(instrument, convertirCoordVersNote(y), volume);
  }

  private void stopper() {
    midiSynth.stop(instrument, convertirCoordVersNote(y));
  }

  private int convertirZoneVersVelocite(int zone) {
    return Math.max(60, Math.min(127, zone / 30));
  }

  private int convertirCoordVersNote(int y) {
    return 70 - y / 12;
  }

  public void setColonneJouee(int colonneCourante) {
    this.colonneJouee = colonneCourante;
  }

  public int getLongueur() {
    return this.longueur;
  }
}
