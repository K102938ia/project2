package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatController {


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

    public boolean fileExistence(String fileName)
    {
        File file = new File(fileName+".txt");
        if(file.exists() && !file.isDirectory())
            return true;
//        String [] name=fileName.split("\\&");
//        String reverseFileName=name[1]+name[0];

//        File file1 = new File(reverseFileName+".txt");
//        if(file1.exists() && !file1.isDirectory())
//            return true;

return false;
    }

    public void chatFileMaker(String fileName)
    {
        File file = new File(fileName+".txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);

            fileWriter.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void chatHistoryMaker(String fileName, String text, String messageWriter)
    {
        File file = new File(fileName+".txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write( messageWriter+":"+ text+"\t");
            fileWriter.write( "#"+messageWriter+"%"+getMessageID(fileName,messageWriter)+"\n");

            fileWriter.close();

        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }


    public int getMessageID(String fileName,String messageWriter) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader(fileName+".txt"));

        if (br.readLine()==null)
        {
            return 1;
        }

        else
        {


            try {
                File usersFile = new File(fileName+".txt");
                String line;

                String [] ID=new String[2];
                Scanner readUsers = new Scanner(usersFile);
                while (readUsers.hasNextLine()) {

                    line = readUsers.nextLine();
                    if(line.startsWith(messageWriter))
                    {
                        ID=line.split("\\#");
                    }


                }


                readUsers.close();

                if (ID[1]==null)
                {
                    return 1;
                }
                else
                    return Integer.parseInt(ID[1].split("\\%")[1])+1;

            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
return 1;
    }


    public String findMessage (String fileName,String contact,String messageID)
    {

        try {
            File usersFile = new File(fileName+".txt");
            String line;

            String [] ID=new String[2];
            Scanner readUsers = new Scanner(usersFile);

            StringBuilder partOfMessage=new StringBuilder();
            while (readUsers.hasNextLine()) {

                line = readUsers.nextLine();

                ID=line.split("\\#");


                String tempText=ID[0].split("\\:")[1];
                boolean replied=false;

                if (tempText.startsWith("REPLY"))
                {
                    replied=true;

                }

                String tempText1;
                if (replied)
                {
                    tempText1=tempText.split("\\)")[1];
                }
                else
                {
                    tempText1=ID[0].split("\\:")[1];

                }

                //counting num of spaces
                int spaceCounter=0;
                for (int i = 0; i < tempText1.length(); i++) {
                    if (tempText1.charAt(i)==' ')
                    {
                        spaceCounter++;
                    }
                }
//                spaceCounter-=8;




                if(line.startsWith(contact))
                {


                    if (messageID.equals(ID[1]))
                    {
if (spaceCounter>=3)
{
    if(!replied)

    for (int i = 0; i <3 ; i++) {
        partOfMessage.append(ID[0].split("\\:")[1].split("\\s") [i]+" ");
    }


    else
    {

        for (int i = 0; i <3 ; i++) {
            partOfMessage.append(tempText1.split("\\s") [i]+" ");
        }

    }
}
else if(spaceCounter<3)
{
    if (spaceCounter==0)
        spaceCounter=1;

    if(!replied)
    for (int i = 0; i <spaceCounter ; i++) {
        partOfMessage.append(ID[0].split("\\:")[1].split("\\s") [i]+" ");
    }
    else
    {
        for (int i = 0; i <spaceCounter ; i++) {
            partOfMessage.append(tempText1.split("\\s") [i]+" ");
        }
    }
}

                  return partOfMessage.toString();
                    }
                }


            }


            readUsers.close();

//            if(partOfMessage.toString()==null)



        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }



}
