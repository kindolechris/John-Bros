package com.genius.odsurveyor.ui.project;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.genius.odsurveyor.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProjectsHomeFragment extends Fragment {

    private ProjectsHomeViewModel mViewModel;

    public static ProjectsHomeFragment newInstance() {
        return new ProjectsHomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.projects_home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn_add_pj = view.findViewById(R.id.btn_add_pj);
        FloatingActionButton fab_addProject=view.findViewById(R.id.fab_addProject);

        final NavController navController= Navigation.findNavController(view);

        fab_addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_projectsHomeFragment2_to_projectAddFragment2);
            }
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProjectsHomeViewModel.class);
        // TODO: Use the ViewModel
    }

}
