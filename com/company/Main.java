package com.company;
import controller.LoginController;
import controller.SignInController;
import veiw.InputProcessor;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        InputProcessor inputProcessor = new InputProcessor();
        inputProcessor.start();

    }
}
