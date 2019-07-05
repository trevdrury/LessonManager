package com.trevordrury.android.lessonmanager.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.trevordrury.android.lessonmanager.R;
import com.trevordrury.android.lessonmanager.Views.StudentAdapter;
import com.trevordrury.android.lessonmanager.Views.StudentsRecyclerView;
import com.trevordrury.android.lessonmanager.model.Student;
import com.trevordrury.android.lessonmanager.model.StudentProvider;
import java.util.List;

public class StudentsFragment extends Fragment {

    private StudentsRecyclerView studentsRecyclerView;
    private StudentAdapter adapter;

    public static StudentsFragment newInstance() {
        return new StudentsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_students, container, false);

        studentsRecyclerView = view.findViewById(R.id.student_recycler_view);
        studentsRecyclerView.setEmptyView(view.findViewById(R.id.student_empty_view));
        studentsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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

        if (studentsRecyclerView.getAdapter() == null) {
            adapter = new StudentAdapter(students);
            studentsRecyclerView.setAdapter(adapter);
        } else {
            adapter.setStudents(students);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.app_bar_button, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_button) {
            Fragment addStudentFragment = new AddStudentFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, addStudentFragment, "ADD_STUDENT_FRAGMENT");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
