package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.udacity.gradle.builditbigger.R;

import tech.niocoders.com.jokelibrary.jokeActivity;


public class MainActivity extends AppCompatActivity {
    public static final String JOKE_TEXT="JOKE_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Pair<Context, String> taskList = new Pair<Context, String>(this, MainActivity.class.getSimpleName());
        new GoogleEndPointsTask().execute(taskList);
    }


}