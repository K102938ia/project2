package controller;

import model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FollowController {


    ManagerBasic managerBasic=new ManagerBasic();


    public void followProcess(String followerPerson ,String followedPerson)
    {

        File following = new File(followerPerson+"following.txt");
        File follower = new File(followedPerson+"follower.txt");

        try {
            User user=null;

            for (int i = 0; i < managerBasic.getSize(); i++) {
                if (managerBasic.getUsers().get(i).getUserName().equals(followerPerson)) {
                    user = managerBasic.getUsers().get(i);
                    break;
                }
            }
            if (user!=null) {

                user.setNumberOfFollowing(user.getNumberOfFollowing() + 1);
            }
            FileWriter fileWriter = new FileWriter(following, true);
            fileWriter.write( followedPerson+"\n");
            fileWriter.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {

            User user = null;

            for (int i = 0; i < managerBasic.getSize(); i++) {
                if (managerBasic.getUsers().get(i).getUserName().equals(followedPerson)) {
                    user = managerBasic.getUsers().get(i);
                    break;
                }
            }
            if (user!=null)
            {
                user.setNumberOfFollower(user.getNumberOfFollower() + 1);
            }



            FileWriter fileWriter = new FileWriter(follower, true);
            fileWriter.write(followerPerson + "\n");
            fileWriter.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }



    }

    public boolean checkUser(String userName)
    {
        ArrayList<String> tempNameSaving=new ArrayList<>();
        try {
            File usersFile = new File("usersAndPasswords.txt");
            Scanner readUsers = new Scanner(usersFile);
            String name;

            while (readUsers.hasNextLine()) {
                name = readUsers.nextLine();
                tempNameSaving.add(name.split("\\:")[0]);
            }
            readUsers.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }


        if (tempNameSaving.contains(userName))
            return true;

        return false;
    }

    public boolean followedBefore(String followerPerson ,String followedPerson)
    {
        User user=null;

        for (int i = 0; i < managerBasic.getSize(); i++) {
            if (managerBasic.getUsers().get(i).getUserName().equals(followerPerson))
            {
                user=managerBasic.getUsers().get(i);
                break;
            }

        }
        if (user!=null)
        if (user.getNumberOfFollowing()>0)
        {
            ArrayList<String> tempNameSaving=new ArrayList<>();
            try {
                File following = new File(followerPerson+"following.txt");
                Scanner readUsers = new Scanner(following);
                String name;
                while (readUsers.hasNextLine()) {
                    name = readUsers.nextLine();
                    tempNameSaving.add(name);
                }
                readUsers.close();
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
            if (tempNameSaving.contains(followedPerson))
                return true;



        }
        return false;

        }


}

