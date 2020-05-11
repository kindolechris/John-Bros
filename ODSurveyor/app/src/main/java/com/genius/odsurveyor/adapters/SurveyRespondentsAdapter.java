package com.genius.odsurveyor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.odsurveyor.R;
import com.genius.odsurveyor.models.RespondentsModel;

import java.util.List;

public class SurveyRespondentsAdapter extends RecyclerView.Adapter<SurveyRespondentsAdapter.ViewHolder> {
    private List<RespondentsModel> respondentsModels;
    Context context;

    public SurveyRespondentsAdapter(Context context, List<RespondentsModel> projects) {
        this.respondentsModels = projects;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.respondent_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtsurespondentname;
        TextView txtsurveydate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtsurespondentname = (TextView)itemView.findViewById(R.id.txtrespondentname);
            txtsurespondentname.setSelected(true);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.itemView.setTag(respondentsModels.get(position));
        holder.txtsurespondentname.setText(respondentsModels.get(position).getRespondentname());
    }

    @Override
    public int getItemCount() {
        return respondentsModels.size();
    }
}
