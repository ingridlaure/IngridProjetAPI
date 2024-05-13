package mvc.model;

import GestionProjet.Metier.Employe;
import GestionProjet.Metier.Projet;
import GestionProjet.Metier.Travail;
import mvc.observer.Subject;

import java.time.LocalDate;
import java.util.List;

public abstract class DAOProjet extends Subject {
    public abstract Projet addprojet(Projet projet);

    public abstract boolean removeProjet(Projet projet);
    public abstract Projet updateProjet(Projet projet);
    public abstract Projet readProjet(int  idprojet);
    public abstract List<Projet> getProjets();
    public abstract boolean addEmploye(Projet proj, Employe emp,int pourcentage);
    public abstract boolean updateEmploye(Projet proj, Employe emp, int pourcentage);
    public abstract boolean removeEmploye(Projet proj,Employe emp);
    public abstract List<Travail> getEmployes(Projet proj);



}
