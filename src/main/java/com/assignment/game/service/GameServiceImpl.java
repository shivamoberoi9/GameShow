package com.assignment.game.service;

import com.assignment.game.io.IOUtil;
import com.assignment.game.model.Result;
import com.assignment.game.model.RoundHistory;

import java.util.Comparator;
import java.util.List;

import static com.assignment.game.model.Result.LOST;
import static com.assignment.game.model.Result.WON;
import static com.assignment.game.util.Utils.getDifferentNumber;
import static com.assignment.game.util.Utils.getRandomNumber;

public class GameServiceImpl extends GameService {

    private final IOUtil ioUtil;

    public GameServiceImpl(IOUtil ioUtil) {
        this.ioUtil = ioUtil;
    }

    @Override
    public int decidePrizeBox(final int min, final int max) {
        return getRandomNumber(min, max);
    }

    @Override
    public int getUserChoice() {
        return ioUtil.getUserInput();
    }

    @Override
    public int getEmptyBox(final int prizeBox, final int userChoiceBox, int boxes) {
        return getDifferentNumber(userChoiceBox, prizeBox, 1, boxes);
    }

    @Override
    public void displayEmptyBox(final int emptyBox) {
        System.out.println("Box " + emptyBox + " is empty");
    }

    @Override
    public boolean wantToSwitchBox() {
        return ioUtil.getUserInputForSwitchingTheOption();
    }

    @Override
    public Result determineResult(final int userChoiceBox, final int prizeBox) {
        return userChoiceBox == prizeBox ? WON : LOST;
    }

    @Override
    public void displayResult(final Result result) {
        System.out.println("You have " + result.name() + " the game!");
    }

    @Override
    public boolean wantToPlayMore() {
        return ioUtil.getUserInputToPlayMore();
    }

    @Override
    public void displayRoundsSummary(List<RoundHistory> roundHistories) {
        roundHistories.sort(Comparator.comparing(RoundHistory::getRoundNo));
        roundHistories.forEach(System.out::println);
    }
}
