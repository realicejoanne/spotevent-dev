package id.co.ifest.marjan.spotevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SponsorReadSingleActivity extends AppCompatActivity {

    ImageView sponsorImage;
    TextView tvSponsorTitle, tvCurrentMoney, tvTargetMoney;
    TextView tvSponsorDesc, tvSponsorTime, tvSponsorCurrentProgress;
    ProgressBar sponsorProgressBar;
    Button btnSponsorGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor_read_single);
        // Init()
        sponsorImage = findViewById(R.id.sponsor_image);
        tvSponsorTitle = findViewById(R.id.tv_sponsor_single_title);
        tvCurrentMoney = findViewById(R.id.tv_sponsor_single_current_money);
        tvTargetMoney = findViewById(R.id.tv_sponsor_single_target_money);
        tvSponsorDesc = findViewById(R.id.tv_sponsor_single_desc);
        tvSponsorTime = findViewById(R.id.tv_sponsor_single_time);
        tvSponsorCurrentProgress = findViewById(R.id.tv_sponsor_current_progress);
        sponsorProgressBar = findViewById(R.id.sponsor_single_progress_bar);
        btnSponsorGo = findViewById(R.id.btn_sponsor_single_go);

        Sponsor sponsor = (Sponsor) getIntent().getSerializableExtra("sponsorObject");
        String progress = getProgress(sponsor.getMoney(),sponsor.getTargetMoney());
        tvSponsorTitle.setText(sponsor.getName());
        tvCurrentMoney.setText(sponsor.getMoney());
        tvTargetMoney.setText(sponsor.getTargetMoney());
        tvSponsorDesc.setText(sponsor.getDesc());
        tvSponsorTime.setText(sponsor.getTime());
        tvSponsorCurrentProgress.setText(progress);
        sponsorProgressBar.setProgress(Integer.parseInt(progress));

        btnSponsorGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SponsorThisActivity.class));
            }
        });
    }

    private String getProgress(String a, String b){
        float intA, intB;
        int value;
        intA = Integer.parseInt(a);
        intB = Integer.parseInt(b);
        value = (int) ((intA/intB)*100);

        return Integer.toString(value);
    }
}
