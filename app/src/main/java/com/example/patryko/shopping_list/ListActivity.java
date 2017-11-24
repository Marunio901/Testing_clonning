package com.example.patryko.shopping_list;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ListView simpleList;
    ArrayList<Product> productsList = new ArrayList<>();
    Button bAdd;
    EditText et_name, et_quant, et_price;
    boolean isProductChecked;

    private DatabaseRepository dbProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);




        simpleList = (ListView) findViewById(R.id.list_view);
        bAdd = (Button) findViewById(R.id.bt_dodaj);
        et_name = (EditText) findViewById(R.id.et_nazwa);
        et_quant = (EditText) findViewById(R.id.et_ilosc);
        et_price = (EditText) findViewById(R.id.et_cena);
        dbProducts = new DatabaseRepository(getBaseContext());


        //dbProducts.RemoveProduct(dbProducts.GetAllItems());





        productsList.add(new Product("Milk", 1, 2.90, true));



        final ListAdapterProduct adapter = new ListAdapterProduct(this, R.layout.rowlayout, dbProducts.GetAllItems());
        simpleList.setAdapter(adapter);


        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nazwa = String.valueOf(et_name.getText());
                int ilosc = Integer.parseInt(String.valueOf(et_quant.getText()));
                double cena = Double.parseDouble(String.valueOf(et_price.getText()));




             // productsList.add(new Product(nazwa, ilosc, cena, true));
                dbProducts.AddItem(new Product(nazwa, ilosc, cena, true));

             //   Log.d("Dodano", dbProducts.GetAllItems().toString());

                et_name.setText("");
                et_price.setText("");
                et_quant.setText("");

                ArrayList<Product> products = dbProducts.GetAllItems();

                adapter.clear();
                adapter.addAll(products);
                adapter.notifyDataSetChanged();

            }
        });
    }

}