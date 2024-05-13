package designPattern.builder;

import GestionProjet.Metier.Projet;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * design pattern de gestion d'une discipline
 *
 * @author Ingrid Ngoune
 * @version 1.0
 */
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
    protected Set<Projet> listeProjets = new HashSet<>();

    /**
     * constructeur parametre
     *
     * @param db objet de la classe DisciplineBuilder contenant les informations d'initialisation
     */

    public Discipline(DisciplineBuilder db) {
        this.idDiscipline = db.idDiscipline;
        this.nom = db.nom;
        this.description = db.description;
    }

    /**
     * methode toString
     *
     * @return les informations complètes de la discipline
     */
    @Override
    public String toString() {
        return "Discipline{" +
                "idDiscipline=" + idDiscipline +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", projets=" + listeProjets +
                '}';
    }

    /**
     * getter idDiscipline
     *
     * @return l'identifiant de la discipline
     */
    public int getIdDiscipline() {
        return idDiscipline;
    }

    /**

    /**
     * getter nom
     *
     * @return le nom de la disciplien
     */
    public String getNom() {
        return nom;
    }

    /**
     * getter description
     *
     * @return le description de la discipline
     */
    public String getDescription() {
        return description;
    }

    /**
     * /**
     * getter listeProjets
     *
     * @return la liste des projets de la discipline
     */
    public Set<Projet> getlisteProjets() {
        return listeProjets;
    }

    /**
     * verification de l'egalite de deux disicipline basée sur leur nom
     *
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
     *
     * @return valeur du hashcode de la discipline
     */
    @Override
    public int hashCode() {
        int hash=5;
        hash=37*hash+this.idDiscipline;
        return hash;
    }

    public static class DisciplineBuilder {
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

        public DisciplineBuilder setIdDiscipline(int idDiscipline) {
            this.idDiscipline = idDiscipline;
            return this;
        }

        public DisciplineBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public DisciplineBuilder setDescription(String description) {
            this.description = description;
            return this;
        }
        public Discipline build()throws Exception{
            if(idDiscipline<=0||nom==null||description==null) throw new Exception("information incomplète");
            return new Discipline(this);
        }

    }
}
