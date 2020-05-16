package com.assignment.game.model;

public enum UserInput {
    YES,
    NO;

    public static boolean isValidInput(String input) {
        for (UserInput userInput : UserInput.values()) {
            if (userInput.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}
