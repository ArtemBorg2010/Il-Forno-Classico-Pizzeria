package com.example.Il.Forno.Classico.Pizzeria.controllers;

import com.example.Il.Forno.Classico.Pizzeria.entity.Employee;
import com.example.Il.Forno.Classico.Pizzeria.entity.Product;
import com.example.Il.Forno.Classico.Pizzeria.repos.CustomerRepository;
import com.example.Il.Forno.Classico.Pizzeria.repos.EmployeeRepository;
import com.example.Il.Forno.Classico.Pizzeria.repos.OrderRepository;
import com.example.Il.Forno.Classico.Pizzeria.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Метод возвращает представление страницы для добавления продуктов (блюд)
     *
     * @param model модель для передачи данных в представлении
     * @return представление страницы для добавления продуктов (блюд)
     */
    @GetMapping("/addProduct")
    public String addProductPage(Model model) {
        List<String> categories = new ArrayList<>();
        categories.addAll(Arrays.asList("Breakfasts",
                "Pizza", "Cold appetizers", "Salads",
                "Soups", "Regular pasta", "Homemade pasta",
                "Risotto", "Entrees", "Side dishes", "Desserts",
                "Tea", "Coffee", "Water", "Freshly squeezed juices",
                "Soft drinks", "Non-alcoholic cocktails"));
        model.addAttribute("categories", categories);
        return "addProduct";
    }

    /**
     * Метод создает продукт, используя данные из полей для ввода и сохраняет его в базу данных
     *
     * @param name     название продукта
     * @param category категория продукта
     * @param price    цена продукта
     * @param cost     стоимость продукта
     * @return представление страницы главного меню
     */
    @PostMapping("/addedProduct")
    public String addedProduct(@RequestParam("name") String name,
                               @RequestParam("category") String category,
                               @RequestParam("price") double price,
                               @RequestParam("cost") double cost) {
        Product p = new Product();
        p.setName(name);
        p.setCategory(category);
        p.setPrice(price);
        p.setCost(cost);
        productRepository.save(p);
        return "menu";
    }

    /**
     * Метод возвращает представление страницы для меню продуктов
     *
     * @param model модель для передачи данных в представлении
     * @return представление страницы для меню продуктов
     */
    @GetMapping("/menu")
    public String menuPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "menu";
    }

    /**
     * Метод возвращает представление страницы для активных заказов
     *
     * @param model модель для передачи данных в представлении
     * @return представление страницы для активных заказов
     */
    @GetMapping("/orders")
    public String ordersPage(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }
}
