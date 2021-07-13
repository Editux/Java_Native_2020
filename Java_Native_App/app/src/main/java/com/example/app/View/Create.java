package com.example.app.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.Model.NewsList;
import com.example.app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Create extends Fragment {


        Button SubmitButton ;

        EditText TitleEditText, AuthorEditText, ContentEditText;

        // Declaring String variable ( In which we are storing firebase server URL ).
        //public static final String Firebase_Server_URL = "https://insertdata-android-examples.firebaseio.com/";

        // Declaring String variables to store name & phone number get from EditText.
        String TitleHolder, AuthorHolder, ContentHolder;


        DatabaseReference databaseReference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        SubmitButton = (Button)rootView.findViewById(R.id.submit);

        TitleEditText = (EditText)rootView.findViewById(R.id.title);

        AuthorEditText= (EditText)rootView.findViewById(R.id.author_name);

        ContentEditText= (EditText)rootView.findViewById(R.id.description) ;

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NewsList news = new NewsList();

                GetDataFromEditText();

                // Adding name into class function object.
                news.setTitle(TitleHolder);

                // Adding phone number into class function object.
                news.setAuthor(AuthorHolder);

                news.setDescription(ContentHolder);

                // Getting the ID from firebase database.
                String ArticleRecordIDFromServer = databaseReference.push().getKey();

                // Adding the both name and number values using student details class object using ID.
                databaseReference.child(ArticleRecordIDFromServer).setValue(news);

                // Showing Toast message after successfully data submit.
                Toast.makeText(getActivity(),"Data Inserted Successfully into Firebase Database", Toast.LENGTH_LONG).show();

            }
        });



        return rootView;
    }

        public void GetDataFromEditText(){

         TitleHolder = TitleEditText.getText().toString().trim();

            AuthorHolder = AuthorEditText.getText().toString().trim();
            ContentHolder =ContentEditText.getText().toString().trim();

        }






}