package com.example.onmart.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.onmart.ItemClickListener;
import com.example.onmart.Product;
import com.example.onmart.ProductAdapter;
import com.example.onmart.R;
import com.example.onmart.databinding.FragmentHomeBinding;

import com.example.onmart.ui.Description;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RequestQueue requestQueue;
    ArrayList<Product> products = new ArrayList<>();
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    String[] proImages = new String[3];


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyler_View);
     //   String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=302012&date=15-04-2022";
        String url ="https://api.escuelajs.co/api/v1/products";

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

//                            JSONObject js = new JSONObject(response);
//                            String stringy = js.getString("");


                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                int id = jsonObject.getInt("id");
                                String title = jsonObject.getString("title");
                                int price = jsonObject.getInt("price");
                                String description = jsonObject.getString("description");
                                String creationAt = jsonObject.getString("creationAt");
                                String  updatedAt= jsonObject.getString("updatedAt");
//                                JSONArray jsonArray1 = jsonObject.getJSONArray("images");
//                                for(int j =0;j<jsonArray1.length();j++){
//                                    String jsonObject1 = jsonArray1.getString(i);
//                                    proImages[i] = jsonObject1;
//                                    System.out.println(jsonArray1.length()+" sfosajfoajf");
//                                }
                                JSONObject catObject = jsonObject.getJSONObject("category");
                                String name = catObject.getString("name");
                                String image = catObject.getString("image");
//                                Log.d("date",name+" print date");
//                                System.out.println(name);
                                products.add(new Product(id,price,title,description,creationAt,updatedAt,image,name));
                              //  proImages = new String[3];

                            }
                            ItemClickListener  itemClickListener = new ItemClickListener() {
                                @Override
                                public void onClick(int position, int id,String image,String name,int price,String title) {
                                    Intent intent = new Intent(getContext(), Description.class);
                                    intent.putExtra("position",position);
                                    intent.putExtra("id",id);
                                    intent.putExtra("image",image);
                                    intent.putExtra("name",name);
                                    intent.putExtra("price",price);
                                    intent.putExtra("title",title);

                                    startActivity(intent);
                                    // Display toast
                                    Toast.makeText(getActivity(),"Position : "
                                            +position +" || Value : "+id,Toast.LENGTH_SHORT).show();
                                }
                            };
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            productAdapter = new ProductAdapter(products,itemClickListener);
                            recyclerView.setAdapter(productAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
//        Log.d("date",products.get(0).getTitle()+" print date");
//        System.out.println(products.get(0).getTitle());
        // Initialize listener





        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}