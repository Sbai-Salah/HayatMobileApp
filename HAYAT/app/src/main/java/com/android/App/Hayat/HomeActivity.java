package com.android.App.Hayat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ListView DonnersList,RequestsList;
    Button btnRequest,btnDonnation;

    Integer[] imgid={
            R.mipmap.avatar1,R.mipmap.avatar2,
            R.mipmap.avatar3,R.mipmap.avatar1,
            R.mipmap.avatar2,
            R.mipmap.avatar1,R.mipmap.avatar2,
            R.mipmap.avatar3,R.mipmap.avatar1,
            R.mipmap.avatar2,
            R.mipmap.avatar1,R.mipmap.avatar2,
            R.mipmap.avatar3,R.mipmap.avatar1,
            R.mipmap.avatar2,
            R.mipmap.avatar1,R.mipmap.avatar2,
            R.mipmap.avatar3,R.mipmap.avatar1,
            R.mipmap.avatar2,
    };
    private DatabaseHelper dataBase;
    private Spinner chooseActivity;
    private static final String[] paths =
            {"Donners","Requests"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // choose activity spinner -----------------------------------------------------------------
        //------------------------------------------------------------------------------------------
        chooseActivity = (Spinner)findViewById(R.id.chooseActivity);
        ArrayAdapter<String> chooseActivitAadapter = new ArrayAdapter<String>(HomeActivity.this,
                android.R.layout.simple_spinner_item,paths);
        chooseActivitAadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseActivity.setAdapter(chooseActivitAadapter);
        chooseActivity.setOnItemSelectedListener(this);
        chooseActivity.setSelection(0);

        // dataBase --------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------
        dataBase = new DatabaseHelper(HomeActivity.this);

        // DonnersList -----------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------
        ArrayList<Donnation> donnationList = new ArrayList<Donnation>();
        donnationList = dataBase.getAllDonnations();
        ArrayList<String> donnationUserIdLabelArrayList = new ArrayList<String>();
        ArrayList<String> donnationIdLabelArrayList = new ArrayList<String>();
        ArrayList<String> donnationFullNameLabelArrayList = new ArrayList<String>();
        ArrayList<String> donnationBloodLabelArrayList = new ArrayList<String>();
        ArrayList<String> donnationAdresseLabelArrayList = new ArrayList<String>();
        for(int i=0;i<donnationList.size();i++){
            donnationUserIdLabelArrayList.add(Integer.toString(donnationList.get(i).getUserId()));
            donnationIdLabelArrayList.add(Integer.toString(donnationList.get(i).getId()));
            donnationFullNameLabelArrayList.add(donnationList.get(i).getFullName());
            donnationBloodLabelArrayList.add(donnationList.get(i).getBloodGroup());
            donnationAdresseLabelArrayList.add(donnationList.get(i).getAdresse());
        }
        String[] donnationIdLabelList = new String[donnationIdLabelArrayList.size()];
        donnationIdLabelArrayList.toArray(donnationIdLabelList);
        String[] donnationUserIdLabelList = new String[donnationUserIdLabelArrayList.size()];
        donnationUserIdLabelArrayList.toArray(donnationUserIdLabelList);
        String[] donnationFullNameLabelList = new String[donnationFullNameLabelArrayList.size()];
        donnationFullNameLabelArrayList.toArray(donnationFullNameLabelList);
        String[] donnationBloodLabelList = new String[donnationBloodLabelArrayList.size()];
        donnationBloodLabelArrayList.toArray(donnationBloodLabelList);
        String[] donnationAdresseLabelList = new String[donnationAdresseLabelArrayList.size()];
        donnationAdresseLabelArrayList.toArray(donnationAdresseLabelList);

        DonnationListAdapter donnersAdapter=new DonnationListAdapter(this, donnationIdLabelList, donnationUserIdLabelList, donnationFullNameLabelList, donnationAdresseLabelList, donnationBloodLabelList,imgid);
        DonnersList=(ListView)findViewById(R.id.DonnersList);
        DonnersList.setAdapter(donnersAdapter);
        DonnersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        // RequestsList ----------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------
        ArrayList<Request> requestsList = new ArrayList<Request>();
        requestsList = dataBase.getAllRequests();
        ArrayList<String> requestsIdLabelArrayList = new ArrayList<String>();
        ArrayList<String> requestsUserIdLabelArrayList = new ArrayList<String>();
        ArrayList<String> requestsFullNameLabelArrayList = new ArrayList<String>();
        ArrayList<String> requestsBloodLabelArrayList = new ArrayList<String>();
        ArrayList<String> requestsAdresseLabelArrayList = new ArrayList<String>();
        for(int i=0;i<requestsList.size();i++){
            requestsIdLabelArrayList.add(Integer.toString(requestsList.get(i).getId()));
            requestsUserIdLabelArrayList.add(Integer.toString(requestsList.get(i).getUserId()));
            requestsFullNameLabelArrayList.add(requestsList.get(i).getFullName());
            requestsBloodLabelArrayList.add(requestsList.get(i).getBloodGroup());
            requestsAdresseLabelArrayList.add(requestsList.get(i).getAdresse());
        }
        String[] requestsIdLabelList = new String[requestsIdLabelArrayList.size()];
        requestsIdLabelArrayList.toArray(requestsIdLabelList);
        String[] requestsUserIdLabelList = new String[requestsUserIdLabelArrayList.size()];
        requestsUserIdLabelArrayList.toArray(requestsUserIdLabelList);
        String[] requestsFullNameLabelList = new String[requestsFullNameLabelArrayList.size()];
        requestsFullNameLabelArrayList.toArray(requestsFullNameLabelList);
        String[] requestsBloodLabelList = new String[requestsBloodLabelArrayList.size()];
        requestsBloodLabelArrayList.toArray(requestsBloodLabelList);
        String[] requestsAdresseLabelList = new String[requestsAdresseLabelArrayList.size()];
        requestsAdresseLabelArrayList.toArray(requestsAdresseLabelList);

        RequestListAdapter requestsAdapter=new RequestListAdapter(this,requestsIdLabelList, requestsUserIdLabelList ,requestsFullNameLabelList, requestsAdresseLabelList, requestsBloodLabelList,imgid);
        RequestsList=(ListView)findViewById(R.id.RequestsList);
        RequestsList.setAdapter(requestsAdapter);
        RequestsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });


        // Make a request :
        btnRequest= (Button) findViewById(R.id.btnRequest);
        btnRequest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomeActivity.this,RequestActivity.class);
                startActivity(intent);
            }
        });
        // Make donnation
        btnDonnation = (Button) findViewById(R.id.btnDonnation);
        btnDonnation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomeActivity.this,DonnateActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        String WarningMessage;
        Intent RequestIntent;
        switch (position) {
            case 0:
                DonnersList.setVisibility(View.VISIBLE);
                RequestsList.setVisibility(View.GONE);
                break;
            case 1:
                RequestsList.setVisibility(View.VISIBLE);
                DonnersList.setVisibility(View.GONE);
                break;
            case 2:
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
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
    public void GoToHome(View view) {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
    public void Logout(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
