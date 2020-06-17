package com.example.retrofit_get;

import android.content.res.Resources;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SaveJson extends JSONObject {

    public JSONObject makeJSONObject (String userId, String jobID, String rating) {

        JSONObject obj = new JSONObject();

        try {
            obj.put("userID", userId);
            obj.put("jobID", jobID);
            obj.put("rating", rating);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
