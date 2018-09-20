package id.co.ifest.marjan.spotevent;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseAddDataActivity extends AppCompatActivity {
    private DatabaseReference database;

    private EditText etSponsorName, etSponsorMoney;
    private Button btnSponsorSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_create);

        etSponsorName = findViewById(R.id.et_sponsor_name);
        etSponsorMoney = findViewById(R.id.et_sponsor_money);
        btnSponsorSubmit = findViewById(R.id.btn_sponsor_submit);

        database = FirebaseDatabase.getInstance().getReference();

        final Sponsor sponsor = (Sponsor) getIntent().getSerializableExtra("data");

        if (sponsor != null) {
            etSponsorName.setText(sponsor.getName());
            etSponsorMoney.setText(sponsor.getMoney());
            btnSponsorSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sponsor.setName(etSponsorName.getText().toString());
                    sponsor.setMoney(etSponsorMoney.getText().toString());

                    updateSponsor(sponsor);
                }
            });
        } else{
            btnSponsorSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!TextUtils.isEmpty(etSponsorName.getText().toString()) && !TextUtils.isEmpty(etSponsorMoney.getText().toString()))
                        submitSponsor(new Sponsor(etSponsorName.getText().toString(), etSponsorMoney.getText().toString()));
                    else
                        Snackbar.make(findViewById(R.id.btn_sponsor_submit), "Data Cannot Empty", Snackbar.LENGTH_LONG).show();

                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(etSponsorName.getWindowToken(), 0);
                }
            });
        }
    }

    private void updateSponsor(Sponsor sponsor){
        database.child("sponsor")
                .child(sponsor.getKey())
                .setValue(sponsor)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Snackbar.make(findViewById(R.id.btn_sponsor_submit), "Data Updated.", Snackbar.LENGTH_LONG).setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();
                    }
                });
    }

    private void submitSponsor(Sponsor sponsor){
        database.child("sponsor").push().setValue(sponsor).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etSponsorName.setText("");
                etSponsorMoney.setText("");
                Snackbar.make(findViewById(R.id.btn_sponsor_submit), "Data Added.", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
