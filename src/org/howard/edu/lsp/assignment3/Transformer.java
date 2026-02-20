package org.howard.edu.lsp.assignment3;

/**
 * Functional interface representing a transformation step in the ETL pipeline.
 *
 * <p>Any class that implements {@code Transformer} is responsible for applying
 * a set of business rules to a {@link Product} object and returning the
 * transformed result. This interface enables polymorphism — different
 * transformer implementations can be swapped in without changing the pipeline.</p>
 *
 * @author Aayush Bashyal
 * @version 1.0
 */
public interface Transformer {

    /**
     * Applies transformation logic to the given product and returns
     * the transformed product.
     *
     * <p>Implementations must not return {@code null}. The same
     * {@link Product} instance may be modified in place and returned.</p>
     *
     * @param product the product to transform
     * @return the transformed product
     */
    Product transform(Product product);
}
