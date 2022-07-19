package veiw;

import controller.ChatController;
import controller.FollowController;
import controller.LoginController;
import controller.SignInController;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputProcessor {

    FollowController followController=new FollowController();

    ChatController chatController =new ChatController();


    public void start() throws FileNotFoundException {


        boolean outOfProgram =true,inAccount=false;

        while (true) {


            if(outOfProgram)
            {
                System.out.println("Login or Signin ? ");

            }


            String chooseMode;
            Scanner scanner = new Scanner(System.in);
            chooseMode = scanner.nextLine();


            if (chooseMode.equalsIgnoreCase("LogIn")) {

                outOfProgram=false;
                LoginController loginController = new LoginController();





                while (loginController.loginErrorType()!=1) {

                    if (loginController.loginErrorType() == -1) {
                        System.err.println("Username not found . Try again");


                        System.out.println(" type Go back or press enter to try again ");
                        String temp3 = scanner.nextLine();
                        if (temp3.equalsIgnoreCase("go back")) {
                            outOfProgram = true;
                            break;

                        }


                }
                else if(loginController.loginErrorType()==-2)
                {
                    System.err.println("Wrong Password . Try again");

                    System.out.println(" type Go back or press enter to try again ");
                    String temp3 = scanner.nextLine();
                    if (temp3.equalsIgnoreCase("go back")) {
                        outOfProgram = true;
                        break;

                    }
                }
                else if(loginController.loginErrorType()==-3)
                {
                    System.err.println("Wrong Answer.try again");

                    System.out.println(" type Go back or press enter to try again ");
                    String temp3 = scanner.nextLine();
                    if (temp3.equalsIgnoreCase("go back")) {
                        outOfProgram = true;
                        break;

                    }
                }






                    System.out.println("Enter your userName:");

                    String temp=scanner.nextLine();
                    loginController.setUserName(temp);


                    System.out.println("Enter your password or type password forgotten:");

                    String temp2 =scanner.nextLine();
                    if (!temp2.equals("password forgotten"))
                    {
                        loginController.setPassword(temp2);
                        loginController.loginNF();
                    }

                    else
                    {
                        System.out.println("What was your favorite elementary school teacher’s name?");
                        String temp3 =scanner.nextLine();
                        loginController.setSecurityAnswer(temp3);
                        loginController.loginF();
                    }


                }

                if(loginController.loginErrorType()==1)
                {
                    System.out.println("Welcome Back");

                    System.out.println(" type Continue or Go back ");
                    String temp3 =scanner.nextLine();
                    if(temp3.equalsIgnoreCase("go back"))
                    {
                        outOfProgram =true;
                    }

                    else if(temp3.equalsIgnoreCase("continue"))
                    {
                        inAccount=true;
                    }

                    int showOnce=0;
                    String currentAccount =loginController.getUserName();
                    while (inAccount)
                    {

                        if (showOnce==0)
                        {
                            System.out.println("You are in account with user name : " +currentAccount);
                            System.out.println("type your commend");

                        }
                        showOnce++;

                        String commend=scanner .nextLine();

                        if (commend.equalsIgnoreCase("logOut"))
                        {
                            outOfProgram=true;
                            inAccount=false;
                            break;
                        }
                        else if (commend.equalsIgnoreCase("continue") )
                        {
                            System.out.println("type your commend");
                        }

                        boolean exist=false;
                        if (commend.split("\\s").length>1) {
                            if (commend.startsWith("follow"))
                            {
                                exist = followController.checkUser(commend.split("\\s")[1]);


                                if (exist) {
                                    boolean repetitive = followController.followedBefore(currentAccount, commend.split("\\s")[1]);

                                    if (!repetitive) {
                                        {
                                            followController.followProcess(currentAccount, commend.split("\\s")[1]);

                                            System.out.println("you followed " + commend.split("\\s")[1]);

                                            System.out.println("type logOut or continue");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("you had followed this user before !");
                                        System.out.println("type logOut or continue");
                                    }



                                }


                                if (!exist) {
                                    System.out.println("user you want to follow doesn't exist");
                                    System.out.println("type logOut or continue");

                                }

                            }

                            else if (commend.startsWith("chat with"))
                            {
                                boolean chatting=false;
                                exist = chatController.checkUser(commend.split("\\s")[2]);
                                if (exist)
                                {
                                    chatting=true;

                                    System.out.println("you are chatting with "+ commend.split("\\s")[2]);


                                    String fileName=currentAccount+"&"+commend.split("\\s")[2];
                                    String [] name=fileName.split("\\&");
                                    String reverseFileName=name[1]+"&"+name[0];

                                    String contact=commend.split("\\s")[2];


                                    boolean chattedBefore=false;
                                    // RorN is an int to check if reverse version of file name is used or not

                                    int RorN=1;
                                    if(chatController.fileExistence(fileName))
                                    {

                                        chattedBefore=true;


                                    }
                                    if(chatController.fileExistence(reverseFileName))
                                    {

                                        chattedBefore=true;
                                        RorN=2;

                                    }


                                    if(!chattedBefore)
                                    {
                                        chatController.chatFileMaker(currentAccount+"&"+commend.split("\\s")[2]);

                                    }
                                    //show history

                                    else
                                    {


                                    }






                                    while (chatting)
                                    {
                                        System.out.print(currentAccount+" : ");
                                        String text=scanner.nextLine();





                                        if (text.equals("EXIT"))
                                        {
                                            System.out.println("chat ended ");
                                            System.out.println("type your commend");
                                            chatting= false;
                                        }

                                        else if( text.startsWith("REPLY TO"))
                                        {
                                            String messageID=text.split("\\s")[2];
                                            String partOfMessage=null;

                                            if (RorN==1)
                                            {
                                                partOfMessage= chatController.findMessage(fileName,contact,messageID);

                                                if (partOfMessage==null)
                                                    System.out.println("there is no message with this ID");

                                                else
                                                {
                                                   StringBuilder updatedText =new StringBuilder();

                                                   updatedText.append(text);
                                                   updatedText.replace(9,messageID.length()+10, "( "+partOfMessage+" )");



                                                    chatController.chatHistoryMaker(fileName,updatedText.toString(),currentAccount);


                                                }

                                            }
                                            else if (RorN==2)

                                            {
                                                partOfMessage=chatController.findMessage(reverseFileName,contact,messageID);

                                                if (partOfMessage==null)
                                                    System.out.println("there is no message with this ID");
                                                else
                                                {
                                                    StringBuilder updatedText =new StringBuilder();

                                                    updatedText.append(text);
                                                    updatedText.replace(9,messageID.length()+10, "( "+partOfMessage+" )");

                                                    chatController.chatHistoryMaker(reverseFileName,updatedText.toString(),currentAccount);
                                                }


                                            }







                                        }

                                        else
                                        {
                                            if (RorN==1)
                                                chatController.chatHistoryMaker(fileName,text,currentAccount);
                                            else if (RorN==2)
                                                chatController.chatHistoryMaker(reverseFileName,text,currentAccount);

                                        }


                                    }


                                }
                                else
                                {
                                    System.out.println("user you want to chat with doesn't exist");
                                    System.out.println("type logOut or continue");
                                }

                            }

                        }


                    }

                }




            }

            else if (chooseMode.equalsIgnoreCase("SignIn")) {

                outOfProgram =false;
                SignInController signInController = new SignInController();



                //getting username process
                System.out.println("Enter a username:");

                int counter1=0;
                signInController.signInUserName();
                while (!signInController.invalUserName())
                {

                    if(counter1!=0)
                    {
                        System.err.println("Sorry, this username is already taken. Please try again:");
                    }
                    String temp=scanner.nextLine();
                    signInController.setUserName(temp);


                    counter1++;
                    signInController.signInUserName();
                }

                //getting password process
                System.out.println("Enter a password:");

                int counter2=0;
                signInController.signInPassW();
                while (!signInController.invalPassword())
                {
                    if(signInController.invalPasswordSpecify()==-1)
                        System.err.println("Your password must contain at least 8 characters. Please try again:");
                    else if (signInController.invalPasswordSpecify()==-2)
                        System.err.println("Your password must contain both letters and numbers. Please try again:");

                    String temp=scanner.nextLine();
                    signInController.setPassword(temp);
                    counter2++;
                    signInController.signInPassW();
                }


                System.out.println("Confirm password:");

                int counter=0;
                signInController.signInPassCo();
                while (!signInController.invalidCoPa())
                {
                    if(counter!=0)
                        System.err.println("Wrong! Try again:");
                    String temp=scanner.nextLine();
                    signInController.setPasswordConfirm(temp);
                    counter++;
                    signInController.signInPassCo();
                }

                System.out.println("SignIn successful");


                System.out.println("Please answer the security question below, it will be used in case you forget your password.");
                System.out.println("What was your favorite elementary school teacher’s name?");

                String temp=scanner.nextLine();
                signInController.setSecurityQuestion(temp);

                //choosing user type

                System.out.println("Enter User type of  your account : Personal or Business");

                int counterT=0;
                signInController.signInUserType();
                while (!signInController.invalUserType())
                {
//                    if(signInController.invalUserType())
                    if(counterT!=0)
                    {
                        System.err.println(" invalid input try again");
                    }
                    String temp4=scanner.nextLine();
                    signInController.setUserType(temp4);

                    counterT++;
                    signInController.signInUserType();

                }



                signInController.signInFin();
                System.out.println("All saved");



                    System.out.println(" type Continue or Go back ");
                    String temp3 =scanner.nextLine();
                    if(temp3.equalsIgnoreCase("go back"))
                    {
                        outOfProgram =true;


                    }

                    else if(temp3.equalsIgnoreCase("continue"))
                    {
                        inAccount=true;
                    }

                    int showOnce=0;
                    String currentAccount =signInController.getUserName();
                    while (inAccount)
                    {

                        if (showOnce==0)
                        {
                            System.out.println("You are in account with user name : " +currentAccount);
                            System.out.println("type your commend");

                        }
                        showOnce++;

                        String commend=scanner .nextLine();

                        if (commend.equalsIgnoreCase("logOut"))
                        {
                            outOfProgram=true;
                            inAccount=false;
                            break;
                        }
                        else if (commend.equalsIgnoreCase("continue") )
                        {
                            System.out.println("type your commend");
                        }

                        boolean exist=false;
                        if (commend.split("\\s").length>1) {
                            if (commend.startsWith("follow"))
                        {
                                exist = followController.checkUser(commend.split("\\s")[1]);


                                if (exist) {
                                    boolean repetitive = followController.followedBefore(currentAccount, commend.split("\\s")[1]);

                                    if (!repetitive) {
                                      {
                                            followController.followProcess(currentAccount, commend.split("\\s")[1]);

                                            System.out.println("you followed " + commend.split("\\s")[1]);

                                            System.out.println("type logOut or continue");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("you had followed this user before !");
                                        System.out.println("type logOut or continue");
                                    }



                                }

                                if (!exist) {
                                    System.out.println("user you want to follow doesn't exist");
                                    System.out.println("type logOut or continue");

                                }
                            }
                            else if (commend.startsWith("chat with"))
                            {
                                boolean chatting=false;
                                exist = chatController.checkUser(commend.split("\\s")[2]);
                                if (exist)
                                {
                                    chatting=true;

                                    System.out.println("you are chatting with "+ commend.split("\\s")[2]);


                                    String fileName=currentAccount+"&"+commend.split("\\s")[2];
                                    String [] name=fileName.split("\\&");
                                    String reverseFileName=name[1]+name[0];


                                    boolean chattedBefore=false;
                                    // RorN is an int to check if reverse version of file name is used or not
                                    int RorN=1;
                                   if(chatController.fileExistence(fileName))
                                   {

                                       chattedBefore=true;


                                   }
                                    if(chatController.fileExistence(reverseFileName))
                                    {

                                        chattedBefore=true;
                                        RorN=2;

                                    }


                                    if(!chattedBefore)
                                    {
                                        chatController.chatFileMaker(currentAccount+"&"+commend.split("\\s")[2]);

                                    }
                                    //show history

                                    else
                                    {


                                    }






                                    while (chatting)
                                    {
                                        System.out.print(currentAccount+" : ");
                                        String text=scanner.nextLine();





                                        if (text.equals("EXIT"))
                                        {
                                            System.out.println("chat ended ");
                                            System.out.println("type your commend");
                                            chatting= false;
                                        }

                                        else
                                        {
                                            if (RorN==1)
                                            chatController.chatHistoryMaker(fileName,text,currentAccount);
                                            else if (RorN==2)
                                                chatController.chatHistoryMaker(reverseFileName,text,currentAccount);

                                        }


                                    }


                                }
                                else
                                {
                                    System.out.println("user you want to chat with doesn't exist");
                                    System.out.println("type logOut or continue");
                                }

                            }

                        }


                    }


            }

            else if(chooseMode.equalsIgnoreCase("exit")){
                break;
            }
            else{
                outOfProgram =true;
                System.err.println("Invalid Command");
            }
        }
    }
}
