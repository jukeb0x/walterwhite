package fr.mm.walterwhite.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

    //creation script
    public static final String CREATE_CONSOMMATION_TABLE_SCRIPT = "Note_Content";
    public static final String CREATE_INGREDIENT_TABLE_SCRIPT = "Note_Content";
    public static final String CREATE_RECIPE_TABLE_SCRIPT = "Note_Content";
    public static final String CREATE_RECIPECONTENT_TABLE_SCRIPT = "Note_Content";
    public static final String CREATE_WEIGHT_TABLE_SCRIPT = "Note_Content";

    //drop script
    public static final String DROP_CONSOMMATION_TABLE_SCRIPT = "Note_Content";
    public static final String DROP_INGREDIENT_TABLE_SCRIPT = "Note_Content";
    public static final String DROP_RECIPE_TABLE_SCRIPT = "Note_Content";
    public static final String DROP_RECIPECONTENT_TABLE_SCRIPT = "Note_Content";
    public static final String DROP_WEIGHT_TABLE_SCRIPT = "Note_Content";

    //get requests
    public static final String GET_CONSOMMATION_TABLE_SCRIPT = "Note_Content";
    public static final String GET_INGREDIENT_TABLE_SCRIPT = "Note_Content";
    public static final String GET_RECIPE_TABLE_SCRIPT = "Note_Content";
    public static final String GET_RECIPECONTENT_TABLE_SCRIPT = "Note_Content";
    public static final String GET_WEIGHT_TABLE_SCRIPT = "Note_Content";


    public static final List<String> DAYS = new ArrayList<>(
            Arrays.asList("Je", "Ve","Sa","Di","Lu","Ma","Me")
    );

    public static final List<String> MEALS = new ArrayList<>(
            Arrays.asList("PETIT-DEJEUNER", "DEJEUNER","DINER","COLLATION"));



}
