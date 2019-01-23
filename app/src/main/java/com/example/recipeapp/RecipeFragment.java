package com.example.recipeapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RecipeFragment extends Fragment {

    Recipe recipe;

    public static Fragment newInstance(Recipe recipe) {
        RecipeFragment recipeFragment = new RecipeFragment();

        Bundle args = new Bundle();
        args.putParcelable("recipe", recipe);
        recipeFragment.setArguments(args);
        return recipeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipe = getArguments().getParcelable("recipe");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.recipe_content, container, false
        );

        TextView title = rootView.findViewById(R.id.recipe_title);
        ImageView imageView = rootView.findViewById(R.id.recipe_image);

        title.setText(recipe.getTitle());
        Glide.with(this)
                .load(recipe.getImageUrl())
                .into(imageView);

        return rootView;
    }
}
