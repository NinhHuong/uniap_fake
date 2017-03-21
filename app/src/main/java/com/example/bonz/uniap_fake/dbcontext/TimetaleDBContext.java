package com.example.bonz.uniap_fake.dbcontext;

import com.example.bonz.uniap_fake.model.LectureModel;
import com.example.bonz.uniap_fake.model.TimetableEntity;

import io.realm.RealmResults;

/**
 * Created by bonz on 3/14/17.
 */

public class TimetaleDBContext extends DBContext {

    public TimetableEntity getTimetableBy(String date, int num, String studentId) {
        RealmResults<LectureModel> realmResults = realm.where(LectureModel.class)
                .findAll();

        return null;
    }

}
