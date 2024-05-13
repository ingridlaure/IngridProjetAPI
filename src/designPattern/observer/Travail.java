package designPattern.observer;

import GestionProjet.Metier.Employe;

import java.time.LocalDate;
import java.util.Objects;

/**
 * classe metier de gestion d'un Travail
 * @author Ingrid Ngoune
 * @version 1.0
 */
public class Travail {
    /**
     * identifiant unique du travail
     */
    protected int idTravail;
    /**
     * pourcentage affecté a l'employe
     */
    protected int pourcentage;
    /**
     * date d'engagement de l'employé
     */
    protected LocalDate dateEngag;
    /**
     * employé concerné par le travail
     */
    protected Employe employe;

    /**
     * constructeur paramétré
     * @param idTravail identifiant du travail
     * @param pourcentage pourcentage à affecté au travailleyr
     * @param dateEngag dagte d'engagement de l'employe dans le projet
     * @param employe employé concerné par le travail
     */
    public Travail(int idTravail, int pourcentage, LocalDate dateEngag, Employe employe) {
        this.idTravail = idTravail;
        this.pourcentage = pourcentage;
        this.dateEngag = dateEngag;
        this.employe = employe;
    }

    /**
     * methode toString
     * @return les informations complete sur le travail
     */
    @Override
    public String toString() {
        return "Travail{" +
                "idTravail=" + idTravail +
                ", pourcentage=" + pourcentage +
                ", dateEngag=" + dateEngag +
                ", employe=" + employe +
                '}';
    }

    /**
     * getter idTravail
     * @return l'identifiant du travail
     */
    public int getIdTravail() {
        return idTravail;
    }

    /**
     * setter du travaim
     * @param idTravail identifiant du travail
     */
    public void setIdTravail(int idTravail) {
        this.idTravail = idTravail;
    }

    /**
     * getter pourcentage
     * @return le pourcentage de travail de l'employe
     */
    public int getPourcentage() {
        return pourcentage;
    }

    /**

    /**
     * getter daetEngagement
     * @return la date d'engagement de l'employe sur le projet
     */
    public LocalDate getDateEngag() {
        return dateEngag;
    }

    /**
     * setter dateEngag
     * @param dateEngag date d'engaament de l'employe sur le projet
     */
    public void setDateEngag(LocalDate dateEngag) {
        this.dateEngag = dateEngag;
    }

    /**
     * getter Employe
     * @return l'employe du travail
     */
    public Employe getEmploye() {
        return employe;
    }

    /**
     * setter employe
     * @param employe employe concerné par le travail
     */
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travail travail = (Travail) o;
        return idTravail == travail.idTravail;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTravail, pourcentage, dateEngag, employe);
    }
}
