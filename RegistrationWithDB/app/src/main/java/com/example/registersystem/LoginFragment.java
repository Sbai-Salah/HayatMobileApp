package com.example.registersystem;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }

    DatabaseHelper dataBase;
    Button loginBtn;
    EditText loginEmail,loginPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RelativeLayout fragment_login = (RelativeLayout) inflater.inflate(R.layout.fragment_login, container, false);

        /* Login Information */
        loginEmail = (EditText)fragment_login.findViewById(R.id.loginEmail);
        loginPassword = (EditText)fragment_login.findViewById(R.id.loginPassword);
        // Button
        loginBtn = (Button)fragment_login.findViewById(R.id.loginBtn);

        /* Databse Initialisation */
        dataBase = new DatabaseHelper(getActivity());

        /* Btn EventListners */
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String _loginEmail = loginEmail.getText().toString();
                String _loginPassword = loginPassword.getText().toString();

                if(_loginEmail.equals("") || _loginPassword.equals("")){
                    String WarningMessage = "Please fill all fields !";
                    Toast.makeText(getActivity(),WarningMessage,Toast.LENGTH_LONG).show();
                }else{
                    String regex = "^(.+)@(.+)$";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(_loginEmail);
                    if(!matcher.matches()){
                        String WarningMessage = "Invalid email format !";
                        Toast.makeText(getActivity(),WarningMessage,Toast.LENGTH_LONG).show();
                    }else{
                        if(!(dataBase.checkUserByEmail(_loginEmail))) {
                            String WarningMessage = "Email doesn't exist !";
                            Toast.makeText(getActivity(), WarningMessage, Toast.LENGTH_LONG).show();
                        }else{
                            if(!(dataBase.checkUser(_loginEmail,getMd5(_loginPassword)))){
                                String WarningMessage = "Incorrect password !";
                                Toast.makeText(getActivity(),WarningMessage,Toast.LENGTH_LONG).show();
                            }else{
                                String WarningMessage = "Login successful !";
                                Toast.makeText(getActivity(),WarningMessage,Toast.LENGTH_LONG).show();

                                // store user info in SharedPreferences :
                                User currentUser = dataBase.getUser(_loginEmail,getMd5(_loginPassword));
                                SharedPreferences settings = getActivity().getSharedPreferences("User", 0);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putInt("currentUserId", currentUser.getId());
                                editor.putString("currentUserFullName", currentUser.getFullName());
                                editor.putString("currentUserEmail", currentUser.getEmail());
                                editor.putString("currentUserPhone", currentUser.getPhone());
                                editor.commit();
                                // redirect to home page :
                                Intent intent = new Intent(getActivity(), Home.class);
                                startActivity(intent);
                            }
                        }
                    }
                }
            }
        });

        return fragment_login;
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