package com.revature.beans;

import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String password;
    private String profilePicFilePath;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicFilePath() {
        return profilePicFilePath;
    }

    public void setProfilePicFilePath(String profilePicFilePath) {
        this.profilePicFilePath = profilePicFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(profilePicFilePath, user.profilePicFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, profilePicFilePath);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profilePicFilePath='" + profilePicFilePath + '\'' +
                '}';
    }
}
