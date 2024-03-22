package GestionProjet.Metier;

import java.util.Objects;

/**
 * classe metier de gestion d'une competence
 * @author Ingrid Ngoune
 * @version 1.0
 */
public class Competence {
 /**
  * identifiant de la competence
  */
 protected int idCompetence;
 /**
  * niveau de competences
  */
 protected int niveau;
 /**
  * Discipline de la competence
  */
 protected Discipline discipline;

 /**
  * constrcuetur paramétré
  * @param id identifiant de la competence
  * @param niveau le niveau de competence
  * @param discipline discipline de la competence
  */
 public Competence(int id ,int niveau, Discipline discipline) {
  this.idCompetence=id;
  this.niveau = niveau;
  this.discipline = discipline;
 }

 /**
  * getter idcompetence
  * @return idCompetence
  *
  */
 public int getIdCompetence() {
  return idCompetence;
 }

 /**
  * setter idCompetence
  * @param idCompetence identifiant de la ligne
  */
 public void setIdCompetence(int idCompetence) {
  this.idCompetence = idCompetence;
 }

 /**
  * getter niveau de competence
  * @return le niveau de competence
  */
 public int getNiveau() {
  return niveau;
 }

 /**
  * setter d
  * @param niveau niveau de la competence
  */
 public void setNiveau(int niveau) {
  this.niveau = niveau;
 }

 /**
  * getter discipline
  * @return la discipline de la competence
  */
 public Discipline getDiscipline() {
  return discipline;
 }

 /**
  * setter de la discipline
  * @param discipline ddiscipline concerné
  */
 public void setDiscipline(Discipline discipline) {
  this.discipline = discipline;
 }

 /**
  * méthode to string
  * @return les informations complete sur la competence
  */
 @Override
 public String toString() {
  return "Competence{" +
          "idCompetence=" + idCompetence +
          ", niveaux=" + niveau +
          ", discipline=" + discipline +
          '}';
 }

 /**
  * méthode de vérification d'égalité" de deux competences
  * @param o autre competence
  * @return
  */
 @Override
 public boolean equals(Object o) {
  if (this == o) return true;
  if (o == null || getClass() != o.getClass()) return false;
  Competence that = (Competence) o;
  return idCompetence == that.idCompetence;
 }

 /**
  * calcul du hashcode de la competence
  * @return hashcode de la competence
  */
 @Override
 public int hashCode() {
  return Objects.hash(idCompetence, niveau, discipline);
 }
}
