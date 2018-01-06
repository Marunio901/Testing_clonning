package com.example.patryko.shopping_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    DatabaseRepository dbrepo;
    Button btOk;
    EditText name, quant, price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btOk = (Button)findViewById(R.id.button_oki);
        name = (EditText)findViewById(R.id.et_prod);
        quant = (EditText)findViewById(R.id.et_qu);
        price = (EditText)findViewById(R.id.et_pr);

        final String productName = getIntent().getStringExtra("productName");
        final int productQuant = getIntent().getIntExtra("productQuant", 0);
        final double productPrice = getIntent().getDoubleExtra("productPrice", 0);

        name.setText(productName);
        quant.setText(String.valueOf(productQuant));
        price.setText(String.valueOf(productPrice));

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nazwa = String.valueOf(name.getText());
                int ilosc = Integer.parseInt(String.valueOf(quant.getText()));
                double cena = Double.parseDouble(String.valueOf(price.getText()));

                dbrepo.UpdateProduct(new Product(nazwa, ilosc, cena, true), productName);

                Intent ekranList = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(ekranList);
            }
        });




    }
}
