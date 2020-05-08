package com.genius.odsurveyor.ui.surveyor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.genius.odsurveyor.Models.SurveyModel;
import com.genius.odsurveyor.R;
import com.genius.odsurveyor.adapters.SurveyAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SurveyListActivity extends AppCompatActivity {
    private FloatingActionButton fabAddSurveybtn;
    private RecyclerView surveyrecycler;
    private SurveyAdapter surveyAdapter;
    private ArrayList<SurveyModel> surveyModel;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fabAddSurveybtn = (FloatingActionButton) findViewById(R.id.addsurveyFloatingActionBtn);
        surveyrecycler = (RecyclerView) findViewById(R.id.surveltrecycler);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("OD SURVEY | Survey");
        }

        fabAddSurveybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SurveyListActivity.this, AddSurveyActivity.class);
                SurveyListActivity.this.startActivity(myIntent);
            }
        });

        getSurveylist();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void  getSurveylist(){
        surveyModel = new ArrayList<SurveyModel>();
        surveyModel.clear();
        databaseReference = FirebaseDatabase.getInstance().getReference("Surveys");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                surveyModel.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    SurveyModel survey = snapshot.getValue(SurveyModel.class);
                    surveyModel.add(survey);
                }
                surveyAdapter = new SurveyAdapter(SurveyListActivity.this, surveyModel);
                surveyrecycler.setHasFixedSize(true);
                surveyrecycler.setLayoutManager(new LinearLayoutManager(SurveyListActivity.this));
                surveyrecycler.setAdapter(surveyAdapter);
                surveyrecycler.setLayoutManager(new LinearLayoutManager(SurveyListActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
