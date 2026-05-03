package com.example.Il.Forno.Classico.Pizzeria.controllers;

import com.example.Il.Forno.Classico.Pizzeria.entity.Orders;
import com.example.Il.Forno.Classico.Pizzeria.entity.Product;
import com.example.Il.Forno.Classico.Pizzeria.services.EmployeeService;
import com.example.Il.Forno.Classico.Pizzeria.services.OrderService;
import com.example.Il.Forno.Classico.Pizzeria.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    private final List<Product> cart = new ArrayList<>();

    /**
     * Метод отвечает за добавление продукта в корзину
     *
     * @param productId id продукта
     * @return представление страницы главного меню
     */
    @GetMapping("/addToCart/{id}")
    public String makeOrderPage(@PathVariable("id") Long productId) {
        Product product = productService.findProductById(productId);
        cart.add(product);
        return "redirect:/menu";
    }

    /**
     * Метод возвращает представление страницы для просмотра корзины продуктов
     *
     * @param model модель для передачи данных в представлении
     * @return представление страницы просмотра корзины продуктов
     */
    @GetMapping("/cart")
    public String cartPage(Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }

    /**
     * Метод отвечает за удаление продукта из корзины
     *
     * @param index индекс продукта в списке
     * @return представление страницы главного меню
     */
    @GetMapping("/removeFromCart/{index}")
    public String removeProductPage(@PathVariable("index") int index) {
        cart.remove(index);
        return "redirect:/cart";
    }

    /**
     * Метод отвечает за создание заказа
     *
     * @return представление страницы главного меню
     */
    @GetMapping("/makeOrder")
    public String makeOrderPage() {
        Orders order = new Orders();
        for (Product p : cart) {
            order.addProduct(p);
        }
        order.setTotalPrice(cart.stream().mapToDouble(Product::getPrice).sum());
        order.setActive(true);
        order.setEmployee(employeeService.getEmployee());
        orderService.saveOrder(order);
        cart.clear();
        return "redirect:/menu";
    }

    /**
     * Метод возвращает представление страницы для меню сотрудника
     *
     * @param model модель для передачи данных в представлении
     * @return представление страницы для меню сотрудника
     */
    @GetMapping("/staffMenu")
    public String staffMenuPage(Model model) {
        model.addAttribute("orders", orderService.findOrdersByActiveIsTrue());
        return "staffMenu";
    }

    /**
     * Метод отвечает за создание заказа
     *
     * @param id id продукта
     * @return представление страницы для меню сотрудника
     */
    @GetMapping("/completeOrder/{id}")
    public String completeOrderPage(@PathVariable("id") Long id) {
        employeeService.addOrder(orderService.findOrdersById(id));
        orderService.makeInactive(id);
        //orderService.saveOrder(orderService.findOrdersById(id));
        return "redirect:/staffMenu";
    }

    /**
     * Метод возвращает представление страницы для просмотра выполненных сотрудником заказов
     *
     * @param model модель для передачи данных в представлении
     * @return представление страницы для просмотра выполненных сотрудником заказов
     */
    @GetMapping("/completedOrders")
    public String completedOrdersPage(Model model) {
        model.addAttribute("orders", employeeService.getOrders());
        return "completedOrders";
    }
}
