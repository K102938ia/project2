package model;

import java.io.Serializable;
import java.util.ArrayList;

public class BusinessAccount extends User implements Serializable {

    public BusinessAccount(String userName) {
        super(userName);
    }
    public ArrayList<BusinessPost> posts = new ArrayList<>();

}
