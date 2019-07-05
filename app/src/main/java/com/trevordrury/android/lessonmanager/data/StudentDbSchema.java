package com.trevordrury.android.lessonmanager.data;

public class StudentDbSchema {

    public static final class StudentTable {
        public static final String NAME = "students";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String STUDENT_NAME = "student_name";
            public static final String STUDIO_NAME = "studio_name";
            public static final String STUDENT_INSTRUMENT = "student_instrument";
            public static final String LESSON_DAY = "lesson_day";
            public static final String LESSON_TIME = "lesson_time";
            public static final String PHONE = "student_phone_number";
            public static final String EMAIL = "student_email";
            public static final String PARENT_NAME = "parent_name";
        }
    }

}
