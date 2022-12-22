package net.golbarg.kankor.model;

public class Location {
    private int id;
    private int type;
    private String zone;
    private int parentId;
    private String name;
    private String PersianName;

    public Location(int id, int type, String zone, int parentId, String name, String persianName) {
        this.id = id;
        this.type = type;
        this.zone = zone;
        this.parentId = parentId;
        this.name = name;
        PersianName = persianName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersianName() {
        return PersianName;
    }

    public void setPersianName(String persianName) {
        PersianName = persianName;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", type=" + type +
                ", zone='" + zone + '\'' +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", PersianName='" + PersianName + '\'' +
                '}';
    }
}
