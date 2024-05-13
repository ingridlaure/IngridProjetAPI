package mvc.model;

import mvc.observer.Subject;
import GestionProjet.Metier.*;

import java.util.List;

public abstract class DAODiscipline extends Subject {
    public abstract Discipline addDiscipline(Discipline discipline);
    public abstract boolean removeDiscipline(Discipline discipline);
    public abstract Discipline updateDiscipline(Discipline discipline);
    public abstract Discipline readDiscipline(int idDiscipline);
    public abstract List<Discipline> getDisciplines();
    public abstract List<Projet> listeProjets(Discipline discipline);


}
