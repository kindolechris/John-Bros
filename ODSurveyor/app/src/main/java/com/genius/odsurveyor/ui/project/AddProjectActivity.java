package com.genius.odsurveyor.ui.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.genius.odsurveyor.Models.ProjectModel;
import com.genius.odsurveyor.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddProjectActivity extends AppCompatActivity {

    private ProgressBar addProjectProgressBar;
    private MaterialButton addProjectBtn;
    private ScrollView addProjectContent;
    private EditText tv_projectName;
    private EditText txt_client_name;
    private EditText txt_address1;
    private EditText txt_address2;
    private Spinner spinner_country;
    private Spinner spinner_state;
    private Spinner spinner_district;
    private Spinner spinner_division;
    private Spinner spinner_ward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addProjectBtn =(MaterialButton) findViewById(R.id.addProjectBtn);
        tv_projectName =(EditText) findViewById(R.id.tv_projectName);
        txt_client_name =(EditText) findViewById(R.id.txtClientName);
        txt_address1 =(EditText) findViewById(R.id.txtAddress1);
        txt_address2 =(EditText) findViewById(R.id.txtAddress2);
        spinner_country =(Spinner) findViewById(R.id.spinner_country);
        spinner_state =(Spinner) findViewById(R.id.spinner_state);
        spinner_district =(Spinner) findViewById(R.id.spinner_district);
        spinner_division =(Spinner) findViewById(R.id.spinner_division);
        spinner_ward =(Spinner) findViewById(R.id.spinner_ward);

        addProjectProgressBar =(ProgressBar) findViewById(R.id.addProjectProgressBar);
        addProjectContent =(ScrollView) findViewById(R.id.addProjectContent);

        addProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(tv_projectName.getText().toString().trim()) || TextUtils.isEmpty(txt_client_name.getText().toString().trim()) || TextUtils.isEmpty(txt_address1.getText().toString().trim()) || TextUtils.isEmpty(txt_address2.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"Please insert values.",Toast.LENGTH_SHORT).show();
                    return;
                }

                addProjectProgressBar.setVisibility(View.VISIBLE);
                addProjectContent.setVisibility(View.INVISIBLE);
                Addproject(tv_projectName.getText().toString().trim(),spinner_division.getSelectedItem().toString(),spinner_state.getSelectedItem().toString(),spinner_country.getSelectedItem().toString(),spinner_district.getSelectedItem().toString(),spinner_ward.getSelectedItem().toString(),txt_client_name.getText().toString().trim(),txt_address1.getText().toString().trim(),txt_address2.getText().toString().trim());
            }
        });


        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("OD SURVEY | Add Project");
        }

    }

    public void Addproject(String projectName,String division,String state,String country,String district,String ward,String clientName,String address1,String address2){
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("Projets");
        String id = reference.push().getKey();
        ProjectModel bondTransaction = new ProjectModel(id,projectName,"0",getCurrentTimeStamp(),division,state,country,district,ward,clientName,address1,address2);
        reference.child(id).setValue(bondTransaction).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"Project added successfully.",Toast.LENGTH_LONG).show();
                addProjectProgressBar.setVisibility(View.INVISIBLE);
                addProjectContent.setVisibility(View.VISIBLE);
            }
        });
    }

    public static String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
