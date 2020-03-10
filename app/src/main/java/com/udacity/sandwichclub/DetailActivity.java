package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    /* Fill the text views in activity_detail.xml with json data extracted into Sandwich object */
    private void populateUI(Sandwich sandwich) {
        // setText for alsoKnownAs
        TextView nickname = findViewById(R.id.also_known_tv);
        listSetText(sandwich.getAlsoKnownAs(), nickname);

        // setText for place of origin
        TextView origin = findViewById(R.id.origin_tv);
        origin.setText(sandwich.getPlaceOfOrigin());

        // setText for description
        TextView description = findViewById(R.id.description_tv);
        description.setText(sandwich.getDescription());

        // setText for ingredients
        TextView ingredients = findViewById(R.id.ingredients_tv);
        listSetText(sandwich.getIngredients(), ingredients);
    }

    /* Helper function used to correctly format a List into a text view */
    private void listSetText(List<String> list, TextView textView){
        for(int i = 0; i < list.size(); i++ ){
            if(i == list.size() - 1) {
                textView.append(list.get(i));
            }else{
                textView.append(list.get(i) + ", ");
            }
        }
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
