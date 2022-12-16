package com.android.App.Hayat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AccountActivity extends AppCompatActivity {

    private Button updateBtn;
    DatabaseHelper Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // set fullname and phone number values
        SharedPreferences settingsResult =this.getSharedPreferences("User", MODE_PRIVATE);
        int currentUserId = settingsResult.getInt("currentUserId", 0);
        String currentUserFullName = settingsResult.getString("currentUserFullName", "");
        String currentUserEmail = settingsResult.getString("currentUserEmail", "");
        String currentUserPhone = settingsResult.getString("currentUserPhone", "");
        String currentUserSexe = settingsResult.getString("currentUserSexe", "");
        String currentUserAge = settingsResult.getString("currentUserAge", "");
        String currentUserBlood = settingsResult.getString("currentUserBlood", "");
        String currentUserAdresse = settingsResult.getString("currentUserAdresse", "");

        TextView FullNameTextView = findViewById(R.id.myProfilFullName);
        FullNameTextView.setText(currentUserFullName);

        TextView PhoneTextView = findViewById(R.id.myProfilPhoneNumber);
        PhoneTextView.setText(currentUserPhone);

        TextView AgeTextView = findViewById(R.id.myProfilAge);
        AgeTextView.setText(currentUserAge);

        TextView BloodTextView = findViewById(R.id.myProfilBloodGroup);
        BloodTextView.setText(currentUserBlood);

        TextView myProfilLabelAge = (TextView)findViewById(R.id.myProfilLabelAge);
        myProfilLabelAge.setText(currentUserAge+" ans");

        TextView myProfilLabelSexe = (TextView)findViewById(R.id.myProfilLabelSexe);
        myProfilLabelSexe.setText(currentUserSexe);

        TextView myProfilAdresse = (TextView)findViewById(R.id.myProfilAdresse);
        myProfilAdresse.setText(currentUserAdresse);

        TextView myProfilLabelFullName = (TextView)findViewById(R.id.myProfilLabelFullName);
        myProfilLabelFullName.setText(currentUserFullName);

        RadioButton myProfilSexeMale = (RadioButton)findViewById(R.id.myProfilSexeMale);
        RadioButton myProfilSexeFemale = (RadioButton)findViewById(R.id.myProfilSexeFemale);
        switch (currentUserSexe){
            case "Male" :
                myProfilSexeMale.setChecked(true);
                myProfilSexeFemale.setChecked(false);
                break;
            case "Female" :
                myProfilSexeMale.setChecked(false);
                myProfilSexeFemale.setChecked(true);
                break;
        }

        RadioGroup myProfilSexe = (RadioGroup) findViewById(R.id.myProfilSexe);
        myProfilSexe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences settings = AccountActivity.this.getSharedPreferences("User", 0);
                SharedPreferences.Editor editor = settings.edit();
                RadioButton myProfilSexeMale = (RadioButton)findViewById(R.id.myProfilSexeMale);
                RadioButton myProfilSexeFemale = (RadioButton)findViewById(R.id.myProfilSexeFemale);
                switch(checkedId){
                    case R.id.myProfilSexeMale:
                        editor.putString("currentUserSexe", "Male");
                        myProfilSexeMale.setChecked(true);
                        myProfilSexeFemale.setChecked(false);
                        break;
                    case R.id.myProfilSexeFemale:
                        editor.putString("currentUserSexe", "Female");
                        myProfilSexeFemale.setChecked(true);
                        myProfilSexeMale.setChecked(false);
                        break;
                }
                editor.commit();
            }
        });

        // update User
        Database = new DatabaseHelper(AccountActivity.this);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myProfilFullName = FullNameTextView.getText().toString();
                String myProfilPhoneNumber = PhoneTextView.getText().toString();
                String myProfilAge = AgeTextView.getText().toString();
                String myProfilBloodGroup = BloodTextView.getText().toString();
                String myProfilLabelAdresse = myProfilAdresse.getText().toString();
                User updatedUser = new User();
                updatedUser.setId(currentUserId);
                updatedUser.setFullName(myProfilFullName);
                updatedUser.setPhone(myProfilPhoneNumber);
                updatedUser.setSexe(currentUserSexe);
                updatedUser.setAge(myProfilAge);
                updatedUser.setBlood(myProfilBloodGroup);
                updatedUser.setAdresse(myProfilLabelAdresse);
                String WarningMessage;
                if(Database.updateUser(updatedUser)==1){
                    WarningMessage = "Information updated successfully !";
                }else{
                    WarningMessage = "Update Faild !";
                }
                Toast.makeText(AccountActivity.this, WarningMessage, Toast.LENGTH_LONG).show();
                // affectation des nouveau valeurs :
                SharedPreferences settings = AccountActivity.this.getSharedPreferences("User", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("currentUserFullName", myProfilFullName);
                editor.putString("currentUserPhone", myProfilPhoneNumber);
                editor.putString("currentUserAge", myProfilAge);
                editor.putString("currentUserBlood", myProfilBloodGroup);
                editor.putString("currentUserAdresse", myProfilLabelAdresse);
                editor.commit();
            }
        });
    }

    public void CallHim(View view)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        SharedPreferences settingsResult =this.getSharedPreferences("User", MODE_PRIVATE);
        String currentUserPhone = settingsResult.getString("currentUserPhone", "");
        intent.setData(Uri.parse("tel:"+currentUserPhone));
        startActivity(intent);
    }
    public void Save(View view) {
        Toast.makeText(AccountActivity.this, "Information saved!",Toast.LENGTH_LONG).show();
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
