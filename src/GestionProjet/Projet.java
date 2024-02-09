package GestionProjet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Projet {

    protected String nom;
    protected Date dateDebut;
    protected Date dateFin;
    protected BigDecimal cout;
    protected List<Discipline> disciplines=new ArrayList<>();
    protected List<Travail> travaux=new ArrayList<>();

    public Projet(String nom, Date dateDebut, Date dateFin, BigDecimal cout) {

        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cout = cout;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public BigDecimal getCout() {
        return cout;
    }

    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public List<Travail> getTravaux() {
        return travaux;
    }

    public void setTravaux(List<Travail> travaux) {
        this.travaux = travaux;
    }
}
