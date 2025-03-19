import java.util.ArrayList;
import java.util.List;

class GuestsList {
    private final int guestsCapacity;
    private final List<Guest> guests;
    private final List<Guest> waitingList;

    public GuestsList(int guestsCapacity) {
        this.guestsCapacity = guestsCapacity;
        this.guests = new ArrayList<>();
        this.waitingList = new ArrayList<>();
    }

    public int add(Guest g) {
        if (isOnTheListAlready(g))
            return -1;

        if (guests.size() < guestsCapacity) {
            guests.add(g);
            System.out.println("[" + g.getLastName() + " " + g.getFirstName() + "] Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
            return 0;
        } else {
            waitingList.add(g);
            System.out.println("[" + g.getLastName() + " " + g.getFirstName() + "] Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine " + waitingList.size() + ". Te vom notifica daca un loc devine disponibil.");
            return waitingList.size();
        }
    }

    private boolean isOnTheListAlready(Guest g) {
        for (Guest guest : guests)
            if (guest.getFirstName().equalsIgnoreCase(g.getFirstName()) && guest.getLastName().equalsIgnoreCase(g.getLastName()) || guest.getEmail().equalsIgnoreCase(g.getEmail()) || guest.getPhoneNumber().equalsIgnoreCase(g.getPhoneNumber()))
                return true;
        return false;
    }

    public Guest search(String firstName, String lastName) {
        for (Guest guest : guests)
            if (guest.getFirstName().equalsIgnoreCase(firstName) && guest.getLastName().equalsIgnoreCase(lastName))
                return guest;

        for (Guest guest : waitingList)
            if (guest.getFirstName().equalsIgnoreCase(firstName) && guest.getLastName().equalsIgnoreCase(lastName))
                return guest;

        return null;
    }

    public Guest search(int opt, String match) {
        for (Guest guest : guests)
            if ((opt == 2 && guest.getEmail().equalsIgnoreCase(match)) || (opt == 3 && guest.getPhoneNumber().equalsIgnoreCase(match)))
                return guest;

        for (Guest guest : waitingList)
            if ((opt == 2 && guest.getEmail().equalsIgnoreCase(match)) || (opt == 3 && guest.getPhoneNumber().equalsIgnoreCase(match)))
                return guest;

        return null;
    }

    public boolean remove(String firstName, String lastName) {
        Guest guest = search(firstName, lastName);
        if (guest != null) {
            if (guests.contains(guest)) { // Dacă este în lista principală
                guests.remove(guest);
                if (!waitingList.isEmpty()) {
                    guests.add(waitingList.remove(0)); // Mută primul din lista de așteptare
                    System.out.println("[" + guests.get(guests.size() - 1).getLastName() + " " + guests.get(guests.size() - 1).getFirstName() + "] Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
                }
            } else {
                waitingList.remove(guest); // Dacă este doar în lista de așteptare, elimină-l
            }
            return true;
        }
        return false;
    }

    public boolean remove(int opt, String match) {
        Guest guest = search(opt, match);
        if (guest != null) {
            if (guests.contains(guest)) {
                guests.remove(guest);
                if (!waitingList.isEmpty()) {
                    guests.add(waitingList.remove(0));
                    System.out.println("[" + guests.get(guests.size() - 1).getLastName() + " " + guests.get(guests.size() - 1).getFirstName() + "] Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
                }
            } else {
                waitingList.remove(guest);
            }
            return true;
        }
        return false;
    }

    public void showGuestsList() {
        int i = 0;
        for (Guest guest : guests) {
            i++;
            System.out.println(i + ". " + "Nume: " + guest.getLastName() + " " + guest.getFirstName() + ", Email: " + guest.getEmail() + ", Telefon: " + guest.getPhoneNumber());
        }
    }

    public void showWaitingList() {
        if (waitingList.isEmpty()) {
            System.out.println("Lista de asteptare este goala...");
        } else {
            int i = 0;
            for (Guest guest : waitingList) {
                i++;
                System.out.println(i + ". " + "Nume: " + guest.getLastName() + " " + guest.getFirstName() + ", Email: " + guest.getEmail() + ", Telefon: " + guest.getPhoneNumber());
            }
        }
    }

    public int numberOfAvailableSpots() {
        return guestsCapacity - guests.size();
    }

    public int numberOfGuests() {
        return guests.size();
    }

    public int numberOfPeopleWaiting() {
        return waitingList.size();
    }

    public int numberOfPeopleTotal() {
        return guests.size() + waitingList.size();
    }

    public List<Guest> partialSearch(String match) {
        List<Guest> result = new ArrayList<>();
        String lowerMatch = match.toLowerCase();
        for (Guest guest : guests) {
            if (guest.getLastName().toLowerCase().contains(lowerMatch) ||
                    guest.getFirstName().toLowerCase().contains(lowerMatch) ||
                    guest.getEmail().toLowerCase().contains(lowerMatch) ||
                    guest.getPhoneNumber().toLowerCase().contains(lowerMatch)) {
                result.add(guest);
            }
        }
        for (Guest guest : waitingList) {
            if (guest.getLastName().toLowerCase().contains(lowerMatch) ||
                    guest.getFirstName().toLowerCase().contains(lowerMatch) ||
                    guest.getEmail().toLowerCase().contains(lowerMatch) ||
                    guest.getPhoneNumber().toLowerCase().contains(lowerMatch)) {
                result.add(guest);
            }
        }

        if (result.isEmpty())
            System.out.println("Nothing found");
        else {
            int i = 0;
            for (Guest guest : result) {
                System.out.println("Nume: " + result.get(i).getLastName() + " " + result.get(i).getFirstName() + ", Email: " + result.get(i).getEmail() + ", Telefon: " + result.get(i).getPhoneNumber());
                i++;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Guests: " + guests + "\nWaiting List: " + waitingList;
    }
}