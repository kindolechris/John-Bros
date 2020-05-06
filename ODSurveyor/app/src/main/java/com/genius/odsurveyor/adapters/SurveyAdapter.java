package com.genius.odsurveyor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.odsurveyor.Models.SurveyModel;
import com.genius.odsurveyor.R;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtsurveyname;
        TextView txtsurveydate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtsurveyname = (TextView)itemView.findViewById(R.id.txtprojectname);
            txtsurveydate = (TextView)itemView.findViewById(R.id.txtsurveydate);
            txtsurveyname.setSelected(true);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.itemView.setTag(survey.get(position));
        holder.txtsurveyname.setText(survey.get(position).getSurveyname());
        holder.txtsurveydate.setText(survey.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return survey.size();
    }
}