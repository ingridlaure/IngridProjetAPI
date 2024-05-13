package mvc.controller;

import GestionProjet.Metier.Discipline;
import GestionProjet.Metier.Projet;
import mvc.model.DAODiscipline;
import mvc.view.DisciplineAbstractView;

import java.util.List;

public class DisciplineController {
    private DAODiscipline model;
    private DisciplineAbstractView view;

    public DisciplineController(DAODiscipline model,DisciplineAbstractView view){
        this.model=model;
        this.view=view;
        this.view.setDisciplineController(this);
    }

    public List<Discipline> getAll(){return model.getDisciplines();}

    public Discipline addDsisicpline( Discipline discipline){return model.addDiscipline(discipline);}
    public boolean removeDiscipline(Discipline discipline){ return model.removeDiscipline(discipline);}
    public  Discipline updateDiscipline(Discipline discipline){return model.updateDiscipline(discipline);}
    public Discipline search(int idDiscipline){return model.readDiscipline(idDiscipline);}
    public List<Projet> projets(Discipline discipline){return model.listeProjets(discipline);}


}
