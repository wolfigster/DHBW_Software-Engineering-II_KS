package selection;

import base.Knapsack;
import base.Population;

import java.util.ArrayList;

// do not modify
public abstract class Selection {
    public abstract ArrayList<Knapsack> doSelection(Population population);
}