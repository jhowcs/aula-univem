package com.univem.aula01_login;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule public IntentsTestRule<LoginActivity> rule
            = new IntentsTestRule<>(LoginActivity.class, true, false);

    Robots robots;

    final String login = "adm";
    final String senha = "123";

    @Before
    public void setup() {
        robots = new Robots(rule);
    }

    @Test
    public void aoIniciarActivityDevePreencherUsuarioESenha() {
        robots.iniciarActivity()
                .digitarLogin(login)
                .digitarSenha(senha)
                .campoLoginVisivel()
                .campoSenhaVisivel()
                .clicarEmLogin();
    }

    @Test
    public void aoClicarEmLoginComUsuarioESenhaValidoDeveIrParaActivityPrincipal() {
        robots.iniciarActivity()
                .digitarLogin(login)
                .digitarSenha(senha)
                .clicarEmLogin()
                .redirecionaParaMainActivity();
    }

    @Test
    public void aoClicarEmLoginComUsuarioESenhaInvalido_DeveExibirMensagemDeErro() {
        final String loginInvalido = "adm1";

        robots.iniciarActivity()
                .digitarLogin(loginInvalido)
                .digitarSenha(senha)
                .clicarEmLogin()
                .fecharTeclado()
                .exibeMensagemToast(R.string.mensagem_erro_login);
    }

    public class Robots {

        public IntentsTestRule<LoginActivity> rule;

        public Robots(IntentsTestRule<LoginActivity> rule) {
            this.rule = rule;
        }

        Robots iniciarActivity() {
            rule.launchActivity(new Intent());
            return this;
        }

        ViewInteraction campoLogin() {
            return Espresso.onView(ViewMatchers.withId(R.id.edtLogin));
        }

        ViewInteraction campoSenha() {
            return Espresso.onView(ViewMatchers.withId(R.id.edtSenha));
        }

        ViewInteraction botaoLogin() {
            return Espresso.onView(ViewMatchers.withId(R.id.btnLogin));
        }

        Robots fecharTeclado() {
            Espresso.closeSoftKeyboard();
            return this;
        }

        Robots digitarLogin(final String texto) {
            campoLogin().perform(ViewActions.typeText(texto));
            return this;
        }

        Robots digitarSenha(final String texto) {
            campoSenha().perform(ViewActions.typeText(texto));
            return this;
        }

        Robots clicarEmLogin() {
            botaoLogin().perform(ViewActions.click());
            return this;
        }

        Robots campoLoginVisivel() {
            campoLogin().check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
            return this;
        }

        Robots campoSenhaVisivel() {
            campoSenha().check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
            return this;
        }

        public void redirecionaParaMainActivity() {
            Intents.intended(IntentMatchers.hasComponent(MainActivity.class.getName()));
        }

        public void exibeMensagemToast(int mensagem) {
            Espresso.onView(ViewMatchers.withText(mensagem))
                    .inRoot(RootMatchers
                            .withDecorView(Matchers.not(Matchers
                                    .is(rule.getActivity().getWindow().getDecorView()))))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        }
    }
}