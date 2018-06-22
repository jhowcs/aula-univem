package com.univem.aula03_integracaomoviedb;

import com.univem.aula03_integracaomoviedb.api.FabricaRetrofit;
import com.univem.aula03_integracaomoviedb.api.ProvedorRetrofit;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;

import br.com.concretesolutions.requestmatcher.InstrumentedTestRequestMatcherRule;
import br.com.concretesolutions.requestmatcher.RequestMatcherRule;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerRule implements TestRule {

    final RequestMatcherRule server = new InstrumentedTestRequestMatcherRule();

    @Override
    public Statement apply(final Statement base, Description description) {
        return server.apply(new StubRetrofitStatement(base), description);
    }

    class StubRetrofitStatement extends Statement {

        private Statement base;

        public StubRetrofitStatement(Statement base) {
            this.base = base;
        }

        @Override
        public void evaluate() throws Throwable {
            String url = server.url("/").toString();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ProvedorRetrofit provedorRetrofit = Mockito.mock(ProvedorRetrofit.class);

            Mockito.when(provedorRetrofit.getRetroInstance()).thenReturn(retrofit);

            FabricaRetrofit.setProvedorRetrofit(provedorRetrofit);

            if (base != null) {
                base.evaluate();
            }
        }
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
