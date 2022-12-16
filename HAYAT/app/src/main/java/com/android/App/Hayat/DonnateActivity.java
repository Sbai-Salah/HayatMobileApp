package com.android.App.Hayat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DonnateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private DatabaseHelper dataBase;
    private Spinner spinner,donnateBloodGroup;
    private Button makedonnateBtn,DonnateCancelBtn;
    private EditText donnateFullName,donnatePhoneNumber,donnateAge,donnateAdresse;
    private static final String[] paths =
            {"Select Your Blood Group","A+","A-","B+","B-","AB+","AB-","0+","O-"};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donnate);


        // Blood groupe spiner :
        spinner = (Spinner)findViewById(R.id.donnateBloodGroup);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DonnateActivity.this,
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(0);

        // Cancel Btn
        DonnateCancelBtn = (Button)findViewById(R.id.DonnateCancelBtn);
        DonnateCancelBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(DonnateActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        // set fullname and phone number values
        SharedPreferences settingsResult =this.getSharedPreferences("User", MODE_PRIVATE);
        int currentUserId = settingsResult.getInt("currentUserId", 0);
        String currentUserFullName = settingsResult.getString("currentUserFullName", "");
        String currentUserEmail = settingsResult.getString("currentUserEmail", "");
        String currentUsePhone = settingsResult.getString("currentUserPhone", "");
        String currentUserAge = settingsResult.getString("currentUserAge", "");
        String currentUserAdresse = settingsResult.getString("currentUserAdresse", "");

        TextView fullNameTextView = findViewById(R.id.donnateFullName);
        fullNameTextView.setText(currentUserFullName);

        TextView PhoneTextView = findViewById(R.id.donnatePhoneNumber);
        PhoneTextView.setText(currentUsePhone);

        TextView AgeTextView = findViewById(R.id.donnateAge);
        AgeTextView.setText(currentUserAge);

        TextView AdresseTextView = findViewById(R.id.donnateAdresse);
        AdresseTextView.setText(currentUserAdresse);
        // insert a Donnation record -----------------------------------------------------------------
        dataBase = new DatabaseHelper(DonnateActivity.this);
        makedonnateBtn = (Button)findViewById(R.id.makedonnateBtn);
        makedonnateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //recuperation de chmaps de formulaire
                donnateFullName = (EditText)findViewById(R.id.donnateFullName);
                donnatePhoneNumber = (EditText)findViewById(R.id.donnatePhoneNumber);
                donnateBloodGroup = (Spinner) findViewById(R.id.donnateBloodGroup);
                donnateAge = (EditText)findViewById(R.id.donnateAge);
                donnateAdresse = (EditText)findViewById(R.id.donnateAdresse);
                String _donnateFullName = donnateFullName.getText().toString();
                String _donnatePhoneNumber = donnatePhoneNumber.getText().toString();
                String _donnateBloodGroup = donnateBloodGroup.getSelectedItem().toString();
                String _donnateAge = donnateAge.getText().toString();
                String _donnateAdresse = donnateAdresse.getText().toString();
                // insertion
                String WarningMessage;
                Donnation donnation = new Donnation(-1,"","","","","",-1);
                donnation.setFullName(_donnateFullName);
                donnation.setPhoneNumber(_donnatePhoneNumber);
                donnation.setBloodGroup(_donnateBloodGroup);
                donnation.setAge(_donnateAge);
                donnation.setAdresse(_donnateAdresse);
                donnation.setUserId(currentUserId);
                if(dataBase.addDonnation(donnation)){
                    WarningMessage = "Donnation added successfully !";
                }else{
                    WarningMessage = "Donnation failed !";
                }
                Toast.makeText(DonnateActivity.this,WarningMessage,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DonnateActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
    public void GoToHome(View view) {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
    // button functions
    public void CallHim(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:0123456789"));
        startActivity(intent);
    }
    public void MyProfile(View view) {
        Intent intent = new Intent(this,AccountActivity.class);
        startActivity(intent);
    }
    public void Logout(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        // Label_BloodGroup
        TextView Label_BloodGroup = (TextView) findViewById(R.id.donnateLabel_BloodGroup);
        switch (position) {
            case 0:
                break;
            case 1:
                Label_BloodGroup.setText("A+");
                break;
            case 2:
                Label_BloodGroup.setText("A-");
                break;
            case 3:
                Label_BloodGroup.setText("B+");
                break;
            case 4:
                Label_BloodGroup.setText("B-");
                break;
            case 5:
                Label_BloodGroup.setText("AB+");
                break;
            case 6:
                Label_BloodGroup.setText("AB-");
                break;
            case 7:
                Label_BloodGroup.setText("O+");
                break;
            case 8:
                Label_BloodGroup.setText("O-");
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}