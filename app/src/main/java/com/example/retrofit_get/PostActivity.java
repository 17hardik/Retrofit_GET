package com.example.retrofit_get;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.example.retrofit_get.MainActivity.readFromFile;

public class PostActivity extends AppCompatActivity {
    Button submitButton, getButton;
    EditText userET, jobET, ratingET, jsonET;
    String userID, jobID, rating;
    TextView getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        submitButton =  findViewById(R.id.submitButton);
        getButton = findViewById(R.id.getButton);
        userET = findViewById(R.id.userET);
        jobET = findViewById(R.id.jobET);
        jsonET = findViewById(R.id.jsonET);
        ratingET = findViewById(R.id.ratingET);
        getData = findViewById(R.id.getData);

        submitButton.setOnClickListener(v -> {
            userID = userET.getText().toString().trim();
            jobID = jobET.getText().toString().trim();
            rating = ratingET.getText().toString().trim();
            SaveJson compositionJso = new SaveJson();
            JSONObject obj;
            obj = compositionJso.makeJSONObject(userID, jobID, rating);
            try {
                //Here we are trying to store our data in phone's storage inside Rojgar folder in json format
                createFolder("Rojgar");
                Writer output;
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE}, 1);
                File file = new File("/storage/emulated/0/Rojgar/" + jobID + ".json");
                output = new BufferedWriter(new FileWriter(file));
                output.write(obj.toString());
                output.close();
                Toast.makeText(getApplicationContext(), "Rating has been saved", Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
        //By this we can fetch the data from local storage and can send this data to our server
        getButton.setOnClickListener(v -> {
            String jobId = jsonET.getText().toString().trim();
            String a = readFromFile("/storage/emulated/0/Rojgar/" + jobId + ".json");
            getData.setText(a);
        });
    }
    public void createFolder(String fname){
        String myfolder= Environment.getExternalStorageDirectory()+"/"+fname;
        File f=new File(myfolder);
        if(!f.exists())
            if(!f.mkdir()){
                Toast.makeText(this, "There is some error", Toast.LENGTH_SHORT).show();

            }
      }
}