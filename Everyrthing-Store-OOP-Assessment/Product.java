// TAMANJI CHE CLAUD, MMUID 22460227
/**
 *
 * The Product class represents a product with an id, sku, description, category, and price.
 */
public class Product {
    private int id;
    private String sku;
    private String description;
    private String category;
    private int price;

      /**
     * Constructs a new Product object.
     *
     * @param id 
     * @param sku
     * @param description 
     * @param category 
     * @param price 
     */
    public Product(int id, String sku, String description, String category, int price) {
        this.id = id;
        this.sku = sku;
        this.description = description;
        this.category = category;
        this.price = price;
    }

      /**
     *
     * @return id
     */
    public int getId() {
        return id;
    }

      /**
     *
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

      /**
     *
     * @return SKU
     */
    public String getSku() {
        return sku;
    }

      /**
     *
     * @param sku
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

      /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

      /**
     *
     * @return category
     */
    public String getCategory() {
        return category;
    }

      /**
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

      /**
     *
     * @return the price of the product
     */
    public int getPrice() {
        return price;
    }

      /**
     *
     * @param price the new price of the product
     */
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return " | " + this.id + " | " + this.sku + " | " + this.description + " | " + this.category + " | " + this.price + " | ";
    }
}
