package com.genius.odsurveyor.ui.questionset;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.genius.odsurveyor.R;

public class QuestionSetAddFragment extends Fragment {

    private QuestionSetAddViewModel mViewModel;

    public static QuestionSetAddFragment newInstance() {
        return new QuestionSetAddFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.question_set_add_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(QuestionSetAddViewModel.class);
        // TODO: Use the ViewModel
    }

}
