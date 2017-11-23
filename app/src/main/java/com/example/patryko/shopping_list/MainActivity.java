package com.example.patryko.shopping_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button przyciskSettings, przyciskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void settOnClick(View v){

        przyciskSettings = (Button)findViewById(R.id.button2);
        Intent ekranSettings = new Intent(getApplicationContext(), SettingsActivity.class);

        startActivity(ekranSettings);
    }

    public void listOnClick(View v){

        przyciskList = (Button)findViewById(R.id.button);
        Intent ekranList = new Intent(getApplicationContext(), ListActivity.class);

        startActivity(ekranList);
    }

}
