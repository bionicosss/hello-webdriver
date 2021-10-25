import builder.CartPayload;
import builder.Product;
import org.junit.jupiter.api.Test;

public class ApiTestingHomework {
    @Test
    public void addProductToCart(){
        CartPayload cartPayload = new CartPayload();
        Product product = new Product();
        product.setId("333");
        cartPayload.setProduct(product);
        System.out.println(cartPayload.getProduct().getId());
}

}
