package CaseMD2.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void menuTech() {
        System.out.println();
        System.out.println("\t♉ ♕ ♕ ♕ ♕ ♕ ♕ ♕ Menu Tech ♕ ♕ ♕ ♕ ♕ ♕ ♕ ♉");
        System.out.println("\t░                                                ░");
        System.out.println("\t░           1. Thêm sản phẩm                     ░");
        System.out.println("\t░           2. Sửa thông tin sản phẩm            ░");
        System.out.println("\t░           3. Xóa Sản phẩm                      ░");
        System.out.println("\t░           4. Hiển thị tất cả các sản phẩm      ░");
        System.out.println("\t░           5. Thoát                             ░");
        System.out.println("\t░                                                ░");
        System.out.println("\t♉ ♚ ♚ ♚ ♚ ♚ ♚ ♚ ♚ ♚ ♚ ♚ ♚ ♚ ♚ ♚ ♚ ♚ ♚ ♉");

    }


    public static void exit() {
        System.out.println("\tTạm biệt. See later!!");
        System.exit(5);
    }
    public static void user(){
        UserView userView = new UserView();
        userView.loginAdmin();
        choose();
    }

    public static void choose() {
        boolean check = true;
        do {
            mainMenu();
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\n Chọn chức năng ");
                System.out.print("➤ ");
                int number = Integer.parseInt(scanner.nextLine());
                switch (number) {
                    case 1:
                        ManagerUserView.run();
                        break;
                    case 2:
                        ManagerTechView.run();
                        break;
                    case 3:
                        ManagerOderView.run();
                        break;
                    default:
                }
            } catch (InputMismatchException io) {
                System.out.println("Incorrectly! Please Try Again!!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (check);
    }

    public static void menuUser() {
        System.out.println("\t❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ URSER MANAGER ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄");
        System.out.println("\t❄                                                 ❄");
        System.out.println("\t❄           1. Thêm người dùng                    ❄");
        System.out.println("\t❄           2. Sửa thông tin người dùng           ❄");
        System.out.println("\t❄           3. Hiện danh sách người dùng          ❄");
        System.out.println("\t❄                                                 ❄");
        System.out.println("\t❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄ ❄");
    }

    public static void mainMenu() {
        System.out.println("\t♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ MAIN MENU ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡");
        System.out.println("\t♡                                                 ♡");
        System.out.println("\t♡            1. Quản lý người dùng                ♡");
        System.out.println("\t♡            2. Quản lý sản phẩm                  ♡");
        System.out.println("\t♡            3. Quản lý đơn đặt hàng              ♡");
        System.out.println("\t♡                                                 ♡");
        System.out.println("\t♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡ ♡");
    }
    public static void orderMenu(){
        System.out.println("\t♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ORDER MENU ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠");
        System.out.println("\t♠                                                  ♠");
        System.out.println("\t♠              1. Tạo oder                         ♠");
        System.out.println("\t♠              2. Xem danh sách order              ♠");
        System.out.println("\t♠                                                  ♠");
        System.out.println("\t♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠ ♠");

    }
}
