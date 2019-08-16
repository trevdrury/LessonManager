package com.trevordrury.android.lessonmanager.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.trevordrury.android.lessonmanager.R;
import com.trevordrury.android.lessonmanager.model.Student;
import com.trevordrury.android.lessonmanager.model.StudentProvider;

import java.util.UUID;

public class AddStudentFragment extends Fragment {

    public static final String ARG_STUDENT_ID = "student_id";
    private Student student;
    private TextInputEditText studentNameField;
    private TextInputEditText studioNameField;
    private TextInputEditText studentInstrumentField;
    private MaterialSpinner lessonDayField;
    private TextInputEditText lessonTimeField;
    private TextInputEditText studentPhoneField;
    private TextInputEditText studentEmailField;
    private TextInputEditText parentNameField;
    private Button addStudentButton;

    public static AddStudentFragment newInstance(UUID studentId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_STUDENT_ID, studentId);
        AddStudentFragment fragment = new AddStudentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }

            String timeValue = data.getStringExtra("Time");

            lessonTimeField.setText(timeValue);
            lessonTimeField.clearFocus();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_student, container, false);

        studentNameField = v.findViewById(R.id.edit_student_name);

        studioNameField = v.findViewById(R.id.edit_studio_name);

        studentInstrumentField = v.findViewById(R.id.edit_student_instrument);

        lessonDayField = v.findViewById(R.id.edit_lesson_day);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.day_array,R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_view);
        lessonDayField.setAdapter(adapter);

        lessonTimeField = v.findViewById(R.id.edit_lesson_time);
        lessonTimeField.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                DialogFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setTargetFragment(AddStudentFragment.this, 0);
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    timePickerFragment.show(getFragmentManager(), "timePicker");
                }
                return false;
            }
        });



        studentPhoneField = v.findViewById(R.id.edit_student_phone);

        studentEmailField = v.findViewById(R.id.edit_student_email);

        parentNameField = v.findViewById(R.id.edit_student_parent);

        addStudentButton = v.findViewById(R.id.button_add_student);
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student = new Student();
                student.setStudentName(studentNameField.getText().toString());
                student.setStudioName(studioNameField.getText().toString());
                student.setStudentInstrument(studentInstrumentField.getText().toString().toLowerCase());
                student.setLessonDay(lessonDayField.getText().toString());
                student.setLessonTime(lessonTimeField.getText().toString());
                student.setPhone(studentPhoneField.getText().toString());
                student.setEmail(studentEmailField.getText().toString());
                student.setParentName(parentNameField.getText().toString());
                StudentProvider.get(getContext()).addStudent(student);

                getFragmentManager().beginTransaction().remove(AddStudentFragment.this).commit();
            }
        });

        return v;
    }

}
