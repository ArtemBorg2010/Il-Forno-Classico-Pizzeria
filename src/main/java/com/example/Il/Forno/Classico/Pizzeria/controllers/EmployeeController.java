package com.example.Il.Forno.Classico.Pizzeria.controllers;

import com.example.Il.Forno.Classico.Pizzeria.entity.Customer;
import com.example.Il.Forno.Classico.Pizzeria.entity.Employee;
import com.example.Il.Forno.Classico.Pizzeria.entity.Orders;
import com.example.Il.Forno.Classico.Pizzeria.entity.Product;
import com.example.Il.Forno.Classico.Pizzeria.repos.CustomerRepository;
import com.example.Il.Forno.Classico.Pizzeria.repos.EmployeeRepository;
import com.example.Il.Forno.Classico.Pizzeria.repos.OrderRepository;
import com.example.Il.Forno.Classico.Pizzeria.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    private List<Product> cart = new ArrayList<>();
    private List<Orders> orders = new ArrayList<>();

    private Employee employee = new Employee();

    /**
     * Метод возвращает представление страницы для выбора роли посетителя сайта (покупатель или работник)
     *
     * @return представление страницы для выбора роли посетителя сайта (покупатель или работник)
     */
    @GetMapping("/whoToAuthorise")
    public String whoToAuthorise() {
        return "whoToAuthorise";
    }

    /**
     * Метод возвращает представление страницы для добавления покупателей
     *
     * @return представление страницы для добавления покупателей
     */
    @GetMapping("/customerAuthorisation")
    public String customerAuthorisationPage() {
        return "customerAuthorisation";
    }

    /**
     * Метод создает покупателя, используя данные из полей для ввода и сохраняет его в базу данных
     *
     * @param name  имя покупателя
     * @param phone номер телефона покупателя
     * @return представление страницы главного меню
     */
    @PostMapping("/customerAuthorised")
    public String customerAuthorised(@RequestParam("name") String name,
                                     @RequestParam("phone") String phone) {
        Customer c = new Customer();
        c.setName(name);
        c.setPhone(phone);
        customerRepository.save(c);
        return "menu";
    }

    /**
     * Метод возвращает представление страницы для добавления работников
     *
     * @return представление страницы для добавления работников
     */
    @GetMapping("/employeeAuthorisation")
    public String employeeAuthorisationPage() {
        return "employeeAuthorisation";
    }

    /**
     * Метод создает работника, используя данные из полей для ввода и сохраняет его в базу данных
     *
     * @param name             имя работника
     * @param position         должность работника
     * @param pizzeriaLocation адрес пиццерии
     * @return представление страницы меню сотрудника
     */
    @PostMapping("/employeeAuthorised")
    public String employeeAuthorised(@RequestParam("name") String name,
                                     @RequestParam("position") String position,
                                     @RequestParam("pizzeriaLocation") String pizzeriaLocation) {
        employee.setName(name);
        employee.setPosition(position);
        employee.setPizzeriaLocation(pizzeriaLocation);
        employeeRepository.save(employee);
        return "redirect:/staffMenu";
    }

    /**
     * Метод отвечает за добавление продукта в корзину
     *
     * @param productId id продукта
     * @return представление страницы главного меню
     */
    @GetMapping("/addToCart/{id}")
    public String makeOrderPage(@PathVariable("id") Long productId) {
        Product product = productRepository.findProductById(productId);
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
        for(Product p:cart){
            order.addProduct(p);
        }
        double sum = 0;
        order.setTotalPrice(cart.stream().mapToDouble(x -> x.getPrice()).sum());
        order.setActive(true);
        orderRepository.save(order);
        orders.add(order);
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
        orders.stream().filter(x -> x.isActive());
        model.addAttribute("orders", orders);
        return "staffMenu";
    }

    /**
     * Метод отвечает за создание заказа
     *
     * @param index индекс продукта в списке
     * @return представление страницы для меню сотрудника
     */
    @GetMapping("/completeOrder/{index}")
    public String completeOrderPage(@PathVariable("index") int index) {
        employee.addOrder(orders.get(index));
        orders.get(index).setActive(false);
        orderRepository.save(orders.get(index));
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
        model.addAttribute("orders", employee.getOrders());
        return "completedOrders";
    }
}
