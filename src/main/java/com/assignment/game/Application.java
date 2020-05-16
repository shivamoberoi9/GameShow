package com.assignment.game;

import com.assignment.game.io.IOUtilImpl;
import com.assignment.game.runner.Runner;
import com.assignment.game.service.GameServiceImpl;

public class Application {
    public static void main(String[] args) {
        new Runner(new GameServiceImpl(new IOUtilImpl()))
                .run();
    }
}
