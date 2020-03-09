package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.Iterator;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        debugPrintSandwich(sandwich); //TODO: Remove debug code
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    // TODO(2): Complete this method
    private void populateUI() {

    }

    /* Temporary debug code to print sandwich details in log */ // TODO: Remove this debug code
    private void debugPrintSandwich(Sandwich sandwich){
        Log.v("DetailsActivity", sandwich.getMainName());

        Iterator i = sandwich.getAlsoKnownAs().iterator();
        while(i.hasNext()){
            Log.v("DetailsActivity", i.next().toString());
        }

        Log.v("DetailsActivity", sandwich.getPlaceOfOrigin());

        Log.v("DetailsActivity", sandwich.getDescription());

        Log.v("DetailsActivity", sandwich.getImage());

        i = sandwich.getIngredients().iterator();
        while(i.hasNext()){
            Log.v("DetailsActivity", i.next().toString());
        }

    }
}
