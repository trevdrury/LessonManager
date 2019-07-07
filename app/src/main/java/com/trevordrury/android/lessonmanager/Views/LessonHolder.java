package com.trevordrury.android.lessonmanager.Views;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.trevordrury.android.lessonmanager.R;
import com.trevordrury.android.lessonmanager.model.Student;

public class LessonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private LessonAdapter adapter;
    private Student student;
    private TextView studentNameView;

    public LessonHolder(View itemView, LessonAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        studentNameView = itemView.findViewById(R.id.text_student_name);
    }

    public void bind(Student student) {
        this.student = student;
        studentNameView.setText(student.getStudentName());
    }

    @Override
    public void onClick(View v) {

    }
}
