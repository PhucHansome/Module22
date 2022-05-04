package CaseMD2.view;

import CaseMD2.model.Role;
import CaseMD2.model.User;
import CaseMD2.services.IUserService;
import CaseMD2.services.UserService;
import CaseMD2.utils.ValidateUtils;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final IUserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public UserView() {
        userService = new UserService();
    }

    public void loginAdmin() {
        System.out.println("* * * * * * * * * * * * LOGIN * * * * * * * * * * * *");
        System.out.println("Username: ");
        System.out.print("➤ ");
        String username = scanner.nextLine();
        System.out.println("Mật khẩu: ");
        System.out.print("➤ ");
        String password = scanner.nextLine();
        if (userService.loginAdmin(username, password) == null) {
            System.out.println("User Incorrect! Please try again!!");
            chon();
        } else {
            System.out.println("Bạn đã đăng nhập thành công!!!! \n");
            System.out.println("CHÀO MỪNG CÁC BẠN ĐÃ ĐẾN VỚI MARKET TECH \n");
        }
    }

    public void chon() {
        System.out.println("\t✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ CHOICE ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
        System.out.println("\t✦                                                                ✦");
        System.out.println("\t✦                   1. Nhấn 'y' để đăng nhâp lại                 ✦");
        System.out.println("\t✦                   2. Nhấn 'n' để thoát chương trình            ✦");
        System.out.println("\t✦                                                                ✦");
        System.out.println("\t✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦ ✦");
        System.out.print("➤ ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "y":
                loginAdmin();
                break;
            case "n":
                Menu.exit();
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect! please try again");
                chon();
        }
    }

    public void addUser() {
        try {
            System.out.println("Nhập id");
            System.out.print("➤ ");
            int id = Integer.parseInt(scanner.nextLine());
            if (userService.exist(id)) {
                System.out.println("Id này đã tồn tại. Vui lòng nhập id khác!");
                addUser();
            } else {
                System.out.println("Nhập Username");
                System.out.print("➤ ");
                String userName = scanner.nextLine();
                while (userService.checkDuplicateUserName(userName)) {
                    System.out.println("Username này đã tồn tại. Vui lòng nhập lại");
                    System.out.print("➤ ");
                    userName = scanner.nextLine();
                }
                System.out.println("Nhập mật khẩu(mật khẩu phải > 8 ký tự )");
                System.out.print("➤ ");
                String password = scanner.nextLine();
                while (!ValidateUtils.isPasswordValid(password)) {
                    System.out.println("Mật khẩu yếu! vui lòng nhập lại");
                    System.out.print("➤ ");
                    password = scanner.nextLine();
                }
                System.out.println("Nhập họ và tên ");
                System.out.print("➤ ");
                String name = scanner.nextLine();
                while (!ValidateUtils.isNameValid(name)) {
                    System.out.println("Tên" + name + " không đúng định dạng. Vui lòng nhập lại! (tên phải viết Hoa chữ cái đầu và không dấu)");
                    System.out.println("Nhập tên ");
                    System.out.print("➤ ");
                    name = scanner.nextLine();
                }
                System.out.println("Nhập số điện thoại(Example: 090807786): ");
                System.out.print("➤ ");
                String phone = scanner.nextLine();
                while (!ValidateUtils.isPhoneValid(phone)) {
                    System.out.println("Số " + phone + " Của bạn không đúng định dạng. Vui lòng nhập lại! (số điện thoại bao gồm 10 số và bắt dầu là số 0)");
                    System.out.println("Nhập số điện thoại (Example: 090807786) ");
                    System.out.print("➤ ");
                    phone = scanner.nextLine();
                }
                System.out.println("Nhập email (example: phucnguyen@gmail.com");
                System.out.print("➤ ");
                String email = scanner.nextLine();
                while (!ValidateUtils.isEmailValid(email)) {
                    System.out.println("Email" + email + " Của bạn không đúng định dạng! vui lòng kiểm tra và nhập lại");
                    System.out.println("Nhập email (vd: Phucnguyen@gmail.com)");
                    System.out.print("➤ ");
                    email = scanner.nextLine();
                }
                System.out.println("Nhập địa chỉ");
                System.out.print("➤ ");
                String address = scanner.nextLine();
                User user = new User(id, userName, password, name, phone, email, address, Role.USER);
                setRole(user);
                userService.add(user);
                System.out.println("Đã thêm thành công! \uD83C\uDF8A");
            }
            boolean check = true;
            do {
                System.out.println("Nhấn 'y' để thêm tiếp người dùng \t|\t Nhấn 'q' để quay lại \t|\t Nhấn 't' để thoát");
                System.out.print("➤ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "y":
                        addUser();
                        break;
                    case "q":
                        ManagerUserView.run();
                        break;
                    case "t":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Incorrect!Please try again");
                        check = false;
                }
            } while (!check);
        } catch (Exception e) {
            System.out.println("Incorrect! Please try again");
        }
    }

    public void setRole(User user) {
        System.out.println("☃ ☃ ☃ SET ROLE ☃ ☃ ☃");
        System.out.println("☃                  ☃");
        System.out.println("☃   1. USER        ☃");
        System.out.println("☃   2. ADMIN       ☃");
        System.out.println("☃                  ☃");
        System.out.println("☃ ☃ ☃ ☃ ☃ ☃ ☃ ☃ ☃ ☃");
        System.out.println("Chọn role: ");
        System.out.print("➤ ");
        int chido = Integer.parseInt(scanner.nextLine());
        switch (chido) {
            case 1:
                user.setRole(Role.USER);
                break;
            case 2:
                user.setRole(Role.ADMIN);
                break;
            default:
                System.out.println("Incorrect! Please try again");
                setRole(user);
        }
    }

    public void show() {
        System.out.println("---------------------------------------------------------LIST CUSTOMER----------------------------------------------");
        System.out.printf("%-5s %-25s %-20s %-25s %-25s %-15s \n", "Id", "Tên", "Số điện thoại", "Email", "Địa chỉ", "Người dùng");
        List<User> users = userService.getUsers();
        for (User user : users) {
            System.out.printf("%-5d %-25s %-20s %-25s %-25s %-15s\n", user.getId(), user.getName(), user.getPhone(), user.getEmail(), user.getAddress(), user.getRole());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println(" ");
    }

    public void showUser() {
        try {
            show();
            boolean is = true;
            do {
                System.out.println("Nhấn 'q' để trở lại \t|\t 't' để thoát ra chương trình");
                System.out.print("➤ ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "q":
                        ManagerUserView.run();
                        break;
                    case "t":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Incorrect! please try again!!");
                        is = false;
                }

            } while (!is);

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void updateUser() {
        try {
            show();
            userService.getUsers();
            System.out.println("Nhập id bạn muốn sửa");
            System.out.print("➤ ");
            int id = Integer.parseInt(scanner.nextLine());
            if (userService.exist(id)) {
                System.out.println("☣ ☣ ☣ ☣ ☣ ☣ ☣ EDIT ☣ ☣ ☣ ☣ ☣ ☣ ☣");
                System.out.println("☣                                   ☣");
                System.out.println("☣         1. Đổi tên                ☣");
                System.out.println("☣         2. Sửa đổi điện thoại     ☣");
                System.out.println("☣         3. Sửa địa chỉ            ☣");
                System.out.println("☣         4. Quay lại               ☣");
                System.out.println("☣                                   ☣");
                System.out.println("☣ ☣ ☣ ☣ ☣ ☣ ☣ ☣ ☣ ☣ ☣ ☣ ☣ ☣ ☣ ☣");
                System.out.println("Chọn chức năng");
                System.out.print("➤ ");
                int choice = Integer.parseInt(scanner.nextLine());
                User user = new User();
                user.setId(id);
                switch (choice) {
                    case 1:
                        System.out.println("Nhập tên mà bạn muốn sửa đổi");
                        System.out.print("➤ ");
                        String name = scanner.nextLine();
                        user.setName(name);
                        userService.update(user);
                        System.out.println("Bạn đã đổi tên thành công! \uD83C\uDF89");
                        break;
                    case 2:
                        System.out.println("Nhập số điện thoại mà bạn muốn đổi");
                        System.out.print("➤ ");
                        String phone1 = scanner.nextLine();
                        while (!ValidateUtils.isPhoneValid(phone1)) {
                            System.out.println("Số" + phone1 + " của bạn không đúng. Vui lòng nhập lại (số điện thoại bao gồm 10 số và bắt đầu bằng số 0)");
                            System.out.println("Nhập số điện thoại (example: 0909807786");
                            System.out.print("➤ ");
                            phone1 = scanner.nextLine();
                        }
                        while (userService.checkDuplicatePhone(phone1)) {
                            System.out.println("Số này đã tồn tại! mời nhập lại");
                            System.out.print("➤ ");
                            phone1 = scanner.nextLine();
                        }
                        user.setPhone(phone1);
                        userService.update(user);
                        System.out.println("bạn đã đổi số điện thoại thành công\uD83C\uDF89");
                        break;
                    case 3:
                        System.out.println("Nhập địa chỉ mà bạn muốn đổi");
                        System.out.print("➤ ");
                        String address = scanner.nextLine();
                        user.setAddress(address);
                        userService.update(user);
                        System.out.println("Bạn đã đổi thành công\uD83C\uDF89");
                    case 4:
                        ManagerUserView.run();
                    default:
                        System.out.println("Incorrect! Please try again!!");
                        updateUser();
                }
                boolean is = true;
                do {
                    System.out.println("Nhấn 'y' để sửa tiếp \t|\t 'q' để quay lại \t|\t 't' để thoát ra chương trình ");
                    System.out.print("➤ ");
                    String choi = scanner.nextLine();
                    switch (choi) {
                        case "y":
                            updateUser();
                            break;
                        case "q":
                            ManagerUserView.run();
                        case "t":
                            Menu.exit();
                            ;
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Incorrect! Please try again!!");
                            is = false;
                    }
                } while (!is);
            } else {
                System.out.println("Không tìm thấy id! Vui lòng nhập lại");
                updateUser();
            }
        } catch (Exception e) {
            System.out.println("Nhập sai! Vui lòng nhập lại");
        }
    }
}
