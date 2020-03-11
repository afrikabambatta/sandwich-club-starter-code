package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        /* All names are children of the "name" object. */
        final String NAME = "name";

        /* The main name and nickname of the sandwich */
        final String MAIN_NAME = "mainName";
        final String NICKNAME = "alsoKnownAs";

        /* The location the sandwich is originated */
        final String ORIGIN = "placeOfOrigin";

        /* A description detailing what is in the sandwich and how to make it */
        final String DESCRIPTION = "description";

        /* An image of the sandwich */
        final String IMAGE = "image";

        /* A string array of ingredients required to make the sandwich */
        final String INGREDIENTS = "ingredients";

        /* The sandwich object we will populate with data from JSON Object */
        Sandwich sandwich = new Sandwich();

        try {
            /* JSONObject created using string array "sandwich_details" from string.xml */
            JSONObject sandwichJson = new JSONObject(json);

            /* Get JSON object that contains sandwich names */
            JSONObject name = sandwichJson.getJSONObject(NAME);

            /* Get the sandwich name */
            sandwich.setMainName(name.getString(MAIN_NAME));

            /* Parse the JSON array to get sandwich nicknames */
            JSONArray akaArray = name.getJSONArray(NICKNAME);
            ArrayList<String> akaList = new ArrayList<String>();
            for (int i = 0; i < akaArray.length(); i++) {
                akaList.add(akaArray.getString(i));
            }
            sandwich.setAlsoKnownAs(akaList);

            /* Get sandwich origin */
            sandwich.setPlaceOfOrigin(sandwichJson.getString(ORIGIN));

            /* Get sandwich description */
            sandwich.setDescription(sandwichJson.getString(DESCRIPTION));

            /* Get sandwich image */
            sandwich.setImage(sandwichJson.getString(IMAGE));

            /* Retrieve JSONArray of ingredients and convert to ArrayList */
            JSONArray ingredientsArray = sandwichJson.getJSONArray(INGREDIENTS);
            ArrayList<String> ingredientsList = new ArrayList<>();
            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredientsList.add(ingredientsArray.getString(i));
            }
            sandwich.setIngredients(ingredientsList);
        } catch (JSONException e){ //
            e.printStackTrace();
        }
        return sandwich;

    }
}
