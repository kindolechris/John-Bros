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

public class QuestionSetEditFragment extends Fragment {

    private QuestionSetEditViewModel mViewModel;

    public static QuestionSetEditFragment newInstance() {
        return new QuestionSetEditFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.question_set_edit_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(QuestionSetEditViewModel.class);
        // TODO: Use the ViewModel
    }

}
