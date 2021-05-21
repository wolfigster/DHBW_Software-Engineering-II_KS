package data;

import base.Item;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public enum Database {
    instance;

    public ArrayList<Item> items = new ArrayList<>();

    public void init(String dataFilePath) {
        // add this line to run the initialisation before each test
        items = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(dataFilePath))) {
            stream.skip(1).forEach(line -> {
                String[] data = line.split(";");
                items.add(new Item(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2])));
            });
            System.out.println("Database with " + items.size() + " items initialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Item getItemByName(String name) {
        return items.get(Integer.parseInt(name) - 1);
    }

    public Item getItem(int index) {
        return items.get(index);
    }
}