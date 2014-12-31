package dukelab.js8ftri.ch3;

import java.lang.reflect.Field;
import java.util.Comparator;

public class Exercise09 {

    static class Person {
        final String firstname;
        final String lastname;
        private Person(String firstname, String lastname) {
            this.firstname = firstname;
            this.lastname = lastname;
        }
    }

    public static void main(String[] args) {
        Person p1 = new Person("Duke", "Jeong");
        Person p2 = new Person("Duke", "Java");
        Person p3 = new Person("James", "Jeong");

        System.out.println("firstname");
        Comparator<Person> c1 = lexicographicComparator("firstname");
        System.out.println(c1.compare(p1, p2));
        System.out.println(c1.compare(p1, p3));

        System.out.println("lastname");
        Comparator<Person> c2 = lexicographicComparator("lastname");
        System.out.println(c2.compare(p1, p2));
        System.out.println(c2.compare(p1, p3));

        System.out.println("lastname, firstname");
        Comparator<Person> c3 = lexicographicComparator("lastname", "firstname");
        System.out.println(c3.compare(p2, p3));
        System.out.println(c3.compare(p1, p1));
    }

    private static <T> Comparator<T> lexicographicComparator(String... fieldNames) {
        return (o1, o2) -> {
            try {
                for (String fieldName : fieldNames) {
                    Field f = o1.getClass().getDeclaredField(fieldName);
                    f.setAccessible(true);

                    String value1 = (String) f.get(o1);
                    String value2 = (String) f.get(o2);
                    if (value1 == null &&  value2 == null) {
                        continue;
                    }
                    if (value1 == null && value2 != null) {
                        return 1;
                    }
                    if (value1 != null && value2 == null) {
                        return -1;
                    }
                    int diff = value1.compareTo(value2);
                    if (diff != 0) {
                        return diff;
                    }
                }
            } catch (ReflectiveOperationException e) {
            }
            return 0;
        };
    }

}
