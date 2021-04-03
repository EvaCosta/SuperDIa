package com.example.superdiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import br.com.superdia.modelo.PerfilUsuario;
import br.com.superdia.modelo.Usuario;
import br.com.superdia.sessionbeans.IServicosAdmin;
import br.com.superdia.sessionbeans.IServicosCliente;

public class CadastroActivity extends AppCompatActivity {
    private EditText editEmail;
    private EditText editPassword;
    private Usuario usuario;
    IServicosCliente iServicosCliente;
    IServicosAdmin iServicosAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void cadastrarCliente() {
        usuario = new Usuario();

        editEmail = (EditText) findViewById(R.id.edit_text_credentials);
        editPassword = (EditText) findViewById(R.id.edit_text_password);

        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        usuario.setUsuario(email);
        usuario.setSenha(password);
        usuario.setPerfil(PerfilUsuario.CLIENTE);

        if (iServicosCliente.adicionaUsuario(usuario) == true) {
            Toast.makeText(getBaseContext(), "Usuario cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);

        } else {
            Toast.makeText(getBaseContext(), "Não foi possível realizar o cadastro!", Toast.LENGTH_SHORT).show();
        }
        editEmail.setText("");
        editPassword.setText("");
    }
}