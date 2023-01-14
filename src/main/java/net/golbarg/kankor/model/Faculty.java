package net.golbarg.kankor.model;

public class Faculty {
    private int id;
    private String name;
    private String department;
    private String code;
    private int minimumGrade;
    private int universityId;
    private int admission;

    public Faculty(int id, String name, String department, String code, int minimumGrade, int universityId, int admission) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.code = code;
        this.minimumGrade = minimumGrade;
        this.universityId = universityId;
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

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
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
                ", universityId=" + universityId +
                ", admission=" + admission +
                '}';
    }
}
