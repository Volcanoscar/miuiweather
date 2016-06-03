package com.example.miuiweather;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewpageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewpageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewpageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //this  are all views in fragment
    @BindView(R.id.av_frag1)
    PageView pageView1;
    @BindView(R.id.av_frag2)
    PageView pageView2;
    @BindView(R.id.av_frag3)
    PageView pageView3;
    @BindView(R.id.av_frag4)
    PageView pageView4;
    @BindView(R.id.fg_tv_cond)
    TextView tvcond;
    @BindView(R.id.fg_tv_tem)
    TextView tvtemp;
    @BindView(R.id.fg_tv_wind)
    TextView tvwind;
    private Unbinder unbinder;

    private OnFragmentInteractionListener mListener;

    public ViewpageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ViewpageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewpageFragment newInstance(String param1) {
        ViewpageFragment fragment = new ViewpageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
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
        View view=inflater.inflate(R.layout.fragment_viewpage, container, false);
        unbinder= ButterKnife.bind(this,view);
        setPageview(pageView1,14,20,16);
        setPageview(pageView2,16,23,12);
        setPageview(pageView3,12,26,18);
        setPageview(pageView4,18,23,15);
        return view;
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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

    /**
     * 用于设置pageview中的折线属性
     * @param page
     * @param yes
     * @param today
     * @param mon
     */
    private void setPageview(PageView page,int yes,int today,int mon) {
        page.setTotaltemp(today);
        page.setYesTotaltemp(yes);
        page.setMonTotaltemp(mon);
    }
}