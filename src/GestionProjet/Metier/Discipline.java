package GestionProjet.Metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * classe metier de gestion d'une discipline
 * @author  Ingrid Ngoune
 * @version 1.0 */
public class Discipline {
    /**
     * id unique de la discipline
     */
    protected int idDiscipline;
    /**
     * nom de la discipline
     */
    protected String nom;
    /**
     * description de la discipline
     */
    protected String description;
    /**
     * liste des projets ayant comme discipline de base
     */
    protected List<Projet> listeProjets=new ArrayList<>();

    /**
     * constructeur parametre
     * @param idDiscipline identifiant unique de la discipline
     * @param nom nom unique de la discipline
     * @param description description de la discipline
     */

    public Discipline(int idDiscipline, String nom, String description) {
        this.idDiscipline = idDiscipline;
        this.nom = nom;
        this.description = description;
    }

    /**
     * methode toString
     * @return les informations complètes de la discipline
     */
    @Override
    public String toString() {
        return "Discipline{" +
                "idDiscipline=" + idDiscipline +
                ", nom='" + nom + '\'' +
                ", description='" + description ;
    }

    /**
     * getter idDiscipline
     * @return l'identifiant de la discipline
     */
    public int getIdDiscipline() {
        return idDiscipline;
    }

    /**
     * setter idDisicipline
     * @param idDiscipline identifiant unique de la discipline
     */
    public void setIdDiscipline(int idDiscipline) {
        this.idDiscipline = idDiscipline;
    }

    /**
     * getter nom
     * @return le nom de la disciplien
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter nom
     * @param nom nom de la discipline
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter description
     * @return le description de la discipline
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter description
     * @param description description de la discipline
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getter listeProjets
     * @return la liste des projets de la discipline
     */
    public List<Projet> getlisteProjets() {
        return listeProjets;
    }

    /**
     * setter listePorjets
     * @param listeProjets liste de projet
     */
    public void setlisteProjets(List<Projet> listeProjets) {
        this.listeProjets = listeProjets;
    }

    /**
     * verification de l'egalite de deux disicipline basée sur leur nom
     * @param o
     * @return egalité ou pas
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return idDiscipline == that.idDiscipline;
    }

    /**
     * calcul du hashcode de la discipline
     * @return valeur du hashcode de la discipline
     */
    @Override
    public int hashCode() {
        return Objects.hash(idDiscipline, nom, description, listeProjets);
    }
}
