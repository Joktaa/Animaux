package fr.jorisrouziere.animaux.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import fr.jorisrouziere.animaux.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    View view;
    Button loginBtn;
    private String email ="", password ="";
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setTitle("Merci de patienter");
        progressDialog.setMessage("Connexion...");
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth = FirebaseAuth.getInstance();
        loginBtn = view.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())// on run time get id what button os click and get id
        {
            case R.id.loginBtn:
                validateData();
                break;
        }
    }

    private void validateData() {
        EditText emailEt = view.findViewById(R.id.editEmail);
        email = emailEt.getText().toString().trim();
        EditText passWordEt = view.findViewById(R.id.editPassword);
        password = passWordEt.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEt.setError("Format invalide");
        }
        else if (TextUtils.isEmpty(password)){
            passWordEt.setError("Veuillez rentrer un mot de passe");
        }
        else{
            firebaseLogin();
        }
    }

    private void firebaseLogin() {
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressDialog.dismiss();
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        String email = firebaseUser.getEmail();
                        Toast.makeText(view.getContext(), "Connexion à : \n"+email, Toast.LENGTH_SHORT).show();
                        NavController navController = NavHostFragment.findNavController(LoginFragment.this);
                        navController.navigate(R.id.action_loginFragment_to_animalsListFragment, null,null);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(view.getContext(), "" +e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}