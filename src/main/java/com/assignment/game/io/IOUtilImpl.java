package com.assignment.game.io;

import com.assignment.game.model.UserInput;

import java.util.Scanner;

public class IOUtilImpl implements IOUtil {

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public int getUserInput() {
        System.out.println("Select any box from 1 to 3");
        int userInput = 0;
        try {
            userInput = Integer.parseInt(scanner.nextLine());
            while (userInput < 1 || userInput > 3) {
                System.out.println("Invalid input. Select any box from 1 to 3");
                userInput = Integer.parseInt(scanner.nextLine());
            }
        } catch (Exception exception) {
            System.out.println("Invalid input. Select any box from 1 to 3");
            getUserInput();
        }
        return userInput;
    }

    @Override
    public boolean getUserInputForSwitchingTheOption() {
        System.out.println("Do you want to switch (Yes/No)? ");
        String userInput = scanner.nextLine();
        boolean validInput = UserInput.isValidInput(userInput);
        while (!validInput) {
            System.out.println("InValid Input. Enter Yes/No? ");
            userInput = scanner.nextLine();
            validInput = UserInput.isValidInput(userInput);
        }
        return UserInput.YES.name().equalsIgnoreCase(userInput);
    }

    @Override
    public boolean getUserInputToPlayMore() {
        System.out.println("Do you Want to play more (yes/no)");
        String userInput = scanner.nextLine();
        return UserInput.YES.name().equalsIgnoreCase(userInput);
    }
}

