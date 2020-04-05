package com.example.uomstudentunionelection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.jar.JarException;

public class Voting_Screen extends AppCompatActivity {
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting__screen);
        Intent in = getIntent();
        String showName =  in.getStringExtra("voter");
        number =  in.getStringExtra("number");
        TextView voter = findViewById(R.id.showVoterName);
        voter.setText(showName);
    }

    public void functionClear (View view)
    {
        RadioButton optionCandidate1 = findViewById(R.id.candidate1);
        RadioButton optionCandidate2 = findViewById(R.id.candidate2);
        RadioButton optionCandidate3 = findViewById(R.id.candidate3);

        optionCandidate1.setChecked(false);
        optionCandidate2.setChecked(false);
        optionCandidate3.setChecked(false);
    }

    public void option1 (View view)
    {
        RadioButton optionCandidate2 = findViewById(R.id.candidate2);
        RadioButton optionCandidate3 = findViewById(R.id.candidate3);

        optionCandidate2.setChecked(false);
        optionCandidate3.setChecked(false);
    }

    public void option2 (View view)
    {
        RadioButton optionCandidate1 = findViewById(R.id.candidate1);
        RadioButton optionCandidate3 = findViewById(R.id.candidate3);

        optionCandidate1.setChecked(false);
        optionCandidate3.setChecked(false);
    }

    public void option3 (View view)
    {
        RadioButton optionCandidate1 = findViewById(R.id.candidate1);
        RadioButton optionCandidate2 = findViewById(R.id.candidate2);

        optionCandidate1.setChecked(false);
        optionCandidate2.setChecked(false);
    }

    public void functionSave (View view)
    {
        Context context = getApplicationContext();
        String text = "You have voted!";
        Toast toast =Toast.makeText(context, text, Toast.LENGTH_SHORT);
        MainActivity main = new MainActivity();
        main.updateVoteStatusInJson(Integer.parseInt(number));
        toast.show();
        finish();
    }
}
