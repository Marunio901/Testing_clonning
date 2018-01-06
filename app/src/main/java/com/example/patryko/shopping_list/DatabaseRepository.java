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

    private final SQLiteDatabase database;

    FirebaseDatabase database2 = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database2.getInstance().getReference();



    public DatabaseRepository(Context context){
        File mDatabaseFile = context.getDatabasePath("patrykDB.db").getAbsoluteFile();
        database = SQLiteDatabase.openOrCreateDatabase(mDatabaseFile, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS Products(ProductName VARCHAR PRIMARY KEY, Quantity INTEGER, Price NUMERIC, Selected INTEGER);");
    }







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
        ContentValues insertValues = new ContentValues();
        insertValues.put("ProductName", product.getName());
        insertValues.put("Quantity", product.getQuantity());
        insertValues.put("Price", product.getPrice());
        insertValues.put("Selected", product.isChecked());
        database.insert("Products", null, insertValues);

        //myRef.push(String.valueOf(product.getName())).setValue("dsfasdf");

        myRef.child(product.getName()).setValue(product);

//
//        myRef.child(product.getName()).push().setValue(product.getName());
//        myRef.child(product.getName()).push().setValue(product.getQuantity());
//        myRef.child(product.getName()).push().setValue(product.getPrice());


    }

    public void RemoveProduct(Product productToRemove){
        database.delete("Products", "ProductName = ?", new String[]{productToRemove.getName()});
        myRef.child(productToRemove.getName()).removeValue();
    }

    public void UpdateProduct(Product product, String productName) {
        ContentValues insertValues = new ContentValues();
        insertValues.put("ProductName", product.getName());
        insertValues.put("Quantity", product.getQuantity());
        insertValues.put("Price", product.getPrice());
        insertValues.put("Selected", product.isChecked());
        database.update("Products", insertValues, "ProductName = ?", new String[]{productName});
    }
}
