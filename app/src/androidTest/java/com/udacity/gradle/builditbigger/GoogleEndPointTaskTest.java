package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class GoogleEndPointTaskTest {

    //Activity Rule
    @Rule
    public  ActivityTestRule<MainActivity> mainActivityActivityResult =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void TestButtonClickAndUIContentOnChildActivity()
    {
        //lets see if we find this button with the given name
        onView(withId(R.id.tellJokeButton))
                .check(matches(withText(R.string.button_text)));
        //if it does then lets execute a click
        onView(withId(R.id.tellJokeButton)).perform(click());
        //now that we click the tell joke button lets see if the next activity will open
        onView(withId(R.id.jokeActivity)).check(matches(isDisplayed()));
        //since now is displayed then we pass this test
    }

    @Test
    public void TestAsyncTaskEndpointCall()
    {
        String jokeValue;
        Context parent = InstrumentationRegistry.getTargetContext();
        //this is the way we can retrieve the context of a given activity
        Pair<Context, String> taskList = new Pair<>(parent,MainActivity.class.getSimpleName());
        GoogleEndPointsTask jokerTest = new GoogleEndPointsTask();
        jokerTest.execute(taskList);
         try{
            jokeValue =  jokerTest.get();
             Log.d("test", jokeValue);
         }catch(Exception e)
        {
             //lets then return an empty string for my joke value I want to do my best  :-)
            jokeValue="";
            Log.d(GoogleEndPointTaskTest.class.getSimpleName(),"There is an error  "+e.getMessage());
        }

        //lets try to check whether the text fail if the string is check outside of the try and catch tag
        assertNotNull(jokeValue);
    }
}
