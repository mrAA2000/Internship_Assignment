package com.example.parthinterntask.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.parthinterntask.Activity.HomeActivity1;
import com.example.parthinterntask.Adapter.upcomingRecyclerViewAdapter;
import com.example.parthinterntask.Common.Helper;
import com.example.parthinterntask.Interface.Communicator;
import com.example.parthinterntask.Model.finishedMatchModel;
import com.example.parthinterntask.Model.upcomingMatchModel;
import com.example.parthinterntask.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpcomingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpcomingFragment extends Fragment implements Communicator {

    private RecyclerView rvMatch;
    private upcomingRecyclerViewAdapter upcomingRecyclerViewAdapter;
    List<upcomingMatchModel> upcomingMatchModelList;
    List<upcomingMatchModel> upcomingMatchModelList1;
    private String JSON_URL = "https://mocki.io/v1/30786c0a-390e-41d5-9ad8-549ed26cba64";
    private ShimmerFrameLayout shimmerFrameLayout;
    private Handler handler = new Handler(Looper.getMainLooper());

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpcomingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpcomingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpcomingFragment newInstance(String param1, String param2) {
        UpcomingFragment fragment = new UpcomingFragment();
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
//        Log.d("Execute","Fragment Upcoming : On CreateView");
        return inflater.inflate(R.layout.fragment_upcoming, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMatch = view.findViewById(R.id.rvMatch);

        shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container);
        shimmerFrameLayout.startShimmer();

        upcomingMatchModelList1 = new ArrayList<>();
        upcomingMatchModelList = new ArrayList<>();
        upcomingRecyclerViewAdapter = new upcomingRecyclerViewAdapter(getContext(), upcomingMatchModelList1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvMatch.setLayoutManager(linearLayoutManager);
        rvMatch.setAdapter(upcomingRecyclerViewAdapter);
        RecyclerView.ItemAnimator animator = rvMatch.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }

//        FetchMatches();
//        FetchMatchesFromFirebase();

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Execute", "Upcoming Finished : On Resume");
        ((HomeActivity1) getActivity()).callFromUpcomingFragment(this);
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
                        Log.d("Date", date);
                        upcomingMatchModel upcomingMatchModel = new upcomingMatchModel(1, date);
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        upcomingMatchModelList.add(upcomingMatchModel);
                        upcomingRecyclerViewAdapter.notifyDataSetChanged();
                        JSONArray jsonArray = jsonObject.getJSONArray("m");
                        for (int j = 0; j < jsonArray.length(); j++) {
                            String team1 = jsonArray.getJSONObject(j).getString("t1");
                            String team2 = jsonArray.getJSONObject(j).getString("t2");
                            String teamFlag1 = jsonArray.getJSONObject(j).getString("t1flag");
                            String teamFlag2 = jsonArray.getJSONObject(j).getString("t2flag");
                            String matchNo = jsonArray.getJSONObject(j).getString("match_no");
                            String date1 = jsonArray.getJSONObject(j).getString("date");
                            Long time = jsonArray.getJSONObject(j).getLong("t");
                            if (jsonArray.getJSONObject(j).has("odds")) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i).getJSONObject("odds");
                                String rate1 = jsonObject1.getString("rate");
                                String rate2 = jsonObject1.getString("rate2");
                                String rateTeam = jsonObject1.getString("rate_team");
                                upcomingMatchModel upcomingMatchModel1 = new upcomingMatchModel(0, team1, team2, teamFlag1, teamFlag2, date1, rateTeam, rate1, rate2, time, matchNo);
                                upcomingMatchModelList.add(upcomingMatchModel1);
                            } else {
                                upcomingMatchModel upcomingMatchModel1 = new upcomingMatchModel(2, team1, team2, teamFlag1, teamFlag2, date1, time, matchNo);
                                upcomingMatchModelList.add(upcomingMatchModel1);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                upcomingMatchModel upcomingMatchModel2 = new upcomingMatchModel(3);
                upcomingMatchModelList.add(upcomingMatchModel2);
                upcomingRecyclerViewAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.getLocalizedMessage());
            }
        });
        queue.add(jsonArrayRequest);
    }

    private void FetchMatchesFromFirebase() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child("liveMatches2");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                upcomingMatchModelList1.clear();
                upcomingMatchModelList.clear();
                for (int i = 0; i <= 12; i++) {
                    String date = snapshot.child(String.valueOf(i)).child("date").getValue() != null ? snapshot.child(String.valueOf(i)).child("date").getValue().toString() : "";
                    Long time = snapshot.child(String.valueOf(i)).child("t").getValue() != null ? (Long) snapshot.child(String.valueOf(i)).child("t").getValue() : 0;
                    String teamFlag1 = snapshot.child(String.valueOf(i)).child("t1flag").getValue() != null ? snapshot.child(String.valueOf(i)).child("t1flag").getValue().toString() : "";
                    String teamFlag2 = snapshot.child(String.valueOf(i)).child("t2flag").getValue() != null ? snapshot.child(String.valueOf(i)).child("t2flag").getValue().toString() : "";
                    String matchNo = snapshot.child(String.valueOf(i)).child("match_no").getValue() != null ? snapshot.child(String.valueOf(i)).child("match_no").getValue().toString() : "";
                    String team1 = snapshot.child(String.valueOf(i)).child("t1").getValue() != null ? snapshot.child(String.valueOf(i)).child("t1").getValue().toString() : "";
                    String team2 = snapshot.child(String.valueOf(i)).child("t2").getValue() != null ? snapshot.child(String.valueOf(i)).child("t2").getValue().toString() : "";
                    String score1 = snapshot.child(String.valueOf(i)).child("score1").getValue() != null ? snapshot.child(String.valueOf(i)).child("score1").getValue().toString() : "";
                    String score2 = snapshot.child(String.valueOf(i)).child("score2").getValue() != null ? snapshot.child(String.valueOf(i)).child("score2").getValue().toString() : "";
                    String overs1 = snapshot.child(String.valueOf(i)).child("overs1").getValue() != null ? snapshot.child(String.valueOf(i)).child("overs1").getValue().toString() : "";
                    String overs2 = snapshot.child(String.valueOf(i)).child("overs2").getValue() != null ? snapshot.child(String.valueOf(i)).child("overs2").getValue().toString() : "";
                    String result = snapshot.child(String.valueOf(i)).child("result").getValue() != null ? snapshot.child(String.valueOf(i)).child("result").getValue().toString() : "";
                    String winner = snapshot.child(String.valueOf(i)).child("winner").getValue() != null ? snapshot.child(String.valueOf(i)).child("winner").getValue().toString() : "";
                    if (snapshot.child(String.valueOf(i)).child("odds").getValue() != null) {
                        String rate1 = snapshot.child(String.valueOf(i)).child("odds").child("rate").getValue() != null ? snapshot.child(String.valueOf(i)).child("odds").child("rate").getValue().toString() : "";
                        Log.d("Check", rate1);
                        String rate2 = snapshot.child(String.valueOf(i)).child("odds").child("rate2").getValue() != null ? snapshot.child(String.valueOf(i)).child("odds").child("rate2").getValue().toString() : "";
                        String rateTeam = snapshot.child(String.valueOf(i)).child("odds").child("rate_team").getValue() != null ? snapshot.child(String.valueOf(i)).child("odds").child("rate_team").getValue().toString() : "";
                        upcomingMatchModel upcomingMatchModel = new upcomingMatchModel(0, team1, team2, teamFlag1, teamFlag2, date, rateTeam, rate1, rate2, time, matchNo);
                        upcomingMatchModelList.add(upcomingMatchModel);
                    } else if (snapshot.child(String.valueOf(i)).child("result").getValue() != null) {
                        upcomingMatchModel upcomingMatchModel = new upcomingMatchModel(4, team1, team2, teamFlag1, teamFlag2, score1, score2, overs1, overs2, winner, matchNo, result, date, time);
                        upcomingMatchModelList.add(upcomingMatchModel);
                    } else {
                        upcomingMatchModel upcomingMatchModel = new upcomingMatchModel(2, team1, team2, teamFlag1, teamFlag2, date, time, matchNo);
                        upcomingMatchModelList.add(upcomingMatchModel);
                    }
                }
                upcomingMatchModelList1.addAll(Helper.sortOnBasisOfTime(upcomingMatchModelList));
                upcomingRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void passData(List<upcomingMatchModel> allMatchModelList) {
        upcomingMatchModelList1.clear();
        upcomingMatchModelList1.addAll(allMatchModelList);
        shimmerFrameLayout.stopShimmer();
        rvMatch.setHasFixedSize(true);
        shimmerFrameLayout.setVisibility(View.GONE);
        upcomingRecyclerViewAdapter.notifyDataSetChanged();
        startTimer();
    }

    @Override
    public void passData1(List<finishedMatchModel> allMatchModelList) {

    }

    Timer t;

    public void startTimer() {
        if (t != null) {
            Log.d("upcoming_fragment", "end timer");
            t.cancel();
            t = null;
        }
        Log.d("upcoming_fragment", "start timer");
        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        upcomingRecyclerViewAdapter.notifyItemRangeChanged(0, upcomingMatchModelList1.size());
                    }
                });
            }
        }, 0, 1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (t != null)
            t.cancel();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (t != null)
            t.cancel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (t != null)
            t.cancel();
    }
}