package mvc.controller;

import GestionProjet.Metier.Employe;
import GestionProjet.Metier.Projet;
import GestionProjet.Metier.Travail;
import mvc.model.DAOProjet;
import mvc.view.ProjetAbstractView;

import java.time.LocalDate;
import java.util.List;

public class ProjetController {
    private DAOProjet model;
    private ProjetAbstractView view;
    public ProjetController(DAOProjet model,ProjetAbstractView view){
        this.model=model;
        this.view=view;
        this.view.setProjetController(this);
    }
    public List<Projet> getAll(){ return model.getProjets();}
    public Projet updateProjet(Projet proj){return model.updateProjet(proj);}
    public Projet search(int idprojet){ return model.readProjet(idprojet);}
    public boolean addEmploye(Projet proj, Employe emp, LocalDate dateengag, int pourcentage){return model.addEmploye(proj,emp,dateengag,pourcentage);}
    public boolean modifEmploye(Projet proj, Employe emp, int pourcentage){return model.updateEmploye(proj,emp,pourcentage);}
    public boolean supEmploye(Projet proj,Employe emp){return model.removeEmploye(proj,emp);}
    public List<Travail> getEmployes(Projet proj){return model.getEmployes(proj);}




}
