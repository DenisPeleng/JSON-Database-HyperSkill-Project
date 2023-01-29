package server;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String[] database = new String[100];
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String fullLine = scanner.nextLine();
            String[] commandArr = fullLine.split(" ");
            try {
                String command = commandArr[0].toLowerCase();
                if (command.equals("exit")) {
                    System.exit(0);
                }
                int numberArr = Integer.parseInt(commandArr[1]) - 1;
                if (numberArr < 0 || numberArr > database.length) {
                    throw new IllegalArgumentException();
                }
                switch (command) {
                    case "set":
                        String text = fullLine.replace(command + " ", "").replace(numberArr + " ", "");
                        database[numberArr] = text;
                        System.out.println("OK");
                        break;
                    case "get":
                        if (database[numberArr] != null) {
                            System.out.println(database[numberArr]);
                        } else {
                            System.out.println("ERROR");
                        }
                        break;
                    case "delete":
                        database[numberArr] = null;
                        System.out.println("OK");
                        break;
                }

            } catch (Exception exception) {
                System.out.println("ERROR");
            }
        }
    }
}
