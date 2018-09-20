package id.co.ifest.marjan.spotevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SponsorshipActivity extends AppCompatActivity {
    Button btnAddSponsor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsorship);

        btnAddSponsor = findViewById(R.id.btn_add_sponsor);

        btnAddSponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SponsorshipActivity.this, AddSponsorActivity.class));
            }
        });
    }
}
