package com.genius.odsurveyor.ui.surveyor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.genius.odsurveyor.Models.ProjectModel;
import com.genius.odsurveyor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddSurveyActivity extends AppCompatActivity {

    private Spinner projectSpinner;
    private DatabaseReference databaseReference;
    private ArrayList<ProjectModel> projectModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_survey);
        projectSpinner = (Spinner) findViewById(R.id.spinner_project_name);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("OD SURVEY | Add survey");
        }

        getprojects();
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
}
