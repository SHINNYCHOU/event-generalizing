package edu.buaa.edu.wordsimilarity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class out {
    private Set<String> strings= new HashSet<>();
    public void out(){
        strings.add("q");
        strings.add("w");
        Map<Integer,Set<String>> subject=new HashMap<>();
        subject.put(1,strings);
        for (Map.Entry<Integer, Set<String>> entry : subject.entrySet()){
            System.out.println(entry.getValue().size());
        }
    }
    public static void main(String[] args) {
        out d=new out();
        d.out();
//        d.dimensionSubject();
//        d.dimensionObject();
//        d.dimensionPredicate();
    }

}
