package com.android.App.Hayat;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by amine-smahi on 12/13/18.
 */

class DonnationListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] fullNameSubtitleList;
    private final String[] adressSubtitleList;
    private final String[] bloodSubtitleList;
    private final String[] idSubtitleList;
    private final String[] donnationUserIdList;
    private final Integer[] imgid;
    private DatabaseHelper database;

    public DonnationListAdapter(Activity context, String[] idSubtitleList, String[] donnationUserIdList, String[] fullNameSubtitleList, String[] adressSubtitleList , String[] bloodSubtitleList, Integer[] imgid) {
        super(context, R.layout.mylist, fullNameSubtitleList);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.idSubtitleList=idSubtitleList;
        this.fullNameSubtitleList=fullNameSubtitleList;
        this.adressSubtitleList=adressSubtitleList;
        this.bloodSubtitleList=bloodSubtitleList;
        this.donnationUserIdList=donnationUserIdList;
        this.imgid=imgid;

    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null,true);

        // delete button ---------------------------------------------------------------------------
        // set fullname and phone number values
        SharedPreferences settingsResult =getContext().getSharedPreferences("User", MODE_PRIVATE);
        String currentUserId = Integer.toString(settingsResult.getInt("currentUserId", 0));
        Button deleteBtn = (Button) rowView.findViewById(R.id.Delete);
        if(currentUserId.equals(donnationUserIdList[position])){
            deleteBtn.setVisibility(View.VISIBLE);
        }else{
            deleteBtn.setVisibility(View.GONE);
        }
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new DatabaseHelper(getContext());
                int donnationIdToDelete = Integer.parseInt(idSubtitleList[position]);
                if(database.deleteDonnation(donnationIdToDelete)){
                    String WarningMessage = "Donnation deleted !";
                    Toast.makeText(getContext(),WarningMessage,Toast.LENGTH_LONG).show();
                    rowView.setVisibility(View.GONE);
                    Intent intent = new Intent(getContext(),HomeActivity.class);
                    context.startActivity(intent);
                }else{
                    String WarningMessage = "An error has occured !";
                    Toast.makeText(getContext(),WarningMessage,Toast.LENGTH_LONG).show();
                }
            }
        });

        // view profil button ----------------------------------------------------------------------
        Button viewProfil = (Button) rowView.findViewById(R.id.viewProfil);
        viewProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set donnation profil info
                database = new DatabaseHelper(getContext());
                String itemId = idSubtitleList[position];
                Donnation donnation = database.getDonnationById(itemId);
                // set donnation details
                SharedPreferences settings = getContext().getSharedPreferences("donnationDetails", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("donnationId", donnation.getId());
                editor.putString("donnationFullName", donnation.getFullName());
                editor.putString("donnationPhoneNumber", donnation.getPhoneNumber());
                editor.putString("donnationAge", donnation.getAge());
                editor.putString("donnationBloodGroup", donnation.getBloodGroup());
                editor.putString("donnationAdresse", donnation.getAdresse());
                editor.putInt("donnationUserId", donnation.getUserId());
                editor.commit();
                //redirect
                Intent intent = new Intent(getContext(), DonnationDetailsActivity.class);
                context.startActivity(intent);
            }
        });

        Button callHim = (Button) rowView.findViewById(R.id.CallHim);
        callHim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new DatabaseHelper(getContext());
                String itemId = idSubtitleList[position];
                Donnation donnation = database.getDonnationById(itemId);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+donnation.getPhoneNumber()));
                context.startActivity(intent);
            }
        });

        TextView fullNameSubtitle = (TextView) rowView.findViewById(R.id.fullNameSubtitle);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView adressSubtitle = (TextView) rowView.findViewById(R.id.adressSubtitle);
        TextView bloodSubtitle = (TextView) rowView.findViewById(R.id.bloodSubtitle);

        fullNameSubtitle.setText(fullNameSubtitleList[position]);
        imageView.setImageResource(imgid[position]);
        adressSubtitle.setText(adressSubtitleList[position]);
        bloodSubtitle.setText(bloodSubtitleList[position]);

        return rowView;
    };
}