package com.example.hsnoble.assignment3;

/**
 * Created by Stephen Noble on 3/26/2018.
 */

public class Team
{
    private String authorName;
    private String teamName;
    private String city;
    private String sport;
    private String mvp;
    private String stadium;

    public Team(String person, String name, String inCity, String inSport, String inMvp, String place)
    {
        authorName = person;
        teamName = name;
        city = inCity;
        sport = inSport;
        mvp =  inMvp;
        stadium = place;
    }
    public Team()
    {
        authorName = "";
        teamName = "";
        city = "";
        sport = "";
        mvp =  "";
        stadium = "";
    }
    //SETTER
    public void setAuthorName(String name)
    {
        this.authorName = name;
    }
    public void setTeamName(String name)
    {
        this.teamName = name;
    }
    public void setCity (String loc)
    {
        this.city = loc;
    }

    public void setMvp(String mvp) {
        this.mvp = mvp;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }
    //GETTERS

    public String getAuthorName() {
        return authorName;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getCity() {
        return city;
    }

    public String getSport() {
        return sport;
    }

    public String getMvp() {
        return mvp;
    }

    public String getStadium() {
        return stadium;
    }
}
