package net.golbarg.kankor.model;

public class UniversityFaculty {
    private int id;
    private University university;
    private String name;
    private String department;
    private String code;
    private int minimumGrade;
    private int admission;

    public UniversityFaculty(int id, University university, String name, String department,
                             String code, int minimumGrade, int admission) {
        this.id = id;
        this.university = university;
        this.name = name;
        this.department = department;
        this.code = code;
        this.minimumGrade = minimumGrade;
        this.admission = admission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getMinimumGrade() {
        return minimumGrade;
    }

    public void setMinimumGrade(int minimumGrade) {
        this.minimumGrade = minimumGrade;
    }

    public int getAdmission() {
        return admission;
    }

    public void setAdmission(int admission) {
        this.admission = admission;
    }

    @Override
    public String toString() {
        return "UniversityFaculty{" +
                "id=" + id +
                ", university=" + university +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", code='" + code + '\'' +
                ", minimumGrade=" + minimumGrade +
                ", admission=" + admission +
                '}';
    }
}
