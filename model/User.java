package model;

public  class User {
    String userName;
    int numberOfFollower=0,numberOfFollowing=0;

    public User(String userName, int numberOfFollower, int numberOfFollowing) {
        this.userName = userName;
        this.numberOfFollower = numberOfFollower;
        this.numberOfFollowing = numberOfFollowing;
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

    public void setNumberOfFollower(int numberOfFollower) {
        this.numberOfFollower = numberOfFollower;
    }

    public void setNumberOfFollowing(int numberOfFollowing) {
        this.numberOfFollowing = numberOfFollowing;
    }

}
