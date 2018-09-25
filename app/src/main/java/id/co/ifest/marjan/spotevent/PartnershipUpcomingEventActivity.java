package id.co.ifest.marjan.spotevent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PartnershipUpcomingEventActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    RecyclerView rvPartner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partnership);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        viewPartner();
    }

    private void viewPartner() {
        rvPartner = (RecyclerView)findViewById(R.id.rv_partnership_activity);
        rvPartner.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvPartner.setLayoutManager(layoutManager);

        prepareData();
    }

    private void prepareData() {
        final DatabaseReference ref = mDatabase.child("sponsor");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Sponsor> sponsors = new ArrayList<>();

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String key = (String) ds.getKey();
                    Log.d("Key : ", "keynya " + key);

                    Sponsor sponsor = new Sponsor();
                    sponsor.setName(ds.child("name").getValue(String.class));
                    sponsor.setDesc(ds.child("desc").getValue(String.class));
                    sponsor.setMoney(ds.child("money").getValue(String.class));
                    sponsor.setTime(ds.child("time").getValue(String.class));
                    sponsor.setImageUrl(ds.child("image").getValue(String.class));
                    sponsor.setTargetMoney(ds.child("targetmoney").getValue(String.class));
                    sponsors.add(sponsor);
                }

                PartnershipAdapter adapter = new PartnershipAdapter(getApplicationContext(), sponsors);
                rvPartner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("PartnershipActivity", "Read Failed");
            }
        });
    }
}
