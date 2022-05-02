package CaseMD2.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Role role;

    public User() {

    }

    public User(int id, String username, String password, String name, String phone, String email, String address, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.role = role;
    }

    public User(String raw) {
        String[] strings = raw.split(",");
        this.id = Integer.parseInt(strings[0].trim());
        this.username = strings[1].trim();
        this.password = strings[2].trim();
        this.name = strings[3].trim();
        this.phone = strings[4].trim();
        this.email = strings[5].trim();
        this.address = strings[6].trim();
        this.role = Role.fromValue(strings[7].trim());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return id + ", " + username + ", " + password + ", " + name + ", " + phone + ", " + email + ", " + address+", " + role;

    }
}
