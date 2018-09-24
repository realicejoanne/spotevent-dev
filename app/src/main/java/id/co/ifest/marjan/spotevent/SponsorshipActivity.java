package id.co.ifest.marjan.spotevent;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class SponsorshipActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    RecyclerView rvSponsor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsorship);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        viewSponsor();
    }

    private void viewSponsor() {
        rvSponsor = (RecyclerView)findViewById(R.id.rv_sponsorship_activity);
        rvSponsor.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvSponsor.setLayoutManager(layoutManager);

        prepareData();
    }

    private void prepareData() {
        final DatabaseReference ref = mDatabase.child("sponsor");
        final ArrayList<Sponsor> sponsors = new ArrayList<>();

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
                    sponsors.add(sponsor);
                }

                SponsorshipAdapter adapter = new SponsorshipAdapter(getApplicationContext(), sponsors);
                rvSponsor.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("SponsorActivity", "Read Failed");
            }
        });
    }
}
