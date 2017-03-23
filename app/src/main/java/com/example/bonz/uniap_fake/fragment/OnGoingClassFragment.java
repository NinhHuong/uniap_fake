package com.example.bonz.uniap_fake.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.bonz.uniap_fake.R;
import com.example.bonz.uniap_fake.dbcontext.DBContext;
import com.example.bonz.uniap_fake.model.SemesterModel;
import com.example.bonz.uniap_fake.model.SubjectOfClassModel;
import com.example.bonz.uniap_fake.other.Constanst;
import com.example.bonz.uniap_fake.other.ExpandableListAdapter;
import com.example.bonz.uniap_fake.other.ListViewClassAdapter;
import com.example.bonz.uniap_fake.other.SemesterSpinnerAdapter;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnGoingClassFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OnGoingClassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnGoingClassFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner spnSemester;
    private ListView lvClass;
    private DBContext dbContext;
    private List<SemesterModel> semesterList;
    private SemesterModel semesterModel;
    private List<SubjectOfClassModel> subjectOfClassModelList;

    private OnFragmentInteractionListener mListener;

    public OnGoingClassFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OnGoingClassFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OnGoingClassFragment newInstance(String param1, String param2) {
        OnGoingClassFragment fragment = new OnGoingClassFragment();
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        spnSemester = (Spinner) getView().findViewById(R.id.spn_semester);
        lvClass = (ListView) getView().findViewById(R.id.lv_class);

        //
        dbContext = DBContext.getInst();
        //semester
        semesterList = dbContext.getAllSemesterModel();
        if(semesterList != null && semesterList.size() > 0) {
            final SemesterSpinnerAdapter spinnerAdapter = new SemesterSpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, semesterList);
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
                    //update data//
                    subjectOfClassModelList = dbContext.getSubjectOfClassModelsBySemesterId(semesterModel.getId());
                    ListViewClassAdapter listViewClassAdapter = new ListViewClassAdapter(getContext(), subjectOfClassModelList);
                    lvClass.setAdapter(listViewClassAdapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            //
            subjectOfClassModelList = dbContext.getSubjectOfClassModelsBySemesterId(semesterModel.getId());
            ListViewClassAdapter listViewClassAdapter = new ListViewClassAdapter(getContext(), subjectOfClassModelList);
            lvClass.setAdapter(listViewClassAdapter);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_going_class, container, false);
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
}
