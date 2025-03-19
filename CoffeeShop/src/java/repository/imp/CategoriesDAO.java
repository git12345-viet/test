package repository.imp;

import model.Categories;
import java.util.*;
import repository.ICategoriesDAO;

public class CategoriesDAO extends MyDAO implements ICategoriesDAO{

    // lay ra tat ca category
    @Override
    public List<Categories> getCategories() {
        List<Categories> vectorCate = new ArrayList<>();
        xSql = "SELECT * FROM Categories";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("ID");
                String xName = rs.getString("Name");
                Categories x = new Categories(xId, xName);
                vectorCate.add(x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vectorCate;
    }

    @Override
    public Categories getCategoryByProductId(int productId) {
        xSql = "SELECT c.Name, c.ID FROM Categories c "
                + "INNER JOIN Products p ON c.ID = p.CategoryID "
                + "WHERE p.ID = ?";
        Categories c = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int categoryId = rs.getInt("ID");
                String categoryName = rs.getString("Name");
                c = new Categories(categoryId, categoryName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void main(String[] args) {
        CategoriesDAO cd = new CategoriesDAO();
        System.out.println(cd.getCategories());
    }
}
