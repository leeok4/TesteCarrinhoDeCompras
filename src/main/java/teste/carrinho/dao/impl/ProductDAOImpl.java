package teste.carrinho.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import teste.carrinho.dao.ProductDAO;
import teste.carrinho.entidades.Product;
import teste.carrinho.modelos.PaginationResult;
import teste.carrinho.modelos.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

// Transactional for Hibernate
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Product findProduct(String code) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.eq("code", code));
		return (Product) crit.uniqueResult();
	}

	public ProductInfo findProductInfo(String code) {
		Product product = this.findProduct(code);
		if (product == null) {
			return null;
		}
		return new ProductInfo(product.getCode(), product.getName(), product.getPrice());
	}

	public void save(ProductInfo productInfo) {
		String code = productInfo.getCode();

		Product product = null;

		boolean isNew = false;
		if (code != null) {
			product = this.findProduct(code);
		}
		if (product == null) {
			isNew = true;
			product = new Product();
			product.setCreateDate(new Date());
		}
		product.setCode(code);
		product.setName(productInfo.getName());
		product.setPrice(productInfo.getPrice());

		if (productInfo.getFileData() != null) {
			byte[] image = productInfo.getFileData().getBytes();
			if (image != null && image.length > 0) {
				product.setImage(image);
			}
		}
		if (isNew) {
			this.sessionFactory.getCurrentSession().persist(product);
		}
		// Se houver erro no banco de dados, as exce��es ser�o descartadas imediatamente
		this.sessionFactory.getCurrentSession().flush();
	}

	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		String sql = "Select new " + ProductInfo.class.getName() //
				+ "(p.code, p.name, p.price) " + " from "//
				+ Product.class.getName() + " p ";
		if (likeName != null && likeName.length() > 0) {
			sql += " Where lower(p.name) like :likeName ";
		}
		sql += " order by p.createDate desc ";
		//
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery(sql);
		if (likeName != null && likeName.length() > 0) {
			query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
		}
		return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
	}

	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
		return queryProducts(page, maxResult, maxNavigationPage, null);
	}

}