package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.R;

import tech.niocoders.com.jokelibrary.jokeActivity;


public class MainActivity extends AppCompatActivity {
    public static final String JOKE_TEXT="JOKE_TEXT";
    public static ProgressBar loader;

    private InterstitialAd mIterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader = findViewById(R.id.progressBar);
        MobileAds.initialize(this,"ca-app-pub-7712232350432668/2678557207");

        //lets instantiate our dd
        mIterstitialAd = new InterstitialAd(this);
        mIterstitialAd.setAdUnitId("ca-app-pub-7712232350432668/2678557207");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showEndPointJokes(String text)
    {
        Class child =  jokeActivity.class;
        Context context = this;
        Bundle bundle = new Bundle();
        bundle.putString(JOKE_TEXT, text);
        Intent intent = new Intent(context,child);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void tellJoke(View view) {
        //generate the joke
        //load the interstitial ad
        mIterstitialAd.loadAd(new AdRequest.Builder().build());
        loader.setVisibility(View.VISIBLE);
        //now lets implement listener to do some great work
        mIterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
              //lets hide the progressbar and show the add
                loader.setVisibility(View.INVISIBLE);
                if (mIterstitialAd.isLoaded()) {
                    mIterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                loader.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                loader.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                loader.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                loader.setVisibility(View.INVISIBLE);
                 final Context context =  MainActivity.this;
                Pair<Context, String> taskList = new Pair<Context, String>(context, MainActivity.class.getSimpleName());
                new GoogleEndPointsTask().execute(taskList);
            }
        });

    }


}
