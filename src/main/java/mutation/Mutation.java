package mutation;

import base.Knapsack;

// do not modify
public abstract class Mutation {
    public abstract void doMutation(Knapsack knapsack);

    public String toString() {
        return getClass().getSimpleName();
    }
}