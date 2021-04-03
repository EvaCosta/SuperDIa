package com.example.superdiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ListaProdutosActivity extends AppCompatActivity {

      ListView list;
    private Activity activity;
    private List<Page> pagesList;
    private static LayoutInflater inflater = null;

    IServicosCliente iServicosCliente;


    public ListaProdutosActivity(Activity activity, List<Produto> produtos, List<Page> pages){
        super(activity, R.layout.activity_lista_produtos, pages);
        this.activity = activity;
        this.pagesList = pages;


        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater= activity.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_lista_produtos, null,true);

        TextView titleName = rowView.findViewById(R.id.name);
        TextView subtitleDescription = rowView.findViewById(R.id.description);
        TextView idText = rowView.findViewById(R.id.id_text);
        TextView lastChange = rowView.findViewById(R.id.price);

        ImageButton btnAdd = rowView.findViewById(R.id.add_btn);


        titleName.setText(pagesList.get(position).getNome());
        subtitleDescription.setText(pagesList.get(position).getDescricao());
        lastChange.setText(lastChange.toString());

        titleName.setOnClickListener(v -> openPage(position));
        subtitleDescription.setOnClickListener(v -> openPage(position));
        lastChange.setOnClickListener(v -> openPage(position));
        btnAdd.setOnClickListener(v -> payPage(position));

        return rowView;

    }

    public void payPage(int position){
        Intent intent = new Intent(activity, CarrinhoActivity.class);

        if (intent != null) {
            Page page = pagesList.get(position);

            //intent.putExtra("ID", page.getId().toString());
            intent.putExtra("Nome", page.getNome());
            intent.putExtra("Descricao", page.getDescricao());
            intent.putExtra("Preco", page.getPreco().toString());

            activity.startActivity(intent);
        }
    }

    public void openPage(int position){
        Intent intent = new Intent(activity, CarrinhoActivity.class);
        try {
            if (intent != null) {
                Page page = pagesList.get(position);

                //intent.putExtra("ID", page.getId().toString());
                intent.putExtra("Nome", page.getNome());
                intent.putExtra("Descricao", page.getDescricao());
                intent.putExtra("Preco", page.getPreco().toString());

                activity.startActivity(intent);
            }

        } catch (Exception e) {
            Toast.makeText(activity,
                    "Não foi possível selecionar o produto", Toast.LENGTH_LONG).show();
        }

    }

    
}
