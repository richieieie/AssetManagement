package data;

import java.io.Serializable;
import java.util.Objects;

public class Asset implements Serializable, DataRow {
    private String id;
    private String name;
    private String color;
    private int price;
    private double weight;
    private int quantity;

    public Asset() {

    }

    public Asset(String id, String name, String color, int price, double weight, int quantity) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Asset{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", color='" + color + '\'' + ", price=" + price + ", weight=" + weight + ", quantity=" + quantity + '}';
    }

    @Override
    public String toStringRow(int[] lens) {
        return String.format("|" + "%1$" + lens[0] + "s|" + "%2$" + lens[1] + "s|" +
                        "%3$" + lens[2] + "s|%4$" + lens[3] + "s|%5$" + lens[4] + "s|" + "%6$" + lens[5] + "s|", id,
                name, color, price, weight, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asset asset)) return false;
        return Objects.equals(getId(), asset.getId());
    }

}
