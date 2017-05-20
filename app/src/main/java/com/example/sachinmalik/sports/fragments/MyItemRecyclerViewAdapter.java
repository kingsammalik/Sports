package com.example.sachinmalik.sports.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sachinmalik.sports.R;
import com.example.sachinmalik.sports.fragments.ItemFragment.OnListFragmentInteractionListener;
import com.example.sachinmalik.sports.utils.Modal1;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Modal1> mValues;
    private final OnListFragmentInteractionListener mListener;
    private int lastPosition = -1;
    Context context;

    public MyItemRecyclerViewAdapter(List<Modal1> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        context=parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.team1name.setText(mValues.get(position).getTeam1());
        holder.score1.setText(mValues.get(position).getTeam1Score());
        holder.over1.setText(mValues.get(position).getTeam1Over());
        holder.team2name.setText(mValues.get(position).getTeam2());
        holder.score2.setText(mValues.get(position).getTeam2Score());
        holder.over2.setText(mValues.get(position).getTeam2Over());
        Picasso.with(context).load(mValues.get(position).getTeam1Logo()).into(holder.team1logo);
        Picasso.with(context).load(mValues.get(position).getTeam2Logo()).into(holder.team2logo);
        if (mValues.get(position).getIslive()){
            holder.live.setVisibility(View.VISIBLE);
        }
        else {
            holder.live.setVisibility(View.GONE);
        }
        setAnimation(holder.itemView, position);
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(501));
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView team1name;
        public final TextView score1;
        public final TextView over1;
        public final ImageView team1logo;

        public final TextView team2name;
        public final TextView score2;
        public final TextView over2;
        public final ImageView team2logo;
        public Modal1 mItem;
        public final TextView live;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            team1name = (TextView) view.findViewById(R.id.team1name);
            score1 = (TextView) view.findViewById(R.id.score1);
            over1 = (TextView) view.findViewById(R.id.over1);
            team2name = (TextView) view.findViewById(R.id.team2name);
            score2 = (TextView) view.findViewById(R.id.score2);
            over2 = (TextView) view.findViewById(R.id.over2);
            live = (TextView) view.findViewById(R.id.live);
            team1logo = (ImageView) view.findViewById(R.id.team1logo);
            team2logo = (ImageView) view.findViewById(R.id.team2logo);
        }

    }
}
