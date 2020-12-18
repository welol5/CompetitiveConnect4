package com.revature.beans;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table()
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String username;
    @Column(name="pass")
    private String password;
    @Column(name = "profile_pic_path")
    private String profilePicFilePath;
    @Column(name = "skill_ranking")
    private Integer rank;



    public Person() {
        id = 0;
        username = "";
        password = "";
        profilePicFilePath = "";
        rank = 0;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicFilePath() {
        return profilePicFilePath;
    }

    public void setProfilePicFilePath(String profilePicFilePath) {
        this.profilePicFilePath = profilePicFilePath;
    }
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profilePicFilePath='" + profilePicFilePath + '\'' +
                ", rank=" + rank +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(username, person.username) && Objects.equals(password, person.password) && Objects.equals(profilePicFilePath, person.profilePicFilePath) && Objects.equals(rank, person.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, profilePicFilePath, rank);
    }
}
