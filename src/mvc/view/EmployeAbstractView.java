package mvc.view;
import GestionProjet.Metier.Employe;
import mvc.controller.EmployeController;
import mvc.observer.Observer;

import java.util.List;

public abstract class EmployeAbstractView implements Observer {
    protected EmployeController employeController;
    protected List<Employe> lemp;
    public void setEmployeController(EmployeController employeController){
        this.employeController=employeController;
    }
    public abstract void affMsg(String msg);
    public abstract Employe selectionner();
    public abstract void menu();
    public abstract void affList(List l);
    public void update(List lemp){
        this.lemp=lemp;
        affList(lemp);
    }

}
