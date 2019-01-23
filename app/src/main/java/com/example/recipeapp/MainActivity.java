package com.example.recipeapp;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends FragmentActivity {

    private ArrayList<Recipe> recipes = new ArrayList<>();

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 3;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestData();

        // Instantiate a ViewPager and a PagerAdapter.
        mPagerAdapter = new RecipePagerAdapter(getSupportFragmentManager(), recipes);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mPagerAdapter);


    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private void updateUI() {
        mPagerAdapter.notifyDataSetChanged();
    }

    private void requestData() {
        RecipeApiService service = RecipeApiService.retrofit.create(RecipeApiService.class);
        retrofit2.Call<RecipeList> call = service.getRecipes();
        call.enqueue(new Callback<RecipeList>() {
            @Override
            public void onResponse(Call<RecipeList> call, Response<RecipeList> response) {
                RecipeList recipesResponse = response.body();
                recipes.addAll(Arrays.asList(recipesResponse.recipes));
                updateUI();
            }

            @Override
            public void onFailure(Call<RecipeList> call, Throwable t) {
                Log.d("recipes", t.getMessage());
            }
        });
    }
}
