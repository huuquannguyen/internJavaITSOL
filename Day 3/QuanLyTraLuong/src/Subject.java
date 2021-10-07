import java.util.Scanner;

public class Subject {
    
    private static int nextId = 1001;

    private String id;
    private String name;
    private int numberOfLesson;
    private int numberOfTheoryLesson;
    private double expense;
    private Scanner input = new Scanner(System.in);

    public Subject() {
        this.id = String.valueOf(nextId).substring(1);
        nextId++;
        if(nextId > 9999){
            throw new RuntimeException("Limited subject");
        }
        System.out.print("Name of subject: ");
        this.name = input.nextLine();
        System.out.print("Number of lesson: ");
        this.numberOfLesson = (int) App.type(input, 1, 200);
        System.out.print("Number of theory lesson: ");
        this.numberOfTheoryLesson = (int) App.type(input, 1, this.numberOfLesson);
        System.out.print("Expense for one theory lesson: ");
        this.expense = App.type(input, 0, 10000);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfLesson() {
        return numberOfLesson;
    }

    public void setNumberOfLesson(int numberOfLesson) {
        this.numberOfLesson = numberOfLesson;
    }

    public int getNumberOfTheoryLesson() {
        return numberOfTheoryLesson;
    }

    public void setNumberOfTheoryLesson(int numberOfTheoryLesson) {
        this.numberOfTheoryLesson = numberOfTheoryLesson;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + numberOfLesson + " - " + numberOfTheoryLesson + " - " + expense;
    }

    
}
