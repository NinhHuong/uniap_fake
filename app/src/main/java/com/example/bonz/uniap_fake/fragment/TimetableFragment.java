package com.example.bonz.uniap_fake.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.activity.TimetableDetailActivity;
import com.example.bonz.uniap_fake.dbcontext.DBContext;
import com.example.bonz.uniap_fake.model.LectureModel;
import com.example.bonz.uniap_fake.model.SubjectModel;
import com.example.bonz.uniap_fake.model.SubjectOfClassModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimetableFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TimetableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimetableFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Calendar calendar;
    SimpleDateFormat genericFormat;
    List<LectureModel> lectureModelList;
    String[] currentWeek;
    private TextView monDay, tueDay, wedDay, thuDay, friDay, satDay, monthTextView, yearTextView;
    private LinearLayout monView, tueView, wedView, thuView, friView, satView;
    private DBContext dbContext;
    private Button nextButton, backButton, currentButton;
    private String[] days;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private LinearLayoutManager mLayoutManager;

    public TimetableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimetableFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimetableFragment newInstance(String param1, String param2) {
        TimetableFragment fragment = new TimetableFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);

        // Inflate the layout for this fragment
        lectureModelList = sampleData();
        genericFormat = new SimpleDateFormat("dd/MM/yyyy");
        //Get layout that contain Slot TextViews
        monView = (LinearLayout) view.findViewById(R.id.mondayLayout);
        tueView = (LinearLayout) view.findViewById(R.id.tuesdayLayout);
        wedView = (LinearLayout) view.findViewById(R.id.wednesdayLayout);
        thuView = (LinearLayout) view.findViewById(R.id.thursdayLayout);
        friView = (LinearLayout) view.findViewById(R.id.fridayLayout);
        satView = (LinearLayout) view.findViewById(R.id.saturdayLayout);

//        for(int i=0; i<monView.getChildCount(); i++) {
//            TextView slot = (TextView) monView.getChildAt(i);
//            slot.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    slot.getText().
//                }
//            });
//        }

        //get each day TextView
        monDay = (TextView) view.findViewById(R.id.mondayDateTextView);
        tueDay = (TextView) view.findViewById(R.id.tuesdayDateTextView);
        wedDay = (TextView) view.findViewById(R.id.wednesdayDateTextView);
        thuDay = (TextView) view.findViewById(R.id.thursdayDateTextView);
        friDay = (TextView) view.findViewById(R.id.fridayDateTextView);
        satDay = (TextView) view.findViewById(R.id.saturdayDateTextView);

        //get month and year textview
        monthTextView = (TextView) view.findViewById(R.id.monthTextView);
        yearTextView = (TextView) view.findViewById(R.id.yearTextView);

        //get page week button
        nextButton = (Button) view.findViewById(R.id.nextWeekButton);
        backButton = (Button) view.findViewById(R.id.lastWeekButton);
        currentButton = (Button) view.findViewById(R.id.currentWeekButton);

        calendar = Calendar.getInstance();


        backButton.setEnabled(false);
        currentButton.setEnabled(false);

        //Set next week view for Button next week
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllTableData(monView, tueView, wedView, thuView, friView, satView);
                //Add 7 days to jump to next week
                calendar.add(Calendar.DAY_OF_MONTH, 7);
                lectureModelList = sampleData();
                initTimetable(calendar.getTime());
                buttonStatus();
            }
        });

        //Set current week view for Button next week
        currentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllTableData(monView, tueView, wedView, thuView, friView, satView);
                //Add 7 days to jump to next week
                calendar = Calendar.getInstance();
//                calendar.clear();
                lectureModelList = sampleData();
                initTimetable(calendar.getTime());
                buttonStatus();
            }
        });

        //Set back week view for Button next week
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllTableData(monView, tueView, wedView, thuView, friView, satView);
                //Add 7 days to jump to next week
                calendar.add(Calendar.DAY_OF_MONTH, -7);
                lectureModelList = sampleData();
                initTimetable(calendar.getTime());
                buttonStatus();
            }
        });


        initTimetable(calendar.getTime());

        return view;

    }

    private void buttonStatus() {
        for (int i = 0; i < currentWeek.length; i++) {
            Log.d("date", new Date().toString());
            if (currentWeek[i].equals(genericFormat.format(new Date()))) {
                backButton.setEnabled(false);
                currentButton.setEnabled(false);
                break;
            } else {
                backButton.setEnabled(true);
                currentButton.setEnabled(true);
            }
        }
    }

    //Get all Lecture data from date to date
    private List<LectureModel> sampleData() {
        Calendar cal = Calendar.getInstance();
        if (calendar != null)
            cal = calendar;
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        Date firstDay = cal.getTime();
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date lastDay = cal.getTime();
        dbContext = DBContext.getInst();
        return dbContext.getAllLectureByDate(firstDay, lastDay);
    }


    //Set data for each slot TextView
    private void setSlot(LinearLayout layout, final LectureModel lectureModel) {
        TextView slot1 = (TextView) layout.getChildAt(0);
        TextView slot2 = (TextView) layout.getChildAt(1);
        TextView slot3 = (TextView) layout.getChildAt(2);
        TextView slot4 = (TextView) layout.getChildAt(3);
        TextView slot5 = (TextView) layout.getChildAt(4);
        TextView slot6 = (TextView) layout.getChildAt(5);

        SubjectOfClassModel subjectOfClassModel = lectureModel.getSubjectOfClassModel();
        final SubjectModel subjectModel = subjectOfClassModel.getSubjectModel();
        switch (lectureModel.getSlot()) {
            case 1:
                slot1.setText(subjectModel.getSubjectCode());
                slot1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = lectureModel.getId();
                        startSubjectDetailActi(id);
                    }
                });
                break;
            case 2:
                slot2.setText(subjectModel.getSubjectCode());
                slot2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = lectureModel.getId();
                        startSubjectDetailActi(id);
                    }
                });
                break;
            case 3:
                slot3.setText(subjectModel.getSubjectCode());
                slot3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = lectureModel.getId();
                        startSubjectDetailActi(id);
                    }
                });
                break;
            case 4:
                slot4.setText(subjectModel.getSubjectCode());
                slot4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = lectureModel.getId();
                        startSubjectDetailActi(id);
                    }
                });
                break;
            case 5:
                slot5.setText(subjectModel.getSubjectCode());
                slot5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = lectureModel.getId();
                        startSubjectDetailActi(id);
                    }
                });
                break;
            case 6:
                slot6.setText(subjectModel.getSubjectCode());
                slot6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = lectureModel.getId();
                        startSubjectDetailActi(id);
                    }
                });
                break;
        }
    }

    private void startSubjectDetailActi(int id) {
        try {
            Intent slotDetail = new Intent(getContext(), TimetableDetailActivity.class);
            slotDetail.putExtra("lectureId", id);
            startActivity(slotDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Set time for Timetable
    private void initTimetable(Date date) {

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        SimpleDateFormat format = new SimpleDateFormat("dd");
        currentWeek = new String[7];
        days = new String[7];
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1; //add 2 if your week start on monday
        now.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            days[i] = format.format(now.getTime());
            currentWeek[i] = genericFormat.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }
        monDay.setText(days[1]);
        tueDay.setText(days[2]);
        wedDay.setText(days[3]);
        thuDay.setText(days[4]);
        friDay.setText(days[5]);
        satDay.setText(days[6]);
        monthTextView.setText(String.valueOf(new SimpleDateFormat("MMM", Locale.getDefault()).format(now.getTime())));
        yearTextView.setText(String.valueOf(new SimpleDateFormat("yyyy", Locale.getDefault()).format(now.getTime())));
        for (LectureModel lectureModel : lectureModelList) {
            String dateCompare = genericFormat.format(lectureModel.getDate());
            if (dateCompare.equals(currentWeek[1]))
                setSlot(monView, lectureModel);
            else if (dateCompare.equals(currentWeek[2]))
                setSlot(tueView, lectureModel);
            else if (dateCompare.equals(currentWeek[3]))
                setSlot(wedView, lectureModel);
            else if (dateCompare.equals(currentWeek[4]))
                setSlot(thuView, lectureModel);
            else if (dateCompare.equals(currentWeek[5]))
                setSlot(friView, lectureModel);
            else if (dateCompare.equals(currentWeek[6]))
                setSlot(satView, lectureModel);
        }
    }

    private void clearAllTableData(LinearLayout... layouts) {
        for (LinearLayout linearLayout : layouts) {
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                View view = linearLayout.getChildAt(i);
                if (view instanceof TextView) {
                    TextView child = ((TextView) view);
                    child.setText("");
                    child.setOnClickListener(null);
                }
            }
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
