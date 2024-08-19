package com.ayaashraf.cairo_metro_app;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class allRoutesDialog extends DialogFragment {
    TextView allStations , totalTime , totalPrice, direction, stationNums;
    ImageView leftArrow , rightArrow, closeIcon;
    ArrayList<ArrayList<String>> allRoutes;
    static int index = 0;

    public allRoutesDialog(ArrayList<ArrayList<String>> allRoutes) {
        this.allRoutes = allRoutes;
        index = 0;
    }

    @Nullable
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(requireContext());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.allroutes_dialog, null);

        dialog.setContentView(dialogView);

        totalTime = dialogView.findViewById(R.id.totalTime);
        totalPrice = dialogView.findViewById(R.id.totalPrice);
        stationNums = dialogView.findViewById(R.id.stationNums);
        direction = dialogView.findViewById(R.id.direction);
        allStations = dialogView.findViewById(R.id.allStations);
        closeIcon = dialogView.findViewById(R.id.closeIcon);
        rightArrow = dialogView.findViewById(R.id.rightArrow);
        leftArrow = dialogView.findViewById(R.id.leftArrow);

        checkIndex();
        printResult(0);

        closeIcon.setOnClickListener(v -> dismiss());
        if(rightArrow.isClickable())
        {
            rightArrow.setOnClickListener(v ->rightArrowClicked());

        }
        if(leftArrow.isClickable()) {
            leftArrow.setOnClickListener(v -> leftArrowClicked());
        }


        return dialog;
    }

    @SuppressLint("SetTextI18n")
    private void leftArrowClicked() {
        if(leftArrow.isClickable()){
            index--;
            printResult(index);
        }
        checkIndex();
    }


    @SuppressLint("SetTextI18n")
    private void rightArrowClicked() {
        if(rightArrow.isClickable())
        {
            index++;
            printResult(index);
        }
        checkIndex();
    }

    public void beginOfRoutes(){
        leftArrow.setClickable(false);
        leftArrow.setColorFilter(Color.parseColor("#808080"));
        rightArrow.setClickable(true);
        rightArrow.setColorFilter(Color.parseColor("#000000"));
        rightArrow.setOnClickListener(v ->rightArrowClicked());
    }

    public void endOfRoutes()
    {
        rightArrow.setClickable(false);
        rightArrow.setColorFilter(Color.parseColor("#808080"));
        leftArrow.setClickable(true);
        leftArrow.setColorFilter(Color.parseColor("#000000"));
        leftArrow.setOnClickListener(v -> leftArrowClicked());

    }

    public void checkIndex()
    {
        if(allRoutes.size() == 1)
        {
            rightArrow.setClickable(false);
            rightArrow.setColorFilter(Color.parseColor("#808080"));
            leftArrow.setClickable(false);
            leftArrow.setColorFilter(Color.parseColor("#808080"));
            printResult(0);
        }
        else if(index == 0)
        {
            beginOfRoutes();
        }
        else if(index == allRoutes.size()-1) {
            endOfRoutes();
        }
        else {
            rightArrow.setClickable(true);
            rightArrow.setColorFilter(Color.parseColor("#000000"));
            leftArrow.setClickable(true);
            leftArrow.setColorFilter(Color.parseColor("#000000"));
            leftArrow.setOnClickListener(v -> leftArrowClicked());
            rightArrow.setOnClickListener(v ->rightArrowClicked());

        }
    }


    @SuppressLint("SetTextI18n")
    private void printResult (int index)
    {
        StringBuilder stations = new StringBuilder();
        totalTime.setText(Controller.getTime(allRoutes.get(index).size()));
        totalPrice.setText(Controller.totalPrice(allRoutes.get(index).size())+ " L.E");
        stationNums.setText(allRoutes.get(index).size()+" Stat");
        direction.setText(Controller.getDirection(allRoutes.get(index)));
        for (String s : allRoutes.get(index)) {
            stations.append("\n").append(s);
        }
        allStations.setText(stations);
    }




}
