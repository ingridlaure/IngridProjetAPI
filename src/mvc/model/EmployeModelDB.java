package mvc.model;

import GestionProjet.Metier.Competence;
import GestionProjet.Metier.Discipline;
import GestionProjet.Metier.Employe;
import myconnections.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeModelDB extends DAOEmploye {
    protected Connection dbConnect;

    public EmployeModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }

    }

    @Override
    public Employe addEmploye(Employe employe) {
        String query1 = "insert into APIEMPLOYE(matricule,nom,prenom,tel,mail) values(?,?,?,?,?)";
        String query2 = "select idemploye from APIEMPLOYE where matricule= ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, employe.getMatricule());
            pstm1.setString(2, employe.getNom());
            pstm1.setString(3, employe.getPrenom());
            pstm1.setString(4, employe.getTel());
            pstm1.setString(5, employe.getMail());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setString(1, employe.getMatricule());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idEmploye = rs.getInt(1);
                    employe.setIdEmploye(idEmploye);
                    notifyObservers();
                    return employe;
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
    public boolean removeEmploye(Employe employe) {
        String query = "delete from APIEMPLOYE where matricule = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, employe.getMatricule());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return false;
        }
    }

    @Override
    public Employe updateEmploye(Employe employe) {
        String query = "update APIEMPLOYE set nom=?,prenom=?,tel=?,mail=? where matricule = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, employe.getNom());
            pstm.setString(2, employe.getPrenom());
            pstm.setString(3, employe.getTel());
            pstm.setString(4, employe.getMail());
            pstm.setString(5, employe.getMatricule());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return readEmploye(employe.getMatricule());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public Employe readEmploye(String mat) {
        String query = "select * from APIEMPLOYE where matricule = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, mat);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int idEmploye=rs.getInt(1);
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                String mail = rs.getString(6);
                Employe emp = new Employe(idEmploye, matricule, nom, prenom, tel, mail);
                return emp;

            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return null;
        }
    }

    @Override
    public List<Employe> getEmployes() {
        List<Employe> lemp = new ArrayList<>();
        String query = "select * from APIEMPLOYE";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int idEmploye = rs.getInt(1);
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                String mail = rs.getString(6);
                Employe emp = new Employe(idEmploye, matricule, nom, prenom, tel, mail);
                lemp.add(emp);
            }
            return lemp;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public boolean addDiscipline(Employe emp, Discipline dis, int niveau) {
        String query="insert into APICOMPETENCE(idemploye,iddiscipline,niveau) values(?,?,?)";
        try(PreparedStatement pstm=dbConnect.prepareStatement(query)){
            pstm.setInt(1,emp.getIdEmploye());
            pstm.setInt(2,dis.getIdDiscipline());
            pstm.setInt(3,niveau);
            int n=pstm.executeUpdate();
            if(n!=0) return true;
            else return false;
        }catch(SQLException e){
            System.out.println("erreur sql : "+e);
            return false;
        }
    }

    @Override
    public boolean updateDiscipline(Employe emp, Discipline dis, int niveau) {
        String query = "update  APICOMPETENCE set niveau= ? where idemploye = ? AND iddiscipline = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,niveau);
            pstm.setInt(2,emp.getIdEmploye());
            pstm.setInt(3,dis.getIdDiscipline());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public boolean removeDiscipline(Employe emp, Discipline dis) {
        String query = "DELETE FROM  APICOMPETENCE where  idemploye = ? AND iddiscipline = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,emp.getIdEmploye());
            pstm.setInt(2,dis.getIdDiscipline());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public List<Competence> getDisciplines(Employe emp) {
        String query="SELECT * FROM COMPETENCEDISCIPLINE WHERE IDDISCIPLINE=?";
        List<Competence> ll=new ArrayList<>();
        try(PreparedStatement pstm=dbConnect.prepareStatement(query)){
            pstm.setInt(1,emp.getIdEmploye());
            ResultSet rs=pstm.executeQuery();
            while(rs.next()){
                int idCompetence=rs.getInt("idcompetence");
                int idEmploye=rs.getInt("idemploye");
                int idDiscipline=rs.getInt("iddiscipline");
                int niveau=rs.getInt("niveau");
                String nom=rs.getString("nom");
                String description=rs.getString("description");
                Discipline dis=new Discipline(idDiscipline,nom,description);
                Competence comp=new Competence(idCompetence,niveau,dis);
                ll.add(comp);
            }
        }catch(SQLException e){
            System.err.println("erreur sql : "+e);
        }
        return ll;
    }

    @Override
    public List getNotification() {
        return getEmployes();
    }

}
