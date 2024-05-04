package mvc.view;

import GestionProjet.Metier.Discipline;
import mvc.controller.DisciplineController;
import mvc.observer.Observer;

import java.util.List;

public abstract class DisciplineAbstractView implements Observer {
    protected DisciplineController disciplineController;
    protected List<Discipline> ld;

    public void setDisciplineController(DisciplineController disciplineController){
        this.disciplineController=disciplineController;    }
    public abstract void affMsg(String msg);

    public abstract Discipline selectionner();
    public abstract void menu();

    public abstract void affList(List l);

    public void update(List ld){
        this.ld=ld;
    }

}
