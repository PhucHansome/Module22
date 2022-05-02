package CaseMD2.view;

import java.util.Scanner;

public class ManagerOderView {
    public static void run() {
        Menu.orderMenu();
        Scanner scanner = new Scanner(System.in);
        OrderView orderView = new OrderView();
        System.out.println("\nChọn chức năng");
        System.out.println("➤ ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                orderView.addOder();
                break;
            case 2:
                orderView.showAllOrder();
                break;
            default:
                System.out.println("Incorrect! Please try again");
                run();
        }

    }
}
