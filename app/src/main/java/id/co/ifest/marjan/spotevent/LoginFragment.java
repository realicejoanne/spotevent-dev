package id.co.ifest.marjan.spotevent;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {
    Button btnRegister, btnLogin;
    EditText etEmail, etPassword;
    View view;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);

        etEmail = view.findViewById(R.id.et_login_email);
        etPassword = view.findViewById(R.id.et_login_password);
        btnRegister = view.findViewById(R.id.btn_goto_register);
        btnLogin = view.findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.login_layout, new RegisterFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(etEmail.getText().toString()) && !TextUtils.isEmpty(etPassword.getText().toString())){
                    mAuth.signInWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString())
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else
                                        updateUI(null);
                                }
                            });
                } else{
                    Snackbar.make(getActivity().findViewById(R.id.btn_login), "Please fill your email and password!", Snackbar.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }

    private void updateUI(FirebaseUser user){
        if(user != null){
            getActivity().finish();
        } else{
            Snackbar.make(getActivity().findViewById(R.id.btn_login), "Login Failed", Snackbar.LENGTH_LONG).show();
        }
    }

}
