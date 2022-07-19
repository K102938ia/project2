package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class PersonalAccount extends User implements Serializable {
    public ArrayList<PersonalPost> posts = new ArrayList<>();

    public PersonalAccount(String userName) {
        super(userName);
    }
}
