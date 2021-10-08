import java.util.Scanner;

public class App {
    private static Lecturer[] lecturers = new Lecturer[1000];
    private static int currIndexOfLecturer = 0;

    private static Subject[] subjects = new Subject[1000];
    private static int currIndexOfSubject = 0;

    private static LecturerSubject[] lecturerSubjectRecords = new LecturerSubject[100];
    private static int currIndexOfLecturerSubjectRecord = 0;
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        menu(input);
    }

    public static void menu(Scanner input){
        System.out.println("-------------------------------------------------");
        System.out.println("Lecturers salary payment management");
        System.out.println("-------------------------------------------------");
        System.out.println("1. Add lecturer");
        System.out.println("2. Print out all lecturers");
        System.out.println("3. Add subject");
        System.out.println("4. Print out all subject");
        System.out.println("5. Create teaching management for each lecturer");
        System.out.println("6. Print out all teaching management");
        System.out.println("7. Sorting lecturer-subject register by lecturer's name");
        System.out.println("8. Sorting lecturer-subject register by the number of lession");
        System.out.println("9. Print salary payment for all lecturers");
        System.out.println("0. Exit");
        int part = (int) type(input, 0, 9);
        switch(part){
            case 0:
                System.out.println("GOOD BYE !!!");
                break;
            case 1:
                addLecturer(input);
                break;
            case 2:
                printAllLecturers(input);
                break;
            case 3:
                addSubject(input);
                break;
            case 4:
                printAllSubject(input);
                break;
            case 5:
                createLecturerSubjectRecordsForEachLecturer(input);
                break;
            case 6:
                printAllLecturerSubjectRecords(input);
                break;
            case 7:
                sortingLectureSubjectRecordListByLecturerName(input);
                break;
            case 8:
                sortingLectureSubjectRecordListByTotalLessionPerSubject(input);
                break;
            case 9:
                salaryPayment(input);
                break;
        }
    }

    public static void addLecturer(Scanner input){
        Lecturer lecturer = new Lecturer();
        lecturers[currIndexOfLecturer] = lecturer;
        currIndexOfLecturer++;
        System.out.println("Adding new lecturer succesfully");
        menu(input);
    }

    public static void printAllLecturers(Scanner input){
        System.out.println("Lecturer list:");
        for (Lecturer lecturer : lecturers) {
            if(lecturer == null){
                break;
            }
            System.out.println(lecturer);
        }
        menu(input);
    }

    public static void addSubject(Scanner input){
        Subject subject = new Subject();
        subjects[currIndexOfSubject] = subject;
        currIndexOfSubject++;
        menu(input);
    }

    public static void printAllSubject(Scanner input){
        System.out.println("Subject list:");
        for (Subject subject : subjects) {
            if(subject == null){
                break;
            }
            System.out.println(subject);
        }
        menu(input);
    }

    public static void createLecturerSubjectRecordsForEachLecturer(Scanner input){
        for (Lecturer lecturer : lecturers) {
            if(lecturer == null){
                break;
            }
            int numberOfLesson = countLessionHaveTaken(lecturer);
            if(numberOfLesson >= 200){
                System.out.println("Cannot take any lesson");
                break;
            }
            System.out.println("Lecturer: " + lecturer.getId() + " - " + lecturer.getName());
            System.out.println("Can take " + (200 - numberOfLesson) + " more lessions");
            System.out.print("Type number of subject wanna take: ");
            int number = (int) type(input, 1, 200);
            for(int i = 1; i <= number; i++){
                Subject subject = findSubjectById(input, i);
                if(subject != null){
                    int recordIndex = findLecturerSubjectRecords(lecturer, subject);
                    if(recordIndex != -1){
                        if(lecturerSubjectRecords[recordIndex].getClassNumber() == 3){
                            System.out.println("Cannot take more classes for this subject");
                        }else{
                            System.out.print("Type the number of class you want to take: ");
                            int classNumber = takingNumberOfClass(lecturerSubjectRecords[recordIndex].getClassNumber(),
                                                                    numberOfLesson, subject, input);
                            if(classNumber != 0){
                                int newClassNumber = lecturerSubjectRecords[recordIndex].getClassNumber();
                                lecturerSubjectRecords[recordIndex].setClassNumber(newClassNumber + classNumber);    
                                System.out.println("Adding class for this subject successfully");
                            }else{
                                System.out.println("Not adding class for this subject");
                            }
                        }
                    }else{
                        System.out.print("Type the number of class you want to take: ");
                        int classNumber = takingNumberOfClass(0,numberOfLesson, subject, input);
                        if(classNumber != 0){
                            lecturerSubjectRecords[currIndexOfLecturerSubjectRecord] = 
                                                new LecturerSubject(lecturer, subject, classNumber);
                            currIndexOfLecturerSubjectRecord++;
                            System.out.println("Taking subject and classes successfully");
                            if(currIndexOfLecturerSubjectRecord == lecturerSubjectRecords.length - 1){
                                lecturerSubjectRecords = addingSpace(lecturerSubjectRecords);
                            }
                        }else{
                            System.out.println("Not take this subject");
                        }
                    }
                }
            }
            System.out.println("-------------------------------------------");
        }
        menu(input);
    }

    public static void printAllLecturerSubjectRecords(Scanner input){
        for (LecturerSubject lecturerSubject : lecturerSubjectRecords) {
            if(lecturerSubject == null){
                break;
            }
            System.out.println(lecturerSubject);
        }
        menu(input);
    }

    public static void sortingLectureSubjectRecordListByLecturerName(Scanner input){
        for (int i = 0; i < lecturerSubjectRecords.length - 1; i++) {
            if(lecturerSubjectRecords[i] == null){
                break;
            }
            int minIndex = i;
            for(int j = i + 1; j < lecturerSubjectRecords.length; j++){
                if(lecturerSubjectRecords[j] == null){
                    break;
                }
                int compareResult = lecturerSubjectRecords[minIndex].getLecturer().getName()
                                    .compareTo(lecturerSubjectRecords[j].getLecturer().getName());
                if(compareResult > 0){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                LecturerSubject temp = lecturerSubjectRecords[i];
                lecturerSubjectRecords[i] = lecturerSubjectRecords[minIndex];
                lecturerSubjectRecords[minIndex] = temp;
            }
        }
        System.out.println("Sort by lecturer name: ");
        printAllLecturerSubjectRecords(input);
    }

    public static void sortingLectureSubjectRecordListByTotalLessionPerSubject(Scanner input){
        for (int i = 0; i < lecturerSubjectRecords.length - 1; i++) {
            if(lecturerSubjectRecords[i] == null){
                break;
            }
            int maxIndex = i;
            for(int j = i + 1; j < lecturerSubjectRecords.length; j++){
                if(lecturerSubjectRecords[j] == null){
                    break;
                }
                int compareResult = lecturerSubjectRecords[maxIndex].getSubject().getNumberOfLesson() * lecturerSubjectRecords[maxIndex].getClassNumber()
                                    - lecturerSubjectRecords[j].getSubject().getNumberOfLesson() * lecturerSubjectRecords[j].getClassNumber();
                if(compareResult < 0){
                    maxIndex = j;
                }
            }
            if(maxIndex != i){
                LecturerSubject temp = lecturerSubjectRecords[i];
                lecturerSubjectRecords[i] = lecturerSubjectRecords[maxIndex];
                lecturerSubjectRecords[maxIndex] = temp;
            }
        }
        System.out.println("Sort by total lessions for each subject: ");
        printAllLecturerSubjectRecords(input);
    }

    public static void salaryPayment(Scanner input){
        for (Lecturer lecturer : lecturers) {
            if(lecturer == null){
                break;
            }
            double totalSalary = 0;
            System.out.println(lecturer.getId() + " - " + lecturer.getName());
            LecturerSubject[] records = findLecturerSubjectRecordsByLecturer(lecturer);
            for (LecturerSubject lecturerSubject : records) {
                if(lecturerSubject == null){
                    break;
                }
                int practicalLessions = lecturerSubject.getSubject().getNumberOfLesson() - 
                                        lecturerSubject.getSubject().getNumberOfTheoryLesson();
                double salary = (lecturerSubject.getSubject().getExpense() * lecturerSubject.getSubject().getNumberOfTheoryLesson() +
                                lecturerSubject.getSubject().getExpense() * practicalLessions * 0.7)
                                 * lecturerSubject.getClassNumber();
                totalSalary += salary;
                System.out.println( "Subject: " + lecturerSubject.getSubject().getName() +
                                    " | Class: " + lecturerSubject.getClassNumber() +
                                    " | Theory lessions: " + lecturerSubject.getSubject().getNumberOfTheoryLesson() +
                                    " | Practical lessions: " + practicalLessions + 
                                    " | Expense: " + lecturerSubject.getSubject().getExpense() + 
                                    " | Salary: " + salary );
            }
            System.out.println("Total salary: " + totalSalary);
        }
        menu(input);
    }

    public static double type(Scanner input, int min, int max){
        double option = -1;
        while(option == -1){
            try {
                option = input.nextDouble();
                if(option < min || option > max){
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                System.out.print("Input value must be between " + min + " - " + max + ". Type again: ");
                input.nextLine();
                option = -1;
            }
        }
        input.nextLine();
        return option;
    }

    private static LecturerSubject[] findLecturerSubjectRecordsByLecturer(Lecturer lecturer){
        LecturerSubject[] records = new LecturerSubject[lecturerSubjectRecords.length];
        int recordsIndex = 0;
        for(int i = 0; i < lecturerSubjectRecords.length; i++){
            if(lecturerSubjectRecords[i] == null){
                break;
            }
            if(lecturerSubjectRecords[i].getLecturer() == lecturer){
                records[recordsIndex] = lecturerSubjectRecords[i];
                recordsIndex++;
            }
        }
        return records;
    }

    private static int countLessionHaveTaken(Lecturer lecturer){
        int count = 0;
        LecturerSubject[] records = findLecturerSubjectRecordsByLecturer(lecturer);
        for (LecturerSubject lecturerSubject : records) {
            if(lecturerSubject == null){
                break;
            }
            count += lecturerSubject.getSubject().getNumberOfLesson() * lecturerSubject.getClassNumber();
        }
        return count;
    }

    private static Subject findSubjectById(Scanner input, int index){
        boolean reType = true;
        Subject subject = null;
        while(reType){
            System.out.print("Type subject id for subject " + index + ": ");
            String subjectId = input.next();
            for (Subject s : subjects) {
                if(s == null){
                    break;
                }
                if(s.getId().equals(subjectId)){
                    subject = s;
                    break;
                }
            }
            if(subject != null){
                reType = false;
            }else{
                System.out.println("Cannot find this subject");
                System.out.println("Do you want to find again?");
                System.out.print("Type 'Y' to keep finding, other buttom to stop: ");
                if(!input.next().equalsIgnoreCase("y")){
                    reType = false;
                }
            }
        }
        return subject;
    }

    private static int takingNumberOfClass(int classNum, int totalLessionHaveTaken, Subject subject, Scanner input){
        int inputNumberOfClass = -1;
        while(inputNumberOfClass == -1){
            inputNumberOfClass = (int) type(input, 1, 3 - classNum);
            if(totalLessionHaveTaken + subject.getNumberOfLesson() * inputNumberOfClass > 200){
                System.out.println("Total lession have taken / Maximun lession can take: " + totalLessionHaveTaken + " / 200");
                System.out.println("The number of lessions of this subject: " + subject.getNumberOfLesson());
                System.out.println("Cannot take this subject with " + inputNumberOfClass + " classes");
                System.out.println("Do you want to choose the number of class again? ('Y' to choose again): ");
                String again = input.next();
                if(!again.equalsIgnoreCase("y")){
                    inputNumberOfClass = 0;
                }else{
                    inputNumberOfClass = -1;
                }
            }
        }
        return inputNumberOfClass;
    }
    
    private static LecturerSubject[] addingSpace(LecturerSubject[] oldArray){
        LecturerSubject[] newArray = new LecturerSubject[oldArray.length * 2];
        for(int i = 0; i < oldArray.length; i++){
            newArray[i] = oldArray[i];
        }
        return newArray;
    }

    private static int findLecturerSubjectRecords(Lecturer lecturer, Subject subject){
        for (int i = 0; i < lecturerSubjectRecords.length; i++) {
            if(lecturerSubjectRecords[i] == null){
                break;
            }
            if(lecturerSubjectRecords[i].getLecturer() == lecturer && lecturerSubjectRecords[i].getSubject() == subject){
                return i;
            }
        }
        return -1;
    }
 }
