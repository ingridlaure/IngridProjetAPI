package mvc.view;

import GestionProjet.Metier.Projet;
import mvc.controller.ProjetController;
import mvc.observer.Observer;

import java.util.List;

public abstract class ProjetAbstractView implements Observer {
    protected ProjetController projetController;
    protected DisciplineAbstractView dv;
    protected EmployeAbstractView ev;
    protected List<Projet> lp;

    public void setProjetController(ProjetController projetController) {
        this.projetController = projetController;
    }

    public void setDisciplineView(DisciplineAbstractView dv) {
        this.dv = dv;
    }

    public void setEmloyeView(EmployeAbstractView ev) {
        this.ev = ev;
    }

    public abstract void affMsg(String msg);

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List lp) {
        this.lp = lp;
        affList(lp);
    }
}
