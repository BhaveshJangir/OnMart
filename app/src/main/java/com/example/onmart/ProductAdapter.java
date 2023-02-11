package com.example.onmart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    ArrayList<Product> object;
    ItemClickListener itemClickListener;

    public ProductAdapter(ArrayList<Product> object,ItemClickListener itemClickListener) {
        this.object = object;
        this.itemClickListener=itemClickListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {
          holder.name.setText(object.get(position).getTitle());
          holder.price.setText("₹"+object.get(position).getPrice()+"");
          holder.title.setText(object.get(position).getName());
          Picasso.get().load(object.get(position).getImage()).into(holder.itemImage);
          holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get adapter position
                int position=holder.getAdapterPosition();
                // call listener
                itemClickListener.onClick(position,object.get(position).getId(),object.get(position).getImage(),object.get(position).getName()
                ,object.get(position).getPrice(),object.get(position).getTitle());
                // update position
              //  selectedPosition=position;
                // notify
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return object.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,price,name;
        ImageView itemImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            title = itemView.findViewById(R.id.item_brand);
            price = itemView.findViewById(R.id.item_price);
            itemImage = itemView.findViewById(R.id.item_image);

        }
    }
}
