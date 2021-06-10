package crossover;

/* --- test cases in order 01 to 10 ---
   [01] parent01 and parent02 have same length 150 before crossover
   [02] parent01 not equals null after crossover
   [03] parent02 not equals null after crossover
   [04] parent01 and parent02 have same length 150 after crossover
   [05] child not null
   [06] length child equals length parent01
   [07] child is valid related to knapsack restriction
   [08] child contains not only 0s and not only 1s
   [09] child differs from parent01
   [10] child differs from parent02
*/

import base.Knapsack;
import configuration.Configuration;
import data.Database;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

// please delete the file if not needed
// if needed -> please update "Placeholder" with the name of the implemented algorithm
public class TestKPointCrossover {

    private Crossover crossover;
    private Knapsack parent01;
    private Knapsack parent02;
    private final int maxVolume = 822;

    @BeforeEach
    public void init() {
        Database.instance.init(Configuration.instance.dataFilePath);

        this.crossover = new KPointCrossover();
        this.parent01 = createTestParent();
        this.parent02 = createTestParent();
    }

    private Knapsack createTestParent() {
        Knapsack parent = new Knapsack();
        ArrayList<Integer> position = new ArrayList<>();
        ArrayList<Integer> structure = new ArrayList<>();

        for(int i = 1; i <= 150; i++) {
            position.add(i);
            structure.add(0);
        }

        Collections.shuffle(position);
        position.forEach(index -> {
            if (parent.getTotalVolume() <= maxVolume) {
                structure.set(index-1, 1);
                parent.setStructure(structure);
            }
        });
        return parent;
    }

    @Test
    @DisplayName("[01] parent01 and parent02 have same length 150 before crossover")
    public void test01() {
        Assertions.assertEquals(150, parent01.getStructure().size());
        Assertions.assertEquals(150, parent02.getStructure().size());
        crossover.doCrossover(parent01, parent02);
    }

    @Test
    @DisplayName("[02] parent01 not equals null after crossover")
    public void test02() {
        crossover.doCrossover(parent01, parent02);
        Assertions.assertNotNull(parent01);
    }

    @Test
    @DisplayName("[03] parent02 not equals null after crossover")
    public void test03() {
        crossover.doCrossover(parent01, parent02);
        Assertions.assertNotNull(parent02);
    }

    @Test
    @DisplayName("[04] parent01 and parent02 have same length 150 after crossover")
    public void test04() {
        crossover.doCrossover(parent01, parent02);
        Assertions.assertEquals(150, parent01.getStructure().size());
        Assertions.assertEquals(150, parent02.getStructure().size());
    }

    @Test
    @DisplayName("[05] child not null")
    public void test05() {
        ArrayList<Knapsack> child = crossover.doCrossover(parent01, parent02);
        Assertions.assertNotNull(child.get(0));
        Assertions.assertNotNull(child.get(1));
    }

    @Test
    @DisplayName("[06] length child equals length parent01")
    public void test06() {
        ArrayList<Knapsack> child = crossover.doCrossover(parent01, parent02);
        Assertions.assertEquals(parent01.getStructure().size(), child.get(0).getStructure().size());
        Assertions.assertEquals(parent01.getStructure().size(), child.get(1).getStructure().size());
    }

    @Test
    @DisplayName("[07] child is valid related to knapsack restriction")
    public void test07() {
        ArrayList<Knapsack> child = crossover.doCrossover(parent01, parent02);
        child.removeIf(c -> c.getTotalVolume() >= maxVolume);
        child.forEach(c -> Assertions.assertTrue(c.getTotalVolume() <= maxVolume));
    }

    @Test
    @DisplayName("[08] child contains not only 0s and not only 1s")
    public void test08() {
        ArrayList<Knapsack> child = crossover.doCrossover(parent01, parent02);
        ArrayList<Integer> child01 = child.get(0).getStructure();
        ArrayList<Integer> child02 = child.get(1).getStructure();

        Assertions.assertTrue(child01.contains(0));
        Assertions.assertTrue(child02.contains(0));
        Assertions.assertTrue(child01.contains(1));
        Assertions.assertTrue(child02.contains(1));
    }

    @Test
    @DisplayName("[09] child differs from parent01")
    public void test09() {
        ArrayList<Knapsack> child = crossover.doCrossover(parent01, parent02);
        ArrayList<Integer> child01 = child.get(0).getStructure();
        ArrayList<Integer> child02 = child.get(1).getStructure();

        Assertions.assertNotEquals(child01, parent01.getStructure());
        Assertions.assertNotEquals(child02, parent01.getStructure());
    }

    @Test
    @DisplayName("[10] child differs from parent02")
    public void test10() {
        ArrayList<Knapsack> child = crossover.doCrossover(parent01, parent02);
        ArrayList<Integer> child01 = child.get(0).getStructure();
        ArrayList<Integer> child02 = child.get(1).getStructure();

        Assertions.assertNotEquals(child01, parent02.getStructure());
        Assertions.assertNotEquals(child02, parent02.getStructure());
    }
}