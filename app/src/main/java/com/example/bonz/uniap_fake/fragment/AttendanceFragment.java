package com.example.bonz.uniap_fake.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.activity.TakeAttendanceActivity;
import com.example.bonz.uniap_fake.dbcontext.DBContext;
import com.example.bonz.uniap_fake.model.LectureModel;
import com.example.bonz.uniap_fake.other.Constanst;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AttendanceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AttendanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AttendanceFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
    private Button btnClass1, btnClass2, btnClass3, btnClass4, btnClass5, btnClass6;
    private TextView tvDateOfLecture;
    private List<LectureModel> lectureModelList;
    private OnFragmentInteractionListener mListener;

    public AttendanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AttendanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AttendanceFragment newInstance(String param1, String param2) {
        AttendanceFragment fragment = new AttendanceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        tvDateOfLecture.setText(getResources().getString(R.string.txt_list_class_of_today) + " "
                + Constanst.KEY_DATE_FORMAT.format(new Date()));
        setDefault();
        loadClassOfDate(Constanst.KEY_DATE_FORMAT.format(new Date()));
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), TakeAttendanceActivity.class);
        intent.putExtra("classId", 1);
        switch (view.getId()) {
            case R.id.btn_class1:
                for(int i=0; i<lectureModelList.size(); i++) {
                    if(lectureModelList.get(i).getSlot() == 1) {
                        intent.putExtra("lectureId", lectureModelList.get(i).getId());
                    }
                }
                startActivity(intent);
                break;
            case R.id.btn_class2:
                for(int i=0; i<lectureModelList.size(); i++) {
                    if(lectureModelList.get(i).getSlot() == 2) {
                        intent.putExtra("lectureId", lectureModelList.get(i).getId());
                    }
                }
                startActivity(intent);
                break;
            case R.id.btn_class3:
                for(int i=0; i<lectureModelList.size(); i++) {
                    if(lectureModelList.get(i).getSlot() == 3) {
                        intent.putExtra("lectureId", lectureModelList.get(i).getId());
                    }
                }
                startActivity(intent);
                break;
            case R.id.btn_class4:
                for(int i=0; i<lectureModelList.size(); i++) {
                    if(lectureModelList.get(i).getSlot() == 4) {
                        intent.putExtra("lectureId", lectureModelList.get(i).getId());
                    }
                }
                startActivity(intent);
                break;
            case R.id.btn_class5:
                for(int i=0; i<lectureModelList.size(); i++) {
                    if(lectureModelList.get(i).getSlot() == 5) {
                        intent.putExtra("lectureId", lectureModelList.get(i).getId());
                    }
                }
                startActivity(intent);
                break;
            case R.id.btn_class6:
                for(int i=0; i<lectureModelList.size(); i++) {
                    if(lectureModelList.get(i).getSlot() == 6) {
                        intent.putExtra("lectureId", lectureModelList.get(i).getId());
                    }
                }
                startActivity(intent);
                break;
            case R.id.txt_date_of_lecture:
                showDatePickerDialog();
                break;
            default:
                break;
        }

    }

    private void loadClassOfDate(String date) {
        DBContext dbContext = DBContext.getInst();
        lectureModelList = dbContext.getLectureModelByDate(date);
        if(lectureModelList != null && lectureModelList.size() > 0) {
            for(int i=0; i<lectureModelList.size(); i++) {
                switch (lectureModelList.get(i).getSlot()) {
                    case 1:
                        btnClass1.setText(lectureModelList.get(i).getClassModel().getClassName());
                        btnClass1.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        btnClass2.setText(lectureModelList.get(i).getClassModel().getClassName());
                        btnClass2.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        btnClass3.setText(lectureModelList.get(i).getClassModel().getClassName());
                        btnClass3.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        btnClass4.setText(lectureModelList.get(i).getClassModel().getClassName());
                        btnClass4.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        btnClass5.setText(lectureModelList.get(i).getClassModel().getClassName());
                        btnClass5.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        btnClass6.setText(lectureModelList.get(i).getClassModel().getClassName());
                        btnClass6.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        } else {
            setDefault();
        }
    }

    private void tvDateOfLectureOnClick() {
        showDatePickerDialog();
        setDefault();
        loadClassOfDate(tvDateOfLecture.getText().toString().trim());
    }

    Calendar myCalendar = Calendar.getInstance();
    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            if(c.get(Calendar.YEAR) == year && c.get(Calendar.MONTH) == monthOfYear && c.get(Calendar.DATE) == dayOfMonth) {
                c.set(year, monthOfYear, dayOfMonth);
                tvDateOfLecture.setText(getResources().getString(R.string.txt_list_class_of_today) + " "
                        + Constanst.KEY_DATE_FORMAT.format(c.getTime()));
            } else {
                c.set(year, monthOfYear, dayOfMonth);
                tvDateOfLecture.setText(getResources().getString(R.string.txt_list_class_of_date) + " "
                        + Constanst.KEY_DATE_FORMAT.format(c.getTime()));
            }

            setDefault();
            loadClassOfDate(Constanst.KEY_DATE_FORMAT.format(c.getTime()));
        }
    };

    private void init() {
        tvDateOfLecture = (TextView) getView().findViewById(R.id.txt_date_of_lecture);
        tvDateOfLecture.setOnClickListener(this);
        btnClass1 = (Button) getView().findViewById(R.id.btn_class1);
        btnClass1.setOnClickListener(this);
        btnClass2 = (Button) getView().findViewById(R.id.btn_class2);
        btnClass2.setOnClickListener(this);
        btnClass3 = (Button) getView().findViewById(R.id.btn_class3);
        btnClass3.setOnClickListener(this);
        btnClass4 = (Button) getView().findViewById(R.id.btn_class4);
        btnClass4.setOnClickListener(this);
        btnClass5 = (Button) getView().findViewById(R.id.btn_class5);
        btnClass5.setOnClickListener(this);
        btnClass6 = (Button) getView().findViewById(R.id.btn_class6);
        btnClass6.setOnClickListener(this);
    }

    private void setDefault() {
        btnClass1.setVisibility(View.INVISIBLE);
        btnClass2.setVisibility(View.INVISIBLE);
        btnClass3.setVisibility(View.INVISIBLE);
        btnClass4.setVisibility(View.INVISIBLE);
        btnClass5.setVisibility(View.INVISIBLE);
        btnClass6.setVisibility(View.INVISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attendance, container, false);
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
