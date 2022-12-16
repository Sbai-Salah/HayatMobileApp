package com.android.App.Hayat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DonnationDetailsActivity extends AppCompatActivity {

    DatabaseHelper Database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donnation_details);

        // get Donnation or Request Details
        SharedPreferences settingsResult =this.getSharedPreferences("donnationDetails", MODE_PRIVATE);
        int donnationId = settingsResult.getInt("donnationId", 0);
        String donnationFullName = settingsResult.getString("donnationFullName", "");
        String donnationPhoneNumber = settingsResult.getString("donnationPhoneNumber", "");
        String donnationAge = settingsResult.getString("donnationAge", "");
        String donnationBloodGroup = settingsResult.getString("donnationBloodGroup", "");
        String donnationAdresse = settingsResult.getString("donnationAdresse", "");

        TextView ProfilLabelFullNameTextView = findViewById(R.id.ProfilLabelFullName);
        TextView FullNameTextView = findViewById(R.id.ProfilFullName);
        TextView PhoneTextView = findViewById(R.id.ProfilPhoneNumber);
        TextView AgeTextView = findViewById(R.id.ProfilAge);
        TextView BloodTextView = findViewById(R.id.ProfilBloodGroup);
        TextView AdresseTextView = findViewById(R.id.ProfilAdresse);

        ProfilLabelFullNameTextView.setText(donnationFullName);
        FullNameTextView.setText(donnationFullName);
        PhoneTextView.setText(donnationPhoneNumber);
        AgeTextView.setText(donnationAge);
        BloodTextView.setText(donnationBloodGroup);
        AdresseTextView.setText(donnationAdresse);

    }
    public void CallHim(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        SharedPreferences settingsResult =this.getSharedPreferences("donnationDetails", MODE_PRIVATE);
        String donnationPhoneNumber = settingsResult.getString("donnationPhoneNumber", "");
        intent.setData(Uri.parse("tel:"+donnationPhoneNumber));
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
