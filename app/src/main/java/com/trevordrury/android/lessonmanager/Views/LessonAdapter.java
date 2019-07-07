package com.trevordrury.android.lessonmanager.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trevordrury.android.lessonmanager.R;
import com.trevordrury.android.lessonmanager.model.Student;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonHolder> {

    private List<Student> students;

    public LessonAdapter(List<Student> students) { this.students = students; }

    @NonNull
    @Override
    public LessonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.lesson_list_item, parent, false);
        LessonHolder holder = new LessonHolder(itemView, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LessonHolder holder, int position) {
        Student student = students.get(holder.getAdapterPosition());
        holder.bind(student);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
