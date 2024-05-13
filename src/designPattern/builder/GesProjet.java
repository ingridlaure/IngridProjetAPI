package designPattern.builder;

public class GesProjet {
    public static  void main(String[] args){
        System.out.println();
        System.out.println(" Builder Employe");
        // builder Employe
        try{
            Employe emp1=new Employe.EmployeBuilder().
                    setIdEmploye(1).setNom("Ngoune").setPrenom("Ingrid").setMatricule("004").setTel("0466117860").setMail("ngouneingrid@gmail.com").build();
            System.out.println(emp1);
        }catch(Exception e){
            System.out.println("erreur"+e);
        }

        try{
            Employe emp2=new Employe.EmployeBuilder().
                    setIdEmploye(2).setNom("Ngoune").setPrenom("Ingrid").setMatricule("004").setTel("0466117860").build();
            System.out.println(emp2);
        }catch(Exception e){
            System.out.println("erreur"+e);
        }

        System.out.println();
        System.out.println(" Builder Discipline");
        // builder discipline
        try{
            Discipline dis1=new Discipline.DisciplineBuilder().setIdDiscipline(3).setNom("base de données").setDescription("utilisation de oracle et postgre sql").build();
            System.out.println(dis1);
        }catch(Exception e){
            System.out.println("Erreur : "+e);
        }
        try{
            Discipline dis2=new Discipline.DisciplineBuilder().setIdDiscipline(4).setDescription("utilisation de oracle et postgre sql").build();
            System.out.println(dis2);
        }catch(Exception e){
            System.out.println("Erreur : "+e);
        }
    }
}
