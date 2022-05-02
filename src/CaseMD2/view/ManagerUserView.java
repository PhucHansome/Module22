package CaseMD2.view;

import java.util.Scanner;

public class ManagerUserView {
    public static void run(){
        Scanner scanner = new Scanner(System.in);
        UserView userView = new UserView();
        Menu.menuUser();
        try {
            System.out.println("\nChọn chức năng ");
            System.out.print("➤ ");
            int number = Integer.parseInt(scanner.nextLine());
            switch (number){
                case 1:
                    userView.addUser();
                    break;
                case 2:
                    userView.updateUser();
                    break;
                case 3:
                    userView.showUser();
                    break;
                default:
                    System.out.println("Incorrect! please try again");
                    run();
            }
        }catch (Exception e){
            System.out.println("Incorrect! Please try again");
        }
    }
}
