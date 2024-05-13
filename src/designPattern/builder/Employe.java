package designPattern.builder;

import GestionProjet.Metier.Competence;

import java.util.*;

public class Employe {
    /**
     * identifiant de l'employe
     */
    protected int idEmploye;
    /**
     * matricule unique de l'employé
     */
    protected String matricule;
    /**
     * $
     * nom de l'employé
     */
    protected String nom;
    /**
     * prenom de l'employé
     */
    protected String prenom;
    /**
     * numéro de telephone de l'employe
     */
    protected String tel;
    /**
     * adresse mail unique de l'employé
     */
    protected String mail;
    /**
     * liste des competences de l'employé
     */
    Set<Competence> listeCompetences= new HashSet<>();
    public Employe(EmployeBuilder eb) {
        this.idEmploye = eb.idEmploye;
        this.matricule = eb.matricule;
        this.nom = eb.nom;
        this.prenom = eb.prenom;
        this.tel = eb.tel;
        this.mail = eb.mail;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTel() {
        return tel;
    }

    public String getMail() {
        return mail;
    }

    public Set<Competence> getListeCompetences() {
        return listeCompetences;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "idEmploye=" + idEmploye +
                ", matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", tel='" + tel + '\'' +
                ", mail='" + mail +
                '}';
    }
    @Override
    public int hashCode() {
        int hash=5;
        hash=37*hash+this.idEmploye;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return idEmploye == employe.idEmploye;
    }

    public static class EmployeBuilder{
        /**
         * identifiant de l'employe
         */
        protected int idEmploye;
        /**
         * matricule unique de l'employé
         */
        protected String matricule;
        /**
         * $
         * nom de l'employé
         */
        protected String nom;
        /**
         * prenom de l'employé
         */
        protected String prenom;
        /**
         * numéro de telephone de l'employe
         */
        protected String tel;
        /**
         * adresse mail unique de l'employé
         */
        protected String mail;

        public EmployeBuilder setIdEmploye(int idEmploye) {
            this.idEmploye = idEmploye;
            return this;
        }

        public EmployeBuilder setMatricule(String matricule) {
            this.matricule = matricule;
            return this;
        }

        public EmployeBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public EmployeBuilder setPrenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public EmployeBuilder setTel(String tel) {
            this.tel = tel;
            return this;
        }

        public EmployeBuilder setMail(String mail) {
            this.mail = mail;
            return this;
        }
        public Employe build() throws Exception{
            if(idEmploye<=0||matricule==null||nom==null||prenom==null||tel==null||mail==null) throw  new Exception("Information de l'employe incomplète");
            return new Employe(this);
        }
    }

}
