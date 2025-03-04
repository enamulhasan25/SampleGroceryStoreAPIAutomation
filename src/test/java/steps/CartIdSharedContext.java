package steps;

public class CartIdSharedContext {
    private static CartIdSharedContext instance;
    private String cartId;

    private CartIdSharedContext() {
    }

    public static CartIdSharedContext getInstance() {
        if (instance == null) {
            instance = new CartIdSharedContext();
        }
        return instance;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
}