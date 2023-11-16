package utilitaire;

import java.util.Vector;

public class Relation{
    String name;
    Vector<Attribut>  attributs;
    Vector donnees;

    public Relation(String name, Vector<Attribut> Tableattributs, Vector donnees) {
        this.name = name;
        this.attributs = Tableattributs;
        this.donnees = donnees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector<Attribut> getAttributs() {
        return attributs;
    }

    public void setAttributs(Vector<Attribut> attributs) {
        this.attributs = attributs;
    }

    public Vector getDonnees() {
        return donnees;
    }

    public void setDonnees(Vector donnees) {
        this.donnees = donnees;
    }
}