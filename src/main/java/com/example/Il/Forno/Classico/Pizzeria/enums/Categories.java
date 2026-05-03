package com.example.Il.Forno.Classico.Pizzeria.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Categories {
    Breakfasts("Breakfasts"),
    Pizza("Pizza"),
    Cold("Cold"),
    Appetizers("Appetizers"),
    Salads("Salads"),
    Soups("Soups"),
    Regular_pasta("Regular pasta"),
    Homemade_pasta("Homemade pasta"),
    Risotto("Risotto"),
    Entrees("Entrees"),
    Side_dishes("Side dishes"),
    Desserts("Desserts"),
    Tea("Tea"),
    Coffee("Coffee"),
    Water("Water"),
    Freshly_squeezed_juices("Freshly squeezed juices"),
    Soft_drinks("Soft drinks"),
    Non_alcoholic_cocktails("Non alcoholic cocktails");
    private final String name;
}
