package mvc;

import mvc.controller.DisciplineController;
import mvc.controller.EmployeController;
import mvc.controller.ProjetController;
import mvc.model.*;
import mvc.view.*;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestionProjet {

    private DAOEmploye em;
    private DAOProjet pm;
    private DAODiscipline dm;
    private EmployeController ec;
    private DisciplineController dc;
    private ProjetController pc;
    private EmployeAbstractView ev;
    private DisciplineAbstractView dv;
    private ProjetAbstractView pv;

    public void gestion() {
        dm = new DisciplineModelDB();
        em = new EmployeModelDB();
        pm = new ProjetModelDB();


        dv = new DiscipineViewConsole();
        ev = new EmployeViewConsole();
        pv = new ProjetViewConsole();

        dc = new DisciplineController(dm, dv);
        ec = new EmployeController(em, ev);
        pc = new ProjetController(pm, pv);

        pv.setDisciplineView(dv);
        pv.setEmloyeView(ev);
        ev.setDisciplineView(dv);

        dm.addObserver(dv);
        em.addObserver(ev);
        pm.addObserver(pv);
        List<String> loptions = Arrays.asList("Disciplines", "Employes", "Projets", "fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch) {
                case 1:
                    dv.menu();
                    break;
                case 2:
                    ev.menu();
                    break;
                case 3:
                    pv.menu();
                    break;
                case 4:
                    System.exit(0);
            }
        } while (true);
    }

    public static void main(String[] args) {
        GestionProjet gp = new GestionProjet();
        gp.gestion();
    }


}
