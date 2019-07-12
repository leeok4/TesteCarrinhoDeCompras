package teste.carrinho.dao;
 
import java.util.List;
 
import teste.carrinho.modelos.CartInfo;
import teste.carrinho.modelos.OrderDetailInfo;
import teste.carrinho.modelos.OrderInfo;
import teste.carrinho.modelos.PaginationResult;
 
public interface OrderDAO {
 
    public void saveOrder(CartInfo cartInfo);
 
    public PaginationResult<OrderInfo> listOrderInfo(int page,
            int maxResult, int maxNavigationPage);
    
    public OrderInfo getOrderInfo(String orderId);
    
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId);
 
}