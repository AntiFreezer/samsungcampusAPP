package com.example.aninterface;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PaletteFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_palette, container, false);
        Button getBackButton = view.findViewById(R.id.getOutButton);
        getBackButton.setOnClickListener(view1 -> getActivity().getSupportFragmentManager().popBackStack());
        return view;
    }
}