package com.assignment.game.model;

public class RoundHistory {

    private final int roundNo;
    private final Result result;

    public RoundHistory(int roundNo, Result result) {
        this.roundNo = roundNo;
        this.result = result;
    }

    public int getRoundNo() {
        return roundNo;
    }

    @Override
    public String toString() {
        return "roundNo=" + roundNo +
                ", outcome=" + result
                ;
    }
}

