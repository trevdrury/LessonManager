package com.trevordrury.android.lessonmanager.Views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.trevordrury.android.lessonmanager.R;
import com.trevordrury.android.lessonmanager.model.Student;

public class LessonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private LessonAdapter adapter;
    private Student student;
    private TextView studentNameView;
    private TextView lessonTimeView;
    private ImageView lessonTypeView;

    public LessonHolder(View itemView, LessonAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        studentNameView = itemView.findViewById(R.id.text_student_name);
        lessonTimeView = itemView.findViewById(R.id.lesson_time_textview);
        lessonTypeView = itemView.findViewById(R.id.icon_lesson_type);
    }

    public void bind(Student student) {
        this.student = student;
        studentNameView.setText(student.getStudentName());
        lessonTimeView.setText(student.getLessonTime());

        if (student.getStudentInstrument().equals("voice")) {
            lessonTypeView.setImageResource(R.drawable.microphone);
        } else if (student.getStudentInstrument().equals("piano")) {
            lessonTypeView.setImageResource(R.drawable.icon_piano);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
