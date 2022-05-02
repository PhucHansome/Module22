package CaseMD2.model;

public class OrderItem {
    private long id;
    private double price;
    private int quantity;
    private long orderId;
    private int techId;
    private String techName;
    private double total;

    public OrderItem(long id, double price, int quantity, long orderId, int techId, String techName, double total) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.techId = techId;
        this.techName = techName;
        this.total = total;
    }

    public OrderItem() {
    }

    public OrderItem(String record) {
        String[] fields = record.split(",");
        id = Long.parseLong(fields[0].trim());
        price = Double.parseDouble(fields[1].trim());
        quantity = Integer.parseInt(fields[2].trim());
        orderId = Long.parseLong(fields[3].trim());
        techId = Integer.parseInt(fields[4].trim());
        techName = fields[5].trim();
        total = Double.parseDouble(fields[6].trim());
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getTechId() {
        return techId;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechId(int techId) {
        this.techId = techId;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    @Override
    public String toString() {
        return  id +
                ", " + price +
                "," + quantity +
                "," + orderId +
                "," + techId +
                "," + techName +
                "," + total ;
    }
}
