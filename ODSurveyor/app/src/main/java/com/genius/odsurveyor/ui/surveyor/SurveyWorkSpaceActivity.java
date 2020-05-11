package com.genius.odsurveyor.ui.surveyor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.genius.odsurveyor.R;
import com.genius.odsurveyor.models.RespondentsModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SurveyWorkSpaceActivity extends AppCompatActivity {

    private ProgressBar conductSurveyProgressBar;
    private RelativeLayout cunductSurveyContent;
    private Button buttonConductSurvey;
    private TextInputEditText respondentnametxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_work_space);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        respondentnametxt = (TextInputEditText) findViewById(R.id.respondentname);
        conductSurveyProgressBar = (ProgressBar) findViewById(R.id.conductSurveyProgressBar);
        cunductSurveyContent = (RelativeLayout) findViewById(R.id.cunductSurveyContent);
        buttonConductSurvey = (Button) findViewById(R.id.conductSurveytBtn);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("OD SURVEY | Survey workspace");
        }

        buttonConductSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(respondentnametxt.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"Respondent name required.",Toast.LENGTH_LONG).show();
                    return;
                }
                conductSurveyProgressBar.setVisibility(View.VISIBLE);
                cunductSurveyContent.setVisibility(View.INVISIBLE);
                ConductSurvey(respondentnametxt.getText().toString().trim());
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

    public void ConductSurvey(String respondentname){
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("Respondents");
        String id = reference.push().getKey();
        RespondentsModel respondentsModel = new RespondentsModel(id,respondentname,getCurrentTimeStamp());
        reference.child(id).setValue(respondentsModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"Survey conducted.",Toast.LENGTH_LONG).show();
                conductSurveyProgressBar.setVisibility(View.INVISIBLE);
                cunductSurveyContent.setVisibility(View.VISIBLE);
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
