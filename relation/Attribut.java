package utilitaire;

import java.util.Objects;

public class Attribut {
    String type;
    String nom;

    public Attribut(String type, String nom) {
        this.type = type;
        this.nom = nom;
    }

    public void setType(String type){
        this.type=type;
    }
    public String getType(){
        return this.type;
    }

    public void setNom(String nom){
        this.nom=nom;
    }
    public String getNom(){
        return this.nom;
    }


    @Override
    public String toString() {
        return getType() + " " + getNom();
    }

     @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Attribut attribut = (Attribut) obj;
        return nom.equals(attribut.nom) && type.equals(attribut.type);
    }

    // Assurez-vous d'ajouter hashCode si vous ajoutez equals
    @Override
    public int hashCode() {
        return Objects.hash(nom, type);
    }
}
