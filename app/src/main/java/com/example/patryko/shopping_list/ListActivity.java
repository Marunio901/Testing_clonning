package com.example.patryko.shopping_list;


import android.content.DialogInterface;
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

    private ListView simpleList;
    private Button bAdd, bDelete;
    private EditText et_name, et_quant, et_price;
    private ListAdapterProduct adapter;
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
        bDelete = (Button) findViewById(R.id.b_usun);
        dbProducts = new DatabaseRepository(getBaseContext());


        adapter = new ListAdapterProduct(this, R.layout.rowlayout, dbProducts.GetAllItems());

        simpleList.setAdapter(adapter);
        simpleList.setLongClickable(true);
        simpleList.setOnItemLongClickListener(itemLongClickListener);


        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(ListActivity.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + position);
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dbProducts.RemoveProduct(adapter.getItem(positionToRemove));
                        adapter.notifyDataSetChanged();
                    }});
                adb.show();
            }
        });

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nazwa = String.valueOf(et_name.getText());
                int ilosc = Integer.parseInt(String.valueOf(et_quant.getText()));
                double cena = Double.parseDouble(String.valueOf(et_price.getText()));

                dbProducts.AddItem(new Product(nazwa, ilosc, cena, true));

                et_name.setText("");
                et_price.setText("");
                et_quant.setText("");

                updateData();
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
            adapter.remove(adapter.getItem(position));

            updateData();

            return true;
        }
    };
}