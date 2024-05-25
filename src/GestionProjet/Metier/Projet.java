package GestionProjet.Metier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * classe metier de gestion d'un projet
 *
 * @author Ingrid Ngoune
 * @version 1.0
 */
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

    /**
     * constructeur parametre
     *
     * @param idProjet  identifiant unique du projet
     * @param nom       nom du projet
     * @param dateDebut date de debut du projet
     * @param dateFin   date de fin du projet
     * @param cout      cout du projet
     */
    public Projet(int idProjet, String nom, LocalDate dateDebut, LocalDate dateFin,BigDecimal cout, Discipline discipline) {
        this.idProjet = idProjet;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cout=cout;
        //cout.setScale(2, RoundingMode.HALF_UP);
        this.disciplineDeBase = discipline;

    }

    public Projet() {
    }

    /**
     * getter idProjet
     *
     * @return l'identifiant du projet
     */
    public int getIdProjet() {
        return idProjet;
    }

    /**
     * setter idPorjet
     *
     * @param idProjet identifiant du projet
     */
    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    /**
     * getter listeTravaux
     *
     * @return la liste des employes et leur pourcentage de travail
     */
    public List<Travail> getListetravaux() {
        return listeTravaux;
    }

    /**
     * setter listeTravaux
     *
     * @param listetravaux liste des employés, leur pourcentage de travail dans le projet et leur date d'engagement dans le projet
     */
    public void setListetravaux(List<Travail> listetravaux) {
        this.listeTravaux = listetravaux;
    }

    /**
     * getter nom
     *
     * @return le nom du projet
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter nom
     *
     * @param nom nom du projet
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter dateDebut
     *
     * @return la date de debut du projet
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * setter dateDebut
     *
     * @param dateDebut
     */
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * getter dateFin
     *
     * @return la date de fin du projet
     */
    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * setter dateFin
     *
     * @param dateFin la date de fin du projet
     */
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * $
     * getter cout
     *
     * @return le cout du projet
     */
    public BigDecimal getCout() {
        return cout.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * setter cout
     *
     * @param cout le cout du projet
     */
    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }

    /**
     * getter disciplineBase
     *
     * @return la discipline de base du projet
     */
    public Discipline getDisciplineDeBase() {
        return disciplineDeBase;
    }

    /**
     * setter disciplinedeBase
     *
     * @param disciplineDeBase la discipline de base du projet
     */
    public void setDisciplineDeBase(Discipline disciplineDeBase) {
        this.disciplineDeBase = disciplineDeBase;
    }

    /**
     * methode toString
     *
     * @return les information sur projet
     */
    @Override
    public String toString() {
        return "Projet{" +
                "idProjet=" + idProjet +
                ", nom='" + nom + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", cout=" + cout +
                ", disciplineDeBase=" + disciplineDeBase +
                ", travaux=" + listeTravaux +
                '}';
    }


    /**
     * @return la liste des employés avec leur poucentage de travail dans le projet ainsi que leur date d'engagement
     */

    public List<Travail> listeEmployesEtPourcentageEtDate() {
        return listeTravaux;
    }

    /**
     * methode d'ajout d'un objet travail qui est composé d'un employe, son pourcentage de travail et sa date d'engagement
     *
     * @param travail le travail à ajouter
     * @return true si l'employe a été ajouté avec succes et false dans le cas contraire
     */
    public boolean addEmploye(Travail travail) {
        for (Travail t : listeTravaux) {
            if (t.getEmploye().equals(travail.employe))
                return false;
        }
        listeTravaux.add(travail);
        return true;
    }

    /**
     * methode de suppresion de l'employe dans le projet
     *
     * @param emp l'employe à supprimer
     */
    public void supEmploye(Employe emp) {
        listeTravaux.removeIf(t -> t.getEmploye().equals(emp));
    }

    /**
     * methode de modfification du poucentage de travail d'un employe
     *
     * @param emp         l'employé à modifier
     * @param pourcentage le nouveau pourcentage à affecter
     * @return vrai si modifé avec  succes et false si l'employe n'existe pas
     */
    public boolean moddifEmploye(Employe emp, int pourcentage) {
        boolean flag = false;
        for (Travail t : listeTravaux) {
            if (t.getEmploye().equals(emp)) {
                t.setPourcentage(pourcentage);
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * methode de calcul de la valeur totale des pourcentage de travail
     *
     * @return la somme des pourcentages du projet
     */
    public int totalPourcentage() {
        int somme = 0;
        for (Travail t : listeTravaux) {
            somme += t.getPourcentage();
        }
        return somme;
    }

    /**
     * @param niveau niveau de competence à comparer
     * @return la liste des employés ayant un niveau de competences superieur ou egal au niveau passé en paramètre dans la discipline de base du projet
     */
    public List<Employe> listeEmployesDisciplineBase(int niveau) {
        List<Employe> l = new ArrayList<>();
        for (Travail t : listeTravaux) {
            for (Competence c : t.getEmploye().getListeCompetences()) {
                if (c.getDiscipline().equals(this.disciplineDeBase) && c.getNiveau() >= niveau) {
                    l.add(t.getEmploye());
                }
            }
        }
        return l;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projet projet = (Projet) o;
        return idProjet == projet.idProjet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProjet, nom, dateDebut, dateFin, cout, disciplineDeBase, listeTravaux);
    }
}

