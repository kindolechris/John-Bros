package com.genius.odsurveyor.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.odsurveyor.Models.ProjectModel;
import com.genius.odsurveyor.R;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private List<ProjectModel> projects;
    Context context;
    Dialog dialog1,dialog2,dialog3;

    int index;

    public ProjectAdapter(Context context, List<ProjectModel> projects) {
        this.projects = projects;
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

        TextView txtprojectname;
        TextView txtprojectdate;
        TextView txtprojectprogress;
        TextView txtPlace;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtprojectname = (TextView)itemView.findViewById(R.id.txtprojectname);
            txtPlace = (TextView)itemView.findViewById(R.id.txtPlace);
            txtprojectdate = (TextView)itemView.findViewById(R.id.txtprojectdate);
            txtprojectprogress = (TextView)itemView.findViewById(R.id.txtprogress);
            txtprojectname.setSelected(true);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.itemView.setTag(projects.get(position));
        holder.txtprojectname.setText(projects.get(position).getProjectName());
        holder.txtPlace.setText(projects.get(position).getAddress1());
        holder.txtprojectdate.setText(projects.get(position).getDate().substring(0,10));
        holder.txtprojectprogress.setText("Progress :: "+ String.valueOf(projects.get(position).getProgress()) + " % DONE");

    }

    @Override
    public int getItemCount() {
        return projects.size();
    }
}
