package com.example.marsapp.ui.fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.marsapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * SecretFragment.OnFragmentInteractionListener interface
 * to handle interaction events.
 * Use the SecretFragment#newInstance factory method to
 * create an instance of this fragment.
 */
public class SecretFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


//    private OnFragmentInteractionListener mListener;

    public SecretFragment() {
        // Required empty public constructor
    }


    Button play, pause;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_secret, container, false);

        View rootView = inflater.inflate(R.layout.fragment_secret, container, false);

        play = (Button) rootView.findViewById(R.id.playMusicBtn);
        pause = (Button) rootView.findViewById(R.id.pauseMusicBtn);

        final MediaPlayer music = MediaPlayer.create(getActivity(), R.raw.ost_stellaris_faster_than_light);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.setLooping(true);
                music.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (music.isPlaying()) {
                    music.pause();
                }
            }
        });
        return rootView;


    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
