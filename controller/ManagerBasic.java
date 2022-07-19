package controller;

import model.BusinessAccount;
import model.PersonalAccount;
import model.Post;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ManagerBasic {

    static ArrayList < User > users = new ArrayList <> ();
    static ArrayList < BusinessAccount > businessUsers = new ArrayList <> ();
    static ArrayList < PersonalAccount > personalUsers = new ArrayList <> ();
    static ArrayList < Post > allPosts = new ArrayList <> ();

    private static int totalUsers =0;
    private static int totalPersonal =0;
    private static int totalBusiness = 0;


    public void setUsers ( User user ) {
        users.add (user);

    }

    public int getSize () {
        return users.size ();
    }

    public static ArrayList < User > getUsers () {
        return users;
    }

    public static void addUsersObjLogin () {

    }

    public static void addUsersObjSignIn (String userName, String password , String securityQuestion , String uType) {
        //saving the object when its first introduced (serialization)   2
        User user = new User ();
        user.setUserName (userName);
        user.setPassWord (password);
        user.setSecurityAnswer (securityQuestion);
        user.setType (uType);

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream (userName + ".ser");
            ObjectOutputStream out = new ObjectOutputStream (fileOut);
            out.writeObject (user);
            out.close ();
            fileOut.close ();
        } catch ( IOException e ) {
            e.printStackTrace ();
        }

        if(uType.equals ("Personal")){
            PersonalAccount personalAccount = new PersonalAccount (userName);
            personalAccount.setPassWord (password);
            personalAccount.setSecurityAnswer (securityQuestion);
            personalAccount.setType (uType);
            personalUsers.add (personalAccount);

            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream (userName + "Personal.ser");
                ObjectOutputStream out = new ObjectOutputStream (fileOutputStream);
                out.writeObject (user);
                out.close ();
                fileOutputStream.close ();
            } catch ( IOException e ) {
                e.printStackTrace ();
            }

        }

        else if(uType.equals ("Business")){
            BusinessAccount businessAccount = new BusinessAccount (userName);
            businessAccount.setPassWord (password);
            businessAccount.setType (uType);
            businessAccount.setSecurityAnswer (securityQuestion);
            businessUsers.add (businessAccount);

            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream (userName + "Business.ser");
                ObjectOutputStream out = new ObjectOutputStream (fileOutputStream);
                out.writeObject (user);
                out.close ();
                fileOutputStream.close ();
            } catch ( IOException e ) {
                e.printStackTrace ();
            }
        }

        users.add (user);
    }

    public void addUsersObjLoggedIn() {
        //deserialization 1
        HashMap < String, String > usersAndPasswords = new HashMap < String, String > ();

        try {
            File usersFile = new File ("usersAndPasswords.txt");
            Scanner readUsers = new Scanner (usersFile);
            String nameAndPass;
            String[] splitNameAndAnswer;
            while (readUsers.hasNextLine ()) {
                nameAndPass = readUsers.nextLine ();
                splitNameAndAnswer = nameAndPass.split (":");
                usersAndPasswords.put (splitNameAndAnswer[0], splitNameAndAnswer[2]);
                User user = null;
                FileInputStream fileIn = new FileInputStream (splitNameAndAnswer[0] + ".ser");
                ObjectInputStream in = new ObjectInputStream (fileIn);
                user = (User) in.readObject ();
                users.add (user);
                // if(user.getType ().equals ("Personal")){
                //PersonalAccount personalAccount = (PersonalAccount) user;
                //personalUsers.add (personalAccount);
                //personalUsers.add ((PersonalAccount) user);
                //}
                // else if(user.getType ().equals ("Business")){
                //BusinessAccount businessAccount = (BusinessAccount) user;
                //businessUsers.add (businessAccount);
                // }
                in.close ();
                fileIn.close ();
            }
            readUsers.close ();
        } catch ( IOException | ClassNotFoundException e ) {
            e.printStackTrace ();
        }
        /*
        
        try{
        File personals = new File ("PersonalUsers.txt");
        Scanner readPersonals = new Scanner (personals);
        String info;
        String[] splitInfo;
        while (readPersonals.hasNextLine ()) {
            info = readPersonals.nextLine ();
            splitInfo = info.split (":");
            PersonalAccount personalAccount = null;
            FileInputStream fileIn = new FileInputStream (splitInfo[0] + "Personal.ser");
            ObjectInputStream inP = new ObjectInputStream (fileIn);
            personalAccount = (PersonalAccount) inP.readObject ();
            personalUsers.add (personalAccount);
            inP.close ();
            fileIn.close ();
        }
        readPersonals.close ();
    }

        catch (  IOException | ClassNotFoundException e  ){
            e.printStackTrace ();
        }

        try{
            File business = new File ("BusinessUsers.txt");
            Scanner readBusiness = new Scanner (business);
            String info;
            String[] splitInfo;
            while (readBusiness.hasNextLine ()) {
                info = readBusiness.nextLine ();
                splitInfo = info.split (":");
                BusinessAccount businessAccount = null;
                FileInputStream fileIn = new FileInputStream (splitInfo[0] + "Business.ser");
                ObjectInputStream in = new ObjectInputStream (fileIn);
                businessAccount = (BusinessAccount) in.readObject ();
                businessUsers.add (businessAccount);
                in.close ();
                fileIn.close ();
            }
            readBusiness.close ();
        }
        catch (  IOException | ClassNotFoundException e  ){
            e.printStackTrace ();
        }

         */
    }


}
// TODO: 7/19/2022  bis and per files add
