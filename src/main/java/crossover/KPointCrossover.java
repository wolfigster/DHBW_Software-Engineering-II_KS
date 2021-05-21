package crossover;

import base.Knapsack;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.stream.IntStream;

// please delete the file if not needed
// if needed -> please update "Placeholder" with the name of the implemented algorithm
public class KPointCrossover extends Crossover {
    public ArrayList<Knapsack> doCrossover(Knapsack knapsack01, Knapsack knapsack02) {
        MersenneTwisterFast mersenneTwisterFast = new MersenneTwisterFast();
        // randomly select k crossover points
        int k = mersenneTwisterFast.nextInt(knapsack01.getStructure().size()-1);
        int[] points = mersenneTwisterFast.ints(1, knapsack01.getStructure().size()-1).distinct().limit(k).toArray();

        ArrayList<Integer> child01 = new ArrayList<>();
        ArrayList<Integer> child02 = new ArrayList<>();

        // initialize currentParents which where been switched during the crossover
        Knapsack currentParent01 = knapsack01;
        Knapsack currentParent02 = knapsack02;

        for (int i = 0; i < knapsack01.getStructure().size(); i++) {
            int finalI = i;
            // switch parent for both children
            if (IntStream.of(points).anyMatch(x -> x == finalI)) {
                currentParent01 = currentParent01 == knapsack01 ? knapsack02 : knapsack01;
                currentParent02 = currentParent02 == knapsack01 ? knapsack02 : knapsack01;
            }
            // add the Knapsack from current parent to the children
            child01.add(currentParent01.getStructure().get(i));
            child02.add(currentParent02.getStructure().get(i));
        }

        // create child (offspring) of k point crossover
        ArrayList<Knapsack> child = new ArrayList<>();
        Knapsack ks01 = new Knapsack();
        Knapsack ks02 = new Knapsack();
        ks01.setStructure(child01);
        ks02.setStructure(child02);
        child.add(ks01);
        child.add(ks02);

        return child;
    }
}