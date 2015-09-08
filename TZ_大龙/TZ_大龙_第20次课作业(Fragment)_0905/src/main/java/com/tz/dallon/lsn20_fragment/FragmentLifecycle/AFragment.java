package com.tz.dallon.lsn20_fragment.FragmentLifecycle;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tz.dallon.lsn20_fragment.R;

public class AFragment extends Fragment {
    public static final String TAG = "A";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, TAG + ">>>> onSaveInstanceState");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, TAG + ">>>> onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, TAG + ">>>> onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, TAG + ">>>> onCreateView");

        View view = inflater.inflate(R.layout.fragment_lifecycle_a,null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, TAG + ">>>> onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i(TAG, TAG + ">>>> onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, TAG + ">>>> onResume");
        super.onResume();
    }

    /*************************fragment actived********************************/
    @Override
    public void onPause() {
        Log.i(TAG, TAG + ">>>> onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, TAG + ">>>> onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, TAG + ">>>> onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, TAG + ">>>> onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, TAG + ">>>> onDetach");
        super.onDetach();
    }
}
