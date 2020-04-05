package com.example.uomstudentunionelection;

public class StudentDetails {
    String id, name, gender, code, voted;

    public StudentDetails(String id, String name, String gender, String code, String voted) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.code = code;
        this.voted = voted;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getCode() {
        return code;
    }

    public String getVoted() {
        return voted;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setVoted(String voted) {
        this.voted = voted;
    }
}
