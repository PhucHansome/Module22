package CaseMD2.view;

import CaseMD2.model.Order;
import CaseMD2.model.OrderItem;
import CaseMD2.model.Tech;
import CaseMD2.services.*;
import CaseMD2.utils.ValidateUtils;

import javax.security.sasl.SaslClient;
import javax.sound.midi.Soundbank;
import javax.xml.bind.ValidationEvent;
import java.util.Scanner;
import java.util.List;

public class OrderView {
    private ITechService techService;
    private IOrderService orderService;
    private IOderItemService orderItemService;
    private final Scanner scanner = new Scanner(System.in);

    public OrderView() {
        techService = new TechService();
        orderService = new OrderService();
        orderItemService = new OrderItemService();
    }

    public OrderItem addOrderItems(long orderId) {
        orderItemService.getOrderItems();
        TechView techView = new TechView();
        techView.show();
        long id = System.currentTimeMillis() / 1000;
        System.out.println("Nhập ID Sản phẩm: ");
        System.out.print("➤ ");
        int techId = Integer.parseInt(scanner.nextLine());
        while (!techService.checkDuplicateId(techId)) {
            System.out.println("Id sản phẩm không tồn tại");
            System.out.println("Nhập id sản phẩm: ");
            System.out.print("➤ ");
            techId = Integer.parseInt(scanner.nextLine());
        }
        Tech tech = techService.getTechById(techId);
        double price = tech.getPrice();
        int oldQuantity = tech.getQuantity();
        System.out.println("Nhập số lượng: ");
        System.out.print("➤ ");
        int quantity = Integer.parseInt(scanner.nextLine());
        while (!checkQualityTech(tech, quantity)) {
            System.out.println("Vượt quá số lượng! vui lòng nhập lại");
            System.out.println("Nhập số lượng");
            System.out.print("➤ ");
            quantity = Integer.parseInt(scanner.nextLine());

        }
        String techName = tech.getName();
        double total = quantity * price;
        int currentQuantity = oldQuantity - quantity;
        tech.setQuantity(currentQuantity);
        techService.update();
        OrderItem orderItem = new OrderItem(id, price, quantity, orderId, techId, techName, total);
        return orderItem;
    }

    public boolean checkQualityTech(Tech tech, int quantity) {
        if (quantity <= tech.getQuantity())
            return true;
        else
            return false;
    }

    public void addOder() {
        try {
            orderService.getOrders();
            long orderId = System.currentTimeMillis() / 1000;
            System.out.println("Nhập họ và tên(Example: Phúc nguyễn) ");
            System.out.print("➤ ");
            String name = scanner.nextLine();
            while (!ValidateUtils.isNameValid(name)) {
                System.out.println("Tên: " + name + " không đúng." + " vui lòng nhập lại! (Tên phải viết hoa chữ cái đâu không dấu)");
                System.out.println("Nhập tên (Example: Phúc nguyễn)");
                System.out.print("➤ ");
                name = scanner.nextLine();
            }
            System.out.println("Nhập số điện thoại");
            System.out.print("➤ ");
            String phone = scanner.nextLine();
            while (!ValidateUtils.isPhoneValid(phone)) {
                System.out.println("Số " + phone + " của bạn không đúng. Vui lòng nhập lại! (Số điện thoại bao gồm 10 số vả bắt đầu là số 0  ");
                System.out.println("Nhập số điện thoại (Example: 090705768");
                System.out.print("➤ ");
                phone = scanner.nextLine();
            }
            System.out.println("Nhập địa chỉ ");
            System.out.print("➤ ");
            String address = scanner.nextLine();
            OrderItem orderItem = addOrderItems(orderId);
            Order order = new Order(orderId, name, phone, address);
            orderItemService.add(orderItem);
            orderService.add(order);
            System.out.println("Tạo đơn hàng Thành công");
            do {
                System.out.println("\t☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠");
                System.out.println("\t☠                                                     ☠");
                System.out.println("\t☠            1. Nhấn 'y' để tạo tiếp đơn hàng         ☠");
                System.out.println("\t☠            2. Nhấn 'q' để trở lại                   ☠");
                System.out.println("\t☠            3. Nhấn 'p' để in hóa đơn                ☠");
                System.out.println("\t☠            4. Nhấn 't' để thoát                     ☠");
                System.out.println("\t☠                                                     ☠");
                System.out.println("\t☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠ ☠");
                System.out.print("➤ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "y":
                        addOder();
                        break;
                    case "q":
                        ManagerOderView.run();
                        break;
                    case "p":
                        showPaymentInfo(orderItem, order);
                        break;
                    case "t":
                        Menu.exit();
                        System.exit(0);
                    default:
                        System.out.println("Incorrect! please try again");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("Incorrect! please try again!!");
        }
    }

    public void showPaymentInfo(OrderItem orderItem, Order order) {
        try {
            System.out.println("----------------------------------BILLS-------------------------------------------");
            System.out.printf("|%-15s %-20s %-15s %-15s %-15s %-15s %-15s\n|", " Id", "Tên khách hàng", "SĐT", "Địa chỉ", "Tên sản phẩm", "Số lượng", "Giá");
            System.out.printf("%-15d %-20s %-15s %-15s %-15s %-15d %-15f \n|", order.getId(), order.getName(), order.getPhone(), order.getAddress(), orderItem.getTechName(), orderItem.getQuantity(), orderItem.getPrice());
            System.out.println("----------------------------------------------------------------Tổng tiền: " + orderItem.getTotal());
            System.out.println("------------------------------Market Techs----------------------------------------");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t Nhấn 't' để thoát chương trình");
                System.out.print("➤ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        ManagerOderView.run();
                        break;
                    case "t":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Incorrect! Please try again");
                        is = false;
                }
            } while (!is);

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void showAllOrder() {
        List<Order> orders = orderService.getOrders();
        List<OrderItem> orderItems = orderItemService.getOrderItems();
        OrderItem newOrderItem = new OrderItem();
        try {
            System.out.println("-----------------------------------------------------------------------LIST ORDER--------------------------------------------------------------------");
            System.out.println("|                                                                                                                                                    |");
            System.out.printf("|%-15s %-20s %-15s %-20s %-20s %-15s %-20s %-25s \n|", " Id", "Tên khách hàng", " SĐT", "Địa chỉ", " Tên sản phẩm", " số lượng", " Giá", "Tổng " +"     |");
            for (Order order : orders) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderId() == order.getId()) {
                        newOrderItem = orderItem;
                        break;
                    }
                }
                System.out.printf("%-15d %-20s %-15s %-20s %-20s %-15d %-20f %-25f %-7s \n|", order.getId(), order.getName(), order.getPhone(), order.getAddress(), newOrderItem.getTechName(), newOrderItem.getQuantity(), newOrderItem.getPrice(), newOrderItem.getTotal(), "  |");
            }
            System.out.println("|                                                                                                                                                               |");
            System.out.println("-----------------------------------------------------------------------Market Tech--------------------------------------------------------------------");
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t Nhấn 't' để thoát chương trình");
                System.out.println("➤ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        ManagerOderView.run();
                        break;
                    case "t":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Incorrect! Please try again");
                        is = false;
                }
            } while (!is);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
