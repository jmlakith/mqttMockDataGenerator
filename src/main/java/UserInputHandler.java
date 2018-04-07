package main.java;

import java.util.Scanner;

public class UserInputHandler implements InputHandler {

    private String userCmd;
    private Thread clientExecutorThread;
    private String payload;

    public void getInput() {

        Scanner scanner = new Scanner(System.in);
        userCmd = scanner.nextLine();

    }


    @Override
    public void run() {

        do {

            getInput();
            System.out.println("Im in user input thread == val -> " + userCmd);

            if ("payload".equalsIgnoreCase(userCmd)) {

                getInput();
                System.out.println("Payload -> " + userCmd);
                payload=userCmd;


            } else if ("start".equalsIgnoreCase(userCmd)) {

                clientExecutorThread = new Thread(new ClientExecutor(payload), "clientExecutor");
                Client.runIt = true;
                clientExecutorThread.start();


            } else if ("pause".equalsIgnoreCase(userCmd)) {

                Client.runIt = false;

            } else if ("exit".equalsIgnoreCase(userCmd)) {

                System.exit(0);

            }


        } while (!userCmd.equals("die"));


    }
}
