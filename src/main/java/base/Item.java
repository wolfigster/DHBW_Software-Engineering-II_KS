package base;

public class Item {
    private final String name;
    private final int size;
    private final int value;

    public Item(String name, int size, int value) {
        this.name = name;
        this.size = size;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getValue() {
        return value;
    }
}