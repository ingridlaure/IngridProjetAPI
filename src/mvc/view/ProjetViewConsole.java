package mvc.view;

import GestionProjet.Metier.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class ProjetViewConsole extends ProjetAbstractView {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println("information : " + msg);

    }

    @Override
    public void affList(List l) {
        affListe(l);

    }

    @Override
    public void menu() {
        update(projetController.getAll());
        do {
            int ch = choixListe(Arrays.asList("ajout", "supprimer", "rechercher", "modifier", "fin"));
            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    supprimer();
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

    private void operationSpeciale(Projet proj) {
        do {
            int choix = choixListe(Arrays.asList("ajouter un employe", "modfier un employe", "supprimer un employe", "lister les employe", "menu principal"));
            switch (choix) {
                case 1:
                    ajouterEmploye(proj);
                    break;
                case 2:
                    modfierEmploye(proj);
                    break;
                case 3:
                    supprimerEmploye(proj);
                    break;
                case 4:
                    listerEmploye(proj);
                    break;
                case 5:
                    return;
                default:
                    System.out.println(" choix invalide , veuillez recommencer");
            }
        } while (true);
    }

    private void ajouterEmploye(Projet proj) {
        System.out.println("Ajout d'un travail");
        Employe emp = ev.selectionner();
        System.out.println("pourcentage : ");
        int pourcent = lireInt();
        boolean ok = projetController.addEmploye(proj, emp, pourcent);
        if (ok) affMsg("employé ajouté avec success");
        else affMsg(" erreur lord e l'ajout de l'employe");
    }

    private void modfierEmploye(Projet proj) {
        System.out.println("Modification d'un employe");
        List<Travail> lt = projetController.getEmployes(proj);
        affList(lt);
        Travail t = lt.get(choixElt(lt)-1);
        Employe emp = t.getEmploye();
        System.out.println(" nouveau pourcentage");
        int pourcentage = lireInt();
        boolean ok = projetController.modifEmploye(proj, emp, pourcentage);
        if (ok) affMsg("employe modifier avec succes");
        else affMsg(" erreur lors de la modification de l'employe");
    }

    private void supprimerEmploye(Projet proj) {
        System.out.println(" Suppresion d'un travail");
        List<Travail> lt = projetController.getEmployes(proj);
        affList(lt);
        Travail t = lt.get(choixElt(lt)-1);
        Employe emp = t.getEmploye();
        boolean ok = projetController.supEmploye(proj, emp);
        if (ok) affMsg(" employe supprimé avec succes");
        else affMsg("erreur lors de la suppresion de l'employe");
    }

    private void listerEmploye(Projet proj) {
        System.out.println("Employé du projet");
        List<Travail> lt = projetController.getEmployes(proj);
        if (lt.isEmpty()) affMsg("aucun employé assigné pour ce projet");
        else affList(lt);
    }

    private void ajouter() {
        Discipline dis = dv.selectionner();
        System.out.println("Nom du projet : ");
        String nom = sc.nextLine();
        System.out.println("Date de début : ");
        String dte = sc.nextLine();
        LocalDate datedebut = getDate(dte);
        System.out.println(" Date de fin ");
        dte = sc.nextLine();
        LocalDate datefin = getDate(dte);
        System.out.println("Cout du projet : ");
        BigDecimal cout = sc.nextBigDecimal();
        Projet proj = projetController.addProjet(new Projet(0, nom, datedebut, datefin, cout, dis));
        if (proj != null) affMsg("création de : " + proj);
        else affMsg(" erreur de création");
    }

    private void supprimer() {
        affList(lp);
        int n = choixElt(lp);
        Projet proj = lp.get(n - 1);
        boolean ok = projetController.removeProjet(proj);
        if (ok) affMsg("projet supprimé");
        else affMsg("echec de la suppresssion");
    }

    private void modifier() {
        affList(lp);
        int n=choixElt(lp);
        Projet proj=lp.get(n-1);
        String nom=modifyIfNotBlank(" nom du projet",proj.getNom());
        String date=modifyIfNotBlank("date de debut :",proj.getDateDebut()+"");
        LocalDate datedebut=!date.equals("null")?LocalDate.parse(date):null;
        date =modifyIfNotBlank("date de fin :",proj.getDateFin()+"");
        LocalDate datefin=!date.equals("null")?LocalDate.parse(date):null;
        BigDecimal cout=new BigDecimal(modifyIfNotBlank(" cout : ",proj.getCout().toString())).setScale(2, RoundingMode.HALF_UP);
        Projet nproj=new Projet(proj.getIdProjet(),nom,datedebut,datefin,cout,proj.getDisciplineDeBase());
        proj=projetController.updateProjet(nproj);
        if(proj!=null) affMsg("projet modifé avec succes");
        else affMsg(" erreur lors de la mise à jour");
    }

    private void rechercher() {
        System.out.println("Id du projet : ");
        int idprojet=sc.nextInt();
        Projet proj=projetController.search(idprojet);
        if(proj==null) affMsg(" Projet inexistant ");
        else{
            affMsg(proj.toString());
            operationSpeciale(proj);
        }
    }

}
