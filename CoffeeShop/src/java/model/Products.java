package model;

/**
 *
 * @author havie
 */
public class Products {

    private int ID;
    private String name;
    private String description;
    private double price;
    private String image;
    private int categoryID;

    // Constructors
    public Products() {
    }

    public Products(int ID, String name, String description, double price, String image, int categoryID) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.categoryID = categoryID;
    }
    
    

    public Products(String name, String description, double price, String image, int categoryID) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.categoryID = categoryID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return "Products{" + "ID=" + ID + ", name=" + name + ", description=" + description + ", price=" + price + ", image=" + image + ", categoryID=" + categoryID + '}';
    }
}
