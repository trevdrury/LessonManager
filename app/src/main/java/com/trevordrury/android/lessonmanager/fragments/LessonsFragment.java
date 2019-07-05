package com.trevordrury.android.lessonmanager.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trevordrury.android.lessonmanager.R;
import com.trevordrury.android.lessonmanager.Views.LessonsRecyclerView;
import com.trevordrury.android.lessonmanager.Views.StudentAdapter;
import com.trevordrury.android.lessonmanager.model.Student;
import com.trevordrury.android.lessonmanager.model.StudentProvider;

import java.util.List;

public class LessonsFragment extends Fragment {

    private LessonsRecyclerView lessonsRecyclerView;
    private StudentAdapter adapter;

    public static LessonsFragment newInstance() {
        return new LessonsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lessons, container,false);

        lessonsRecyclerView = view.findViewById(R.id.lesson_recycler_view);
        lessonsRecyclerView.setEmptyView(view.findViewById(R.id.lesson_empty_view));
        lessonsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        StudentProvider studentProvider = StudentProvider.get(getActivity());
        List<Student> students = studentProvider.getStudents();

        if (lessonsRecyclerView.getAdapter() == null) {
            adapter = new StudentAdapter(students);
            lessonsRecyclerView.setAdapter(adapter);
        } else {
            adapter.setStudents(students);
            adapter.notifyDataSetChanged();
        }
    }
}
