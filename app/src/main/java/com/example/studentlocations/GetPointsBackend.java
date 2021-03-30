package com.example.studentlocations;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetPointsBackend extends AsyncTask<Void, Child, Void> {
    Context context;
    Activity activity;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    StudentListAdapter studentListAdapter;

    public static ArrayList<Child> childArrayList = new ArrayList<>();
    String link = "http://10.0.2.2/locationexample/getChildren.php";

    public GetPointsBackend(Context context) {
        this.context = context;
        this.activity = (Activity)context;
    }

    @Override
    protected void onPreExecute() {
        recyclerView = (RecyclerView)activity.findViewById(R.id.recyclerView2);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        studentListAdapter = new StudentListAdapter(childArrayList);
        recyclerView.setAdapter(studentListAdapter);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL(link);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line+"\n");
            }
            httpURLConnection.disconnect();
            String json_string =  stringBuilder.toString().trim();
            JSONObject jsonObject = new JSONObject(json_string);
            JSONArray jsonArray = jsonObject.getJSONArray("Server_response");
            Log.d("JSON_RE", jsonArray.toString());
            int count =0;
            while(count<jsonArray.length()){
                JSONObject jo =jsonArray.getJSONObject(count);
                Log.d("obj", jo.toString());
                count++;
                //Child child = new Child(jo.getInt("childId"),jo.getString("fname"),jo.getString("lname"));
                int id = jo.getInt("childId");
                double lat = jo.getDouble("lat");
                double lon = jo.getDouble("lon");

                Child child = new Child(lat, lon, id);
                publishProgress(child);
                MainActivity.childList.add(child);
                }
            } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Child... values) {
        childArrayList.add(values[0]);
        Log.d("child is ", childArrayList.toString());
        studentListAdapter.notifyDataSetChanged();

    }
}
