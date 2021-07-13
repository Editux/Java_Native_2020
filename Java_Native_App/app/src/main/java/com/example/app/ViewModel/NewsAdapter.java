package com.example.app.ViewModel;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Model.NewsList;
import com.example.app.R;
import com.example.app.View.Details;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.Objects;


public class NewsAdapter extends FirebaseRecyclerAdapter<
        NewsList, NewsAdapter.ViewHolder> {


    public NewsAdapter(
            @NonNull FirebaseRecyclerOptions<NewsList> news)
    {
        super(news);
    }



    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull NewsList model) {
        holder.textView1.setText(model.getTitle());
        holder.textView2.setText(model.getAuthor());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"click on item: "+model.getTitle(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), Details.class);
                intent.putExtra("title", model.getTitle());
                intent.putExtra("author",model.getAuthor());
                intent.putExtra("description", model.getDescription());
                view.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView1 = (TextView) itemView.findViewById(R.id.textView1);
            this.textView2 = (TextView) itemView.findViewById(R.id.textView2);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}