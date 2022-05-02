package CaseMD2.model;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private long id;
    private String name;
    private String phone;
    private String address;

    private List<OrderItem> orderItems = new ArrayList<>();

    private Order() {

    }

    public Order(String record) {
        String[] field = record.split(",");
        id = Long.parseLong(field[0].trim());
        name = field[1].trim();
        phone = field[2].trim();
        address = field[3].trim();
    }

    public Order(Long id, String name, String phone, String address) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return address;
    }

    public void setEmail() {
        this.address = address;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + phone + "," + address;
    }
}
