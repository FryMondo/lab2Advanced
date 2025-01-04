import java.lang.reflect.Field;

// Генератор запитів SQL
class SQLQueryGenerator {

    public static <T> String generateCreateQuery(Class<T> clazz) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        StringBuilder query = new StringBuilder("CREATE TABLE " + tableAnnotation.name() + " (");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                query.append(column.name()).append(" VARCHAR(255), ");
            }
        }
        query.setLength(query.length() - 2);
        query.append(");");

        return query.toString();
    }

    public static <T> String generateInsertQuery(T obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        StringBuilder query = new StringBuilder("INSERT INTO " + tableAnnotation.name() + " (");
        StringBuilder values = new StringBuilder("VALUES (");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                query.append(column.name()).append(", ");
                field.setAccessible(true);
                values.append("'").append(field.get(obj)).append("', ");
            }
        }
        query.setLength(query.length() - 2);
        values.setLength(values.length() - 2);
        query.append(") ").append(values).append(");");

        return query.toString();
    }

    public static <T> String generateUpdateQuery(T obj, String condition) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        StringBuilder query = new StringBuilder("UPDATE " + tableAnnotation.name() + " SET ");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                field.setAccessible(true);
                query.append(column.name()).append("='").append(field.get(obj)).append("', ");
            }
        }
        query.setLength(query.length() - 2);
        query.append(" WHERE ").append(condition).append(";");

        return query.toString();
    }

    public static <T> String generateDeleteQuery(Class<T> clazz, String condition) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        return "DELETE FROM " + tableAnnotation.name() + " WHERE " + condition + ";";
    }
}
