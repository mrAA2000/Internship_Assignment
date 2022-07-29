package com.example.parthinterntask.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.parthinterntask.Activity.HomeActivity1;
import com.example.parthinterntask.Adapter.finishedRecyclerViewAdapter;
import com.example.parthinterntask.Interface.Communicator;
import com.example.parthinterntask.Model.finishedMatchModel;
import com.example.parthinterntask.Model.upcomingMatchModel;
import com.example.parthinterntask.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FinishedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FinishedFragment extends Fragment implements Communicator {

    private RecyclerView rvMatch;
    private finishedRecyclerViewAdapter finishedRecyclerViewAdapter;
    List<finishedMatchModel> finishedMatchModelList;
    private String JSON_URL = "https://mocki.io/v1/2389d44c-81aa-4e04-bd2e-b8c7e17572c0";
    private ShimmerFrameLayout shimmerFrameLayout;
    private List<String> keyList;
    private LinearSnapHelper snapHelper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FinishedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FinishedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FinishedFragment newInstance(String param1, String param2) {
        FinishedFragment fragment = new FinishedFragment();
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
        Log.d("Execute", "Fragment Finished : On CreateView");
        return inflater.inflate(R.layout.fragment_finished, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        snapHelper = new LinearSnapHelper();
        rvMatch = view.findViewById(R.id.rvMatch);
        snapHelper.attachToRecyclerView(rvMatch);

        Log.d("Execute", "Fragment Finished : OnViewCreated");
        shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container);
        shimmerFrameLayout.startShimmer();

        finishedMatchModelList = new ArrayList<>();
        finishedRecyclerViewAdapter = new finishedRecyclerViewAdapter(getContext(), finishedMatchModelList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvMatch.setLayoutManager(linearLayoutManager);
        rvMatch.setAdapter(finishedRecyclerViewAdapter);

        keyList = new ArrayList<String>();
        keyList.add("t1");
        keyList.add("t2");
        keyList.add("t1flag");
        keyList.add("t2flag");
        keyList.add("score1");
        keyList.add("score2");
        keyList.add("overs1");
        keyList.add("overs2");
        keyList.add("winner");
        keyList.add("match_no");
        keyList.add("date");
        keyList.add("result");
        keyList.add("t");

//        FetchMatches();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Execute", "Fragment Finished : On Resume");
        ((HomeActivity1) getActivity()).callFromFinishedFragment(this);
    }

    private void FetchMatches() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String date = jsonObject.getString("date");
                        finishedMatchModel finishedMatchModel = new finishedMatchModel(1, date);
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        finishedMatchModelList.add(finishedMatchModel);
                        finishedRecyclerViewAdapter.notifyDataSetChanged();
                        JSONArray jsonArray = jsonObject.getJSONArray("m");
                        for (int j = 0; j < jsonArray.length(); j++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                            String team1 = jsonObject1.getString(keyList.get(0));
                            String team2 = jsonObject1.getString(keyList.get(1));
                            String teamFlag1 = jsonObject1.getString(keyList.get(2));
                            String teamFlag2 = jsonObject1.getString(keyList.get(3));
                            String score1 = jsonObject1.getString(keyList.get(4));
                            String score2 = jsonObject1.getString(keyList.get(5));
                            String overs1 = jsonObject1.getString(keyList.get(6));
                            String overs2 = jsonObject1.getString(keyList.get(7));
                            String winner = jsonObject1.getString(keyList.get(8));
                            String matchNo = jsonObject1.getString(keyList.get(9));
                            String date1 = jsonObject1.getString(keyList.get(10));
                            String result = jsonObject1.getString(keyList.get(11));
                            Long time = jsonObject1.getLong(keyList.get(12));
                            finishedMatchModel finishedMatchModel1 = new finishedMatchModel(0, team1, team2, teamFlag1, teamFlag2, score1, score2, overs1, overs2, winner, matchNo, result, date1, time);
                            finishedMatchModelList.add(finishedMatchModel1);
                            finishedRecyclerViewAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                finishedMatchModel finishedMatchModel = new finishedMatchModel(2);
                finishedMatchModelList.add(finishedMatchModel);
                finishedRecyclerViewAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.getLocalizedMessage());
            }
        });
        queue.add(jsonArrayRequest);
    }

    @Override
    public void passData(List<upcomingMatchModel> allMatchModelList) {

    }

    @Override
    public void passData1(List<finishedMatchModel> allMatchModelList) {
        finishedMatchModelList.clear();
        finishedMatchModelList.addAll(allMatchModelList);
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        finishedRecyclerViewAdapter.notifyDataSetChanged();
    }
}