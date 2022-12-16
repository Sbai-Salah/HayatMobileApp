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

public class RequestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private DatabaseHelper dataBase;
    private Spinner spinner,requestBloodGroup;
    private Button makeRequestBtn,CancelBtn;
    private EditText requestFullName,requestPhoneNumber,requestAge,requestAdresse;
    private static final String[] paths =
            {"Select Your Blood Group","A+","A-","B+","B-","AB+","AB-","0+","O-"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        // Blood groupe spiner :
        spinner = (Spinner)findViewById(R.id.requestBloodGroup);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(RequestActivity.this,
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(0);

        // Cancel Btn
        CancelBtn = (Button)findViewById(R.id.CancelBtn);
        CancelBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(RequestActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // set fullname and phone number values
        SharedPreferences settingsResult =this.getSharedPreferences("User", MODE_PRIVATE);
        int currentUserId = settingsResult.getInt("currentUserId", 0);
        String currentUserFullName = settingsResult.getString("currentUserFullName", "");
        String currentUserEmail = settingsResult.getString("currentUserEmail", "");
        String currentUserPhone = settingsResult.getString("currentUserPhone", "");
        String currentUserAge = settingsResult.getString("currentUserAge", "");
        String currentUserAdresse = settingsResult.getString("currentUserAdresse", "");

        TextView fullNameTextView = findViewById(R.id.requestFullName);
        fullNameTextView.setText(currentUserFullName);

        TextView PhoneTextView = findViewById(R.id.requestPhoneNumber);
        PhoneTextView.setText(currentUserPhone);

        TextView AgeTextView = findViewById(R.id.requestAge);
        AgeTextView.setText(currentUserAge);

        TextView AdresseTextView = findViewById(R.id.requestAdresse);
        AdresseTextView.setText(currentUserAdresse);

        // insert a Request record -----------------------------------------------------------------
        dataBase = new DatabaseHelper(RequestActivity.this);
        makeRequestBtn = (Button)findViewById(R.id.makeRequestBtn);
        makeRequestBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //recuperation de chmaps de formulaire
                requestFullName = (EditText)findViewById(R.id.requestFullName);
                requestPhoneNumber = (EditText)findViewById(R.id.requestPhoneNumber);
                requestBloodGroup = (Spinner) findViewById(R.id.requestBloodGroup);
                requestAge = (EditText) findViewById(R.id.requestAge);
                requestAdresse = (EditText) findViewById(R.id.requestAdresse);
                String _requestFullName = requestFullName.getText().toString();
                String _requestPhoneNumber = requestPhoneNumber.getText().toString();
                String _requestBloodGroup = requestBloodGroup.getSelectedItem().toString();
                String _requestAge = requestAge.getText().toString();
                String _requestAdresse = requestAdresse.getText().toString();
                // insertion
                String WarningMessage;
                Request request = new Request(-1,"","","","","",-1);
                request.setFullName(_requestFullName);
                request.setPhoneNumber(_requestPhoneNumber);
                request.setBloodGroup(_requestBloodGroup);
                request.setAge(_requestAge);
                request.setAdresse(_requestAdresse);
                request.setUserId(currentUserId);
                if(dataBase.addRequest(request)){
                    WarningMessage = "Request added successfully !";
                }else{
                    WarningMessage = "Request failed !";
                }
                Toast.makeText(RequestActivity.this,WarningMessage,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(RequestActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        // Label_BloodGroup
        TextView Label_BloodGroup = (TextView) findViewById(R.id.Label_BloodGroup);
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
    public void Logout(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    public void CallHim(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:0123456789"));
        startActivity(intent);
    }
    public void MyProfile(View view) {
        Intent intent = new Intent(this,AccountActivity.class);
        startActivity(intent);
    }
    public void GoToHome(View view) {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}