package com.example.sachinmalik.sports.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sachinmalik.sports.R;
import com.example.sachinmalik.sports.utils.Modal;
import com.squareup.picasso.Picasso;

public class ItemDetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    int position;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public ItemDetail() {
        // Required empty public constructor
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

        View view=inflater.inflate(R.layout.fragment_item_detail, container, false);
        position=getArguments().getInt("position",0);
        ((TextView)view.findViewById(R.id.team1name)).setText(Modal.itemlist.get(position).getTeam1());
        ((TextView)view.findViewById(R.id.score1)).setText(Modal.itemlist.get(position).getTeam1Score());
        ((TextView)view.findViewById(R.id.over1)).setText(Modal.itemlist.get(position).getTeam1Over());
        ((TextView)view.findViewById(R.id.team2name)).setText(Modal.itemlist.get(position).getTeam2());
        ((TextView)view.findViewById(R.id.score2)).setText(Modal.itemlist.get(position).getTeam2Score());
        ((TextView)view.findViewById(R.id.over2)).setText(Modal.itemlist.get(position).getTeam2Over());
        Picasso.with(getActivity()).load(Modal.itemlist.get(position).getTeam1Logo()).into(((ImageView) view.findViewById(R.id.team1logo)));
        Picasso.with(getActivity()).load(Modal.itemlist.get(position).getTeam2Logo()).into(((ImageView) view.findViewById(R.id.team2logo)));
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());


        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        return view;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return Summary.newInstance("0", "Page 1");
                case 1:
                    return Scorecard.newInstance("1", "Page 2");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Summary";
                case 1:
                    return "Score Card";
                default:
                    return null;

            }

        }
    }

}
