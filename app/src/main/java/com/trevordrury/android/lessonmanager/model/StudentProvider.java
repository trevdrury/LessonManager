package com.trevordrury.android.lessonmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.trevordrury.android.lessonmanager.data.StudentBaseHelper;
import com.trevordrury.android.lessonmanager.data.StudentCursorWrapper;
import com.trevordrury.android.lessonmanager.data.StudentDbSchema;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class StudentProvider {

    private static StudentProvider studentProvider;

    private Context context;
    private SQLiteDatabase database;

    public  static StudentProvider get(Context context) {
        if (studentProvider == null) {
            studentProvider = new StudentProvider(context);
        }
        return studentProvider;
    }

    private StudentProvider(Context context) {
        this.context = context.getApplicationContext();
        database = new StudentBaseHelper(this.context)
                .getWritableDatabase();
    }

    public void addStudent(Student student) {
        ContentValues values = getContentValues(student);

        database.insert(StudentDbSchema.StudentTable.NAME, null, values);
    }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        StudentCursorWrapper cursor = queryStudents( null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                students.add(cursor.getStudent());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return students;
    }

    public Student getStudent(UUID id) {
        StudentCursorWrapper cursor = queryStudents(
                StudentDbSchema.StudentTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getStudent();
        } finally {
            cursor.close();
        }
    }

    public void updateStudent(Student student) {
        String uuidString = student.getID().toString();
        ContentValues values = getContentValues(student);

        database.update(StudentDbSchema.StudentTable.NAME, values,
                StudentDbSchema.StudentTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    public void deleteStudent(Student student) {
        String uuidString = student.getID().toString();

        database.delete(StudentDbSchema.StudentTable.NAME,
                StudentDbSchema.StudentTable.Cols.UUID + " = ?",
                new String[] {uuidString});

    }

    public List<Student> getCurrentLessons(List<Student> students) {
        List<Student> todayStudents = new ArrayList<>();
        final Calendar c = Calendar.getInstance();
        String day = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        for (Student student : students) {
            if (student.getLessonDay().equals(day)) {
                todayStudents.add(student);
            }
        }
        return todayStudents;
    }

    private StudentCursorWrapper queryStudents(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                StudentDbSchema.StudentTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new StudentCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Student student) {
        ContentValues values = new ContentValues();
        values.put(StudentDbSchema.StudentTable.Cols.UUID, student.getID().toString());
        values.put(StudentDbSchema.StudentTable.Cols.STUDENT_NAME, student.getStudentName());
        values.put(StudentDbSchema.StudentTable.Cols.STUDIO_NAME, student.getStudioName());
        values.put(StudentDbSchema.StudentTable.Cols.STUDENT_INSTRUMENT, student.getStudentInstrument());
        values.put(StudentDbSchema.StudentTable.Cols.LESSON_DAY, student.getLessonDay());
        values.put(StudentDbSchema.StudentTable.Cols.LESSON_TIME, student.getLessonTime());
        values.put(StudentDbSchema.StudentTable.Cols.PARENT_NAME, student.getParentName());
        values.put(StudentDbSchema.StudentTable.Cols.PHONE, student.getPhone());
        values.put(StudentDbSchema.StudentTable.Cols.EMAIL, student.getEmail());

        return values;
    }

}
