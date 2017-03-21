package com.example.bonz.uniap_fake.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.dbcontext.DBContext;
import com.example.bonz.uniap_fake.model.AttendanceModel;
import com.example.bonz.uniap_fake.model.SemesterModel;
import com.example.bonz.uniap_fake.other.Constanst;
import com.example.bonz.uniap_fake.other.ExpandableListAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AttendanceStudentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AttendanceStudentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AttendanceStudentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ExpandableListView expListView;
    private Spinner spnSemester;
    private List<SemesterModel> semesterList;
    private List<AttendanceModel> attendanceList;
    private SemesterModel semesterModel;
    private ExpandableListAdapter expAdapter;
    private List<String> headerList;
    private HashMap<String, List<AttendanceModel>> childData;
    //
    DBContext dbContext;

    private OnFragmentInteractionListener mListener;

    public AttendanceStudentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AttendanceStudentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AttendanceStudentFragment newInstance(String param1, String param2) {
        AttendanceStudentFragment fragment = new AttendanceStudentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        //
        dbContext = DBContext.getInst();
        //semester
        semesterList = dbContext.getAllSemesterModel();
        if(semesterList != null && semesterList.size() > 0) {
            final SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, semesterList);
            spnSemester.setAdapter(spinnerAdapter);
            for(int i=0; i<semesterList.size(); i++) {
                try {
                    Date start = Constanst.KEY_DATE_FORMAT.parse(semesterList.get(i).getStartDate());
                    Date end = Constanst.KEY_DATE_FORMAT.parse(semesterList.get(i).getEndDate());
                    if((new Date()).before(end) && (new Date()).after(start)) {
                        semesterModel = semesterList.get(i);
                        spnSemester.setSelection(i);
                        break;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            spnSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    semesterModel = spinnerAdapter.getItem(i);
                    //update data
                    prepareData();expAdapter = new ExpandableListAdapter(getContext(), headerList, childData);
                    expListView.setAdapter(expAdapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        //attendance
        if(semesterModel != null) {
            prepareData();
            expAdapter = new ExpandableListAdapter(getContext(), headerList, childData);
            expListView.setAdapter(expAdapter);
        }
    }

    private void init() {
        expListView = (ExpandableListView) getView().findViewById(R.id.exp_list_view);
        spnSemester = (Spinner) getView().findViewById(R.id.spn_semester);
    }

    private void prepareData() {
        headerList = new ArrayList<>();
        childData = new HashMap<>();
        attendanceList = dbContext.getAttendanceBySemesterId(semesterModel.getId());
        if(attendanceList != null && attendanceList.size() > 0) {
            for(AttendanceModel a : attendanceList)  {
                String subject = a.getLectureModel().getSubjectOfClassModel().getSubjectModel().getSubjectName();
                List<AttendanceModel> temp = new ArrayList<>();
                if(childData != null && childData.containsKey(subject)) {
                    temp = childData.get(subject);
                    childData.remove(subject);
                } else {
                    headerList.add(subject);
                }
                temp.add(a);
                childData.put(subject, temp);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attendance_student, container, false);
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
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
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

    private class SpinnerAdapter extends ArrayAdapter<SemesterModel> {
        private Context context;
        private List<SemesterModel> list;

        public SpinnerAdapter(Context context, int textViewResourceId, List<SemesterModel> list) {
            super(context, textViewResourceId, list);
            this.context = context;
            this.list = list;
        }

        public int getCount(){
            return list.size();
        }

        public SemesterModel getItem(int position){
            return list.get(position);
        }

        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView label = new TextView(context);
            label.setTextColor(Color.BLACK);
            label.setText(list.get(position).getSemesterName());

            return label;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            TextView label = new TextView(context);
            label.setTextColor(Color.BLACK);
            label.setText(list.get(position).getSemesterName());

            return label;
        }
    }
}
