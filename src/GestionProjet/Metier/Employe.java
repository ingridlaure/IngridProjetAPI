package GestionProjet.Metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * classe métier de la gestion d'un employe
 *
 * @author Ingrid Ngoune
 * @version 1.0
 */
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
    List<Competence> listeCompetences= new ArrayList<>();

    /**
     * constructeur paramétré
     *
     * @param idEmploye        identifiant unique de l'employe
     * @param matricule        matricule unique de l'employé
     * @param nom              nom de l'employ"
     * @param prenom           prenom de l'employe
     * @param tel              numero de telephone ne l'employe
     * @param mail             adresse mail de l'employe
     */
    public Employe(int idEmploye, String matricule, String nom, String prenom, String tel, String mail) {
        this.idEmploye = idEmploye;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.mail = mail;
    }

    /**
     * getter idEmploye
     *
     * @return l'identifiant de l'employe
     */

    public int getIdEmploye() {
        return idEmploye;
    }

    /**
     * setter idEmploye
     * @param idEmploye identifiant de l'employe
     */
    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    /**
     * getter matricule
     * @return le matricule de l'employe
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * setter matricule
     * @param matricule matricule e l'employe
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * getter du nom
     * @return le nom du client
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter nom
     * @param nom nom de l'employe
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter prenom
     * @return le prenom de l'employe
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * setter du prenom
     * @param prenom preno de l'employe
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * getter tel
     * @return le numero de telephone de l'employe
     */
    public String getTel() {
        return tel;
    }

    /**
     * setter telephone
     * @param tel numero de telephone de l'employé
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * getter mail
     * @return l'email de l'employe
     */
    public String getMail() {
        return mail;
    }

    /**
     * setter mail
     * @param mail email de l'employe
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * getter listecompetences
     * @return la liste des competences de l'employé
     */
    public List<Competence> getListeCompetences() {
        return listeCompetences;
    }

    /**
     * setter listecompetences
     * @param listeCompetences liste des competences de l'employe
     */
    public void setListeCompetences(List<Competence> listeCompetences) {
        this.listeCompetences = listeCompetences;
    }

    /**
     * retourne la liste des disciplines et leur niveau de l'employe
     * @return
     */
    public List<Competence> listeDisciplinesEtNiveau() {
        return listeCompetences;
    }

    /**
     * ajoute un niveau de discipline à l'employe
     * @param competence
     * @return
     */
    public boolean addDiscipline(Competence competence) {
        for (Competence c : listeCompetences) {
            if (c.getDiscipline().equals(competence.getDiscipline()))
                return false;
        }
        listeCompetences.add(competence);
        return true;
    }

    /**
     * modifie le niveau d'une discipline
     * @param discipline discipline à modifier
     * @param niveau nouveau niveau à affecter
     * @return vrai ou faux selon que la modififcation a ete effectuée ou nom
     */
    public boolean modifDiscipline(Discipline discipline, int niveau) {
        boolean flag = false;
        for (Competence c : listeCompetences) {
            if (c.getDiscipline().equals(discipline)) {
                c.setNiveau(niveau);
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * supprime une discipline
     * @param discipline discipline à supprimer dans la liste des competences de l'employe
     */
    public void suppDiscipline(Discipline discipline) {
        listeCompetences.removeIf(c -> c.getDiscipline().equals(discipline));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return idEmploye == employe.idEmploye ;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "idEmploye=" + idEmploye +
                ", matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", tel='" + tel + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmploye, matricule, nom, prenom, tel, mail, listeCompetences);
    }
}

