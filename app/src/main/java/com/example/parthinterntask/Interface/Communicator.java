package com.example.parthinterntask.Interface;

import com.example.parthinterntask.Model.finishedMatchModel;
import com.example.parthinterntask.Model.upcomingMatchModel;

import java.util.List;

public interface Communicator {

    void passData(List<upcomingMatchModel> allMatchModelList);
    void passData1(List<finishedMatchModel> allMatchModelList);

}
