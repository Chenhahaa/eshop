import com.doriss.dao.ProductDao;
import com.doriss.daoimpl.ProductDaoImpl;
import com.doriss.entity.Product;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chenhaha on 2018/10/10.
 */
public class ProductDaoImplTest {
    @Test
    public void add() throws Exception {
        ProductDao productDao = new ProductDaoImpl();
        Product product = new Product();

    }

    @org.junit.Test
    public void delete() throws Exception {

    }

    @org.junit.Test
    public void update() throws Exception {

    }
    @Test
    public void queryById()throws Exception{
        ProductDao productDao = new ProductDaoImpl();
        Product product =productDao.queryById("1");
        Assert.assertNotNull(product);
    }
}