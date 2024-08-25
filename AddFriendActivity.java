package com.example.fitnessmedia_congressionalappchallenge;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class AddFriendActivity extends AppCompatActivity {

    FirebaseRecyclerOptions<UserDataRecView> options;
    FirebaseRecyclerAdapter<UserDataRecView,RecViewHolder>adapter;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser user;
    RecyclerView recView;
    EditText search;
    ImageView searchIcon;
    RelativeLayout layout;
    FirebaseUser fUser;

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        ChangeSystemElements();

        reference = FirebaseDatabase.getInstance().getReference().child("User");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        search = findViewById(R.id.userSearch);
        searchIcon = findViewById(R.id.searchIcon);
        layout = findViewById(R.id.searchLayout);

        fUser = FirebaseAuth.getInstance().getCurrentUser();


        loadUser("");

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = search.getText().toString();
                loadUser(s);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUser("");
            }
        });





    }

    

}
