package com.genius.odsurveyor.ui.surveyor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.genius.odsurveyor.models.ProjectModel;
import com.genius.odsurveyor.R;
import com.genius.odsurveyor.models.SurveyModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddSurveyActivity extends AppCompatActivity {

    private Spinner projectSpinner;
    private Spinner cordinatesSpinner;
    private Spinner stationSpinner;
    private Spinner questionSpinner;
    private DatabaseReference databaseReference;
    private ArrayList<ProjectModel> projectModel;
    private EditText surveynameEditext;
    private RelativeLayout addSurveyContent;
    private ProgressBar addSurveyProgressBar;
    private Button addSurveyBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_survey);
        projectSpinner = (Spinner) findViewById(R.id.spinner_project_name);
        stationSpinner = (Spinner) findViewById(R.id.spinner_station_name);
        cordinatesSpinner = (Spinner) findViewById(R.id.spinner_cordinates_name);
        questionSpinner = (Spinner) findViewById(R.id.spinner_question_set);
        surveynameEditext = (EditText) findViewById(R.id.txtsurveyname);
        addSurveyContent = (RelativeLayout) findViewById(R.id.addSurveyLayout);
        addSurveyProgressBar = (ProgressBar) findViewById(R.id.add_survey_loader);
        addSurveyBtn = (Button) findViewById(R.id.addSurveytBtn);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("OD SURVEY | Add survey");
        }

        getprojects();
        getStations();
        getCordinates();
        getQuestionSet();

        addSurveyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(surveynameEditext.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"Survey name required.",Toast.LENGTH_LONG).show();
                    return;
                }
                addSurveyProgressBar.setVisibility(View.VISIBLE);
                addSurveyContent.setVisibility(View.INVISIBLE);
                AddSurvey(surveynameEditext.getText().toString().trim(),projectSpinner.getSelectedItem().toString(),stationSpinner.getSelectedItem().toString(),cordinatesSpinner.getSelectedItem().toString(),questionSpinner.getSelectedItem().toString());
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void  getprojects(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Projets");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final List<String> projects = new ArrayList<String>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String _b = snapshot.child("projectName").getValue(String.class);
                    projects.add(_b);
                }

                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(AddSurveyActivity.this, R.layout.spinnertext, projects);
                areasAdapter.setDropDownViewResource(R.layout.spinnerdropdown);
                projectSpinner.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getStations(){
        String[] stations = {"Station 1","Station 2", "Station 3", "Station 4", "Station 5", "Station 6"};

        ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(AddSurveyActivity.this, R.layout.spinnertext, stations);
        areasAdapter.setDropDownViewResource(R.layout.spinnerdropdown);
        stationSpinner.setAdapter(areasAdapter);
    }

    private void getCordinates(){
        String[] cordinates = {"Coordinate 1","Coordinate 2", "Coordinate 3", "Coordinate 4", "Coordinate 5", "Coordinate 6"};

        ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(AddSurveyActivity.this, R.layout.spinnertext, cordinates);
        areasAdapter.setDropDownViewResource(R.layout.spinnerdropdown);
        cordinatesSpinner.setAdapter(areasAdapter);
    }

    private void getQuestionSet(){
        String[] questionset = {"Question set 1","Question set 2", "Question set 3", "Question set 4", "Question set 5", "Question set 6"};

        ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(AddSurveyActivity.this, R.layout.spinnertext, questionset);
        areasAdapter.setDropDownViewResource(R.layout.spinnerdropdown);
        questionSpinner.setAdapter(areasAdapter);
    }

    public void AddSurvey(String surveyname,String projectname,String station,String cordinate,String question ){
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("Surveys");
        String id = reference.push().getKey();
        SurveyModel surveyModel = new SurveyModel(id,surveyname,projectname,question,cordinate,station,getCurrentTimeStamp());
        reference.child(id).setValue(surveyModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"Survey added successfully.",Toast.LENGTH_LONG).show();
                addSurveyProgressBar.setVisibility(View.INVISIBLE);
                addSurveyContent.setVisibility(View.VISIBLE);
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
}
