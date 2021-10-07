public class LecturerSubject {

    private Lecturer lecturer;
    private Subject subject;
    private int classNumber;


    public LecturerSubject() {

    }


    public LecturerSubject(Lecturer lecturer, Subject subject, int classNumber) {
        this.lecturer = lecturer;
        this.subject = subject;
        this.classNumber = classNumber;
    }



    public Lecturer getLecturer() {
        return lecturer;
    }
    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    public int getClassNumber() {
        return classNumber;
    }
    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    @Override
    public String toString() {
        return lecturer.getName() + " - " + subject.getName() + " - " + classNumber + " - " + subject.getNumberOfLesson() * classNumber;
    }



}
