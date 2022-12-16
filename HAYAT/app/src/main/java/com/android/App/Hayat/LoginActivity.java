package com.android.App.Hayat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper dataBase;
    Button loginBtn;
    EditText loginEmail,loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // redirect to signup page :
        TextView GoToLogIn = (TextView)findViewById(R.id.GoToSignup);
        GoToLogIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        /* Login Information */
        loginEmail = (EditText)findViewById(R.id.loginEmail);
        loginPassword = (EditText)findViewById(R.id.loginPassword);
        // Button
        loginBtn = (Button)findViewById(R.id.loginBtn);

        /* Databse Initialisation */
        dataBase = new DatabaseHelper(LoginActivity.this);

        /* Btn EventListners */
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View view){
                String _loginEmail = loginEmail.getText().toString();
                String _loginPassword = loginPassword.getText().toString();

                if(_loginEmail.equals("") || _loginPassword.equals("")){
                    String WarningMessage = "Please fill all fields !";
                    Toast.makeText(LoginActivity.this,WarningMessage,Toast.LENGTH_LONG).show();
                }else{
                    String regex = "^(.+)@(.+)$";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(_loginEmail);
                    if(!matcher.matches()){
                        String WarningMessage = "Invalid email format !";
                        Toast.makeText(LoginActivity.this,WarningMessage,Toast.LENGTH_LONG).show();
                    }else{
                        if(!(dataBase.checkUserByEmail(_loginEmail))) {
                            String WarningMessage = "Email doesn't exist !";
                            Toast.makeText(LoginActivity.this, WarningMessage, Toast.LENGTH_LONG).show();
                        }else{
                            if(!(dataBase.checkUser(_loginEmail,getMd5(_loginPassword)))){
                                String WarningMessage = "Incorrect password !";
                                Toast.makeText(LoginActivity.this,WarningMessage,Toast.LENGTH_LONG).show();
                            }else{
                                // store user info in SharedPreferences :
                                User currentUser = dataBase.getUser(_loginEmail,getMd5(_loginPassword));
                                SharedPreferences settings = LoginActivity.this.getSharedPreferences("User", 0);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putInt("currentUserId", currentUser.getId());
                                editor.putString("currentUserFullName", currentUser.getFullName());
                                editor.putString("currentUserEmail", currentUser.getEmail());
                                editor.putString("currentUserPhone", currentUser.getPhone());
                                editor.putString("currentUserSexe", currentUser.getSexe());
                                editor.putString("currentUserAge", currentUser.getAge());
                                editor.putString("currentUserBlood", currentUser.getBlood());
                                editor.putString("currentUserAdresse", currentUser.getAdresse());
                                editor.commit();
                                // redirect to home page :
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }

    /* hash md5 fucntion for secyrity reasons : */
    public static String getMd5(String input)
    {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
