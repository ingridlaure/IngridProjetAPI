package mvc.model;

import GestionProjet.Metier.Competence;
import GestionProjet.Metier.Discipline;
import GestionProjet.Metier.Employe;
import mvc.observer.Subject;

import java.util.List;

public abstract class DAOEmploye extends Subject {
    public abstract Employe addEmploye(Employe employe);
    public abstract boolean removeEmploye(Employe employe);
    public abstract Employe updateEmploye(Employe employe);
    public abstract Employe readEmploye(String matricule);
    public abstract List<Employe> getEmployes();
    public abstract boolean addDiscipline(Employe emp, Discipline dis,int niveau);
    public abstract boolean updateDiscipline(Employe emp, Discipline dis,int niveau);
    public abstract boolean removeDiscipline(Employe emp, Discipline dis);
    public abstract List<Competence> getDisciplines(Employe emp);




}
