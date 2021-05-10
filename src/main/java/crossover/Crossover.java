package crossover;

import base.Knapsack;

import java.util.ArrayList;

// do not modify
public abstract class Crossover {
    public abstract ArrayList<Knapsack> doCrossover(Knapsack knapsack01, Knapsack knapsack02);

    public String toString() {
        return getClass().getSimpleName();
    }
}