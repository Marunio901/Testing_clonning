package com.example.patryko.shopping_list;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {

    private ListView simpleList, lv;
    private Button bAdd, bmenu;
    private EditText et_name, et_quant, et_price;
    private ListAdapterProduct adapter, adapter2;
    private DatabaseRepository dbProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        simpleList = (ListView) findViewById(R.id.list_view);
        bAdd = (Button) findViewById(R.id.bt_dodaj);
        bmenu = (Button) findViewById(R.id.bt_menu);

        et_name = (EditText) findViewById(R.id.et_nazwa);
        et_quant = (EditText) findViewById(R.id.et_ilosc);
        et_price = (EditText) findViewById(R.id.et_cena);
       // bDelete = (Button) findViewById(R.id.b_usun);

        dbProducts = new DatabaseRepository(getBaseContext());

        adapter = new ListAdapterProduct(this, R.layout.rowlayout, dbProducts.GetAllItems());

        simpleList.setAdapter(adapter);
        simpleList.setLongClickable(true);
        simpleList.setOnItemLongClickListener(itemLongClickListener);

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    String nazwa = String.valueOf(et_name.getText());
                    int ilosc = Integer.parseInt(String.valueOf(et_quant.getText()));
                    double cena = Double.parseDouble(String.valueOf(et_price.getText()));

                    dbProducts.AddItem(new Product(nazwa, ilosc, cena, false));
                    et_name.setText("");
                    et_price.setText("");
                    et_quant.setText("");

                }catch(Exception e){
                };

                updateData();
            }
        });

        bmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ekranMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(ekranMain);
            }
        });

    }

     private void updateData(){
        ArrayList<Product> products = dbProducts.GetAllItems();

        adapter.clear();
        adapter.addAll(products);
        adapter.notifyDataSetChanged();
    }


    private final AdapterView.OnItemLongClickListener itemLongClickListener
            = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Product item = adapter.getItem(position);
            dbProducts.RemoveProduct(item);

            ArrayList<Product> products = dbProducts.GetAllItems();
            adapter.clear();
            adapter.addAll(products);
            adapter.notifyDataSetChanged();

            return true;
        }
    };
}