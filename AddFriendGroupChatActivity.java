package com.example.fitnessmedia_congressionalappchallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddFriendGroupChat extends AppCompatActivity {

    DatabaseReference GroupReference, UserReference, FriendReference, GroupMessagesReference;
    FirebaseUser user;
    RecyclerView Friends_List;
    Button button;
    String push_id, name_group;
    String UID;
    Boolean onCLicked = false;
    StorageReference StorageRef;
    String imageURL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend_group_chat);
        user = FirebaseAuth.getInstance().getCurrentUser();
        StorageRef = FirebaseStorage.getInstance().getReference().child("GroupImages");
        push_id = getIntent().getStringExtra("Group_Push_ID_Key");
        name_group = getIntent().getStringExtra("Group_NAME");


        Log.d("TAG","About to retrieve the image url and " + push_id);

        imageURL = getIntent().getStringExtra("Group_IMAGE");
        UID = user.getUid();
        FriendReference = FirebaseDatabase.getInstance().getReference().child("Friends").child(UID);

        GroupReference = FirebaseDatabase.getInstance().getReference().child("Group");
        UserReference = FirebaseDatabase.getInstance().getReference().child("User");
        Friends_List = findViewById(R.id.list_of_Friends);
        Friends_List.setLayoutManager(new LinearLayoutManager(this));

        GroupMessagesReference = FirebaseDatabase.getInstance().getReference().child("GroupMessages");

        DisplayAllFriends();

        button = findViewById(R.id.createButtonGroup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupReference.child(UID).child(push_id).child("Information").child("name").setValue(name_group);
                GroupReference.child(UID).child(push_id).child("Information").child("image").setValue(imageURL);

                startActivity(new Intent(getApplicationContext(),GroupActivity.class));
            }
        });

    }

  

}
