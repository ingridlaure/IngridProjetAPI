package mvc.controller;

import GestionProjet.Metier.Employe;
import mvc.model.DAOEmploye;
import mvc.view.EmployeAbstractView;

import java.util.List;

public class EmployeController {
    private DAOEmploye model;
    private EmployeAbstractView view;

    public EmployeController(DAOEmploye model,EmployeAbstractView view)
    {
       this.model=model;
       this.view=view;
       this.view.setEmployeController(this);
    }

    public List<Employe> getAll(){return model.getEmployes();}
    public Employe addEmploye(Employe employe){return model.addEmploye(employe);}
    public boolean removeEmploye(Employe employe){return model.removeEmploye(employe);}
    public Employe update(Employe employe){
        return model.updateEmploye(employe);
    }
    public Employe search(String matricule){ return model.readEmploye(matricule);}
}
