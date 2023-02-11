package com.example.onmart.ui;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.onmart.Database.OderEntity;
import com.example.onmart.Database.OrderDatabase;
import com.example.onmart.ItemClickListener;
import com.example.onmart.Product;
import com.example.onmart.ProductAdapter;
import com.example.onmart.R;
import com.example.onmart.ui.Cart.CartFragment;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Description extends AppCompatActivity {
 int position = 0,gPrice=0,gId=0;
 String gImage,gName,gTitle;
 String uName ="bhavesh",uEmail="bjangir266@gmail.com";
 ImageView imageView;
 TextView title,price,name;
 Button buyNow,addToCart;
 OrderDatabase orderDatabase;
 int totalPrice;

 String oderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        title = findViewById(R.id.d_item_brand);
        name = findViewById(R.id.d_item_name);
        price = findViewById(R.id.d_item_price);
        buyNow = findViewById(R.id.buy_now);
        addToCart = findViewById(R.id.cart_tv);
        imageView = findViewById(R.id.d_item_image);


        SharedPreferences sh;
        sh = getApplication().getSharedPreferences("MySharedPref", MODE_PRIVATE);

        // The value will be default as empty string because for
        // the very first time when the app is opened, there is nothing to show
        String s1 = sh.getString("name", "bhavesh");
        totalPrice = sh.getInt("totalPrice", 0);





        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            gId = bundle.getInt("id");
            position = bundle.getInt("position");
            gPrice = bundle.getInt("price");
            gTitle = bundle.getString("title");
            gName = bundle.getString("name");
            gImage = bundle.getString("image");
        }
        totalPrice += gPrice;




        title.setText(gName);
        name.setText(gTitle);
        price.setText("₹"+gPrice);
        Picasso.get().load(gImage).into(imageView);



        orderDatabase  = OrderDatabase.getDb(this);
        //orderDatabase.oderDao().addUserOrder(new OderEntity());
        orderDatabase.oderDao().deleteUserOrder(new OderEntity());

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addData();

            }
        });
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
                Toast.makeText(Description.this, "Product Added", Toast.LENGTH_SHORT).show();
                // Storing data into SharedPreferences
                SharedPreferences sharedPreferences = getApplication().getSharedPreferences("MySharedPref",MODE_PRIVATE);

                // Creating an Editor object to edit(write to the file)
                SharedPreferences.Editor myEdit = sharedPreferences.edit();

               // Storing the key and its value as the data fetched from edittext
                myEdit.putString("name", "bhavesh");
                myEdit.putInt("totalPrice", totalPrice);
                System.out.println(totalPrice+""+s1+"descriton");
                Toast.makeText(getApplication(), ""+totalPrice ,Toast.LENGTH_SHORT).show();

             // Once the changes have been made,
             // we need to commit to apply those changes made,
             // otherwise, it will throw an error
                myEdit.commit();
            }
        });

        orderDatabase.oderDao().getOrderData();




    }

    private void addData() {
        orderDatabase.oderDao().addUserOrder(new OderEntity(uName,uEmail,gTitle,gImage,gPrice));
    }
}