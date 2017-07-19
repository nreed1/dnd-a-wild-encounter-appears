package com.example.nreed.awildencounterappears.Fragments.ViewAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nreed.awildencounterappears.Classes.Objects.Lists.MonsterList;
import com.example.nreed.awildencounterappears.Classes.Objects.Monster;
import com.example.nreed.awildencounterappears.Fragments.MonsterFragment.OnListFragmentInteractionListener;
import com.example.nreed.awildencounterappears.R;


import java.util.ArrayList;
/**
 * {@link RecyclerView.Adapter} that can display a {@link Monster} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMonsterRecyclerViewAdapter extends RecyclerView.Adapter<MyMonsterRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Monster> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyMonsterRecyclerViewAdapter(MonsterList items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_monster, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText("x"+String.valueOf(mValues.get(position).getCount()));
        holder.mChallengeRating.setText("CR: " + String.valueOf(mValues.get(position).getChallengeRating()));
        holder.mXP.setText("XP: " + String.valueOf(mValues.get(position).getXP()));
        holder.mHP.setText(String.valueOf(mValues.get(position).getHP()));
        holder.mAC.setText(String.valueOf(mValues.get(position).getArmorClass()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mChallengeRating;
        public final TextView mXP;
        public Monster mItem;
        public final TextView mHP;
        public final TextView mAC;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.monsterName);
            mContentView = (TextView) view.findViewById(R.id.monsterCount);
            mChallengeRating = (TextView) view.findViewById(R.id.monsterChallengeRating);
            mXP = (TextView) view.findViewById(R.id.monsterXP);
            mHP = (TextView) view.findViewById(R.id.heartHP);
            mAC = (TextView) view.findViewById(R.id.shieldArmorClass);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
