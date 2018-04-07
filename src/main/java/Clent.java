package main.java;

class Client {

    public static Boolean runIt=false;

    public static void main(String[] args) {



        Thread userInputHandlerThread = new Thread(new UserInputHandler(),"userInputHandler");
        userInputHandlerThread.start();



//        new ClientExecutor().publish();
        System.out.println("Enter command to continue");
    }
}
