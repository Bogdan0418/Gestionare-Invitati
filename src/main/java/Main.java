import java.util.Scanner;

public class Main {
    private static void showCommands() {
        System.out.println("help         - Afiseaza aceasta lista de comenzi");
        System.out.println("add          - Adauga o noua persoana (inscriere)");
        System.out.println("check        - Verifica daca o persoana este inscrisa la eveniment");
        System.out.println("remove       - Sterge o persoana existenta din lista");
        System.out.println("update       - Actualizeaza detaliile unei persoane");
        System.out.println("guests       - Lista de persoane care participa la eveniment");
        System.out.println("waitlist     - Persoanele din lista de asteptare");
        System.out.println("available    - Numarul de locuri libere");
        System.out.println("guests_no    - Numarul de persoane care participa la eveniment");
        System.out.println("waitlist_no  - Numarul de persoane din lista de asteptare");
        System.out.println("subscribe_no - Numarul total de persoane inscrise");
        System.out.println("search       - Cauta toti invitatii conform sirului de caractere introdus");
        System.out.println("save         - Salveaza lista cu invitati");
        System.out.println("restore      - Completeaza lista cu informatii salvate anterior");
        System.out.println("reset        - Sterge informatiile salvate despre invitati");
        System.out.println("quit         - Inchide aplicatia");
    }

    private static void addNewGuest(Scanner sc, GuestsList list) {
        String lastName = sc.nextLine();
        String firstName = sc.nextLine();
        String email = sc.nextLine();
        String phoneNumber = sc.nextLine();

        Guest newGuest = new Guest(lastName, firstName, email, phoneNumber);
        list.add(newGuest);
    }

    private static void checkGuest(Scanner sc, GuestsList list) {
        int opt = Integer.parseInt(sc.nextLine());
        Guest guest = null;

        switch (opt) {
            case 1:
                String lastName = sc.nextLine();
                String firstName = sc.nextLine();
                guest = list.search(firstName, lastName);
                System.out.println("Nume: " + guest.getLastName() + " " + guest.getFirstName() + ", Email: " + guest.getEmail() + ", Telefon: " + guest.getPhoneNumber());
                break;
            case 2:
                String email = sc.nextLine();
                guest = list.search(2, email);
                System.out.println("Nume: " + guest.getLastName() + " " + guest.getFirstName() + ", Email: " + guest.getEmail() + ", Telefon: " + guest.getPhoneNumber());
                break;
            case 3:
                String phoneNumber = sc.nextLine();
                guest = list.search(3, phoneNumber);
                System.out.println("Nume: " + guest.getLastName() + " " + guest.getFirstName() + ", Email: " + guest.getEmail() + ", Telefon: " + guest.getPhoneNumber());
                break;
            default:
                System.out.println("Optiune invalida.");
                //return;
        }
    }

    private static void removeGuest(Scanner sc, GuestsList list) {
        int opt = Integer.parseInt(sc.nextLine());

        switch (opt) {
            case 1:
                String lastName = sc.nextLine();
                String firstName = sc.nextLine();
                list.remove(firstName, lastName);
                break;
            case 2:
                String email = sc.nextLine();
                list.remove(2, email);
                break;
            case 3:
                String phoneNumber = sc.nextLine();
                list.remove(3, phoneNumber);
                break;
            default:
                System.out.println("Optiune invalida.");
                //return;
        }
    }

    private static void updateGuest(Scanner sc, GuestsList list) {
        int opt = Integer.parseInt(sc.nextLine());
        Guest guest = null;

        switch (opt) {
            case 1:
                String lastName = sc.nextLine();
                String firstName = sc.nextLine();
                guest = list.search(firstName, lastName);
                break;
            case 2:
                String email = sc.nextLine();
                guest = list.search(2, email);
                break;
            case 3:
                String phoneNumber = sc.nextLine();
                guest = list.search(3, phoneNumber);
                break;
            default:
                System.out.println("Optiune invalida.");
                return;
        }

        if (guest == null) {
            System.out.println("Persoana nu a fost gasita.");
            return;
        }

        int field = Integer.parseInt(sc.nextLine());
        String newValue = sc.nextLine();

        switch (field) {
            case 1:
                guest.setLastName(newValue);
                break;
            case 2:
                guest.setFirstName(newValue);
                break;
            case 3:
                guest.setEmail(newValue);
                break;
            case 4:
                guest.setPhoneNumber(newValue);
                break;
            default:
                System.out.println("Optiune invalida.");
                return;
        }
    }

    private static void searchList(Scanner sc, GuestsList list) {
        String match = sc.nextLine();
        list.partialSearch(match);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        scanner.nextLine();

        GuestsList list = new GuestsList(size);

        boolean running = true;
        while (running) {
            String command = scanner.nextLine();

            switch (command) {
                case "help":
                    showCommands();
                    break;
                case "add":
                    addNewGuest(scanner, list);
                    break;
                case "check":
                    checkGuest(scanner, list);
                    break;
                case "remove":
                    removeGuest(scanner, list);
                    break;
                case "update":
                    updateGuest(scanner, list);
                    break;
                case "guests":
                    list.showGuestsList();
                    break;
                case "waitlist":
                    list.showWaitingList();
                    break;
                case "available":
                    System.out.println("Numarul de locuri ramase: " + list.numberOfAvailableSpots());
                    break;
                case "guests_no":
                    System.out.println("Numarul de participanti: " + list.numberOfGuests());
                    break;
                case "waitlist_no":
                    System.out.println("Dimensiunea listei de asteptare: " + list.numberOfPeopleWaiting());
                    break;
                case "subscribe_no":
                    System.out.println("Numarul total de persoane: " + list.numberOfPeopleTotal());
                    break;
                case "search":
                    searchList(scanner, list);
                    break;
                case "quit":
                    System.out.println("Aplicatia se inchide...");
                    scanner.close();
                    running = false;
                    break;
                default:
                    System.out.println("Comanda introdusa nu este valida.");
                    System.out.println("Incercati inca o data.");

            }
        }
    }
}