package com.android.App.Hayat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class SignupActivity extends AppCompatActivity {

    DatabaseHelper dataBase;
    Button registerBtn;
    EditText registerFullName,registerEmail,registerPhone,registerPassword,registerRepeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // redirect to login page :
        TextView GoToLogIn = (TextView)findViewById(R.id.GoToLogIn);
        GoToLogIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        /* Register Information */
        // EditText fields
        registerFullName = (EditText)findViewById(R.id.registerFullname);
        registerEmail = (EditText)findViewById(R.id.registerEmail);
        registerPhone = (EditText)findViewById(R.id.registerPhone);
        registerPassword = (EditText)findViewById(R.id.registerPassword);
        registerRepeatPassword = (EditText)findViewById(R.id.registerRepeatPassword);
        // Button
        registerBtn = (Button)findViewById(R.id.registerBtn);
        /* Databse Initialisation */
        dataBase = new DatabaseHelper(SignupActivity.this);

        /* Btn EventListners */
        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String _registerFullName = registerFullName.getText().toString();
                String _registerEmail = registerEmail.getText().toString();
                String _registerPhone = registerPhone.getText().toString();
                String _registerPassword = registerPassword.getText().toString();
                String _registerRepeatPassword = registerRepeatPassword.getText().toString();

                if(_registerFullName.equals("") || _registerEmail.equals("") || _registerPhone.equals("") || _registerPassword.equals("") || _registerRepeatPassword.equals("") ){
                    String WarningMessage = "Please fill all fields !";
                    Toast.makeText(SignupActivity.this,WarningMessage,Toast.LENGTH_LONG).show();
                }else{
                    String regex = "^(.+)@(.+)$";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(_registerEmail);
                    if(!matcher.matches()){
                        String WarningMessage = "Invalid email format !";
                        Toast.makeText(SignupActivity.this,WarningMessage,Toast.LENGTH_LONG).show();
                    }else{
                        if(!(_registerPassword.equals(_registerRepeatPassword))){
                            String WarningMessage = "Password confirmation don't match !";
                            Toast.makeText(SignupActivity.this,WarningMessage,Toast.LENGTH_LONG).show();
                        }else{
                            if(!isValidPassword(_registerPassword)){
                                String WarningMessage = "Your password must be at least 8 characters long, contain at least one number and have a mixture of uppercase and lowercase letters!";
                                Toast.makeText(SignupActivity.this,WarningMessage,Toast.LENGTH_LONG).show();
                            }else{
                                if(dataBase.checkUserByEmail(_registerEmail)){
                                    String WarningMessage = "Email already taken !";
                                    Toast.makeText(SignupActivity.this,WarningMessage,Toast.LENGTH_LONG).show();
                                }else{
                                    /* create a new user instance */
                                    User newUser = new User();
                                    newUser.setFullName(_registerFullName);
                                    newUser.setEmail(_registerEmail);
                                    newUser.setPhone(_registerPhone);
                                    newUser.setSexe("");
                                    newUser.setAge("");
                                    newUser.setBlood("");
                                    newUser.setAdresse("");
                                    // hasing password md5 :
                                    newUser.setPassword(getMd5(_registerPassword));
                                    // add user in databse table 'user'
                                    Boolean isAdded = dataBase.addUser(newUser);
                                    if(isAdded){
                                        String WarningMessage = "Registration successful ! \n Try to Log in";
                                        Toast.makeText(SignupActivity.this,WarningMessage,Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    /* password format validation */
    public static boolean isValidPassword(String s) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9\\!\\@\\#\\$]{8,}");
        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
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