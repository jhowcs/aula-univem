package com.univem.aula03_integracaomoviedb;

import com.univem.aula03_integracaomoviedb.api.FabricaRetrofit;
import com.univem.aula03_integracaomoviedb.api.ProvedorRetrofit;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;

import java.io.IOException;

import br.com.concretesolutions.requestmatcher.InstrumentedTestRequestMatcherRule;
import br.com.concretesolutions.requestmatcher.RequestMatcherRule;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerRule implements TestRule {

    private RequestMatcherRule server = new InstrumentedTestRequestMatcherRule();

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                setupMockRetrofit();
                base.evaluate();

            }
        };
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

    public RequestMatcherRule getServer() {
        return server;
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
