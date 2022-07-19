package controller;

import model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LoginController {

    ManagerBasic managerBasic;


    public LoginController() {
        this.managerBasic =new ManagerBasic();
    }

    String userName;
    String password;
    String securityAnswer;

    int errorType=-5;

    ArrayList<User> users=new ArrayList<>();


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getUserName() {
        return userName;
    }

    public void loginNF() throws FileNotFoundException {

            if (userName != null && password != null) {
                errorType = checkUser(userName, password);


            }



    }

    public void loginF() throws FileNotFoundException {
        if (userName != null && securityAnswer != null)
        {


            errorType=checkAnswer(userName, securityAnswer);

        }

    }


    public int loginErrorType()
    {
        return errorType;

    }


    private int checkUser(String userName, String password) throws FileNotFoundException {
        HashMap<String, String> usersAndPasswords = new HashMap<String, String>();
        try {
            File usersFile = new File("usersAndPasswords.txt");
            Scanner readUsers = new Scanner(usersFile);
            String nameAndPass;
            String[] splitNameAndPass;
            while (readUsers.hasNextLine()) {
                nameAndPass = readUsers.nextLine();
                splitNameAndPass = nameAndPass.split(":");
                usersAndPasswords.put(splitNameAndPass[0], splitNameAndPass[1]);
            }
           readUsers.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
            if(usersAndPasswords.containsKey(userName) && usersAndPasswords.get(userName).equals(password)){

                return 1;
            }
            else if(!usersAndPasswords.containsKey(userName)){

                return -1;
            }
            else if(usersAndPasswords.containsKey(userName) && !usersAndPasswords.get(userName).equals(password)){

                return -2;
            }

            return 0;
    }


    private int checkAnswer(String username, String answer){
        HashMap<String, String> usersAndAnswers = new HashMap<String, String>();
        try {
            File usersFile = new File("usersAndPasswords.txt");
            Scanner readUsers = new Scanner(usersFile);
            String nameAndPass;
            String[] splitNameAndAnswer;
            while (readUsers.hasNextLine()) {
                nameAndPass = readUsers.nextLine();
                splitNameAndAnswer = nameAndPass.split(":");
                usersAndAnswers.put(splitNameAndAnswer[0], splitNameAndAnswer[2]);
            }
            readUsers.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        if(usersAndAnswers.containsKey(username) && usersAndAnswers.get(username).equals(answer)){
            return 1;

        }
        else if(!usersAndAnswers.containsKey(username)){

            return -1;

        }
        else if(usersAndAnswers.containsKey(username) && !usersAndAnswers.get(username).equals(answer))
        {
            return -3;

        }
      return 0;
    }


}
