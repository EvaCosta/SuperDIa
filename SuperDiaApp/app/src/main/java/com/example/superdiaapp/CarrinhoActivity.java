package com.example.superdiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CarrinhoActivity extends AppCompatActivity {
 private Activity activity;
    IServicosCliente iServicosCliente;
    ItemCarrinho itemCarrinho;
    private List<Page> pagesList;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        list= findViewById(R.id.button_sign_in);
        ListaProdutosActivity adapter=new ListaProdutosActivity(this, iServicosCliente.listaProdutos());
        list.setAdapter(adapter);
    }


    public void showDeleteDialog(int position, String idText){

        long id = Long.parseLong(idText);

        ImageButton btn = activity.findViewById(R.id.delete_btn);

        AlertDialog.Builder box = new AlertDialog.Builder(activity);
        box.setTitle("Excluir");
        box.setIcon(android.R.drawable.ic_menu_delete);
        box.setMessage("Tem certeza que deseja excluir este item?");

        box.setPositiveButton("Sim", (dialog, which) -> {
            iServicosCliente.removeItemCarrinho(itemCarrinho);
            pagesList.remove(position);
        });
        box.setNegativeButton("Não", (dialog, which) -> {

        });
        box.show();
    }
}
