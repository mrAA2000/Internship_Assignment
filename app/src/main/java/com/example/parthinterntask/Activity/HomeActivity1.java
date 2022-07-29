package com.example.parthinterntask.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.parthinterntask.Common.Helper;
import com.example.parthinterntask.Fragment.ExpandableListFragment;
import com.example.parthinterntask.Fragment.FinishedFragment;
import com.example.parthinterntask.Fragment.UpcomingFragment;
import com.example.parthinterntask.Interface.Communicator;
import com.example.parthinterntask.Model.finishedMatchModel;
import com.example.parthinterntask.Model.upcomingMatchModel;
import com.example.parthinterntask.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity1 extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private List<upcomingMatchModel> allUpcomingMatchList;
    private List<upcomingMatchModel> allUpcomingMatchList1;
    private List<finishedMatchModel> allFinishedMatchList;
    private List<finishedMatchModel> allFinishedMatchList1;
    private UpcomingFragment upcomingFragment;
    private FinishedFragment finishedFragment;
    private ExpandableListFragment expandableListFragment;
    private Adapter adapter;
    private Communicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Fresco.initialize(this);

        Log.d("Execute", "Main Activity : On Create");

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager);

        allUpcomingMatchList = new ArrayList<>();
        allUpcomingMatchList1 = new ArrayList<>();
        allFinishedMatchList = new ArrayList<>();
        allFinishedMatchList1 = new ArrayList<>();

        SubscribeToTopic();
        setViewPager();
        FetchMatchesFromFirebase();
    }

    public void callFromUpcomingFragment(Communicator communicator) {
        this.communicator = communicator;
        this.communicator.passData(allUpcomingMatchList1);
    }

    public void callFromFinishedFragment(Communicator communicator) {
        this.communicator = communicator;
        this.communicator.passData1(allFinishedMatchList1);
    }

    public class Adapter extends FragmentStateAdapter {
        public Adapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    upcomingFragment = new UpcomingFragment();
                    return upcomingFragment;
                case 1:
                    finishedFragment = new FinishedFragment();
                    return finishedFragment;
                case 2:
                    expandableListFragment = new ExpandableListFragment();
                    return expandableListFragment;
            }
            return null;
        }

        @Override
        public int getItemCount() {
            Log.d("msg", tabLayout.getTabCount() + "");
            return 3;

        }
    }

    private String[] titles = new String[]{"Upcoming", "Finished", "Expandable"};

    private void setViewPager() {

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new Adapter(HomeActivity1.this);
        //Behaviour Resume will make all the other fragments as inactive and the shown fragment active..
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(titles[position])).attach();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void FetchMatchesFromFirebase() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child("liveMatches2");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allUpcomingMatchList.clear();
                allUpcomingMatchList1.clear();
                allFinishedMatchList.clear();
                allFinishedMatchList1.clear();
                for (int i = 0; i < snapshot.getChildrenCount(); i++) {
//                    uploadToFirestore(snapshot.child(String.valueOf(i)),i);
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
                        allUpcomingMatchList.add(upcomingMatchModel);
                    } else if (snapshot.child(String.valueOf(i)).child("result").getValue() != null) {
                        finishedMatchModel finishedMatchModel = new finishedMatchModel(0, team1, team2, teamFlag1, teamFlag2, score1, score2, overs1, overs2, winner, matchNo, result, date, time);
                        allFinishedMatchList.add(finishedMatchModel);
                    } else {
                        upcomingMatchModel upcomingMatchModel = new upcomingMatchModel(2, team1, team2, teamFlag1, teamFlag2, date, time, matchNo);
                        allUpcomingMatchList.add(upcomingMatchModel);
                    }
                }
                allUpcomingMatchList1.addAll(Helper.sortOnBasisOfTime(allUpcomingMatchList));
                allFinishedMatchList1.addAll(Helper.sortOnBasisOfTime1(allFinishedMatchList));
//                Log.d("Data", String.valueOf(allUpcomingMatchList1.size()));
//                Log.d("Data", String.valueOf(allFinishedMatchList1.size()));

                if (communicator instanceof UpcomingFragment)
                    communicator.passData(allUpcomingMatchList1);

                if (communicator instanceof FinishedFragment)
                    communicator.passData1(allFinishedMatchList1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Data Load Failed : ", error.toString());
            }
        });
    }

    private void SubscribeToTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("Cricket")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                        Log.d("Message", msg);
                        Toast.makeText(HomeActivity1.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadToFirestore(DataSnapshot snapshot, int i) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Matches").document(snapshot.getKey()).set(snapshot.getValue()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("Check", "Data Added Successfully !!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Check", "Data Failed to Add !!");
            }
        });
    }

}