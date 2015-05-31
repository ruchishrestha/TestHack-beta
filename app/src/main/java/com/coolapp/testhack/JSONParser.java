package com.coolapp.testhack;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ruchi on 2015-05-30.
 */
public class JSONParser {

    public JSONParser()
    {
        super();
    }

    @SuppressLint("LongLogTag")
    public ArrayList<LocDescTable> parseLocDesc(JSONObject object)
    {
        ArrayList<LocDescTable> arrayList=new ArrayList<LocDescTable>();
        try {
            JSONArray jsonArray=object.getJSONArray("Value");
            JSONObject jsonObj=null;
            for(int i=0;i<jsonArray.length();i++)
            {
                jsonObj=jsonArray.getJSONObject(i);
                arrayList.add(new LocDescTable(jsonObj.getDouble("Latitude"), jsonObj.getDouble("Longitude")));
            }

        } catch (JSONException e) {
            Log.d("JSONParser => parseLocDesc", e.getMessage());
        }
        return arrayList;
    }
}
