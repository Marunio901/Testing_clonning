package com.example.patryko.shopping_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryko on 11/24/2017.
 */

public class DatabaseRepository {

    FirebaseDatabase database2 = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database2.getInstance().getReference();

    public ArrayList<Product> GetAllItems(){

       final ArrayList<Product> productList = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot snapshot) {
                productList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Product product = postSnapshot.getValue(Product.class);
                 productList.add(product);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

//
   return productList;
    }

    public void AddItem(Product product){
        myRef.child(product.getName()).setValue(product);
    }

    public void RemoveProduct(Product productToRemove){
        myRef.child(productToRemove.getName()).removeValue();
    }

    public void UpdateProduct(Product product, String productName) {
        myRef.child(productName).removeValue();
        myRef.child(product.getName()).setValue(product);
    }
}
