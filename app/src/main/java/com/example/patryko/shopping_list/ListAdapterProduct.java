package com.example.patryko.shopping_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by Patryko on 11/21/2017.
 */

public class ListAdapterProduct extends ArrayAdapter<Product> {


   // private static final String TAG = ListAdapterProduct.class.getSimpleName();

//    List<Product> products;
//    Context context;
//    LayoutInflater layoutInflater;

    ArrayList<Product> productsList = new ArrayList<>();

    public ListAdapterProduct(Context context, int textViewResourceId, ArrayList<Product> objects) {
        super(context, textViewResourceId, objects);
        productsList = objects;
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


        textView.setText(productsList.get(position).getName());
        textView2.setText(productsList.get(position).getQuantity());


        return v;

    }



















//    public ListAdapterProduct(Context context,List<Product> products) {
//        this.products = products;
//        this.context = context;
//    }
//
//    public int getCount() {
//        return products.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return products.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//
//        layoutInflater = LayoutInflater.from(this.context);
//
//        final Product product = products.get(i);
//
//        view = layoutInflater.inflate(R.layout.rowlayout, null);
//        ((TextView) view.findViewById(R.id.tv_name)).setText(product.getName());
//      ((TextView) view.findViewById(R.id.tv_quant)).setText(product.getQuantity());
//        ((TextView) view.findViewById(R.id.tv_price)).setText(String.valueOf(product.getPrice()));
//
//        //((TextView)view.findViewById(R.id.textViewNationality)).setText(product.getNationality());
//        Log.i(TAG,"Index: "+i+" : "+view);
//        return view;
//
//    }
}
