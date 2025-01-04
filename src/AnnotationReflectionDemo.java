public class AnnotationReflectionDemo {
    public static void main(String[] args) throws IllegalAccessException {
        User user = new User(1, "John Doe", "john.doe@example.com");
        Product product = new Product(101, "Laptop", 1500.50);
        SimpleCustomer customer = new SimpleCustomer(201, "Jane Smith", "123-456-7890");

        // Створення запитів SQL за допомогою reflection
        System.out.println("Generated CREATE query for User:");
        System.out.println(SQLQueryGenerator.generateCreateQuery(User.class));

        System.out.println("\nGenerated INSERT query for User:");
        System.out.println(SQLQueryGenerator.generateInsertQuery(user));

        System.out.println("\nGenerated UPDATE query for User:");
        System.out.println(SQLQueryGenerator.generateUpdateQuery(user, "id=1"));

        System.out.println("\nGenerated DELETE query for User:");
        System.out.println(SQLQueryGenerator.generateDeleteQuery(User.class, "id=1"));

        // Створення запитів SQL для Product
        System.out.println("\nGenerated CREATE query for Product:");
        System.out.println(SQLQueryGenerator.generateCreateQuery(Product.class));

        System.out.println("\nGenerated INSERT query for Product:");
        System.out.println(SQLQueryGenerator.generateInsertQuery(product));

        // Створення SQL запитів для Customer без reflection
        long start = System.nanoTime();
        String nonReflectionQuery = customer.generateInsertQuery();
        long end = System.nanoTime();
        System.out.println("\nGenerated INSERT query for Customer (no reflection):");
        System.out.println(nonReflectionQuery);
        System.out.println("Execution time without reflection: " + (end - start) + " ns");

        // Вимірювання часу
        start = System.nanoTime();
        String reflectionQuery = SQLQueryGenerator.generateInsertQuery(user);
        end = System.nanoTime();
        System.out.println("\nExecution time with reflection: " + (end - start) + " ns");
    }
}

