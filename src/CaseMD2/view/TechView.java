package CaseMD2.view;

import CaseMD2.model.Tech;
import CaseMD2.services.TechService;

import java.util.List;
import java.util.Scanner;
public class TechView {
    private final TechService techService;
    private final Scanner scanner;
    public TechView() {
        techService = new TechService();
        scanner = new Scanner(System.in);
    }

    public void add() {
        techService.getTechs();
        System.out.println("Nhập ID sản phẩm: ");
        System.out.print("➤ ");
        int id = Integer.parseInt(scanner.nextLine());
        if (techService.checkDuplicateId(id)) {
            System.out.println("ID tech đã tồn tại! vui lòng nhập lại");
            add();
        } else {
            System.out.println("Nhập tên sản phẩm: ");
            System.out.print("➤ ");
            String name = scanner.nextLine();
            if (techService.checkDuplicateName(name)) {
                System.out.println("Tên sản phẩm đã tồn tại! vui lòng nhập lại: ");
                add();
            } else {
                System.out.println("Nhập giá sản phẩm : ");
                System.out.print("➤ ");
                double price = Double.parseDouble(scanner.nextLine());
                System.out.println("Nhập số lượng: ");
                System.out.print("➤ ");
                int quantity = Integer.parseInt(scanner.nextLine());
                System.out.println("Mô tả sản phẩm: ");
                System.out.print("➤ ");
                String description = scanner.nextLine();
                Tech tech = new Tech(id, name, price, quantity, description);
                techService.add(tech);
                System.out.println("bạn đã thêm sản phẩm thành công\n");
            }
        }
        boolean is = false;
        do {
            System.out.println("Nhấn 'y' để thêm sản phẩm mới \t|\t 'b' để quay lại \t|\t nhấn 'q' để thoát");
            System.out.print("➤ ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "y":
                    add();
                    break;
                case "b":
                    ManagerTechView.run();
                    break;
                case "t":
                    Menu.exit();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Incorrect! please try again!!");
                    is = true;
            }
        } while (is);
    }

    public void update() {
        show();
        techService.getTechs();
        System.out.println("nhập ID sản phẩm cần sửa: ");
        System.out.print("➤ ");
        int id = Integer.parseInt(scanner.nextLine());
        Tech tech = techService.getTechById(id);
        if (techService.checkDuplicateId(id)) {
            System.out.println("----------------------");
            System.out.println("| 1. Sửa số lượng    |");
            System.out.println("| 2. Sửa giá sản phẩm|");
            System.out.println("| 3. Quay lại Menu   |");
            System.out.println("----------------------");
            System.out.println("Chọn chức năng: ");
            System.out.print("➤ ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("nhập số lượng muốn sửa: ");
                    System.out.print("➤ ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    techService.update();
                    System.out.println("Số lượng sản phẩm đã cập nhật thành công!!");
                    break;
                case 2:
                    System.out.println("Nhập vào giá bạn muốn sửa: ");
                    System.out.println("➤ ");
                    double price = Double.parseDouble(scanner.nextLine());
                    tech.setPrice(price);
                    techService.update();
                    System.out.println("Bạn đã sửa giá thành công!!");
                    break;
                case 3:
                    ManagerTechView.run();
                default:
                    System.out.println("Incorrect! Please try again!!");
                    update();
            }
            boolean is = true;
            do {
                System.out.println("Nhấn 'y' để sửa tiếp \t|\t nhấn 'b' để quay lại \t|\t nhấn 'q' để thoát chương trình ");
                System.out.print("➤ ");
                String choi = scanner.nextLine();
                switch (choi) {
                    case "y":
                        update();
                        break;
                    case "b":
                        ManagerTechView.run();
                    case "q":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Incorrect! please try again!!!");
                        is = false;
                }
            } while (!is);
        } else {
            System.out.println("Incorrect ID! Please Try again!!");
            update();
        }
    }

    public void show() {
        List<Tech> techs = techService.getTechs();
        System.out.println("-----------------------------------LIST TECH-----------------------------------");
        System.out.printf("%-10s %-30s %-18s %-10s %-10s", "Id", "Tên sản phẩm", "Giá sản phẩm", "Số lượng", "Mô tả");
        System.out.println(" ");
        for (Tech te : techs) {
            System.out.printf("%-10s %-30s %-18s %-10s %-10s", te.getId(), te.getName(), te.getPrice(), te.getQuantity(), te.getDescription());
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void showTech() {
        show();
        boolean is = true;
        do {
            System.out.println("-------------------------------------------------------");
            System.out.println("| Nhấn 'y' để trở lại \t|\t 'n' để thoát chương trình |");
            System.out.println("-------------------------------------------------------");
            System.out.println("➤ ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "y":
                    ManagerTechView.run();
                    break;
                case "n":
                    Menu.exit();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Incorrect! Please try again!!");
                    is = false;
            }
        } while (!is);
    }

    public void remove() {
        show();
        techService.getTechs();
        System.out.println("nhập id cần xóa: ");
        System.out.print("➤ ");
        int id = Integer.parseInt(scanner.nextLine());
        Tech tech = techService.getTechById(id);
        if (tech == null) {
            System.out.println("Không tìm thấy id để xóa!");
        } else {
            boolean check = true;
            System.out.println("\t☆ ☆ ☆ ☆ ☆ REMOVE COFIRM ☆ ☆ ☆ ☆ ☆");
            System.out.println("\t☆                                  ☆");
            System.out.println("\t☆      1. Nhấn 1 để xóa            ☆");
            System.out.println("\t☆      2. Nhấn 2 để quay lại       ☆");
            System.out.println("\t☆                                  ☆");
            System.out.println("\t☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆ ☆");
            System.out.print("➤ ");
            int choi = Integer.parseInt(scanner.nextLine());
            switch (choi) {
                case 1:
                    techService.remove(tech);
                    System.out.println("đã xóa thành công \uD83C\uDF8A");
                    do {
                        System.out.println("------------------------------------------------------------");
                        System.out.println("| Nhấn 'y' để trở lại \t|\t Nhấn 'n' để thoát chương trình |");
                        System.out.println("------------------------------------------------------------");
                        System.out.println("➤ ");
                        String choice = scanner.nextLine();
                        switch (choice) {
                            case "y":
                                ManagerTechView.run();
                                break;
                            case "n":
                                Menu.exit();
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Incorrect! please try again");
                                check = false;
                        }
                    } while (!check);
                    break;
                case 2:
                    ManagerTechView.run();
                    break;
                default:
                    System.out.println("Incorrect! Please try again!!");
            }
        }
    }
}
