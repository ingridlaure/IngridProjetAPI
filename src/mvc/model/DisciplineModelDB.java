package mvc.model;

import GestionProjet.Metier.Discipline;
import GestionProjet.Metier.Projet;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DisciplineModelDB extends DAODiscipline{

    protected Connection dbConnect;
    public  DisciplineModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
    }

    @Override
    public Discipline addDiscipline(Discipline discipline) {
        String query1 = "insert into APIDISCIPLINE(nom,description) values(?,?)";
        String query2 = "select idDiscipline from APIDISCIPLINE where nom=?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,discipline.getNom());
            pstm1.setString(2,discipline.getDescription());
            int n = pstm1.executeUpdate();
            if(n==1){
                pstm2.setString(1,discipline.getNom());
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idDiscipline= rs.getInt(1);
                    discipline.setIdDiscipline(idDiscipline);
                    notifyObservers();
                    return discipline;
                }
                else {

                    System.err.println("record introuvable");
                    return null;
                }
            }
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public boolean removeDiscipline(Discipline discipline) {
        String query = "delete from APIDISCIPLINE WHERE idDiscipline=?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,discipline.getIdDiscipline());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return false;
        }
    }

    @Override
    public Discipline updateDiscipline(Discipline discipline) {
        String query = "update APIDISCIPLINE set nom =?,description=? where iddiscipline = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,discipline.getNom());
            pstm.setString(2,discipline.getDescription());
            pstm.setInt(3,discipline.getIdDiscipline());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readDiscipline(discipline.getIdDiscipline());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public Discipline readDiscipline(int idDiscipline) {
        /*
       create or replace view APIDISCIPLINE_PROJET(iddiscipline,nomdiscipline,descriptiondiscipline,idprojet,nomprojet,datedebutprojet,datefinprojet,coutprojet)
as select
d.iddiscipline,
d.nom,d.description,
p.idprojet,
p.nom,
p.datedebut,
p.datefin,
p.cout
from apidiscipline d join apiprojet p on d.iddiscipLine=p.iddiscipline
order by d.iddiscipline;

 */

        String query = "select * from APIDISCIPLINE_PROJET where iddiscipline= ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idDiscipline);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String nom = rs.getString(2);
                String description= rs.getString(3);
                Discipline dis=new Discipline(idDiscipline,nom,description);
                List<Projet> lp=new ArrayList<>();
                int idProjet=rs.getInt(4);
                if(idProjet!=0){
                    do{
                        String nomProjet=rs.getString(5);
                        Date date=rs.getDate(6);
                        LocalDate dateDebut= date!=null?date.toLocalDate():null;
                        date=rs.getDate(7);
                        LocalDate dateFin= date!=null?date.toLocalDate():null;
                        BigDecimal cout=rs.getBigDecimal(8);
                        Projet proj=new Projet(idProjet,nomProjet,dateDebut,dateFin,cout,dis);
                        lp.add(proj);
                    }while(rs.next());
                }
                dis.setlisteProjets(lp);

                return dis;
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return null;
        }
    }

    @Override
    public List<Discipline> getDisciplines() {
        List<Discipline> ld = new ArrayList<>();
        String query="select * from APIDISCIPLINE";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int iddiscipline = rs.getInt(1);
                String nom = rs.getString(2);
                String description= rs.getString(3);
                Discipline dis = new Discipline(iddiscipline,nom,description);
                ld.add(dis);
            }
            return ld;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return null;
        }
    }
    @Override
    public List<Projet> listeProjets(Discipline discipline) {
        return discipline.getlisteProjets();
    }


    @Override
    public List getNotification() {
        return getDisciplines();
    }
}
