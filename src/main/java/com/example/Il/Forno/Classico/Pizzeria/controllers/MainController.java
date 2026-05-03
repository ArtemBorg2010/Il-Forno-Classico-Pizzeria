package com.example.Il.Forno.Classico.Pizzeria.controllers;

import com.example.Il.Forno.Classico.Pizzeria.enums.Categories;
import com.example.Il.Forno.Classico.Pizzeria.services.CustomerService;
import com.example.Il.Forno.Classico.Pizzeria.services.EmployeeService;
import com.example.Il.Forno.Classico.Pizzeria.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Контроллер для авторизации и админ-панели
 */

@Controller
public class MainController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProductService productService;

    /**
     * Метод возвращает представление страницы для добавления продуктов (блюд)
     *
     * @param model модель для передачи данных в представлении
     * @return представление страницы для добавления продуктов (блюд)
     */
    @GetMapping("/addProduct")
    public String addProductPage(Model model) {
        List<String> categories = new ArrayList<>(Arrays.asList("Breakfasts",
                "Pizza", "Cold appetizers", "Salads",
                "Soups", "Regular pasta", "Homemade pasta",
                "Risotto", "Entrees", "Side dishes", "Desserts",
                "Tea", "Coffee", "Water", "Freshly squeezed juices",
                "Soft drinks", "Non-alcoholic cocktails"));
        model.addAttribute("categories", categories);
        //Categories.Pizza.getName()
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
        productService.addProduct(name, category, price, cost);
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
        model.addAttribute("products", productService.findAll());
        return "menu";
    }

    /**
     * Метод возвращает представление страницы для выбора роли посетителя сайта для авторизации (покупатель или работник)
     *
     * @return представление страницы для выбора роли посетителя сайта для авторизации (покупатель или работник)
     */
    @GetMapping("/whoToAuthorise")
    public String whoToAuthorise() {
        return "whoToAuthorise";
    }

    /**
     * Метод возвращает представление страницы для авторизации покупателей
     *
     * @return представление страницы для авторизации покупателей
     */
    @GetMapping("/guestAuthorisation")
    public String customerAuthorisationPage() {
        return "guestAuthorisation";
    }

    /**
     * Метод авторизует покупателя (покупатель входит в аккаунт)
     *
     * @param nickname логин
     * @param password пароль
     * @return представление страницы для меню продуктов при успешной авторизации
     * или представление страницы об ошибке в противном случае
     */
    @PostMapping("/guestLogin")
    public String guestLogin(@RequestParam("nicknameLogin") String nickname,
                             @RequestParam("passwordLogin") String password) {
        if (customerService.login(nickname, password)) {
            return "redirect:/menu";
        } else {
            return "redirect:/loginError";
        }
    }

    /**
     * Метод регистрирует покупателя (покупатель создает новый аккаунт)
     *
     * @param name     имя
     * @param phone    номер телефона
     * @param nickname логин
     * @param password пароль
     * @return представление страницы для меню продуктов при успешной авторизации
     * или представление страницы об ошибке в противном случае
     */
    @PostMapping("/guestRegister")
    public String guestRegister(@RequestParam("name") String name,
                                @RequestParam("phone") String phone,
                                @RequestParam("nickname") String nickname,
                                @RequestParam("password") String password) {
        if (customerService.register(name, phone, nickname, password)) {
            return "redirect:/menu";
        } else {
            return "redirect:/registerError";
        }
    }

    /**
     * Метод возвращает представление страницы для авторизации работников
     *
     * @return представление страницы для добавления работников
     */
    @GetMapping("/employeeAuthorisation")
    public String employeeAuthorisationPage() {
        return "employeeAuthorisation";
    }

    /**
     * Метод авторизует работника (работник входит в аккаунт)
     *
     * @param nickname логин
     * @param password пароль
     * @return представление страницы для меню работника при успешной авторизации
     * или представление страницы об ошибке в противном случае
     */
    @PostMapping("/employeeLogin")
    public String employeeLogin(@RequestParam("nicknameLogin") String nickname,
                                @RequestParam("passwordLogin") String password) {
        if (employeeService.login(nickname, password)) {
            return "redirect:/staffMenu";
        } else {
            return "redirect:/loginError";
        }

    }

    /**
     * Метод регистрирует работника (работник создает новый аккаунт)
     *
     * @param name     имя
     * @param position должность
     * @param nickname логин
     * @param password пароль
     * @return представление страницы для меню работника при успешной авторизации
     * или представление страницы об ошибке в противном случае
     */
    @PostMapping("/employeeRegister")
    public String employeeRegister(@RequestParam("name") String name,
                                   @RequestParam("position") String position,
                                   @RequestParam("nickname") String nickname,
                                   @RequestParam("password") String password) {
        if (employeeService.register(name, position, nickname, password)) {
            return "redirect:/staffMenu";
        } else {
            return "redirect:/registerError";
        }
    }
}
