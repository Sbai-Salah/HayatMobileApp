package com.example.hayat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void SeeDonners(View v){
        Intent intent = new Intent(this,DonnorActivity.class);
        startActivity(intent);
    }


//    public void DonateBlood(View v){
//        Intent intent = new Intent(this,DonateActivity.class);
//        startActivity(intent);
//    }


    public void SearchMap(View v){
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }
}