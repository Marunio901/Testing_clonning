package com.example.patryko.shopping_list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
/**
 * Created by Patryko on 11/21/2017.
 */

public class ListAdapterProduct extends ArrayAdapter<Product> {

    ArrayList<Product> productsList = new ArrayList<>();
    private Context mycontext;

    public ListAdapterProduct(Context context, int textViewResourceId, ArrayList<Product> objects) {
        super(context, textViewResourceId, objects);
        productsList = objects;

        mycontext = context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.rowlayout, null);
        TextView textView = (TextView) v.findViewById(R.id.tv_name);
        TextView textView2 = (TextView) v.findViewById(R.id.tv_quant);
        TextView textView3 = (TextView) v.findViewById(R.id.tv_price);
        Button editButton = (Button) v.findViewById(R.id.b_edytuj);

        textView.setText(productsList.get(position).getName());
        textView2.setText(String.valueOf(productsList.get(position).getQuantity()));
        textView3.setText(String.valueOf(productsList.get(position).getPrice()));
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mycontext.startActivity(new Intent(mycontext, EditActivity.class));
            }
        });



        return v;

    }
}
