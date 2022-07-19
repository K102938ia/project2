package controller;

import model.BusinessAccount;
import model.PersonalAccount;
import model.User;
import veiw.InputProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInController {


    String userName;
    String password;
    String passwordConfirm;
    String securityQuestion;
    String UType;


    ManagerBasic managerBasic;


    public SignInController () {
        this.managerBasic = new ManagerBasic ();
    }

    FollowController followController = new FollowController ();
    InputProcessor inputProcessor = new InputProcessor ();

    public void setUserName ( String userName ) {
        this.userName = userName;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }

    public void setPasswordConfirm ( String passwordConfirm ) {
        this.passwordConfirm = passwordConfirm;
    }

    public void setSecurityQuestion ( String securityQuestion ) {
        this.securityQuestion = securityQuestion;
    }

    public void setUserType ( String UType ) {
        this.UType = UType;
    }

    public String getUserName () {
        return userName;
    }

    boolean isValidUN = false;
    boolean isValidPa = false;
    boolean isValidCoPa = false;
    boolean isValidUT = false;


    int passIsValid = -5;


    public void signInUserName () {
        if ( userName != null ) {
            boolean nameIsValid = checkUsername (userName);


            if ( nameIsValid )
                isValidUN = true;

        }
    }


    public void signInPassW () {
        if ( password != null ) {
            passIsValid = checkPassword (password);


            if ( passIsValid == 1 ) {
                isValidPa = true;

            }

        }
    }

    public void signInPassCo () {
        if ( passwordConfirm != null && password != null )
            if ( passwordConfirm.equals (password) ) {

                isValidCoPa = true;
            }
    }

    public void signInUserType () {
        if ( UType != null ) {
            if ( UType.equalsIgnoreCase ("personal") || UType.equalsIgnoreCase ("business") ) {
                isValidUT = true;
            }
        }
    }


    public void signInFin () {
        if ( userName != null && password != null && securityQuestion != null && UType != null ) {

            // User user1 = new User(userName,0,0);
            if ( this.UType.equalsIgnoreCase ("Business") ) {
                // BusinessAccount businessAccount = (BusinessAccount) user1;
                BusinessAccount businessAccount = new BusinessAccount (userName);
                this.managerBasic.businessUsers.add (businessAccount);
                User user = new BusinessAccount (userName);
                //this.managerBasic.setUsers (user);
                //this.addUser (this.userName, this.password, this.securityQuestion, this.UType);
            } else if ( this.UType.equalsIgnoreCase ("Personal") ) {
                PersonalAccount personalAccount = new PersonalAccount (userName);
                User user = new PersonalAccount (userName);
                user.setUserName (userName);
                user.setPassWord (password);
                user.setPassWord (password);
                user.setSecurityAnswer (securityQuestion);
                //this.managerBasic.setUsers (user);
                ManagerBasic.addUsersObjSignIn(userName,password,securityQuestion,UType);
                //this.addUser (this.userName, this.password, this.securityQuestion, this.UType);
                //PersonalAccount personalAccount = (PersonalAccount) user1;
                // this.managerBasic.personalUsers.add(personalAccount);
            }
            this.addUser (this.userName, this.password, this.securityQuestion, this.UType);
            //managerBasic.setUsers(user1);
            //addUser(userName, password, securityQuestion,UType);
        }


    }


    public boolean invalUserName () {
        return isValidUN;

    }

    public boolean invalPassword () {
        return isValidPa;

    }

    public int invalPasswordSpecify () {
        return passIsValid;

    }

    public boolean invalUserType () {
        return isValidUT;
    }


    public boolean invalidCoPa () {
        return isValidCoPa;
    }


    private boolean checkUsername ( String username ) {
        ArrayList<String> usernames = new ArrayList<> ();
        String name;
        String[] splitName;
        try {
            File usersFile = new File ("usersAndPasswords.txt");
            Scanner readUsers = new Scanner (usersFile);
            while (readUsers.hasNextLine ()) {
                name = readUsers.nextLine ();
                splitName = name.split (":");
                usernames.add (splitName[0]);
            }
            readUsers.close ();
        } catch ( FileNotFoundException e ) {
            e.printStackTrace ();
        }
        if ( usernames.contains (username) ) {

            return false;
        } else {
            return true;
        }
    }


    private int checkPassword ( String password ) {

        boolean isValid = false;
        boolean letter = false;
        boolean num = false;
        /*
        for ( int i = 0; i < password.length (); i++ ) {
            char c = password.charAt (i);
            if ( (int) c > 48 && (int) c < 57 ) {
                num = true;
            } else if ( ((int) c > 65 && (int) c < 90) || ((int) c > 97 && (int) c < 122) ) {
                letter = true;
            }
            if ( letter == true && num == true ) {
                break;
            }
        }

        if ( num == true && letter == true ) {
            isValid = true;
        }

         */
        String regex = "^[A-Za-z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches ()){
            isValid = true;
        }

        if ( password.length () < 8 ) {
            return -1;
        } else if ( !isValid && password.length () >= 8 ) {
            return -2;
        } else if ( isValid && password.length () >= 8 )
            return 1;


        return 0;


    }

    private void addUser ( String userName, String password, String securityQuestion, String UType ) {
        File file = new File ("usersAndPasswords.txt");
        try {
            FileWriter fileWriter = new FileWriter (file, true);
            fileWriter.write (userName + ":" + password + ":" + securityQuestion + ":" + UType + "\n");
            fileWriter.close ();
//            User user = new User (userName,0,0);
//            user.setPassWord (password);
//            user.setType (UType);
//            user.setSecurityAnswer (securityQuestion);
//            ManagerBasic.users.add (user);
            if(UType.equals ("Personal")){
                PersonalAccount personalAccount = new PersonalAccount (userName);
                personalAccount.setType (UType);
                personalAccount.setPassWord (password);
                personalAccount.setSecurityAnswer (securityQuestion);
                ManagerBasic.personalUsers.add (personalAccount);
                User user = (User) personalAccount;
                ManagerBasic.users.add (user);
                File file1 = new File (userName+"Posts.txt");
                file1.createNewFile ();
            }

            else if(UType.equals ("Business")){
                BusinessAccount businessAccount = new BusinessAccount (userName);
                businessAccount.setType (UType);
                businessAccount.setSecurityAnswer (securityQuestion);
                businessAccount.setPassWord (password);
                ManagerBasic.businessUsers.add (businessAccount);
                User user = (User) businessAccount;
                ManagerBasic.users.add (user);
                File file1 = new File (userName+"Posts.txt");
                file1.createNewFile ();
            }

        } catch ( IOException e ) {
            e.printStackTrace ();
        }

        if(UType.equals ("Personal")) {
            File personalFile = new File ("PersonalUsers.txt");
            try {
                FileWriter fileWriter = new FileWriter (personalFile, true);
                fileWriter.write (userName + ":" + password + ":" + securityQuestion + ":" + UType + "\n");
                fileWriter.close ();

            } catch ( IOException e ) {
                e.printStackTrace ();
            }
        }

        else if(UType.equals ("Business")) {
            File businessFile = new File ("BusinessUsers.txt");
            try {
                FileWriter fileWriter = new FileWriter (businessFile, true);
                fileWriter.write (userName + ":" + password + ":" + securityQuestion + ":" + UType + "\n");
                fileWriter.close ();

            } catch ( IOException e ) {
                e.printStackTrace ();
            }
        }

    }

}

