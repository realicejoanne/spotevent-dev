package id.co.ifest.marjan.spotevent;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class RegisterFragment extends Fragment {
    View view;
    Button btnRegister;
    EditText etEmail, etPassword, etFullname, etPublicIdNumber, etBirthplace, etAddress, etBirthday;
    RadioGroup rgPublicId;
    RadioButton rbPublicId;
    TextView tvAlreadyRegistered;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        etEmail = view.findViewById(R.id.et_register_email);
        etPassword = view.findViewById(R.id.et_register_password);
        etFullname = view.findViewById(R.id.et_register_fullname);
        etPublicIdNumber = view.findViewById(R.id.et_register_public_id);
        etBirthplace = view.findViewById(R.id.et_register_birthplace);
        etAddress = view.findViewById(R.id.et_register_address);
        etBirthday = view.findViewById(R.id.et_register_birthday);
        rgPublicId = view.findViewById(R.id.rg_register_public_id);
        rbPublicId = view.findViewById(R.id.rb_student_card);

        // Fill in the blank (just in case)
        etBirthday.setText(" ");
        etBirthplace.setText(" ");
        etAddress.setText(" ");
        rbPublicId.setChecked(true);

        // Get birthday
        getBirthday();

        // Register User
        btnRegister = view.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(formNotEmpty()){
                    // Get Public ID Types
                    int selectedId = rgPublicId.getCheckedRadioButtonId();
                    rbPublicId = view.findViewById(selectedId);

                    // Create user
                    final User user = new User();
                    user.setFullname(etFullname.getText().toString());
                    user.setPublicID(etPublicIdNumber.getText().toString());
                    user.setPublicIDTypes(rbPublicId.getText().toString());
                    user.setEmail(etEmail.getText().toString());
                    user.setBirthday(etBirthday.getText().toString());
                    user.setBirthplace(etBirthplace.getText().toString());
                    user.setAddress(etAddress.getText().toString());
                    registerUser(user);
                } else{
                    Snackbar.make(getActivity().findViewById(R.id.btn_register),"Please fill at least email and password", Snackbar.LENGTH_INDEFINITE).show();
                }
            }
        });

        // Get back to login if already register
        tvAlreadyRegistered = view.findViewById(R.id.tv_already_registered);
        tvAlreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().popBackStack();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private boolean formNotEmpty(){
        if(!TextUtils.isEmpty(etEmail.getText().toString()) && !TextUtils.isEmpty(etPassword.getText().toString()) &&
                !TextUtils.isEmpty(etPublicIdNumber.getText().toString()))
            return true;
        else
            return false;
    }

    private void getBirthday(){
        etBirthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            etBirthday.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });
    }

    private void registerUser(final User user){
        mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userKey = firebaseUser.getUid();
                            mDatabase.child("users").child(userKey).push().setValue(user).addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    etEmail.setText("");
                                    etFullname.setText("");
                                    etPublicIdNumber.setText("");
                                    etBirthplace.setText("");
                                    etBirthday.setText("");
                                    etAddress.setText("");
                                    rbPublicId.setChecked(false);
                                    Snackbar.make(view.findViewById(R.id.btn_register), "User Registered Successfully", Snackbar.LENGTH_LONG).show();
                                    getActivity().finish();
                                }
                            });
                        } else{
                            Snackbar.make(getActivity().findViewById(R.id.btn_register),"Create user Failed", Snackbar.LENGTH_INDEFINITE).show();
                        }
                    }
                });
    }
}
