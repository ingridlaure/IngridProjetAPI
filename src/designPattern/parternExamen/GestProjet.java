package designPattern.parternExamen;

import java.math.BigDecimal;

public class GestProjet {
    public static void main(String[] args) {
        Discipline dis = new Discipline(1, "Base de données", "Histoire de la base de donnée", new BigDecimal(2000));
        try {
            Projet pro = new Projet.ProjetBuilder().setIdProjet(1).setNom("site de ecommerce").setCout(new BigDecimal(3000)).build();
            System.out.println(pro);
        } catch (
                Exception ex) {
            System.out.println(ex);

        }
    }
}

