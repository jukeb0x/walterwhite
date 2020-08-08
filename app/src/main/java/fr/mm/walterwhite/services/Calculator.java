package fr.mm.walterwhite.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Calculator {

    private static final Map<String, double[]> coeffs;
    static {
        Map<String, double[]> map = new HashMap<>();
        double[] bleu= {0.0302592171847568,0.272821576763485,0.121374865735768, -0.102941176470588};
        double[] vert= {0.0302592171847568,0.272821576763485,0.121374865735768, -0.102941176470588};
        double[] violet= {0.0302592171847568,0.272821576763485,0.121374865735768, -0.102941176470588};
        map.put("Bleu", bleu);
        map.put("Vert", vert);
        map.put("Violet", violet);
        coeffs = Collections.unmodifiableMap(map);
    }

    private static String getColorFromPreferences(){
        return "Bleu";
    }


    public static int computePoints(double energy, double fat, double sugar, double protein){
        double[] coeffPerso=coeffs.get(getColorFromPreferences());
        double points=energy*coeffPerso[0]+fat*coeffPerso[1]+sugar*coeffPerso[2]+protein*coeffPerso[3];
        return (int) Math.rint(points);
    }

    public static int computePortionPoints(double energy, double fat, double sugar, double protein, double portion){
        return computePoints(energy*portion, fat*portion, sugar*portion, protein*portion);
    }
}
