package com.genius.odsurveyor.models;

public class  RespondentsModel {
    private String respondentname;
    private String id;


    public RespondentsModel(String respondentname, String id) {
        this.respondentname = respondentname;
        this.id = id;
    }

    public String getId() {
        return id;
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
