package com.example.app_conductor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    EditText message;
    Button send;
    ListView chatListView;
    ArrayAdapter arrayAdapter;
    ArrayList<String> messages = new ArrayList<>();
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    int contador=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        message = findViewById(R.id.message);
        send = findViewById(R.id.send);
        chatListView = findViewById(R.id.chatListView);

        Intent intent =getIntent();

        String otherEmail = intent.getStringExtra("EmailTo");
        String email = mAuth.getCurrentUser().getEmail();

        setTitle("Chat with " + otherEmail);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message.getText().toString().isEmpty()) {
                    Toast.makeText(ChatActivity.this, "Write a message!", Toast.LENGTH_SHORT).show();
                } else {
                    Map<String, Object> messageData = new HashMap<>();
                    messageData.put("sender", email);
                    messageData.put("receiver", otherEmail);
                    messageData.put("message", message.getText().toString());

                    databaseReference.child("chats").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int count;
                            if (snapshot.exists()){
                                count = (int) (snapshot.getChildrenCount() + 1);
                            } else {
                                count = 1;
                            }
                            databaseReference.child("chats").child(String.valueOf(count)).setValue(messageData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        message.setText("");

                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ChatActivity.this, "Error in sending message: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        arrayAdapter = new ArrayAdapter(ChatActivity.this, android.R.layout.simple_list_item_1, messages);
        chatListView.setAdapter(arrayAdapter);
        databaseReference.child("chats").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();
                if (snapshot.exists()){

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String message = dataSnapshot.child("message").getValue().toString();
                        if (dataSnapshot.child("sender").getValue().toString().equals(email) && dataSnapshot.child("receiver").getValue().toString().equals(otherEmail)) {
                            message = "                                                                           < " + message;
                            messages.add(message);
                        }
                        if (dataSnapshot.child("receiver").getValue().toString().equals(email) && dataSnapshot.child("sender").getValue().toString().equals(otherEmail)) {
                            message = "> " + message;
                            messages.add(message);
                        }
                        arrayAdapter = new ArrayAdapter(ChatActivity.this, android.R.layout.simple_list_item_1, messages);
                        chatListView.setAdapter(arrayAdapter);
                       }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}