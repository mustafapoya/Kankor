package net.golbarg.kankor.model;

public class Faculty {
    private int id;
    private String name;
    private String department;
    private String code;
    private int minimumGrade;
    private University university;
    private int admission;

    public Faculty(int id, String name, String department, String code, int minimumGrade, University university, int admission) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.code = code;
        this.minimumGrade = minimumGrade;
        this.university = university;
        this.admission = admission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public int getAdmission() {
        return admission;
    }

    public void setAdmission(int admission) {
        this.admission = admission;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", code='" + code + '\'' +
                ", minimumGrade=" + minimumGrade +
                ", university=" + university +
                ", admission=" + admission +
                '}';
    }
}
