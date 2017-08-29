package com.univem.aula01_login;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> rule
            = new IntentsTestRule<>(MainActivity.class, true, false);

    @Test
    public void aoClicarEmBotaoDetalheProduto_DeveReceberStringDeRetorno() {
        rule.launchActivity(new Intent());

        Intent intent = new Intent();
        intent.putExtra("meu_extra", "qualquer coisa");

        Intents.intending(IntentMatchers
                .hasComponent(DetalheProdutoActivity.class.getName()))
                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, intent));

        Espresso.onView(ViewMatchers.withId(R.id.btnDetalheProduto))
                .perform(ViewActions.click());
    }
}