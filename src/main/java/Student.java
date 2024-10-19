import java.util.List;

public class Student {
    private String name;
    private int age;
    private List<Book> librarySubscription;

    public Student(String name, int age, List<Book> librarySubscription) {
        this.name = name;
        this.age = age;
        this.librarySubscription = librarySubscription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getLibrarySubscription() {
        return librarySubscription;
    }

    public void setLibrarySubscription(List<Book> librarySubscription) {
        this.librarySubscription = librarySubscription;
    }

}