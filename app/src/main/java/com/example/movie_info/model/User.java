/**
 * Created with IntelliJ IDEA.
 * User: Georges Amewe KASSI
 * Date: 22/03/2019
 * Time: 10:49
 */
package com.example.movie_info.model;

public class User {

    private String mFirstname;

    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }

    @Override
    public String toString() {
        return "User{" +
                "mFirstname='" + mFirstname + '\'' +
                '}';
    }
}
