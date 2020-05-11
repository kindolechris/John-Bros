package com.genius.odsurveyor.ui.respondents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.genius.odsurveyor.R;
import com.genius.odsurveyor.adapters.SurveyAdapter;
import com.genius.odsurveyor.adapters.SurveyRespondentsAdapter;
import com.genius.odsurveyor.models.RespondentsModel;
import com.genius.odsurveyor.models.SurveyModel;
import com.genius.odsurveyor.ui.surveyor.SurveyListActivity;
import com.genius.odsurveyor.ui.surveyor.SurveyWorkSpaceActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RespondentsActivity extends AppCompatActivity {

    private String surveyname;
    private RecyclerView surveyrespondentsrecycler;
    private SurveyRespondentsAdapter surveyRespondentsAdapter;
    private FloatingActionButton startsurveyFloatingBtn;
    private ArrayList<RespondentsModel> respondentsModels;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respondents);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        surveyrespondentsrecycler = (RecyclerView) findViewById(R.id.surveyrespondentsrecycler);
        startsurveyFloatingBtn = (FloatingActionButton) findViewById(R.id.startSurveyingFloatingActionBtn);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            surveyname = extras.getString("surveyname");
        }


        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Respondents of " + "( " + surveyname + " )");
        }

        startsurveyFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), SurveyWorkSpaceActivity.class);
                startActivity(myIntent);
            }
        });

        getRespondents();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getRespondents(){
        respondentsModels = new ArrayList<RespondentsModel>();
        respondentsModels.clear();
        databaseReference = FirebaseDatabase.getInstance().getReference("Respondents");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                respondentsModels.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    RespondentsModel survey = snapshot.getValue(RespondentsModel.class);
                    respondentsModels.add(survey);
                }
                surveyRespondentsAdapter = new SurveyRespondentsAdapter(RespondentsActivity.this, respondentsModels);
                surveyrespondentsrecycler.setHasFixedSize(true);
                surveyrespondentsrecycler.setLayoutManager(new LinearLayoutManager(RespondentsActivity.this));
                surveyrespondentsrecycler.setAdapter(surveyRespondentsAdapter);
                surveyrespondentsrecycler.setLayoutManager(new LinearLayoutManager(RespondentsActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
