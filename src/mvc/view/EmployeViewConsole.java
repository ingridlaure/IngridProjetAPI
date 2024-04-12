package mvc.view;

import GestionProjet.Metier.Employe;

import java.math.BigDecimal;
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
            int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));

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
        employeController.search(mat);
    }

    private void retirer() {

        int nl = choixElt(lemp);
        Employe emp = lemp.get(nl - 1);
        boolean ok =employeController.removeEmploye(emp);
        if (ok) affMsg("employé effacé");
        else affMsg("employé non effacé");
    }

    private void ajouter() {

        System.out.println("Matricule :");
        String matricule=sc.nextLine();
        System.out.println("Nom : ");
        String nom=sc.nextLine();
        System.out.println("Prenom : ");
        String prenom=sc.nextLine();
        System.out.println("Télephone : ");
        String tel=sc.nextLine();
        System.out.println("Mail : ");
        String mail=sc.nextLine();
        Employe emp=employeController.addEmploye(new Employe(0,matricule,nom,prenom,tel,mail));
        if (emp != null) affMsg("création de :" + emp);
        else affMsg("erreur de création");
    }

    @Override
    public Employe selectionner() {
        update(employeController.getAll());
        int nl = choixListe(lemp);
        Employe emp = lemp.get(nl - 1);
        return emp;
    }

}
