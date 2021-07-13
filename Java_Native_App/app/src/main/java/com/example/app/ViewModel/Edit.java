package com.example.app.ViewModel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.Model.NewsList;
import com.example.app.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Edit extends Fragment {
    View root;
    RecyclerView recyclerView;
    DatabaseReference mbase;
    EditAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_edit, container, false);
        recyclerView = root.findViewById(R.id.recyclerView2);

        mbase = FirebaseDatabase.getInstance().getReference();
        FirebaseRecyclerOptions<NewsList> news
                = new FirebaseRecyclerOptions.Builder<NewsList>()
                .setQuery(mbase, NewsList.class)
                .build();
        adapter = new EditAdapter(news);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);




        return root;
    }
    @Override
    public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}