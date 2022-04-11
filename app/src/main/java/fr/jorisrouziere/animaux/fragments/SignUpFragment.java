package fr.jorisrouziere.animaux.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import fr.jorisrouziere.animaux.Room.models.Animal;
import fr.jorisrouziere.animaux.Utils.ApiUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener{

    View view;
    Button saveUser;
    private String email ="", password ="";
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    public SignUpFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
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
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setTitle("Merci de patienter");
        progressDialog.setMessage("Création de votre compte...");
        progressDialog.setCanceledOnTouchOutside(false);
        saveUser = view.findViewById(R.id.signUpBtn);
        saveUser.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())// on run time get id what button os click and get id
        {
            case R.id.signUpBtn:
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
        else if (password.length() < 6 ){
            passWordEt.setError("Le mot de passe doit contenir au minimum 6 caractères");
        }
        else{
            firebaseSignUp();
        }
    }

    private void firebaseSignUp() {
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressDialog.dismiss();
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        String email = firebaseUser.getEmail();
                        Toast.makeText(SignUpFragment.this.getContext(), "Compte créé\n"+email, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(SignUpFragment.this.getContext(), ""+ e.getMessage(), Toast.LENGTH_SHORT).show();;
                    }
                });
    }
}