package com.example.sachinmalik.sports.utils;

/**
 * Created by sachinmalik on 20/05/17.
 */


public class Modal1 {

    private boolean islive;

    private String team1;
    private String team1Score;
    private String team1Over;

    private String team1Logo;

    private String team2;
    private String team2Score;
    private String team2Over;

    private String team2Logo;


    public Modal1(boolean islive, String team1, String team1Score, String team1Over, String team1Logo, String team2, String team2Score, String team2Over, String team2Logo) {
        this.islive = islive;
        this.team1 = team1;
        this.team1Score = team1Score;
        this.team1Over = team1Over;
        this.team1Logo = team1Logo;
        this.team2 = team2;
        this.team2Score = team2Score;
        this.team2Over = team2Over;
        this.team2Logo = team2Logo;
    }

    public boolean getIslive() {
        return islive;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam1Score() {
        return team1Score;
    }

    public String getTeam1Over() {
        return team1Over;
    }

    public String getTeam1Logo() {
        return team1Logo;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTeam2Score() {
        return team2Score;
    }

    public String getTeam2Over() {
        return team2Over;
    }

    public String getTeam2Logo() {
        return team2Logo;
    }
}