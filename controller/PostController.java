package controller;

import model.*;

import java.io.*;

public class PostController {

    public boolean addedPost = false;

    public void addPost(String userName,String post){
        for (User user : ManagerBasic.users) {
            //System.out.println (user.getUserName ());
            if(user.getUserName().equals(user.getUserName())){
                //Class c = user.getClass();
                if(user.getType ().equals ("Business")){
                    String confPost = "$(Ad):  " + post;
                    for (BusinessAccount businessUser : ManagerBasic.businessUsers) {
                        if(businessUser.getUserName().equals(userName)) {
                            BusinessPost businessPost = new BusinessPost(confPost);
                            user.posts.add(businessPost);
                            Post post1 = new Post (confPost);
                            ManagerBasic.allPosts.add (post1);
                            File file = new File (userName + "Posts.txt");
                            FileWriter fileWriter = null;
                            try {
                                fileWriter = new FileWriter (file, true);
                                fileWriter.write(confPost + "\n");
                                fileWriter.close();
                            } catch ( IOException e ) {
                                e.printStackTrace ();
                            }

                            FileOutputStream fileOutputStream = null;
                            try {
                                fileOutputStream = new FileOutputStream (post + "Post.ser");
                                ObjectOutputStream out = new ObjectOutputStream (fileOutputStream);
                                out.writeObject (businessPost);
                                out.close ();
                                fileOutputStream.close ();
                            } catch ( IOException e ) {
                                e.printStackTrace ();
                            }

                            addedPost = true;
                            break;
                        }
                    }
                }
                else if(user.getType ().equals ("Personal")){
                    for (PersonalAccount personalUser : ManagerBasic.personalUsers) {
                        if(personalUser.getUserName().equals(userName)){
                            PersonalPost personalPost = new PersonalPost(post);
                            personalUser.posts.add(personalPost);
                            Post post2 = new Post (post);
                            ManagerBasic.allPosts.add (post2);
                            FileWriter fileWriter = null;
                            File file = new File (userName + "Posts.txt");
                            try {
                                fileWriter = new FileWriter (file, true);
                                fileWriter.write(post + "\n");
                                fileWriter.close();
                            } catch ( IOException e ) {
                                e.printStackTrace ();
                            }

                            FileOutputStream fileOutputStream = null;
                            try {
                                fileOutputStream = new FileOutputStream (post + "Post.ser");
                                ObjectOutputStream out = new ObjectOutputStream (fileOutputStream);
                                out.writeObject (personalPost);
                                out.close ();
                                fileOutputStream.close ();
                            } catch ( IOException e ) {
                                e.printStackTrace ();
                            }

                            addedPost = true;
                            break;
                        }
                    }
                }
                break;
            }
        }
    }
}
