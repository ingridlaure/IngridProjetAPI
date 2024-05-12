package mvc.model;

import GestionProjet.Metier.Discipline;
import GestionProjet.Metier.Employe;
import GestionProjet.Metier.Projet;
import GestionProjet.Metier.Travail;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjetModelDB extends DAOProjet {
    private Connection dbConnect;

    public ProjetModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");
            System.exit(1);
        }
    }


    @Override
    public Projet addprojet(Projet projet) {
        String query1 = "insert into APIPROJET(nom,datedebut,datefin,cout,iddiscipline) values(?,?,?,?,?)";
        String query2 = "select max(idprojet) from APIPROJET where iddiscipline = ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);) {

            pstm1.setString(1, projet.getNom());
            pstm1.setDate(2, projet.getDateDebut() != null ? Date.valueOf(projet.getDateDebut()) : null);
            pstm1.setDate(3, projet.getDateFin() != null ? Date.valueOf(projet.getDateDebut()) : null);
            pstm1.setBigDecimal(4, projet.getCout());
            pstm1.setInt(5, projet.getDisciplineDeBase().getIdDiscipline());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setInt(1, projet.getIdProjet());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idprojet = rs.getInt(1);
                    projet.setIdProjet(idprojet);
                    notifyObservers();
                    return projet;
                } else {

                    System.err.println("record introuvable");
                    return null;
                }
            } else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public boolean removeProjet(Projet projet) {
        String query = "delete from APIPROJET where idprojet=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, projet.getIdProjet());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
            return false;
        }

    }

    @Override
    public Projet updateProjet(Projet projet) {
        String query = "update APIPROJET set nom=?,datedebut=?,datefin=?,cout=?,iddiscipline=? where idprojet=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, projet.getNom());
            pstm.setDate(2, projet.getDateDebut() != null ? Date.valueOf(projet.getDateDebut()) : null);
            pstm.setDate(3, projet.getDateFin() != null ? Date.valueOf(projet.getDateFin()) : null);
            pstm.setBigDecimal(4, projet.getCout());
            pstm.setInt(5, projet.getDisciplineDeBase().getIdDiscipline());
            pstm.setInt(6, projet.getIdProjet());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return readProjet(projet.getIdProjet());
            else return null;
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
            return null;
        }
    }

    @Override
    public Projet readProjet(int idprojet) {
        String query = "select * from APIDISCIPLINEPROJET where idprojet=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idprojet);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int iddiscipline = rs.getInt(1);
                String nomdis = rs.getString(2);
                String desc = rs.getString(3);
                Discipline dis = new Discipline(iddiscipline, nomdis, desc);
                int idproj = rs.getInt(4);
                String nomProj = rs.getString(5);
                Date date = rs.getDate(6);
                LocalDate datedebut = date != null ? date.toLocalDate() : null;
                date = rs.getDate(7);
                LocalDate datefin = date != null ? date.toLocalDate() : null;
                BigDecimal cout = rs.getBigDecimal(8);
                return new Projet(idproj, nomProj, datedebut, datefin, cout, dis);
            }else{
                return null;
            }
        }catch(SQLException e){
            System.err.println("erreur sql : "+e);
            return null;
        }
    }

    @Override
    public List<Projet> getProjets() {
        List<Projet> lproj=new ArrayList<>();
        String query = "select * from APIDISCIPLINEPROJET";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()) {
                int iddiscipline = rs.getInt(1);
                String nomdis = rs.getString(2);
                String desc = rs.getString(3);
                Discipline dis = new Discipline(iddiscipline, nomdis, desc);
                int idproj = rs.getInt(4);
                String nomProj = rs.getString(5);
                Date date = rs.getDate(6);
                LocalDate datedebut = date != null ? date.toLocalDate() : null;
                date = rs.getDate(7);
                LocalDate datefin = date != null ? date.toLocalDate() : null;
                BigDecimal cout = rs.getBigDecimal(8);
                Projet proj = new Projet(idproj, nomProj, datedebut, datefin, cout, dis);
               lproj.add(proj);
            }
                return lproj;
        }catch(SQLException e){
            System.err.println("erreur sql : "+e);
            return null;
        }
    }

    @Override
    public boolean addEmploye(Projet proj, Employe emp,int pourcentage) {
        String query="insert into APITRAVAIL(idprojet,idemploye,dateengag,pourcentage) values(?,?,CURRENT_DATE,?)";
        try(PreparedStatement pstm= dbConnect.prepareStatement(query)){
            pstm.setInt(1,proj.getIdProjet());
            pstm.setInt(2,emp.getIdEmploye());
            pstm.setInt(3,pourcentage);
            int n=pstm.executeUpdate();
            if(n!=0) return true;
            else return false;
        }catch(SQLException e){
            System.err.println("erreur sql :"+e);
            return false;
        }
    }

    @Override
    public boolean updateEmploye(Projet proj, Employe emp, int pourcentage) {
        String query = "update APITRAVAIL set pourcentage=? where idprojet=? and idemploye=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, pourcentage);
            pstm.setInt(2, proj.getIdProjet());
            pstm.setInt(3, emp.getIdEmploye());
            int n = pstm.executeUpdate();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql: " + e);
            return false;
        }
    }

    @Override
    public boolean removeEmploye(Projet proj, Employe emp) {
        String query="delete from apitravail where idprojet=? and idemploye=?";
        try(PreparedStatement pstm=dbConnect.prepareStatement(query)){
            pstm.setInt(1,proj.getIdProjet());
            pstm.setInt(2,emp.getIdEmploye());
            int n=pstm.executeUpdate();
            if(n!=0) return true;
            else return false;
        }catch(SQLException e){
            System.err.println("erreur sql : "+e);
            return false;
        }
    }

    @Override
    public List<Travail> getEmployes(Projet proj) {
        List<Travail> lt=new ArrayList<>();
        String query="select * from APITRAVAILEMPLOYE where idprojet=?";
        try(PreparedStatement pstm=dbConnect.prepareStatement(query)){
            pstm.setInt(1,proj.getIdProjet());
            ResultSet rs=pstm.executeQuery(query);
            while(rs.next()){
                int idtravail=rs.getInt(1);
                int idprojet=rs.getInt(2);
                int idemploye=rs.getInt(3);
                Date dte=rs.getDate(4);
                LocalDate dateengag=dte!=null?dte.toLocalDate():null;
                int pourcentage=rs.getInt(5);
                String mat=rs.getString(6);
                String nom=rs.getString(7);
                String prenom=rs.getString(8);
                String tel=rs.getString(9);
                String mail=rs.getString(10);
                Employe emp=new Employe(idemploye,mat,nom,prenom,tel,mail);
                Travail t= new Travail(idtravail,pourcentage,dateengag,emp);
                lt.add(t);
            }
        }catch(SQLException e){
            System.err.println("erreur sql :"+e);
        }
        return lt;
    }

    @Override
    public List getNotification() {
        return getProjets();
    }
}
