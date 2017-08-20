package com.univem.aula01_login;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule public ActivityTestRule<LoginActivity> rule
            = new ActivityTestRule<>(LoginActivity.class, false);

    @Test
    public void aoIniciarActivityDevePreencherUsuarioESenha() throws Exception {
        iniciarActivity();

        final String login = "adm";
        final String senha = "123";

        onView(withId(R.id.edtLogin)).perform(typeText(login));

        onView(withId(R.id.edtSenha)).perform(typeText(senha));

        onView(withId(R.id.btnLogin)).perform(click());
        onView(withText(login)).check(matches(isDisplayed()));
        Thread.sleep(1500);
        onView(withText(senha)).check(matches(isDisplayed()));
    }

    private void iniciarActivity() {
        rule.launchActivity(new Intent());
    }

}