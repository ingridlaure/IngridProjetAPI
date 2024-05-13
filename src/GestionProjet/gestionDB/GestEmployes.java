package gestionDB;

import GestionProjet.Metier.Employe;
import myconnections.DBConnection;

import java.sql.*;
import java.util.Scanner;

public class GestEmployes {
    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;


    public void gestion() {
        dbConnect = DBConnection.getConnection();

        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("Connexion établi avec success");
        String[] options = new String[]{"Ajout", "Recherche", "Modification", "Supression", "Tous", "Fin"};
        int choix;
        do {
            choix = menu(options);
            switch (choix) {
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modification();
                    break;
                case 4:
                    suppresion();
                    break;
                case 5:
                    tous();
                    break;
                case 6:
                    System.out.println("Aurevoir");
                    break;
            }
        } while (choix != options.length);
    }


    public void ajout() {
        System.out.println("Matricule : ");
        String matricule = sc.nextLine();
        System.out.println("Nom : ");
        String nom = sc.nextLine();
        System.out.println("Prenom : ");
        String prenom = sc.nextLine();
        System.out.println("Telephone : ");
        String telephone = sc.nextLine();
        System.out.println("Mail : ");
        String mail = sc.nextLine();
        String query1 = "insert into APIEMPLOYE(matricule,nom,prenom,tel,mail) VALUES(?,?,?,?,?)";
        String query2 = "select idemploye from APIEMPLOYE where matricule=? ";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, matricule);
            pstm1.setString(2, nom);
            pstm1.setString(3, prenom);
            pstm1.setString(4, telephone);
            pstm1.setString(5, mail);
            int n = pstm1.executeUpdate();
            System.out.println(n + "ligne insérée avec success");
            if (n == 1) {
                pstm2.setString(1, matricule);
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idEmploye = rs.getInt(1);
                    System.out.println("idEmploye = " + idEmploye);
                } else {
                    System.out.println("record introuvable");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e);
        }

    }

    public void recherche() {
        System.out.println("Matricule de l'employé recherché");
        String matRech = sc.nextLine();
        String query = "select * from APIEMPLOYE where matricule=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, matRech);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                String mail = rs.getString(6);
                Employe emp=new Employe(id,matricule,nom,prenom,tel,mail);
                System.out.println(emp);
            }else{
                System.out.println("l'employe n'existe pas");
            }

        } catch (SQLException e) {
            System.out.println(" erreur sql : " +e);
        }

    }

    public void modification() {
        System.out.println("Matricule de l'employe recherchée");
        String mat=sc.nextLine() ;
        System.out.println("Nouveau numero de téléphone");
        String tel=sc.nextLine();
        String query="update APIEMPLOYE set tel=? where matricule=? ";
        try(PreparedStatement pstm=dbConnect.prepareStatement(query)){
            pstm.setString(1,tel);
            pstm.setString(2,mat);
            int flag=pstm.executeUpdate();
            if(flag!=0){
                System.out.println(" ligne mise à jour avec success");
            }else{
                System.out.println("Employé introuvable");
            }
        }catch(SQLException e){
            System.out.println("erreur sql : "+e);
        }

    }

    public void suppresion() {
        System.out.println("Matricule du de l'employé à supprimer");
        String mat=sc.nextLine();
        String query="delete from APIEMPLOYE where matricule=?";
        try(PreparedStatement pstm=dbConnect.prepareStatement(query)){
            pstm.setString(1,mat);
            int flag=pstm.executeUpdate();
            if(flag!=0) {
                System.out.println("Employé supprimé avec succes");
            }else{
                System.out.println(" Employé introuvable");
            }

        }catch(SQLException e){
            System.out.println("Erreur sql : "+e);
        }

    }

    public void tous() {
        String query="select * from APIEMPLOYE";
        try(Statement stm=dbConnect.createStatement()){
            ResultSet rs= stm.executeQuery(query);
            while(rs.next()){
                int id=rs.getInt(1);
                String mat=rs.getString(2);
                String nom=rs.getString(3);
                String prenom=rs.getString(4);
                String tel=rs.getString(5);
                String mail=rs.getString(6);
                Employe emp=new Employe(id,mat,nom,prenom,tel,mail);
                System.out.println(emp);
            }
        }catch(SQLException e) {
            System.out.println("Erreur sql : " + e);
        }

    }

    public int menu(String[] options) {
        int i = 1, choix;
        for (String opt : options) {
            System.out.println(i + ". " + opt);
            i++;
        }
        do {
            System.out.println("Choix : ");
            choix = sc.nextInt();
            sc.skip("\n");
        } while (choix < 1 || choix > options.length);
        return choix;
    }

    public static void main(String[] ars) {
        GestEmployes g = new GestEmployes();
        g.gestion();
    }
}
