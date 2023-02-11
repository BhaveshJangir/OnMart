package com.example.onmart.ui.Cart;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onmart.Database.OderEntity;
import com.example.onmart.Database.OrderDatabase;
import com.example.onmart.ProductAdapter;
import com.example.onmart.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
   OrderDatabase orderDatabase;
    List<OderEntity> ls;
    Context context;
    SharedPreferences.Editor myEdit;


    public CartAdapter( Context context) {
        this.context = context;
    }

    {
        orderDatabase = OrderDatabase.getDb(context);
        ls = orderDatabase.oderDao().getOrderData();




    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.title.setText(ls.get(position).getTitle());
      holder.price.setText("₹"+ls.get(position).getPrice());
        Picasso.get().load(ls.get(position).getImage()).into(holder.imageView);
    //    Toast.makeText(context, ls.get(position).getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

   static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,price;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_cart);
            price = itemView.findViewById(R.id.price_cart);
            imageView = itemView.findViewById(R.id.image_cart);
        }
    }

}
