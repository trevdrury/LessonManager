package com.trevordrury.android.lessonmanager.Views;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trevordrury.android.lessonmanager.R;
import com.trevordrury.android.lessonmanager.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentHolder> {

    private List<Student> students;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.student_list_item, parent, false);
        StudentHolder holder = new StudentHolder(itemView, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
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
