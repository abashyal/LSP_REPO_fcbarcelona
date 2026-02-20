package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a single product record in the ETL pipeline.
 *
 * <p>This class encapsulates all fields associated with a product:
 * its ID, name, price, category, and the derived price range label.
 * It serves as the core data model passed between pipeline stages.</p>
 *
 * @author Aayush Bashyal
 * @version 1.0
 */
public class Product {

    /** Unique integer identifier for the product. */
    private int productId;

    /** Display name of the product. */
    private String name;

    /** Price of the product, stored with full decimal precision. */
    private BigDecimal price;

    /** Category the product belongs to (e.g., Electronics, Furniture). */
    private String category;

    /**
     * Derived label indicating the price tier of the product.
     * Set during the transformation phase.
     */
    private String priceRange;

    /**
     * Constructs a new Product with the given field values.
     *
     * @param productId the unique integer product ID
     * @param name      the product name
     * @param price     the product price as a BigDecimal
     * @param category  the product category
     */
    public Product(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = "";
    }

    /**
     * Returns the product ID.
     *
     * @return the integer product ID
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Returns the product name.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     *
     * @param name the new product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the product price.
     *
     * @return the price as a BigDecimal
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     *
     * @param price the new price as a BigDecimal
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Returns the product category.
     *
     * @return the category string
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the product category.
     *
     * @param category the new category string
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the price range label (e.g., "Low", "Medium", "High", "Premium").
     *
     * @return the price range label
     */
    public String getPriceRange() {
        return priceRange;
    }

    /**
     * Sets the price range label.
     *
     * @param priceRange the price range label to assign
     */
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    /**
     * Returns a CSV-formatted string representation of this product.
     * Fields are ordered as: ProductID, Name, Price, Category, PriceRange.
     *
     * @return a comma-separated string of all product fields
     */
    @Override
    public String toString() {
        return productId + "," + name + "," + price.toPlainString() + "," + category + "," + priceRange;
    }
}
