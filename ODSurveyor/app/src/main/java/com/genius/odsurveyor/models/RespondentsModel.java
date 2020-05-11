package com.genius.odsurveyor.models;

public class  RespondentsModel {
    private String date;
    private String respondentname;
    private String id;

    public RespondentsModel(String id,String respondentname, String date) {
        this.id = id;
        this.respondentname = respondentname;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RespondentsModel() {
    }

    public String getRespondentname() {
        return respondentname;
    }

    public void setRespondentname(String respondentname) {
        this.respondentname = respondentname;
    }
}
