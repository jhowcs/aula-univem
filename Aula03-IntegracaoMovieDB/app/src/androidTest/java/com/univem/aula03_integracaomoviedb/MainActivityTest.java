package com.univem.aula03_integracaomoviedb;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    @Rule
    public final ServerRule rule = new ServerRule();

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, false);


    @Test
    public void aoIniciarActivity_DeveExibirListaDeFilmesPopulares() throws InterruptedException {
        rule.server.addFixture(200, "popular_movies_result_ok.json");

        activityTestRule.launchActivity(new Intent());

        Espresso.onView(
                Matchers.allOf(
                        ViewMatchers.withId(R.id.txtNomeFilme),
                        ViewMatchers.withText("Minions"))
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}