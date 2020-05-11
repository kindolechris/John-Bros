package com.genius.odsurveyor.ui.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.genius.odsurveyor.models.ProjectModel;
import com.genius.odsurveyor.R;
import com.genius.odsurveyor.adapters.ProjectAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProjectsActivity extends AppCompatActivity {

    private RecyclerView projectrecycler;
    private ArrayList<ProjectModel> projectModel;
    private DatabaseReference reference;
    private ProjectAdapter projectAdapter;
    private FloatingActionButton fabAddProjectbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fabAddProjectbtn = (FloatingActionButton) findViewById(R.id.addprojectFloatingActionBtn);

        projectrecycler = (RecyclerView) findViewById(R.id.projectlistrecycler);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("OD SURVEY | Projects");
        }

        fabAddProjectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ProjectsActivity.this, AddProjectActivity.class);
                ProjectsActivity.this.startActivity(myIntent);
            }
        });

        getProjectlist();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void  getProjectlist(){
        //serverpgsBar.setVisibility(View.VISIBLE);
        projectModel = new ArrayList<ProjectModel>();
        projectModel.clear();
        reference = FirebaseDatabase.getInstance().getReference("Projets");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                projectModel.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ProjectModel user = snapshot.getValue(ProjectModel.class);
                    projectModel.add(user);
                }
                projectAdapter = new ProjectAdapter(ProjectsActivity.this, projectModel);

                projectrecycler.setHasFixedSize(true);
                projectrecycler.setLayoutManager(new LinearLayoutManager(ProjectsActivity.this));
                projectrecycler.setAdapter(projectAdapter);
                projectrecycler.setLayoutManager(new LinearLayoutManager(ProjectsActivity.this));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
