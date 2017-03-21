package com.example.bonz.uniap_fake.dbcontext;

import com.example.bonz.uniap_fake.model.AttendanceModel;
import com.example.bonz.uniap_fake.model.TimetableEntity;

/**
 * Created by bonz on 3/14/17.
 */

public class TimetaleDBContext extends DBContext {

    public TimetableEntity getTimetableBy(String date, int num, String studentId) {
        realm.where(AttendanceModel.class).equalTo("studentModel.id", id);

        return null;
    }

}
