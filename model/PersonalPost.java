package model;

import java.io.Serializable;
import java.util.ArrayList;

public class PersonalPost extends Post implements Serializable {
    public PersonalPost( String text ) {
        super.text = text;
    }

    ArrayList<Comment> comments = new ArrayList<>();
}
