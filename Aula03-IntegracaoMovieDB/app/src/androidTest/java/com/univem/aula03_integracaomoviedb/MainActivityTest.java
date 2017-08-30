package com.univem.aula03_integracaomoviedb;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.univem.aula03_integracaomoviedb.api.FabricaRetrofit;
import com.univem.aula03_integracaomoviedb.api.ProvedorRetrofit;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityTest {

    private MockWebServer server;

    ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setup() throws IOException {
        server = new MockWebServer();
        server.start();
        setupMockRetrofit();
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

    private void setupMockRetrofit() {
        String url = server.url("/").toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProvedorRetrofit provedorRetrofit = Mockito.mock(ProvedorRetrofit.class);

        Mockito.when(provedorRetrofit.getRetroInstance()).thenReturn(retrofit);

        FabricaRetrofit.setProvedorRetrofit(provedorRetrofit);
    }

    @Test
    public void aoIniciarActivity_DeveExibirListaDeFilmesPopulares() {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(FilmesPopularesMock.mock));
        activityTestRule.launchActivity(new Intent());
        Espresso.onView(
                Matchers.allOf(
                        ViewMatchers.withId(R.id.txtNomeFilme),
                        ViewMatchers.withText("Minions"))
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}