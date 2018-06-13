package com.univem.aula03_integracaomoviedb;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import okhttp3.mockwebserver.MockResponse;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Rule
    public ServerRule serverRule = new ServerRule();


    @Test
    public void aoIniciarActivity_DeveExibirListaDeFilmesPopulares() {
        serverRule.getServer().enqueue(new MockResponse().setResponseCode(200).setBody(FilmesPopularesMock.mock));
        activityTestRule.launchActivity(new Intent());
        Espresso.onView(
                Matchers.allOf(
                        ViewMatchers.withId(R.id.txtNomeFilme),
                        ViewMatchers.withText("Minions"))
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}