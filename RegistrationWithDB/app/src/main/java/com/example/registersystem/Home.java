package com.example.registersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences settingsResult =this.getSharedPreferences("User", MODE_PRIVATE);
        int currentUserId = settingsResult.getInt("currentUserId", 0);
        String currentUserFullName = settingsResult.getString("currentUserFullName", "");
        String currentUserEmail = settingsResult.getString("currentUserEmail", "");
        String currentUsePhone = settingsResult.getString("currentUserPhone", "");

        String currentUserIdString = String.valueOf(currentUserId);
        TextView IdTextView = findViewById(R.id.UserId);
        IdTextView.setText(currentUserIdString);

        TextView fullNameTextView = findViewById(R.id.fullName);
        fullNameTextView.setText(currentUserFullName);

        TextView EmailTextView = findViewById(R.id.Email);
        EmailTextView.setText(currentUserEmail);

        TextView PhoneTextView = findViewById(R.id.Phone);
        PhoneTextView.setText(currentUsePhone);

    }
}