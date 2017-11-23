package com.example.patryko.shopping_list;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME1 = "myPreferences" ;
    private static final String PREFERENCES_NAME2 = "myPreferences2" ;

    private static final String PREFERENCES_TEXT_FIELD = "tekstField" ;
    private static final String PREFERENCES_TEXT_FIELD2 = "tekstField2" ;
    public Button przyciskMenu, przyciskZatwierdz;
    public EditText etToSave;
    int colorBG;
    public TextView tv1, tv2;
    private SharedPreferences preferences, preferences1;
    public EditText tekst;
    public RadioButton rbB, rbG, rbR;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        preferences = getSharedPreferences(PREFERENCES_NAME1, Activity.MODE_PRIVATE);
        preferences1 = getSharedPreferences(PREFERENCES_NAME2, Activity.MODE_PRIVATE);
        etToSave = (EditText)findViewById(R.id.editText4);
        tv1 = (TextView)findViewById(R.id.textView);
        tv2 = (TextView)findViewById(R.id.textView2);

        przyciskMenu = (Button)findViewById(R.id.button4);
        przyciskZatwierdz = (Button)findViewById(R.id.button3);
        rbB = (RadioButton)findViewById(R.id.rbB);
        rbG = (RadioButton)findViewById(R.id.rbG);
        rbR = (RadioButton)findViewById(R.id.rbR);


        rl = (RelativeLayout)findViewById(R.id.RL1);





        zatwierdzOnClick();
        menuOnClick();
        restoreData();

        etToSave.setTextSize(Float.parseFloat(etToSave.getText().toString()));
        //tv1.setTextSize(10);


    rbB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            rl.setBackgroundColor(Color.BLUE);
            colorBG = Color.BLUE;

        }
    });


        rbG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rl.setBackgroundColor(Color.GREEN);
                colorBG = Color.GREEN;

            }
        });


        rbR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rl.setBackgroundColor(Color.RED);
                colorBG = Color.RED;

            }
        });
    }







    private void zatwierdzOnClick() {

        przyciskZatwierdz.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                saveData();
                etToSave.setTextSize(Float.parseFloat(etToSave.getText().toString()));
                showToast("Zapisano ustawienia");


//             Intent ekranMain = new Intent(getApplicationContext(), MainActivity.class);
//             startActivity(ekranMain);

            }
        });
    }



    private void menuOnClick(){

        przyciskMenu.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

             Intent ekranMain = new Intent(getApplicationContext(), MainActivity.class);
             startActivity(ekranMain);

            }
        });


    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    private void saveData(){
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        SharedPreferences.Editor preferencesEditor2 = preferences1.edit();
        String editTextData = etToSave.getText().toString();

        preferencesEditor.putString(PREFERENCES_TEXT_FIELD, editTextData);
        preferencesEditor2.putInt(PREFERENCES_TEXT_FIELD2, colorBG);

        preferencesEditor.commit();
        preferencesEditor2.commit();

    }

    private void restoreData(){
        String textFromPreferences = preferences.getString(PREFERENCES_TEXT_FIELD, "");
        int kolorFromPreferences = preferences1.getInt(PREFERENCES_TEXT_FIELD2, -1);
        etToSave.setText(textFromPreferences);
        rl.setBackgroundColor(kolorFromPreferences);
    }

}
