package fr.jorisrouziere.animaux.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import fr.jorisrouziere.animaux.R;
import fr.jorisrouziere.animaux.Utils.ApiUtils;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        ApiUtils.getAnimals(getApplicationContext(), this);
    }
}
