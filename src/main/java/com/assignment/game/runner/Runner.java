package com.assignment.game.runner;

import com.assignment.game.service.GameService;

public class Runner {

    private final int round = 1;
    private final int boxes = 3;
    private final GameService gameService;

    public Runner(final GameService gameService) {
        this.gameService = gameService;
    }

    public void run() {
        gameService.run(round, boxes);
    }
}
