package id.co.ifest.marjan.spotevent;

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
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterFragment extends Fragment {
    View view;
    Button btnRegister;
    EditText etEmail, etPassword;
    TextView tvAlreadyRegistered;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);

        mAuth = FirebaseAuth.getInstance();

        etEmail = view.findViewById(R.id.et_register_email);
        etPassword = view.findViewById(R.id.et_register_password);
        btnRegister = view.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(etEmail.getText().toString()) && !TextUtils.isEmpty(etPassword.getText().toString())){
                    mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else{
                                        updateUI(null);
                                    }
                                }
                            });
                } else{
                    Snackbar.make(getActivity().findViewById(R.id.btn_register),"Please fill your email and password!", Snackbar.LENGTH_INDEFINITE).show();
                }
            }
        });

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

    private void updateUI(FirebaseUser user){
        if(user != null){
            getActivity().finish();
        } else{
            Snackbar.make(getActivity().findViewById(R.id.btn_register),"Create user Failed", Snackbar.LENGTH_INDEFINITE).show();
        }
    }


}
