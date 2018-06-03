package com.itheima.web.servlet;

import com.alibaba.fastjson.JSONObject;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.service.impl.ProductServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getParameter("method");
        try {
            switch (method) {
                case "findAll":
                    String allPro = findAll(request, response);
                    response.getWriter().write(allPro);
                    break;
                case "editPro":
                    editPro(request, response);
                    break;
                case "addPro":
                    addPro(request, response);
                    break;
                case "delPro":
                    delPro(request, response);
                    break;
                case "findPro":
                    findPro(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String findPro(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        //新建Service对象
        ProductService productService = new ProductServiceImpl();
        Product product = productService.findPro(id);
        String jsonString = JSONObject.toJSONString(product);
        response.getWriter().write(jsonString);
        return jsonString;
    }

    private void delPro(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        //新建Service对象
        ProductService productService = new ProductServiceImpl();
        int row = productService.delPro(id);
        if (row > 0) {
            response.getWriter().write("删除成功");
        } else {
            response.getWriter().write("删除失败");
        }
        System.out.println(getClass().getSimpleName() + "==del==" + row);
    }

    private void addPro(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, SQLException, IOException {
        Product product = new Product();
        BeanUtils.populate(product, request.getParameterMap());
        //新建Service对象
        ProductService productService = new ProductServiceImpl();
        int row = productService.addPro(product);
        System.out.println(getClass().getSimpleName() + "==add==" + row);
        if (row > 0) {
            response.getWriter().write("添加成功");
        } else {
            response.getWriter().write("添加失败");
        }
    }

    private void editPro(HttpServletRequest request, HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException, SQLException {
        Product product = new Product();
        BeanUtils.populate(product, request.getParameterMap());
        //新建Service对象
        ProductService productService = new ProductServiceImpl();
        int row = productService.editPro(product);
        System.out.println(getClass().getSimpleName() + "==edit==" + row);
        if (row > 0) {
            response.getWriter().write("修改成功");
        } else {
            response.getWriter().write("修改成功");
        }
    }

    /**
     * 搜需所有商品
     *
     * @param request
     * @param response
     * @return
     */
    private String findAll(HttpServletRequest request, HttpServletResponse response) {
        ProductService productService = new ProductServiceImpl();
        List<Product> list = null;
        try {
            list = productService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String jsonString = JSONObject.toJSONString(list);
        return jsonString;
    }
}
