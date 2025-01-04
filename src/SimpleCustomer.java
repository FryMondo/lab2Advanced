class SimpleCustomer {
    private int id;
    private String name;
    private String phone;

    public SimpleCustomer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String generateInsertQuery() {
        return "INSERT INTO customers (id, name, phone) VALUES (" + id + ", '" + name + "', '" + phone + "');";
    }
}