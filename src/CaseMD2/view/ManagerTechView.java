package CaseMD2.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagerTechView {
    public static void run() {
        int number;
        boolean check = true;
        do {
            Scanner scanner = new Scanner(System.in);
            TechView techView = new TechView();
            Menu.menuTech();
            try {
                System.out.println("\nChọn chức năng: ");
                System.out.println("➤ ");
                number = Integer.parseInt(scanner.nextLine());
                switch (number) {
                    case 1:
                        techView.add();
                        break;
                    case 2:
                        techView.update();
                        break;
                    case 3:
                        techView.remove();
                        break;
                    case 4:
                        techView.showTech();
                        break;
                    case 5:
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Incorrect! please try again");
                        run();
                }

            } catch (InputMismatchException io) {
                System.out.println("Incorrect! Please Try again");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (check);

    }
}
