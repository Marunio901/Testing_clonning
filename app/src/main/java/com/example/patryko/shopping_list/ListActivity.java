package com.example.patryko.shopping_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {


    private ListView listView;

    //ArrayAdapter<String> namesAdapter;
    //ListAdapterProduct listAdapterProduct;
    //Product mleko;
    //List<Product> productList;

    ListView simpleList;
    ArrayList<Product> productsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        simpleList = (ListView)findViewById(R.id.list_view);


        productsList.add(new Product("Milk",1, 2.90));
        productsList.add(new Product("Banana",2, 3.20));
        productsList.add(new Product("Water",3, 1.00));

        //listView=(ListView)findViewById(R.id.list_view);


        ListAdapterProduct adapter=new ListAdapterProduct(this,R.layout.rowlayout, productsList );
        simpleList.setAdapter(adapter);

    }

}