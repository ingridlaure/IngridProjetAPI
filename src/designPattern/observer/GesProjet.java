package designPattern.observer;


import designPattern.observer.Employe;

import java.math.BigDecimal;

public class GesProjet {
    public static void main(String[] args){
        Projet p1=new Projet(2,"Site ecommerce de la boulangerie du coin",5000);
        Projet p2=new Projet(5,"Site ecommerce de la boulangerie du coin",1000);

       Employe emp1=new Employe(2,"005","ngoune","ingrid","0466117860","ingridngoune@gmail.com");
       Employe emp2=new Employe(2,"009","wansi","simeon","017860","simeonwansi@gmail.com");
       p1.addObserver(emp1);
       p1.addObserver(emp2);
       p2.addObserver(emp1);

       p1.setCout(1200);
       p2.setCout(500);


    }
}
