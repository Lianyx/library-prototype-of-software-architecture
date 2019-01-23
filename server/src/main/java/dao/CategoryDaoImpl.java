package dao;

import object.po.Category;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.util.Util.*;

public class CategoryDaoImpl implements CategoryDao {
    private PreparedStatement insertQuery, selectByIdQuery;

    public CategoryDaoImpl() throws SQLException {
        this.insertQuery = getStatement("INSERT INTO Category (id, name) VALUES (?, ?);");
        this.selectByIdQuery = getStatement("" +
                "SELECT *\n" +
                "FROM Category\n" +
                "WHERE id = ?;");
    }

    @Override
    public void insert(Category category) throws SQLException {
        voidQuery(insertQuery, category.getId(), category.getName());
    }

    @Override
    public Category selectById(String id) throws SQLException {
        List<Category> categories = retrieveQuery(Category.class, selectByIdQuery, id);
        if (categories.isEmpty()) {
            return null;
        }
        return categories.get(0);
    }
}
