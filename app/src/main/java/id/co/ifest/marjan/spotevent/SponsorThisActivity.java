package id.co.ifest.marjan.spotevent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SponsorThisActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    TextView tvName, tvPublicIdType, tvPublicId, tvEmail;
    EditText etMoney;
    Button btnThisGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor_this);

        tvName = findViewById(R.id.tv_this_name);
        tvPublicIdType = findViewById(R.id.tv_this_public_id);
        tvPublicId = findViewById(R.id.tv_this_public_id_number);
        tvEmail = findViewById(R.id.tv_this_email);
        etMoney = findViewById(R.id.et_add_money);
        btnThisGo = findViewById(R.id.btn_this_go);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
//        String key = mAuth.getCurrentUser().getUid();

        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    tvName.setText(ds.child("fullname").getValue(String.class));
                    tvPublicIdType.setText(ds.child("publicIDTypes").getValue(String.class));
                    tvPublicId.setText(ds.child("publicID").getValue(String.class));
                    tvEmail.setText(ds.child("email").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Sponsor This Event", "Failed");
            }
        });

        btnThisGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
