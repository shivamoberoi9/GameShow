package com.assignment.game.service;

import com.assignment.game.model.Result;
import com.assignment.game.model.RoundHistory;

import java.util.ArrayList;
import java.util.List;

import static com.assignment.game.util.Utils.getDifferentNumber;

public abstract class GameService {

    public abstract int decidePrizeBox(int min, int max);

    public abstract int getUserChoice();

    public abstract int getEmptyBox(int prizeBox, int userChoiceBox, int boxes);

    public abstract void displayEmptyBox(int emptyBox);

    public abstract boolean wantToSwitchBox();

    public abstract Result determineResult(int userChoiceBox, int prizeBox);

    protected abstract boolean wantToPlayMore();

    public abstract void displayResult(Result result);

    public abstract void displayRoundsSummary(List<RoundHistory> roundHistories);

    public void run(int round, int boxes) {
        List<RoundHistory> roundHistories = new ArrayList<>();
        final int prizeBox = decidePrizeBox(1, 3);
        int userChoiceBox = getUserChoice();
        final int emptyBox = getEmptyBox(prizeBox, userChoiceBox, boxes);
        displayEmptyBox(emptyBox);
        final boolean switchBox = wantToSwitchBox();
        if (switchBox) {
            userChoiceBox = getDifferentNumber(userChoiceBox, emptyBox, 1, boxes);
        }
        final Result result = determineResult(userChoiceBox, prizeBox);
        roundHistories.add(new RoundHistory(round, result));
        displayResult(result);
        if (wantToPlayMore()) {
            round++;
            run(round, boxes);
        }
        displayRoundsSummary(roundHistories);
    }
}
