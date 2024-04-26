package mvc.view;

import GestionProjet.Metier.Discipline;
import mvc.controller.DisciplineController;

import java.util.List;

public class DisciplineAbstractView {
    protected DisciplineController disciplineController;
    protected List<Discipline> ld;

    public void setDisciplineController(DisciplineController disciplineController){
        this.disciplineController=disciplineController;    }
}
