package spring.mvc.analyze.dao;

import java.util.List;
import java.util.Optional;

import spring.mvc.analyze.entity.Level;
import spring.mvc.analyze.entity.Product;
import spring.mvc.analyze.entity.ProductSubType;
import spring.mvc.analyze.entity.ProductType;

public interface ProductTypeDao {

	List<ProductType> findAllProductTypes();
	List<ProductSubType> findAllProductSubTypes();
	
	Optional<ProductType> findProductTypeById(Integer productTypeId);
	Optional<ProductSubType> findProductSubTypeById(Integer productSubTypeId);
	
	public int addProductType (ProductType productType);
	public int addProductSubType (ProductSubType productSubType);
	
	public int updateProductType(ProductType productType);
	public int updateProductSubType(ProductSubType productSubType);
	
	public int removeProductTypeById(Integer id);
	public int removeProductSubTypeById(Integer id);
	
}
