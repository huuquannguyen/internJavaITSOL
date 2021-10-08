public class OrderLine {

    private Customer customer;
    private Phone phone;
    private int quantity;
    
    public OrderLine() {
    }

    public OrderLine(Customer customer, Phone phone, int quantity) {
        this.customer = customer;
        this.phone = phone;
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return customer.getId() + " - " + customer.getName() + " - " + phone.getModel() + " - " + quantity;
    }
}
