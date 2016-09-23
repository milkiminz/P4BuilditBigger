package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Milki Minz on 9/19/2016.
 */
class EndpointAsyncTask extends AsyncTask<com.udacity.gradle.builditbigger.MainActivityFragment, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private com.udacity.gradle.builditbigger.MainActivityFragment mainActivityFragment;

    @Override
    protected String doInBackground(com.udacity.gradle.builditbigger.MainActivityFragment... params) {
        mainActivityFragment = params[0];
        context = mainActivityFragment.getActivity();
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new
                    AndroidJsonFactory(), null)
                    .setRootUrl("https:/joketelling-143915.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }




        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mainActivityFragment.loadedJoke = result;
        mainActivityFragment.launchDisplayJokeActivity();
    }
}
