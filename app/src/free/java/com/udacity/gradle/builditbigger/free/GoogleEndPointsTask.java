package com.udacity.gradle.builditbigger.free;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.text.TextUtils;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import tech.niocoders.com.jokelibrary.jokeActivity;

public class GoogleEndPointsTask extends AsyncTask<Pair<Context, String>, Void,String> {
    //perfect now my api Servicee object is being recognized by the asynctask class object
    private static MyApi myApiService = null;
    private static final String LOCAL_HOST = "http://10.0.2.2:8080/_ah/api/";
    private Context context;
    private MainActivity myParentActivity;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(LOCAL_HOST)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            myApiService = builder.build();
        }

        context = params[0].first;

        try {
            return myApiService.getJokes().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(!TextUtils.isEmpty(result)) {
            Class child = jokeActivity.class;
            Bundle bundle = new Bundle();
            bundle.putString(jokeActivity.JOKE_TEXT, result);
            Intent intent = new Intent(context, child);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
}

