package teste.carrinho.util;
 
import javax.servlet.http.HttpServletRequest;
 
import teste.carrinho.modelos.CartInfo;
 
public class Utils {
 
    // Produtos no carrinho, armazenados em sessão.
    public static CartInfo getCartInSession(HttpServletRequest request) {
 
        // Pega o carrinho da sessao atual.
        CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCart");
        
        // Se etiver nulo, crie isso.
        if (cartInfo == null) {
            cartInfo = new CartInfo();
            
            // E Armazena a sessão.
            request.getSession().setAttribute("myCart", cartInfo);
        }
 
        return cartInfo;
    }
 
    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("myCart");
    }
 
    public static void storeLastOrderedCartInSession(HttpServletRequest request, CartInfo cartInfo) {
        request.getSession().setAttribute("lastOrderedCart", cartInfo);
    }
    
    public static CartInfo getLastOrderedCartInSession(HttpServletRequest request) {
        return (CartInfo) request.getSession().getAttribute("lastOrderedCart");
    }
 
}