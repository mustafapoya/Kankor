package net.golbarg.kankor.model;

public class KankorForm {
    private int id;
    private int userId;
    private String name;
    private String lastName;
    private String fatherName;
    private String grandFatherName;
    private Location currentLocation;
    private String currentDistrict;
    private String currentVillage;
    private Location originLocation;
    private String originDistrict;
    private String originVillage;
    private int graduateYear;
    private String schoolName;
    private String tazkiraId;
    private Gender gender;
    private Language language;

    public KankorForm(int id, int userId, String name, String lastName, String fatherName, String grandFatherName, Location currentLocation, String currentDistrict, String currentVillage, Location originLocation, String originDistrict, String originVillage, int graduateYear, String schoolName, String tazkiraId, Gender gender, Language language) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.grandFatherName = grandFatherName;
        this.currentLocation = currentLocation;
        this.currentDistrict = currentDistrict;
        this.currentVillage = currentVillage;
        this.originLocation = originLocation;
        this.originDistrict = originDistrict;
        this.originVillage = originVillage;
        this.graduateYear = graduateYear;
        this.schoolName = schoolName;
        this.tazkiraId = tazkiraId;
        this.gender = gender;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getCurrentDistrict() {
        return currentDistrict;
    }

    public void setCurrentDistrict(String currentDistrict) {
        this.currentDistrict = currentDistrict;
    }

    public String getCurrentVillage() {
        return currentVillage;
    }

    public void setCurrentVillage(String currentVillage) {
        this.currentVillage = currentVillage;
    }

    public Location getOriginLocation() {
        return originLocation;
    }

    public void setOriginLocation(Location originLocation) {
        this.originLocation = originLocation;
    }

    public String getOriginDistrict() {
        return originDistrict;
    }

    public void setOriginDistrict(String originDistrict) {
        this.originDistrict = originDistrict;
    }

    public String getOriginVillage() {
        return originVillage;
    }

    public void setOriginVillage(String originVillage) {
        this.originVillage = originVillage;
    }

    public int getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(int graduateYear) {
        this.graduateYear = graduateYear;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getTazkiraId() {
        return tazkiraId;
    }

    public void setTazkiraId(String tazkiraId) {
        this.tazkiraId = tazkiraId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "KankorForm{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", grandFatherName='" + grandFatherName + '\'' +
                ", currentLocation=" + currentLocation +
                ", currentDistrict='" + currentDistrict + '\'' +
                ", currentVillage='" + currentVillage + '\'' +
                ", originLocation=" + originLocation +
                ", originDistrict='" + originDistrict + '\'' +
                ", originVillage='" + originVillage + '\'' +
                ", graduateYear=" + graduateYear +
                ", schoolName='" + schoolName + '\'' +
                ", tazkiraId='" + tazkiraId + '\'' +
                ", gender=" + gender +
                ", language=" + language +
                '}';
    }
}
