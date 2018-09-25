package id.co.ifest.marjan.spotevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnSponsorship, btnPartnership, btnVolunteer, btnEvent, btnGotoLogin, btnLogout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        btnSponsorship = findViewById(R.id.btn_sponsorship);
        btnPartnership = findViewById(R.id.btn_partnership);
        btnVolunteer = findViewById(R.id.btn_voluteer);
        btnEvent = findViewById(R.id.btn_event);
        btnGotoLogin = findViewById(R.id.btn_goto_login);
        btnLogout = findViewById(R.id.btn_logout);

        btnSponsorship.setOnClickListener(this);
        btnPartnership.setOnClickListener(this);
        btnVolunteer.setOnClickListener(this);
        btnEvent.setOnClickListener(this);
        btnGotoLogin.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            btnGotoLogin.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.INVISIBLE);
        } else{
            btnGotoLogin.setVisibility(View.INVISIBLE);
            btnLogout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.btn_sponsorship:
                startActivity(new Intent(this, SponsorshipUpcomingEventActivity.class));
                break;
            case R.id.btn_partnership:
                startActivity(new Intent(this, PartnershipUpcomingEventActivity.class));
                break;
//            case R.id.btn_voluteer:
//                startActivity(new Intent(this, VolunteerActivity.class));
//                break;
            case R.id.btn_event:
                startActivity(new Intent(this, EventActivity.class));
                break;
            case R.id.btn_goto_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btn_logout:
                mAuth.signOut();
                finish();
                startActivity(getIntent());
                break;
        }
    }
}
