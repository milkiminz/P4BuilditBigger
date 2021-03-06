package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.jokefactory.DisplayJokeActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ProgressBar progressBar = null;
    public String loadedJoke = null;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main_activity, container, false);


        Button button = (Button) root.findViewById(R.id.joke_btn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getJoke();
            }
        });

        progressBar = (ProgressBar) root.findViewById(R.id.joke_progressbar);
        progressBar.setVisibility(View.GONE);
        return root;
    }
    public void getJoke(){
        new EndpointAsyncTask().execute();
    }

    public void launchDisplayJokeActivity(){
        Context context = getActivity();
        Intent intent = new Intent(context, DisplayJokeActivity.class);
        intent.putExtra(context.getString(R.string.jokeEnvelope), loadedJoke);
        //Toast.makeText(context, loadedJoke, Toast.LENGTH_LONG).show();
        context.startActivity(intent);
        progressBar.setVisibility(View.GONE);
    }

}
