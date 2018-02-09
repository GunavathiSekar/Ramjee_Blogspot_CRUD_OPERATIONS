public class CustomerModel  {
    public String firstName;
    public String lastName;
    public CustomerModel()  {

    }
    public CustomerModel(String firstName,String lastName)  {
        this.firstName=firstName;
        this.lastName=lastName;
    }
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }/**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }/**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public String toString() {
        return "first_name"+ firstName+" "+lastName;
    }
}