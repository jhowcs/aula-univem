package com.univem.aula03_integracaomoviedb;

import com.univem.aula03_integracaomoviedb.api.FabricaRetrofit;
import com.univem.aula03_integracaomoviedb.api.ProvedorRetrofit;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;

import java.io.IOException;

import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerRule implements TestRule {

    private MockWebServer server;

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                server = new MockWebServer();

                try {
                    server.start();
                    setupMockRetrofit();
                    base.evaluate();
                } catch (IOException io) {
                    System.out.println("Error on start mock web server");
                } finally {
                    tearDown();
                }

            }

            public void tearDown() throws IOException {
                server.shutdown();
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

    public MockWebServer getServer() {
        return server;
    }
}
