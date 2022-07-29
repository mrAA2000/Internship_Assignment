package com.example.parthinterntask.Fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.parthinterntask.Adapter.CustomizedExpandableListAdapter;
import com.example.parthinterntask.Common.Helper;
import com.example.parthinterntask.Model.finishedMatchModel;
import com.example.parthinterntask.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExpandableListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpandableListFragment extends Fragment {

    ExpandableListView expandableListView;
    List<String> expandableTitleList;
    HashMap<String,List<finishedMatchModel>> expandableMatchList;
    CustomizedExpandableListAdapter expandableListAdapter;
    private String JSON_URL="https://mocki.io/v1/2389d44c-81aa-4e04-bd2e-b8c7e17572c0";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExpandableListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExpandableListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExpandableListFragment newInstance(String param1, String param2) {
        ExpandableListFragment fragment = new ExpandableListFragment();
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
        return inflater.inflate(R.layout.fragment_expandable_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        expandableTitleList = new ArrayList<String>();
        expandableMatchList = new HashMap<>();
        expandableListAdapter = new CustomizedExpandableListAdapter(getContext(),expandableTitleList,expandableMatchList);
        expandableListView = view.findViewById(R.id.expandableListView);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                ;
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                ;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });


        FetchMatches();
    }

    private void FetchMatches() {
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        String date=jsonObject.getString("date");
                        String formatDate=Helper.formatDateForCard3(date);
                        Log.d("Date",date);
                        expandableTitleList.add(formatDate);
                        List<finishedMatchModel> finishedMatchModelList=new ArrayList<>();
                        JSONArray jsonArray=jsonObject.getJSONArray("m");
                        for (int j=0;j<jsonArray.length();j++){
                            String team1=jsonArray.getJSONObject(j).getString("t1");
                            String team2=jsonArray.getJSONObject(j).getString("t2");
                            String teamFlag1=jsonArray.getJSONObject(j).getString("t1flag");
                            String teamFlag2=jsonArray.getJSONObject(j).getString("t2flag");
                            String score1=jsonArray.getJSONObject(j).getString("score1");
                            String score2=jsonArray.getJSONObject(j).getString("score2");
                            String overs1=jsonArray.getJSONObject(j).getString("overs1");
                            String overs2=jsonArray.getJSONObject(j).getString("overs2");
                            String winner=jsonArray.getJSONObject(j).getString("winner");
                            String matchNo=jsonArray.getJSONObject(j).getString("match_no");
                            String date1=jsonArray.getJSONObject(j).getString("date");
                            String result=jsonArray.getJSONObject(j).getString("result");
                            Long time=jsonArray.getJSONObject(j).getLong("t");
                            finishedMatchModel finishedMatchModel1 =new finishedMatchModel(0,team1,team2,teamFlag1,teamFlag2,score1,score2,overs1,overs2,winner,matchNo,result,date1,time);
                            finishedMatchModelList.add(finishedMatchModel1);
                        }
                        expandableMatchList.put(formatDate,finishedMatchModelList);
                        expandableListAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.getLocalizedMessage());
            }
        });
        queue.add(jsonArrayRequest);
    }
}