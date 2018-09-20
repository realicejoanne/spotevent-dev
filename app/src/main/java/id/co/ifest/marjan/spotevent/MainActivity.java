package id.co.ifest.marjan.spotevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnSponsorship, btnPartnership, btnVolunteer, btnEvent, btnGotoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSponsorship = findViewById(R.id.btn_sponsorship);
        btnPartnership = findViewById(R.id.btn_partnership);
        btnVolunteer = findViewById(R.id.btn_voluteer);
        btnEvent = findViewById(R.id.btn_event);
        btnGotoLogin = findViewById(R.id.btn_goto_login);

        btnSponsorship.setOnClickListener(this);
        btnPartnership.setOnClickListener(this);
        btnVolunteer.setOnClickListener(this);
        btnEvent.setOnClickListener(this);
        btnGotoLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.btn_sponsorship:
                startActivity(new Intent(this, SponsorshipActivity.class));
                break;
//            case R.id.btn_partnership:
//                startActivity(new Intent(this, PartnershipActivity.class));
//                break;
//            case R.id.btn_voluteer:
//                startActivity(new Intent(this, VolunteerActivity.class));
//                break;
//            case R.id.btn_event:
//                startActivity(new Intent(this, EventActivity.class));
//                break;
            case R.id.btn_goto_login:
                startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
