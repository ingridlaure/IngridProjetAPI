package mvc.view;

import GestionProjet.Metier.Competence;
import GestionProjet.Metier.Discipline;
import GestionProjet.Metier.Employe;
import GestionProjet.Metier.Projet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class EmployeViewConsole extends EmployeAbstractView {
    private Scanner sc = new Scanner(System.in);


    @Override
    public void affMsg(String msg) {
        System.out.println("information:" + msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }


    public void menu() {
        update(employeController.getAll());
        do {
            int ch = choixListe(Arrays.asList("ajout", "supprimer", "rechercher", "modifier", "fin"));

            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    rechercher();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }


    private void modifier() {
        affList(lemp);
        int nl = choixElt(lemp);
        Employe emp = lemp.get(nl - 1);
        String matricule = modifyIfNotBlank("matricule de l'employe : ", emp.getMatricule());
        String nom = modifyIfNotBlank("Nom de l'employe :", emp.getNom());
        String prenom = modifyIfNotBlank("Preom de l'employe :", emp.getPrenom());
        String tel = modifyIfNotBlank("Numéro de telephone :", emp.getTel());
        String mail = modifyIfNotBlank("Email : ", emp.getMail());
        Employe empMaj = employeController.update(new Employe(emp.getIdEmploye(), matricule, nom, prenom, tel, mail));
        if (empMaj == null) affMsg("mise à jour infructueuse");
        else affMsg("mise à jour effectuée : " + empMaj);
    }

    private void rechercher() {
        System.out.println("matricule de l'employe : ");
        String mat = sc.nextLine();
        Employe emp = employeController.search(mat);
        if (emp == null) affMsg(" Employe inexistant");
        else {
            affMsg(emp.toString());
            special(emp);
        }
    }

    private void retirer() {
        affList(lemp);

        int nl = choixElt(lemp);
        Employe emp = lemp.get(nl - 1);
        boolean ok = employeController.removeEmploye(emp);
        if (ok) affMsg("employé effacé");
        else affMsg("employé non effacé");
    }

    private void ajouter() {

        System.out.println("Matricule :");
        String matricule = sc.nextLine();
        System.out.println("Nom : ");
        String nom = sc.nextLine();
        System.out.println("Prenom : ");
        String prenom = sc.nextLine();
        System.out.println("Télephone : ");
        String tel = sc.nextLine();
        System.out.println("Mail : ");
        String mail = sc.nextLine();
        Employe emp = employeController.addEmploye(new Employe(0, matricule, nom, prenom, tel, mail));
        if (emp != null) affMsg("création de :" + emp);
        else affMsg("erreur de création");
    }

    @Override
    public Employe selectionner() {
        update(employeController.getAll());
        int nl = choixElt(lemp);
        Employe emp = lemp.get(nl - 1);
        return emp;
    }

    private void special(Employe emp) {
        do {
            affMsg(" Employe " + emp.toString());

            int choix = choixListe(Arrays.asList("ajouter discipline", "modifier discipline", "supprimer discipline", "lister discipline", "Liste des projets d'une competence", "Niveau de competence maximale", "fin"));
            switch (choix) {
                case 1:
                    ajouterDidscipline(emp);
                    break;
                case 2:
                    modfifierDiscipline(emp);
                    break;
                case 3:
                    supprimerDiscipline(emp);
                    break;
                case 4:
                    listerDisciplines(emp);
                    break;
                case 5:
                    listeProjetParCompetence(emp);
                    break;
                case 6:
                    niveauCompetenceMax(emp);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);
    }

    private void listeProjetParCompetence(Employe emp) {
        List<Projet> lp = new ArrayList<>();
        List<Discipline> ldis = new ArrayList<>();
        List<Competence> lc = employeController.getCompetences(emp);
        for (Competence c : lc) {
            ldis.add(c.getDiscipline());
        }
        lp = employeController.listeProjetDiscipline(ldis);
        if (lp.isEmpty()) {
            System.out.println("Pas de projet pour les competences de cet employe");
        } else {
            affList(lp);
        }
    }
    private void niveauCompetenceMax(Employe emp){
        int niveauMax=employeController.niveauxCompetenceMax(emp);
        System.out.println("Le niveau de competence max de "+emp.getNom()+" est : "+niveauMax);
    }

    private void ajouterDidscipline(Employe emp) {
        System.out.println("Ajout d'une competence");
        Discipline dis = dv.selectionner();
        System.out.println(" Niveau : ");
        int niveau = lireInt();
        boolean ok = employeController.addDiscipline(emp, dis, niveau);
        if (ok) affMsg("Discipline ajoutée avec succes");
        else affMsg(" erreur lors de l'ajout");
    }

    private void modfifierDiscipline(Employe emp) {
        System.out.println("Modification d'une competence");
        List<Competence> lc = employeController.getCompetences(emp);
        affList(lc);
        Competence c = lc.get(choixElt(lc) - 1);
        Discipline dis = c.getDiscipline();
        //Discipline dis = dv.selectionner();
        System.out.println("niveau : ");
        int niveau = lireInt();
        boolean ok = employeController.updateDiscipline(emp, dis, niveau);
        if (ok) affMsg("Mise à jour éffectuée");
        else affMsg(" echec de la mise à jour");
    }

    private void supprimerDiscipline(Employe emp) {
        System.out.println(" Suprresion d'une compétence ");
        List<Competence> lc = employeController.getCompetences(emp);
        affList(lc);
        Competence c = lc.get(choixElt(lc) - 1);
        Discipline dis = c.getDiscipline();
        boolean ok = employeController.supDiscipline(emp, dis);
        if (ok) affMsg("Competence supprimé avec succes");
        else affMsg(" echec de la suppresion ");
    }

    private void listerDisciplines(Employe emp) {
        System.out.println("Competence de l'employé : " + emp);
        List<Competence> lc = employeController.getCompetences(emp);
        if (lc.isEmpty())
            affMsg("aucune competence pour cette employe");
        else affList(lc);
    }

}
