package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    String userName;
    int numberOfFollower=0,numberOfFollowing=0;
    String type;
    String passWord;
    String securityAnswer;
    public ArrayList<Post> posts = new ArrayList<> ();

    public User(String userName, int numberOfFollower, int numberOfFollowing) {
        this.userName = userName;
        this.numberOfFollower = numberOfFollower;
        this.numberOfFollowing = numberOfFollowing;
    }

    public User(){

    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getNumberOfFollower() {
        return numberOfFollower;
    }

    public int getNumberOfFollowing() {
        return numberOfFollowing;
    }

    public String getType () {
        return type;
    }

    public void setNumberOfFollower( int numberOfFollower) {
        this.numberOfFollower = numberOfFollower;
    }

    public void setNumberOfFollowing(int numberOfFollowing) {
        this.numberOfFollowing = numberOfFollowing;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public void setPassWord( String passWord ) {
        this.passWord = passWord;
    }

    public void setSecurityAnswer ( String securityAnswer ) {
        this.securityAnswer = securityAnswer;
    }
}
