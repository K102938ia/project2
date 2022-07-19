package model;

import java.io.Serializable;
import java.util.ArrayList;

public class BusinessPost extends Post implements Serializable {
    public BusinessPost(String text ) {
        super.text = text;
    }

    ArrayList<Comment> comments = new ArrayList<>();
}
