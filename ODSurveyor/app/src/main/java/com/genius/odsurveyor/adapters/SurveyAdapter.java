package com.genius.odsurveyor.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.odsurveyor.models.SurveyModel;
import com.genius.odsurveyor.R;
import com.genius.odsurveyor.ui.respondents.RespondentsActivity;

import java.util.List;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.ViewHolder> {
    private List<SurveyModel> survey;
    Context context;

    public SurveyAdapter(Context context, List<SurveyModel> projects) {
        this.survey = projects;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.survey_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtsurveyname;
        TextView txtsurveydate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtsurveyname = (TextView)itemView.findViewById(R.id.txtsurveyname);
            txtsurveydate = (TextView)itemView.findViewById(R.id.txtsurveydate);
            txtsurveyname.setSelected(true);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.itemView.setTag(survey.get(position));
        holder.txtsurveyname.setText(survey.get(position).getSurveyname());
        holder.txtsurveydate.setText(survey.get(position).getDate().substring(0,10));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, RespondentsActivity.class);
                myIntent.putExtra("surveyname",survey.get(position).getSurveyname());
                context.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return survey.size();
    }
}
