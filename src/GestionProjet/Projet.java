package GestionProjet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projet {

    protected int idProjet;
    protected String nom;
    protected LocalDate dateDebut;
    protected LocalDate dateFin;
    protected BigDecimal cout;
    protected Discipline disciplineDeBase;
    protected List<Travail> travaux = new ArrayList<>();

    public Projet(String nom, LocalDate dateDebut, LocalDate dateFin, BigDecimal cout) {

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

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public BigDecimal getCout() {
        return cout;
    }

    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }

    public Discipline getDisciplineDeBase() {
        return disciplineDeBase;
    }

    public void setDisciplineDeBase(Discipline disciplineDeBase) {
        this.disciplineDeBase = disciplineDeBase;
    }

    public List<Travail> getTravaux() {
        return travaux;
    }

    public void setTravaux(List<Travail> travaux) {
        this.travaux = travaux;
    }

    public List<Travail> listeEmployesEtPourcentageEtDate() {
        List<Travail> t = new ArrayList<>();
        return t;
    }

    public void addEmploye(Employe emp, int pourcentage, LocalDate date) {


    }

    public void supEmploye(Employe emp) {

    }

    public void moddifEmploye(Employe emp, int pourcentage) {
    }

    public int totalPourcentage() {
        int t = 0;
        return t;
    }
public List<Employe> listeEmployesDisciplineBase(int niveau)
{
    List<Employe> l=new ArrayList<>();
    return l;

}
}

