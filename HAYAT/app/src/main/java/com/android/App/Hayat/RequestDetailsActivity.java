package com.android.App.Hayat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RequestDetailsActivity extends AppCompatActivity {

    DatabaseHelper Database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);

        // get Donnation or Request Details
        SharedPreferences settingsResult =this.getSharedPreferences("requestDetails", MODE_PRIVATE);
        int donnationId = settingsResult.getInt("requestId", 0);
        String requestFullName = settingsResult.getString("requestFullName", "");
        String requestPhoneNumber = settingsResult.getString("requestPhoneNumber", "");
        String requestAge = settingsResult.getString("requestAge", "");
        String requestBloodGroup = settingsResult.getString("requestBloodGroup", "");
        String requestAdresse = settingsResult.getString("requestAdresse", "");

        TextView ProfilLabelFullNameTextView = findViewById(R.id.ProfilLabelFullName);
        TextView FullNameTextView = findViewById(R.id.ProfilFullName);
        TextView PhoneTextView = findViewById(R.id.ProfilPhoneNumber);
        TextView AgeTextView = findViewById(R.id.ProfilAge);
        TextView BloodTextView = findViewById(R.id.ProfilBloodGroup);
        TextView AdresseTextView = findViewById(R.id.ProfilAdresse);

        ProfilLabelFullNameTextView.setText(requestFullName);
        FullNameTextView.setText(requestFullName);
        PhoneTextView.setText(requestPhoneNumber);
        AgeTextView.setText(requestAge);
        BloodTextView.setText(requestBloodGroup);
        AdresseTextView.setText(requestAdresse);
    }
    public void CallHim(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        SharedPreferences settingsResult =this.getSharedPreferences("requestDetails", MODE_PRIVATE);
        String requestPhoneNumber = settingsResult.getString("requestPhoneNumber", "");
        intent.setData(Uri.parse("tel:"+requestPhoneNumber));
        startActivity(intent);
    }
    public void MyProfile(View view)
    {
        Intent intent = new Intent(this,AccountActivity.class);
        startActivity(intent);
    }
    public void GoToHome(View view) {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
    public void Logout(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}