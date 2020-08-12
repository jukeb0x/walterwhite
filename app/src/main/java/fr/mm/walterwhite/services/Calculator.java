package fr.mm.walterwhite.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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

    private static String getColorFromPreferences(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString("sp_nb", "Vert");
    }


    public static double computePoints(double energy, double fat, double sugar, double protein, Context context){
        double[] coeffPerso=coeffs.get(getColorFromPreferences(context));
        double points=energy*coeffPerso[0]+fat*coeffPerso[1]+sugar*coeffPerso[2]+protein*coeffPerso[3];
        return Math.rint(points);
    }

    public static double computePortionPoints(double energy, double fat, double sugar, double protein, double portion, Context context){
        return computePoints(energy*portion, fat*portion, sugar*portion, protein*portion, context);
    }

    public static int computeRoundPoints(double points){
        return (int) Math.rint(points);
    }
}
