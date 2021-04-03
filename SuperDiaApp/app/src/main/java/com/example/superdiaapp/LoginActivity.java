package com.example.superdiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

   private EditText editEmail;
    private EditText editPassword;
    private Usuario usuario;
    ListView list;
    static IServicosCliente servicosClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY,"org.jboss.naming.remote.client.InitialContextFactory");
        props.put(Context.PROVIDER_URL,"http-remoting://192.168.0.103:8090");
        InitialContext ic = null;
        try {
            ic = new InitialContext(props);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            servicosClient = (IServicosCliente)ic.lookup("ejb:SuperDiaEAR/SuperDia/ServicosClienteBean!br.com.superdia.sessionbeans.IServicosCliente?stateful");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


    public void efetuarLogin(){
        Usuario usuario = new Usuario();

        editEmail = (EditText) findViewById(R.id.edit_text_credentials);
        editPassword = (EditText) findViewById(R.id.edit_text_password);


        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        usuario.setUsuario(email);
        usuario.setSenha(password);
        usuario.setPerfil(PerfilUsuario.CLIENTE);

        if(servicosClient.autentica(usuario) != null){
            acessar();
        }else{
            editEmail.setText("");
            editPassword.setText("");
        }

    }

    public void acessar() {
        list= findViewById(R.id.button_sign_in);
        //ListaProdutosActivity adapter=new ListaProdutosActivity(this, servicosClient.listaProdutos());
        //list.setAdapter(adapter);

        Intent lista = new Intent(this, ListaProdutosActivity.class);
        startActivity(lista);
    }
}
