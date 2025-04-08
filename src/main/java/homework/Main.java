package homework;

import homework.entity.Book;
import homework.entity.Student;
import homework.utility.CustomHashMap;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        map.put("apple", 10);
        map.put("pear", 15);
        map.put("banana", 30);
        map.put("cherry", 7);

        System.out.println(map.get("banana"));
        System.out.println(map.size());
        System.out.println(map.remove("pear"));
        System.out.println(map.size());
        map.clear();
        System.out.println(map.size());
        System.out.println(map);

        List<Book> listOfBooks1 = List.of(new Book("Effective Java", "J. Bloch", 2001),
                new Book("Head First Java", "K. Sierra", 2012));
        List<Book> listOfBooks2 = List.of(new Book("Core Java for the Impatient", "C. Horstmann", 2015),
                new Book("Java For Dummies", "B. Burd", 2006));
        List<Book> listOfBooks3 = List.of(new Book("Thinking in Java", "B. Eckel", 1998),
                new Book("Clean Code", "R. Martin", 2008));

        Student student1 = new Student("Maven", 21, listOfBooks1);
        Student student2 = new Student("Gradle", 19, listOfBooks2);
        Student student3 = new Student("April", 20, listOfBooks3);

        List<Student> students = List.of(student1, student2, student3);

        Optional<Integer> yearOfPublication = students.stream()
                .flatMap(student -> student.getLibrarySubscription().stream())
                .map(Book::getYearOfPublication)
                .min(Comparator.naturalOrder());

        yearOfPublication.ifPresentOrElse(
                year -> System.out.println("Год выпуска самой старой книги: " + year),
                () -> System.out.println("Список книг пуст")
        );
    }
}
