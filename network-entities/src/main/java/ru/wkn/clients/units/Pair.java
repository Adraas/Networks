package ru.wkn.clients.units;

public class Pair<F, S> {

    private F firstValue;
    private S secondValue;

    public Pair(F firstValue, S secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public F getFirstValue() {
        return firstValue;
    }

    public S getSecondValue() {
        return secondValue;
    }
}
