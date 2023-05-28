package pl_sql_pack;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
    	
    	//DbHandler dBhandLer = new DbHandler();
    	
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMainMenu();
            int choice = getUserChoice(scanner, 5);

            switch (choice) {
                case 1:
                    System.out.println("");
                    System.out.println("random generalas");
                    DbHandler.randomGeneralHivas();
                    
                    break;
                case 2:
                    System.out.println("FIle feltoltes kivalasztva. (HIVAS) ");
                    DbHandler.hivasBeolvas();
                   
                    
                    break;
                case 3:
                    System.out.println("Uj hivas.");
                                       
                    
                    
                    
                    break;
                case 4:
                    boolean backToMain = false;
                    while (!backToMain) {
                        displaySubMenu();
                        int subChoice = getUserChoice(scanner, 3);

                        switch (subChoice) {
                            case 1:
                                System.out.println("Hivasszam");
                                
                                
                                Scanner sc = new Scanner(System.in);
                                
                                System.out.print("Add meg a datumot(YYYY.MM.DD formátumban): ");
                                String userInput = sc.nextLine();
                                
                                LocalDate date;
                                try {
                                    date = LocalDate.parse(userInput);
                                    System.out.println("A megadott dátum: " + date);
                                    
                                    DbHandler.hivasSzam(userInput);
                                    
                                    
                                } catch (DateTimeParseException e) {
                                    System.out.println("Hibas datum formatum!");
                                    getHivasDataFromUser();
                                }
                                
                                sc.close();
                                
                              
                                
                                
                                break;
                            case 2:
                                System.out.println("Szures kivalasztva.");
                                //  almenü - 2. pont műveleteit
                                
                                
                                break;
                            case 3:
                                backToMain = true;
                                break;
                            default:
                                System.out.println("Érvénytelen választás.");
                                break;
                        }
                    }
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid chice");
                    break;
            }
        }

        System.out.println("Kilepes.");
    }

    private static void displayMainMenu() {
        System.out.println("----- MENU -----");
        System.out.println("1. Random generalas");
        System.out.println("2. Feltoltes file-bol");
        System.out.println("3. Uj hivas felvitel");
        System.out.println("4. Szuresek");
        System.out.println("5. EXIT");
        System.out.println("------------------");
        System.out.print("VALASSZ EGY MENUPONTOT!:: ");
    }

    private static void displaySubMenu() {
        System.out.println("----- SZURES menupont -----");
        System.out.println("1. --Nap hivasok szama");
        System.out.println("2. --Adott idoszak ugyeletes neve ");
        System.out.println("3. --Vissza a menube");
        System.out.println("------------------");
        System.out.print("VALASSZ EGY MENUPONTOT: ");
    }

    private static int getUserChoice(Scanner scanner, int maxChoice) {
        int choice = 0;
        boolean validChoice = false;

        while (!validChoice) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= maxChoice) {
                    validChoice = true;
                } else {
                    System.out.print("Invalid choice ");
                }
            } else {
                scanner.next(); // Eltávolítja a beolvasott értéket
                System.out.print("Invalid choice: ");
            }
        }

        return choice;
    }
    
    private static void getHivasDataFromUser() {
    	
    	 Scanner scanner = new Scanner(System.in);
         
         System.out.print("HivID: ");
         int hivID = scanner.nextInt();
         scanner.nextLine(); 
         
         System.out.print("HivoNev: ");
         String hivoNev = scanner.nextLine();
         
         System.out.print("Datum (YYYY.MM.DD): ");
         String datumString = scanner.nextLine();
         
         System.out.print("Cim: ");
         String cim = scanner.nextLine();
         
         System.out.print("Surgosseg: ");
         String surgossag = scanner.nextLine();
         
         System.out.print("Statusz: ");
         String statusz = scanner.nextLine();
         
         System.out.print("Ugyeletes nev: ");
         String ugyeletesNev = scanner.nextLine();
         
         System.out.println("\nBekért adatok:");
         System.out.println("HivID: " + hivID);
         System.out.println("HivoNev: " + hivoNev);
         System.out.println("Datum: " + datumString);
         System.out.println("Cim: " + cim);
         System.out.println("Surgossag: " + surgossag);
         System.out.println("Statusz: " + statusz);
         System.out.println("Ugyeletes nev: " + ugyeletesNev);
         
         scanner.close();
    	
    }
}
