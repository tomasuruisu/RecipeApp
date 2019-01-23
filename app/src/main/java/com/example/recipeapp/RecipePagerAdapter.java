package com.example.recipeapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class RecipePagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Recipe> recipes;

    public RecipePagerAdapter(FragmentManager fm, ArrayList<Recipe> recipes) {
        super(fm);
        this.recipes = recipes;
    }

    @Override
    public Fragment getItem(int i) {

        return RecipeFragment.newInstance(recipes.get(i));
    }

    @Override
    public int getCount() {
        return recipes.size();
    }
}
