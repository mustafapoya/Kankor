package net.golbarg.kankor.model;

public class UserKankorForm {
    private int id;
    private User user;
    private String fatherName;
    private String grandFatherName;
    private Location currentLocation;
    private String currentDistrict;
    private String currentVillage;
    private Location originLocation;
    private String originDistrict;
    private String originVillage;
    private int graduateYear;
    private String tazkiraId;
    private Language language;

    public UserKankorForm(int id, User user, String fatherName, String grandFatherName, Location currentLocation,
                          String currentDistrict, String currentVillage, Location originLocation, String originDistrict,
                          String originVillage, int graduateYear, String tazkiraId, Language language) {
        this.id = id;
        this.user = user;
        this.fatherName = fatherName;
        this.grandFatherName = grandFatherName;
        this.currentLocation = currentLocation;
        this.currentDistrict = currentDistrict;
        this.currentVillage = currentVillage;
        this.originLocation = originLocation;
        this.originDistrict = originDistrict;
        this.originVillage = originVillage;
        this.graduateYear = graduateYear;
        this.tazkiraId = tazkiraId;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getTazkiraId() {
        return tazkiraId;
    }

    public void setTazkiraId(String tazkiraId) {
        this.tazkiraId = tazkiraId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "UserKankorForm{" +
                "id=" + id +
                ", user=" + user +
                ", fatherName='" + fatherName + '\'' +
                ", grandFatherName='" + grandFatherName + '\'' +
                ", currentLocation=" + currentLocation +
                ", currentDistrict='" + currentDistrict + '\'' +
                ", currentVillage='" + currentVillage + '\'' +
                ", originLocation=" + originLocation +
                ", originDistrict='" + originDistrict + '\'' +
                ", originVillage='" + originVillage + '\'' +
                ", graduateYear=" + graduateYear +
                ", tazkiraId='" + tazkiraId + '\'' +
                ", language=" + language +
                '}';
    }
}
