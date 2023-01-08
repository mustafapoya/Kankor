package net.golbarg.kankor.model;

public class KankorResult {
    private int id;
    private String kankorId;
    private String name;
    private String fatherName;
    private String grandFatherName;
    private String schoolName;
    private String province;
    private double score;
    private String result;

    public KankorResult(int id, String kankorId, String name, String fatherName, String grandFatherName,
                        String schoolName, String province, double score, String result) {
        this.id = id;
        this.kankorId = kankorId;
        this.name = name;
        this.fatherName = fatherName;
        this.grandFatherName = grandFatherName;
        this.schoolName = schoolName;
        this.province = province;
        this.score = score;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKankorId() {
        return kankorId;
    }

    public void setKankorId(String kankorId) {
        this.kankorId = kankorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGrandFatherName() {
        return grandFatherName;
    }

    public void setGrandFatherName(String grandFatherName) {
        this.grandFatherName = grandFatherName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "KankorResult{" +
                "id=" + id +
                ", kankorId='" + kankorId + '\'' +
                ", name='" + name + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", grandFatherName='" + grandFatherName + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", province='" + province + '\'' +
                ", score=" + score +
                ", result='" + result + '\'' +
                '}';
    }
}
