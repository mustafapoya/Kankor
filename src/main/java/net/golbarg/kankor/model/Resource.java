package net.golbarg.kankor.model;

public class Resource {
    private int id;
    private ResourceCategory category;
    private String name;
    private String fileName;
    private String description;
    private double size;

    public Resource(int id, ResourceCategory category, String name, String fileName, String description, double size) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.fileName = fileName;
        this.description = description;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ResourceCategory getCategory() {
        return category;
    }

    public void setCategory(ResourceCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", fileName='" + fileName + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                '}';
    }
}
