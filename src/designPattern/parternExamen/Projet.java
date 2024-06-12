package designPattern.parternExamen;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Projet {
    /**
     * identifiant unique d'un projet
     */
    protected int idProjet;
    /**
     * nom unique du projet
     */
    protected String nom;
    /**
     * date de debut  du projet
     */
    protected LocalDate dateDebut;
    /**
     * date de fin du projet
     */
    protected LocalDate dateFin;
    /**
     * cout du projet
     */
    protected BigDecimal cout;
    /**
     * discipline de base du projet
     */
    protected Discipline disciplineDeBase;
    /**
     * liste des employe ainsi que le poucentage de charge qui leur est affectét
     */
    protected List<Travail> listeTravaux = new ArrayList<>();

    public int getIdProjet() {
        return idProjet;
    }

    public String getNom() {
        return nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public BigDecimal getCout() {
        return cout;
    }

    public Discipline getDisciplineDeBase() {
        return disciplineDeBase;
    }

    public List<Travail> getListeTravaux() {
        return listeTravaux;
    }

    public Projet(ProjetBuilder pro) {
        this.idProjet = pro.idProjet;
        this.nom = pro.nom;
        this.dateDebut = pro.dateDebut;
        this.dateFin = pro.dateFin;
        this.cout = pro.cout;
        this.disciplineDeBase = pro.disciplineDeBase;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projet projet = (Projet) o;
        return idProjet == projet.idProjet;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "idProjet=" + idProjet +
                ", nom='" + nom + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", cout=" + cout +
                ", disciplineDeBase=" + disciplineDeBase +
                ", listeTravaux=" + listeTravaux +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProjet);
    }

    public static class ProjetBuilder {
        protected int idProjet;
        /**
         * nom unique du projet
         */
        protected String nom;
        /**
         * date de debut  du projet
         */
        protected LocalDate dateDebut;
        /**
         * date de fin du projet
         */
        protected LocalDate dateFin;
        /**
         * cout du projet
         */
        protected BigDecimal cout;
        /**
         * discipline de base du projet
         */
        protected Discipline disciplineDeBase;
        /**
         * liste des employe ainsi que le poucentage de charge qui leur est affectét
         */
        protected List<Travail> listeTravaux = new ArrayList<>();

        public ProjetBuilder setIdProjet(int idProjet) {
            this.idProjet = idProjet;
            return this;
        }

        public ProjetBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public ProjetBuilder setDateDebut(LocalDate dateDebut) {
            this.dateDebut = dateDebut;
            return this;
        }

        public ProjetBuilder setDateFin(LocalDate dateFin) {
            this.dateFin = dateFin;
            return this;
        }

        public ProjetBuilder setCout(BigDecimal cout) {
            this.cout = cout;
            return this;
        }

        public ProjetBuilder setDisciplineDeBase(Discipline disciplineDeBase) {
            this.disciplineDeBase = disciplineDeBase;
            return this;
        }

        public Projet build() throws Exception {
            if (disciplineDeBase == null) throw new Exception("discipline de base null");
            else {
                if (disciplineDeBase.getCout().compareTo(this.cout) > 0)
                    throw new Exception("cout de la discipline superieurs à celle du projet");
            }
            return new Projet(this);
        }
    }

}

