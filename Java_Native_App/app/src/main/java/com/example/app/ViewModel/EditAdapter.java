package com.example.app.ViewModel;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Model.NewsList;
import com.example.app.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditAdapter extends FirebaseRecyclerAdapter<
        NewsList, EditAdapter.ViewHolder> {
    public EditAdapter(
            @NonNull FirebaseRecyclerOptions<NewsList> news)
    {
        super(news);
    }



    @Override
    protected void onBindViewHolder(@NonNull EditAdapter.ViewHolder holder, int position, @NonNull NewsList model) {
        holder.textView1.setText(model.getTitle());
        holder.textView2.setText(model.getAuthor());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mbase = FirebaseDatabase.getInstance().getReference();
                DatabaseReference itemRef = getRef(position);
                String myKey = itemRef.getKey();
                mbase.child(myKey).removeValue();

                Toast.makeText(view.getContext(),"Item removed: "+model.getTitle(),Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public EditAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        EditAdapter.ViewHolder viewHolder = new EditAdapter.ViewHolder(listItem);
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
