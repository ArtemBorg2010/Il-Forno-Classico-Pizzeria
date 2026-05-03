package com.example.Il.Forno.Classico.Pizzeria.services;

/**
 * Интерфейс с перечислением методов для работы с функциональностью покупателя
 */
public interface CustomerService {

    /**
     * Метод регистрирует покупателя (покупатель создает новый аккаунт)
     *
     * @param name     имя
     * @param phone    номер телефона
     * @param nickname логин
     * @param password пароль
     * @return true если авторизация прошла успешно и false в противном случае
     */
    boolean register(String name, String phone, String nickname, String password);

    /**
     * Метод авторизует покупателя (покупатель входит в аккаунт)
     *
     * @param nickname логин
     * @param password пароль
     * @return true если авторизация прошла успешно и false в противном случае
     */
    boolean login(String nickname, String password);

}
