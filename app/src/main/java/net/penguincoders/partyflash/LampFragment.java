package net.penguincoders.partyflash;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class LampFragment extends Fragment {

    Button yellow;
    Button white;
    Button blue;
    Button green;

    public LampFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lamp, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        white = getView().findViewById(R.id.whiteLamp);
        yellow = getView().findViewById(R.id.yellowLamp);
        green = getView().findViewById(R.id.greenLamp);
        blue = getView().findViewById(R.id.blueLamp);

        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LampLightActivity.class);
                intent.putExtra("bgColor","#ffffff");
                startActivity(intent);
            }
        });

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LampLightActivity.class);
                intent.putExtra("bgColor","#ffff00");
                startActivity(intent);
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LampLightActivity.class);
                intent.putExtra("bgColor","#59e817");
                startActivity(intent);
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LampLightActivity.class);
                intent.putExtra("bgColor","#3bb9ff");
                startActivity(intent);
            }
        });
    }

}
