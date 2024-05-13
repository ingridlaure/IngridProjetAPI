package designPattern.singleton;

import java.util.ArrayList;
import java.util.List;

public class Journal {
    private List<String> lignes;
    private static Journal journal;

    private Journal(){lignes=new ArrayList<>();}
    public static Journal getInstance(){
        if(journal==null) journal=new Journal();
        return journal;
    }
    public List<String> getLignes(){return lignes;}
}
