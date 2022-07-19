package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Post implements Serializable {
    Post(){

    }
  public Post(String text){
        this.text = text;
    }

    ArrayList<Comment> comments = new ArrayList<> ();
    ArrayList<Like> likes = new ArrayList<> ();
    int totalLikes = 0;

    String text;
}
