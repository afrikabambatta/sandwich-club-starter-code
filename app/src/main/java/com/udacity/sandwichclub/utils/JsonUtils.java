package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

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

        return null;

    }
}
