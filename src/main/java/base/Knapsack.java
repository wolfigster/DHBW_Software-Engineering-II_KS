package base;

import data.Database;

import java.util.ArrayList;

public class Knapsack {
    private ArrayList<Integer> structure;

    public Knapsack() {
        structure = new ArrayList<>();

        for (int i = 0; i < Database.instance.items.size(); i++) {
            structure.add(0);
        }
    }

    public int getTotalVolume() {
        int total = 0;

        for (int i = 0; i < structure.size(); i++) {
            if (structure.get(i) == 1) {
                total += Database.instance.items.get(i).getSize();
            }
        }

        return total;
    }

    public int getTotalValue() {
        int total = 0;

        for (int i = 0; i < structure.size(); i++) {
            if (structure.get(i) == 1) {
                total += Database.instance.items.get(i).getValue();
            }
        }

        return total;
    }

    public ArrayList<Integer> getStructure() {
        return structure;
    }

    public void setStructure(ArrayList<Integer> structure) {
        this.structure = structure;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < structure.size(); i++) {
            stringBuilder.append(Database.instance.items.get(i).getName()).append(" ");
        }

        stringBuilder.append(" - ").append("total: ").append(getTotalValue());
        return stringBuilder.toString();
    }
}