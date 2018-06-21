package com.univem.aula03_integracaomoviedb;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import br.com.concretesolutions.requestmatcher.model.HttpMethod;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Rule
    public ServerRule serverRule = new ServerRule();


    @Test
    public void aoIniciarActivity_DeveExibirListaDeFilmesPopulares() {
        serverRule.getServer().addFixture(200, "popular_movies_result_ok.json")
                ;

        activityTestRule.launchActivity(new Intent());
        serverRule.sleep(5000);
        Espresso.onView(
                Matchers.allOf(
                        ViewMatchers.withId(R.id.txtNomeFilme),
                        ViewMatchers.withText("Minions"))
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}