package com.example.notesdata_;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Note {

    private static final String JSON_NAME = "name";
    private static final String JSON_DATE = "date";
    private static final String JSON_DESC = "desc";

    private String name;
    private String date;
    private String desc;
    // public Note() {

    //   date = new Date().toString(); }


    public Note (JSONObject jsonObject)
            throws JSONException{
        name = jsonObject.getString(JSON_NAME);
        date = jsonObject.getString(JSON_DATE);
        desc = jsonObject.getString(JSON_DESC);

    }


    public JSONObject toJSON()
            throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_NAME, name);
        jsonObject.put(JSON_DATE, date);
        jsonObject.put(JSON_DESC, desc);
        return jsonObject;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }




    public Note() {
        // Default to today's date
        date = new Date().toString();
    }
}
