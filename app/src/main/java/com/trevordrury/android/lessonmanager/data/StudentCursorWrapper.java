package com.trevordrury.android.lessonmanager.data;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.trevordrury.android.lessonmanager.data.StudentDbSchema.StudentTable;
import com.trevordrury.android.lessonmanager.model.Student;

import java.util.UUID;

public class StudentCursorWrapper extends CursorWrapper {

    public StudentCursorWrapper(Cursor cursor) { super(cursor); }

    public Student getStudent() {
        String uuidString = getString(getColumnIndex(StudentTable.Cols.UUID));
        String studentName = getString(getColumnIndex(StudentTable.Cols.STUDENT_NAME));
        String studioName = getString(getColumnIndex(StudentTable.Cols.STUDIO_NAME));
        String studentInstrument = getString(getColumnIndex(StudentTable.Cols.STUDENT_INSTRUMENT));
        String lessonDay = getString(getColumnIndex(StudentTable.Cols.LESSON_DAY));
        String lessonTime = getString(getColumnIndex(StudentTable.Cols.LESSON_TIME));
        String parentName = getString(getColumnIndex(StudentTable.Cols.PARENT_NAME));
        String phone = getString(getColumnIndex(StudentTable.Cols.PHONE));
        String email = getString(getColumnIndex(StudentTable.Cols.EMAIL));

        Student student = new Student(UUID.fromString(uuidString));
        student.setStudentName(studentName);
        student.setStudioName(studioName);
        student.setStudentInstrument(studentInstrument);
        student.setLessonDay(lessonDay);
        student.setLessonTime(lessonTime);
        student.setParentName(parentName);
        student.setPhone(phone);
        student.setEmail(email);

        return student;
    }

}
