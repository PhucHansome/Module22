package CaseMD2.model;

public class Tech {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String description;

    public Tech(int id, String name, double price, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public Tech(String record) {
        String[] fields = record.split(",");
        id = Integer.parseInt(fields[0].trim());
        name = fields[1].trim();
        price = Double.parseDouble(fields[2].trim());
        quantity = Integer.parseInt(fields[3].trim());
        description = fields[4].trim();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + price + ", " + quantity + ", " + description;
    }
}
