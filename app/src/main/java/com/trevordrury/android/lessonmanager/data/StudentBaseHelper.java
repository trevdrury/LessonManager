package com.trevordrury.android.lessonmanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "studentBase.db";

    public StudentBaseHelper(Context context) { super(context, DATABASE_NAME, null, VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + StudentDbSchema.StudentTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                StudentDbSchema.StudentTable.Cols.UUID + ", " +
                StudentDbSchema.StudentTable.Cols.STUDENT_NAME + ", " +
                StudentDbSchema.StudentTable.Cols.STUDIO_NAME + ", " +
                StudentDbSchema.StudentTable.Cols.STUDENT_INSTRUMENT + ", " +
                StudentDbSchema.StudentTable.Cols.LESSON_DAY + ", " +
                StudentDbSchema.StudentTable.Cols.LESSON_TIME + ", " +
                StudentDbSchema.StudentTable.Cols.PARENT_NAME + ", " +
                StudentDbSchema.StudentTable.Cols.PHONE + ", " +
                StudentDbSchema.StudentTable.Cols.EMAIL +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
