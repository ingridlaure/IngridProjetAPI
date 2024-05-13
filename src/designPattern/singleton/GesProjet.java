package designPattern.singleton;

public class GesProjet {
    public static void main(String [] args){
        Discipline dis=new Discipline(3,"programmation web","html,css,javascript,php");
        Employe emp=new Employe(2,"005","ngoune","ingrid","0466117860","ingridngoune@gmail.com");
        for(String l:Journal.getInstance().getLignes()){
            System.out.println(l);
        }
    }
}
