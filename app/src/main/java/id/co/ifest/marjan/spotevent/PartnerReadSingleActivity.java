package id.co.ifest.marjan.spotevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PartnerReadSingleActivity extends AppCompatActivity {
    ImageView sponsorImage;
    TextView tvSponsorTitle, tvCurrentMoney, tvTargetMoney;
    TextView tvSponsorDesc, tvSponsorTime, tvSponsorCurrentProgress;
    ProgressBar sponsorProgressBar;
    Button btnSponsorGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_read_single);
        // Init()
        sponsorImage = findViewById(R.id.partner_image);
        tvSponsorTitle = findViewById(R.id.tv_partner_single_title);
        tvCurrentMoney = findViewById(R.id.tv_partner_single_current_money);
        tvTargetMoney = findViewById(R.id.tv_partner_single_target_money);
        tvSponsorDesc = findViewById(R.id.tv_partner_single_desc);
        tvSponsorTime = findViewById(R.id.tv_partner_single_time);
        tvSponsorCurrentProgress = findViewById(R.id.tv_partner_current_progress);
        sponsorProgressBar = findViewById(R.id.partner_single_progress_bar);
        btnSponsorGo = findViewById(R.id.btn_partner_single_go);

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
