package com.example.bonz.uniap_fake.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.dbcontext.DBContext;
import com.example.bonz.uniap_fake.model.LectureModel;
import com.example.bonz.uniap_fake.model.TimetableEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
    public Boolean isLoading;
    int totalItemCount, lastVisibleItem, firstVisibleItem;
    int visibleThreshold = 7;
    TextView monDay, tueDay, wedDay, thuDay, friDay, satDay;
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

    private List<TimetableEntity> sampleData() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(new Date());
        TimetableEntity timetableEntity = new TimetableEntity(currentDate, "ESS", "PRM", "", "", "TEST", "TEST");
        TimetableEntity timetableEntity2 = new TimetableEntity(currentDate, "ESS", "PRM", "TEST", "TEST", "TEST", "TEST");
        TimetableEntity timetableEntity3 = new TimetableEntity(currentDate, "ESS", "PRM", "", "", "TEST", "TEST");
        TimetableEntity timetableEntity4 = new TimetableEntity(currentDate, "ESS", "PRM", "ESS", "PRM", "TEST", "TEST");
        TimetableEntity timetableEntity5 = new TimetableEntity(currentDate, "ESS", "PRM", "ESS", "", "TEST", "TEST");
        TimetableEntity timetableEntity6 = new TimetableEntity(currentDate, "ESS", "PRM", "", "PRM", "TEST", "TEST");
        List<TimetableEntity> sample = new ArrayList<>();
        sample.add(timetableEntity);
        sample.add(timetableEntity2);
        sample.add(timetableEntity3);
        sample.add(timetableEntity4);
        sample.add(timetableEntity5);
        sample.add(timetableEntity6);
        return sample;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);

        // Inflate the layout for this fragment


        DBContext dbContext = new DBContext();
        List<LectureModel> timetableEntityList = dbContext.getAllLectureModel();

        View monView = view.findViewById(R.id.mondayLayout);
        View tueView = view.findViewById(R.id.tuesdayLayout);
        View wedView = view.findViewById(R.id.wednesdayLayout);
        View thuView = view.findViewById(R.id.thursdayLayout);
        View friView = view.findViewById(R.id.fridayLayout);
        View satView = view.findViewById(R.id.saturdayLayout);

        monDay = (TextView) view.findViewById(R.id.mondayDateTextView);
        tueDay = (TextView) view.findViewById(R.id.tuesdayDateTextView);
        wedDay = (TextView) view.findViewById(R.id.wednesdayDateTextView);
        thuDay = (TextView) view.findViewById(R.id.thursdayDateTextView);
        friDay = (TextView) view.findViewById(R.id.fridayDateTextView);
        satDay = (TextView) view.findViewById(R.id.saturdayDateTextView);

        ArrayList<View> collumns = new ArrayList<>();
        collumns.add(monView);
        collumns.add(tueView);
        collumns.add(wedView);
        collumns.add(thuView);
        collumns.add(friView);
        collumns.add(satView);

        int i = 0;
        for (View dayView : collumns) {

            LinearLayout dayLayout = (LinearLayout) dayView;
            // setSlot(dayLayout, timetableEntityList.get(i));
            i++;

        }


//            slot1.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.box5));

//        }


        initTimetable();

        monDay.setText(days[1]);
        tueDay.setText(days[2]);
        wedDay.setText(days[3]);
        thuDay.setText(days[4]);
        friDay.setText(days[5]);
        satDay.setText(days[6]);


        return view;

    }

    private void setSlot(LinearLayout layout, TimetableEntity timetableEntity) {
//        for (int i = 0; i < monLayout.getChildCount(); i++) {
        TextView slot1 = (TextView) layout.getChildAt(0);
        slot1.setText(timetableEntity.getSlot1());
        TextView slot2 = (TextView) layout.getChildAt(1);
        slot2.setText(timetableEntity.getSlot2());
        TextView slot3 = (TextView) layout.getChildAt(2);
        slot3.setText(timetableEntity.getSlot3());
        TextView slot4 = (TextView) layout.getChildAt(3);
        slot4.setText(timetableEntity.getSlot4());
        TextView slot5 = (TextView) layout.getChildAt(4);
        slot5.setText(timetableEntity.getSlot5());
        TextView slot6 = (TextView) layout.getChildAt(5);
        slot6.setText(timetableEntity.getSlot6());
    }

    private void initTimetable() {

        Calendar now = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("dd");

        days = new String[7];
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1; //add 2 if your week start on monday
        now.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
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
