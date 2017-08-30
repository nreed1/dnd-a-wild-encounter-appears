package com.example.nreed.awildencounterappears.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nreed.awildencounterappears.Classes.Calculators.XPCalculator;
import com.example.nreed.awildencounterappears.Classes.DataAdapters.MonsterDataAdapter;
import com.example.nreed.awildencounterappears.Classes.MonsterMultiplier;
import com.example.nreed.awildencounterappears.Classes.Objects.DifficultyEnum;
import com.example.nreed.awildencounterappears.Classes.Objects.Lists.MonsterList;
import com.example.nreed.awildencounterappears.R;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RandomEncounterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RandomEncounterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RandomEncounterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    com.shawnlin.numberpicker.NumberPicker partyLevel = null;
    com.shawnlin.numberpicker.NumberPicker numberInParty = null;
    com.shawnlin.numberpicker.NumberPicker minCR = null;
    FloatingActionButton floatingActionButton = null;

    LinearLayout additionalOptions =null;
    ImageButton showAdditionalOptionsButton =null;

    TextView partyXP = null;
    TextView monstersToKill = null;
    LinearLayout monsterLayout= null;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RandomEncounterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RandomEncounterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RandomEncounterFragment newInstance(String param1, String param2) {
        RandomEncounterFragment fragment = new RandomEncounterFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_random_encounter, container, false);
        additionalOptions =(LinearLayout) view.findViewById(R.id.additionalOptions);
        showAdditionalOptionsButton=(ImageButton) view.findViewById(R.id.showAdditionalOptionsButton);
        showAdditionalOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(additionalOptions.getVisibility() == View.GONE) {
                    additionalOptions.setVisibility(View.VISIBLE);
                    showAdditionalOptionsButton.setRotation(180);
                }else {
                    additionalOptions.setVisibility(View.GONE);
                    showAdditionalOptionsButton.setRotation(0);
                }
            }
        });
        partyLevel = (com.shawnlin.numberpicker.NumberPicker)view.findViewById(R.id.levelOfPartyNumberPicker);
        numberInParty = (com.shawnlin.numberpicker.NumberPicker) view.findViewById(R.id.numberOfMembers);
        partyXP = (TextView) view.findViewById(R.id.partyXP);
        monsterLayout =(LinearLayout) view.findViewById(R.id.monsterLayout);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        if(fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CreateEncounter();
                    //Snackbar.make(view, "Creating Encounter", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
            });
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void CreateEncounter(){
        XPCalculator calculator = new XPCalculator(DifficultyEnum.Easy);
        int partyLevelInt =partyLevel.getValue();
        int partyMembersInt = numberInParty.getValue();

        Random randomSelect = new Random();
        int randomMonsterHorde = randomSelect.nextInt(partyLevelInt+1);

        double initialXPPool = calculator.CalculatePartyXP(partyMembersInt, partyLevelInt, MonsterMultiplier.GetMonsterMultiplier(randomMonsterHorde,partyMembersInt));



        MonsterDataAdapter monsterDataAdapter = new MonsterDataAdapter(this.getContext());
        MonsterList monsters = monsterDataAdapter.GetMonsterList("cave", partyLevelInt, initialXPPool, randomMonsterHorde, partyMembersInt, partyLevelInt,DifficultyEnum.Easy);

        if(monsters == null) return;

        if(!monsters.isEmpty()){
            ShowMonsterList(monsters);
        }else{

            monsterLayout.removeAllViews();
        }
    }

    private void ShowMonsterList(MonsterList monsters) {

        MonsterFragment monsterFragment = new MonsterFragment();
        Bundle b = new Bundle();
        b.putParcelable("monsters", monsters);
        monsterFragment.setArguments(b);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.monsterLayout,monsterFragment,"fragment1");
        fragmentTransaction.commit();
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
}
