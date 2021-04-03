package com.example.superdiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.superdia.modelo.ItemCarrinho;
import br.com.superdia.modelo.Produto;
import br.com.superdia.modelo.Usuario;
import br.com.superdia.sessionbeans.IServicosCliente;

public class MainActivity extends AppCompatActivity {
    private EditText editEmail;
    private EditText editPassword;
    private Usuario usuario;
    IServicosCliente iServicosCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void efetuarLogin(){
        Usuario usuario = new Usuario();

        editEmail = (EditText) findViewById(R.id.edit_text_credentials);
        editPassword = (EditText) findViewById(R.id.edit_text_password);


        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        usuario.setUsuario(email);
        usuario.setSenha(password);

        if(iServicosCliente.autentica(usuario) != null){
            acessar();
        }else{
            editEmail.setText("");
            editPassword.setText("");
        }

    }

    public void acessar() {
        Intent cadastro = new Intent(this, ListaProdutosActivity.class);
        startActivity(cadastro);
    }
}