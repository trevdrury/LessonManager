package com.trevordrury.android.lessonmanager.Views;

import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.trevordrury.android.lessonmanager.R;
import com.trevordrury.android.lessonmanager.model.Student;
import com.trevordrury.android.lessonmanager.model.StudentProvider;

public class StudentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Student student;
    private StudentAdapter adapter;
    private TextView studentNameView;
    private LinearLayout buttonContainer;
    private ImageView buttonClose;
    private Button deleteStudentButton;
    private Button editStudentButton;

    public StudentHolder(View itemView, StudentAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        buttonContainer = itemView.findViewById(R.id.item_view_button_container);
        buttonClose = itemView.findViewById(R.id.button_close);
        deleteStudentButton = itemView.findViewById(R.id.button_delete_student);
        editStudentButton = itemView.findViewById(R.id.button_edit_student);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                buttonClose.setVisibility(View.VISIBLE);
                buttonContainer.setVisibility(View.VISIBLE);
                return false;
            }
        });

        studentNameView = itemView.findViewById(R.id.text_student_name);

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClose.setVisibility(View.GONE);
                buttonContainer.setVisibility(View.GONE);
            }
        });

        deleteStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                alertDialog(context);
            }
        });
    }

    public void bind(Student student) {
        this.student = student;
        studentNameView.setText(student.getStudentName());
    }

    @Override
    public void onClick(View v) {
        //TODO: this should open a student detail screen
    }

    private void alertDialog(final Context c) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(c);
        dialog.setMessage("Are you sure you would like to delete this student?");
        dialog.setTitle("Delete Student");
        dialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StudentProvider studentProvider = StudentProvider.get(c);
                        studentProvider.deleteStudent(student);
                        adapter.setStudents(studentProvider.getStudents());
                        adapter.notifyDataSetChanged();
                        Toast toast = Toast.makeText(c, "Student deleted", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM, 0, -16);
                        toast.show();
                    }
                });
        dialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing when "NO" is clicked
                    }
                });
        AlertDialog confirmDeleteDialog = dialog.create();
        confirmDeleteDialog.show();
    }


}
