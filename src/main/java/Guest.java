class Guest {
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;

    public Guest(String lastName, String firstName, String email, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        Guest guestObj = (Guest) obj;
        return this.lastName.equals(guestObj.lastName) && this.firstName.equals(guestObj.firstName) && this.email.equals(guestObj.email) && this.phoneNumber.equals(guestObj.phoneNumber);
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + email + " " + phoneNumber;
    }

    public String fullName() {
        return lastName + " " + firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setLastName(String newValue) {
        this.lastName = newValue;
    }

    public void setFirstName(String newValue) {
        this.firstName = newValue;
    }

    public void setEmail(String newValue) {
        this.email = newValue;
    }

    public void setPhoneNumber(String newValue) {
        this.phoneNumber = newValue;
    }
}
