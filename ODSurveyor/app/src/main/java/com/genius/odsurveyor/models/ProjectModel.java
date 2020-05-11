package com.genius.odsurveyor.models;

public class ProjectModel {
    private String id;
    private String projectName;
    private String  progress;
    private String date;
    private String division;
    private String country;
    private String state;
    private String district;
    private String ward;
    private String questionSet;
    private String clientName;
    private String address1;
    private String address2;

    public ProjectModel(String id, String projectName, String progress, String date, String division, String country, String state, String district, String ward,String clientName,String address1,String address2) {
        this.id = id;
        this.projectName = projectName;
        this.progress = progress;
        this.date = date;
        this.division = division;
        this.country = country;
        this.state = state;
        this.district = district;
        this.ward = ward;
        this.clientName =clientName;
        this.address1 =address1;
        this.address2 =address2;
    }

    public ProjectModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(String questionSet) {
        this.questionSet = questionSet;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
}
