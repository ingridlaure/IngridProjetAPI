package mvc.view;

import GestionProjet.Metier.Discipline;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class DiscipineViewConsole extends DisciplineAbstractView {
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        update(disciplineController.getAll());
        do {
            int choix = choixListe(Arrays.asList("ajout", "supprimer", "recherhcer", "modfifer", "fin"));
            switch (choix) {
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
                    modfifer();
                    break;
                case 5:
                    return;

            }
        } while (true);

    }

    private void ajouter() {
        System.out.println("Nom  de la discipline : ");
        String nom = sc.nextLine();
        System.out.println("Description: ");
        String description = sc.nextLine();
        Discipline dis = disciplineController.addDsisicpline(new Discipline(0, nom, description));
        if (dis != null) affMsg(" création de :" + dis);
        else affMsg("erreur de création");
    }

    private void supprimer() {
        affList(ld);
        int n = choixElt(ld) - 1;
        Discipline dis = ld.get(n);
        boolean ok = disciplineController.removeDiscipline(dis);
        if (ok) affMsg(" discipline supprimé");
        else affMsg("erreur de suppresion");
    }

    private void rechercher() {
        System.out.println("Id discipline :");
        int idsiscipline = lireInt();
        Discipline dis = disciplineController.search(idsiscipline);
        if (dis == null) affMsg(" discipline non trouvée");
        else {
            affMsg(dis.toString());
            operationSpeciale(dis);
        }

    }

    private void modfifer() {
        affList(ld);
        int n = choixElt(ld) - 1;
        Discipline dis = ld.get(n);
        String nom = modifyIfNotBlank(" nom :", dis.getNom());
        String description = modifyIfNotBlank(" description ", dis.getDescription());
        Discipline discipline = disciplineController.updateDiscipline(new Discipline(dis.getIdDiscipline(), nom, description));
        if (discipline == null) affMsg("echec de la mise à jour");
        else affMsg("Mise à jour effecté avec succes :" + discipline);
    }

    private void operationSpeciale(Discipline dis) {
        do {
            int choix = choixListe(Arrays.asList("Liste des projets", "Fin"));
            if (choix == 2) return;
            List l = switch (choix) {
                case 1 -> disciplineController.projets(dis);
                default -> null;

            };
            if (l == null || l.isEmpty()) affMsg("Aucun élement trouvé");
            affList(l);
        } while (true);
    }


    @Override
    public void affMsg(String msg) {
        System.out.println("Information :" + msg);

    }

    @Override
    public Discipline selectionner() {
        update(disciplineController.getAll());
        int n = choixListe(ld);
        Discipline dis = ld.get(n - 1);
        return dis;
    }

    @Override
    public void affList(List l) {
        affListe(l);

    }

}


