package com.univem.aula01_login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText edtLogin;
    private EditText edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();

        if (id == R.id.btnLogin) {
            if (loginValido()) {
                Intent intent = MainActivity.newIntent(getApplicationContext());
                startActivity(intent);

                finalizarActivityDeLogin();
            } else {
                exibirMensagemDeErro();
            }
        }
    }

    private void finalizarActivityDeLogin() {
        this.finish();
    }

    private void exibirMensagemDeErro() {
        Toast.makeText(this, R.string.mensagem_erro_login, Toast.LENGTH_SHORT).show();
    }

    private boolean loginValido() {
        String login = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();

        if (!TextUtils.isEmpty(login) && !TextUtils.isEmpty(senha)) {
            return login.equals("adm") && senha.equals("123");
        }

        return false;
    }
}
