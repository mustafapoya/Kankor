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
