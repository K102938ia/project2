package controller;

import model.User;

import java.util.ArrayList;

public class ManagerBasic {

   private static ArrayList<User> users=new ArrayList<>();




    public void setUsers(User user) {
       users .add(user);

    }
public  int getSize()
{
    return users.size();
}

    public ArrayList<User> getUsers() {
        return users;
    }

}
