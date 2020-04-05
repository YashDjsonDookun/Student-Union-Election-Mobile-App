package com.example.uomstudentunionelection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<StudentDetails> studentDetail;

    String text = "";
    public String jsonStr;
    public static int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadAllStudents();
    }

    public void functionVote (View view)
    {
        EditText studentIdInput = findViewById(R.id.txtStudentId);
        EditText studentVoterCode = findViewById(R.id.txtVoterCode);
        Context context = getApplicationContext();
        String idInput = studentIdInput.getText().toString();
        String voterCode = studentVoterCode.getText().toString();

        if (idInput.length() > 0 && voterCode.length()>0)
        {
            number = -1;
            switch (idInput)
            {
                case "140015160":
                    number = 0;
                    //Log.i("testName", studentDetail.get(number).getName());
                    if (voterCode.equals(studentDetail.get(number).getCode()))
                    {
                        if (studentDetail.get(number).getVoted().equals("false"))
                        {
                            Intent in = new Intent(this, Voting_Screen.class);
                            String voterName = studentDetail.get(number).getName();
                            in.putExtra("voter", voterName.concat(" is voting for:"));
                            in.putExtra("number", "0");
                            startActivity(in);
                        }
                        else
                        {
                            text = "Voter has voted!";
                            Toast toast =Toast.makeText(context, text, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                    else {
                        text = "Voter is not registered!";
                        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                break;
                case "140015161":
                    number = 1;

                    //Log.i("testName", studentDetail.get(number).getName());
                    //Log.i("testName", studentDetail.get(number).getVoted());
                    if (voterCode.equals(studentDetail.get(number).getCode()))
                    {
                        if (studentDetail.get(number).getVoted().equals("false"))
                        {
                            Intent in = new Intent(this, Voting_Screen.class);
                            String voterName = MainActivity.studentDetail.get(MainActivity.number).getName();
                            in.putExtra("voter", voterName.concat(" is voting for:"));
                            in.putExtra("number", "1");
                            startActivity(in);
                        }
                        else
                        {
                            text = "Voter has voted!";
                            Toast toast =Toast.makeText(context, text, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                    else {
                        text = "Voter is not registered!";
                        Toast toast =Toast.makeText(context, text, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                break;
                case "140015162":
                    number = 2;
                   // Log.i("testName", studentDetail.get(number).getName());
                    if (voterCode.equals(studentDetail.get(number).getCode()))
                    {
                        if (studentDetail.get(number).getVoted().equals("false"))
                        {
                            Intent in = new Intent(this, Voting_Screen.class);
                            String voterName = MainActivity.studentDetail.get(MainActivity.number).getName();
                            in.putExtra("voter", voterName.concat(" is voting for:"));
                            in.putExtra("number", "2");
                            startActivity(in);
                        }
                        else
                        {
                            text = "Voter has voted!";
                            Toast toast =Toast.makeText(context, text, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                    else {
                        text = "Voter is not registered!";
                        Toast toast =Toast.makeText(context, text, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                break;
                case "140015163":
                    number = 3;
                    //Log.i("testName", studentDetail.get(number).getName());
                    if (voterCode.equals(studentDetail.get(number).getCode())) {
                        if (voterCode.equals(studentDetail.get(number).getCode())) {
                            if (studentDetail.get(number).getVoted().equals("false")) {
                                Intent in = new Intent(this, Voting_Screen.class);
                                String voterName = MainActivity.studentDetail.get(MainActivity.number).getName();
                                in.putExtra("voter", voterName.concat(" is voting for:"));
                                in.putExtra("number", "3");
                                startActivity(in);
                            } else {
                                text = "Voter has voted!";
                                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    }
                    else {
                        text = "Voter is not registered!";
                        Toast toast =Toast.makeText(context, text, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                break;
                default:
                    text = "Voter is not registered!";
                    Toast toast =Toast.makeText(context, text, Toast.LENGTH_SHORT);
                    toast.show();
                break;
            }
        }
        else {
            text = "Student ID or Voter Code cannot be left blank!";
            Toast toast =Toast.makeText(context, text, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void functionClear (View view)
    {
        clear();
    }

    // make a list of students
    public void loadAllStudents()
    {
        studentDetail = new ArrayList<>();
        jsonStr = loadJSONFromAsset("/sdcard/Documents/StudentRegistration.json");
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray students = jsonObj.getJSONArray("Students");
            for (int i=0; i<students.length(); i++) {
                JSONObject student = students.getJSONObject(i);
                String idString = student.getString("Id");
                String nameString = student.getString("Name");
                String genderString = student.getString("Gender");
                String codeString = student.getString("Code");
                String voted = student.getString("Voted");
                studentDetail.add(new StudentDetails(
                        idString,
                        nameString,
                        genderString,
                        codeString,
                        voted
                ));
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    // update json file
   public void updateVoteStatusInJson(int num)
   {
       String jsonStr = loadJSONFromAsset("/sdcard/Documents/StudentRegistration.json");
       try {
           JSONObject jsonObj = new JSONObject(jsonStr);
           JSONArray students = jsonObj.getJSONArray("Students");
           JSONObject student = students.getJSONObject(num);
           student.remove("Voted");
           student.put("Voted", "true");
           //Log.i("testingVote", "updateVoteStatusInJson: " + student.getString("Voted"));
           try
           {
               Writer writer = new FileWriter("/storage/emulated/0/Documents/StudentRegistration.json");
               writer.write(jsonObj.toString());
               writer.flush();
               writer.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       } catch (JSONException e)
       {
           e.printStackTrace();
       }
       studentDetail.get(num).setVoted("true");
   }

    private void clear() {
        EditText studentIdInput = findViewById(R.id.txtStudentId);
        EditText studentVoterCode = findViewById(R.id.txtVoterCode);
        studentIdInput.setText("");
        studentVoterCode.setText("");
    }

    // load the json file
    private String loadJSONFromAsset(String file)
    {
        String json = "";
        try {
            InputStream is = new FileInputStream(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
