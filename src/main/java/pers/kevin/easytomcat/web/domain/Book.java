package pers.kevin.easytomcat.web.domain;

public class Book {
    private String id;
    private String name;
    private float price;
    private int count;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public Book(String id, String name, float price, int count) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
    }
    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", price=" + price + ", count=" + count + "]";
    }
    
    
    
    
}
