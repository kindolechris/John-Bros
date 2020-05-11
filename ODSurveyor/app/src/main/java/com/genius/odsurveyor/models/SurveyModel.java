package com.genius.odsurveyor.models;

public class SurveyModel {
    private String id;
    private String surveyname;
    private String date;
    private String questionSet;
    private String coordinate;
    private String station;


    public SurveyModel(String id,String surveyname,String question,String coordinate,String station,String date) {
         this.id = id;
        this.surveyname = surveyname;
        this.questionSet = question;
        this.coordinate = coordinate;
        this.station = station;
        this.date = date;
    }

    public SurveyModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSurveyname() {
        return surveyname;
    }

    public void setSurveyname(String surveyname) {
        this.surveyname = surveyname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(String questionSet) {
        this.questionSet = questionSet;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
