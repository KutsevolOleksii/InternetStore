package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // =========================
        // КАТЕГОРИИ
        // =========================

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        // =========================
        // ТОВАРЫ
        // =========================

        Product phone = new Product(
                1,
                "iPhone 15",
                39999.99,
                "Современный смартфон",
                smartphones
        );

        Product laptop = new Product(
                2,
                "Ноутбук Lenovo",
                29999.99,
                "Ноутбук для работы и учебы",
                electronics
        );

        Product headphones = new Product(
                3,
                "Наушники JBL",
                4999.99,
                "Беспроводные наушники",
                accessories
        );

        // =========================
        // КАТАЛОГ
        // =========================

        List<Product> products = new ArrayList<>();

        products.add(phone);
        products.add(laptop);
        products.add(headphones);

        // =========================
        // КОРЗИНА
        // =========================

        Cart cart = new Cart();

        // =========================
        // ИСТОРИЯ ЗАКАЗОВ
        // =========================

        List<Order> orderHistory = new ArrayList<>();

        // =========================
        // ГЛАВНОЕ МЕНЮ
        // =========================

        while (true) {

            System.out.println("\n================================");
            System.out.println("       ИНТЕРНЕТ-МАГАЗИН");
            System.out.println("================================");
            System.out.println("1. Просмотреть товары");
            System.out.println("2. Добавить товар в корзину");
            System.out.println("3. Просмотреть корзину");
            System.out.println("4. Удалить товар из корзины");
            System.out.println("5. Сделать заказ");
            System.out.println("6. История заказов");
            System.out.println("7. Поиск товара");
            System.out.println("0. Выход");
            System.out.println("================================");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                // ==================================
                // 1. ПРОСМОТР ТОВАРОВ
                // ==================================

                case 1:

                    System.out.println("\n===== КАТАЛОГ ТОВАРОВ =====");

                    for (Product product : products) {
                        System.out.println(
                                "ID: " + product.getId()
                                        + " | Название: " + product.getName()
                                        + " | Цена: " + product.getPrice() + " грн"
                                        + " | Категория: " + product.getCategory().getName()
                                        + " | Описание: " + product.getDescription()
                        );
                    }

                    break;

                // ==================================
                // 2. ДОБАВЛЕНИЕ В КОРЗИНУ
                // ==================================

                case 2:

                    System.out.println("\n===== ДОБАВЛЕНИЕ В КОРЗИНУ =====");

                    for (Product product : products) {
                        System.out.println(
                                product.getId()
                                        + ". "
                                        + product.getName()
                                        + " - "
                                        + product.getPrice()
                                        + " грн"
                        );
                    }

                    System.out.print("Введите ID товара: ");
                    int productId = scanner.nextInt();

                    Product selectedProduct = null;

                    for (Product product : products) {

                        if (product.getId() == productId) {
                            selectedProduct = product;
                            break;
                        }
                    }

                    if (selectedProduct != null) {

                        cart.addProduct(selectedProduct);

                        System.out.println(
                                "Товар \""
                                        + selectedProduct.getName()
                                        + "\" добавлен в корзину."
                        );

                    } else {

                        System.out.println("Товар с таким ID не найден.");
                    }

                    break;

                // ==================================
                // 3. ПРОСМОТР КОРЗИНЫ
                // ==================================

                case 3:

                    System.out.println("\n===== КОРЗИНА =====");

                    if (cart.getProducts().isEmpty()) {

                        System.out.println("Корзина пуста.");

                    } else {

                        for (Product product : cart.getProducts()) {

                            System.out.println(
                                    "ID: " + product.getId()
                                            + " | "
                                            + product.getName()
                                            + " | "
                                            + product.getPrice()
                                            + " грн"
                            );
                        }

                        System.out.println(
                                "----------------------------"
                        );

                        System.out.println(
                                "Общая стоимость: "
                                        + cart.getTotalPrice()
                                        + " грн"
                        );
                    }

                    break;

                // ==================================
                // 4. УДАЛЕНИЕ ИЗ КОРЗИНЫ
                // ==================================

                case 4:

                    System.out.println("\n===== УДАЛЕНИЕ ИЗ КОРЗИНЫ =====");

                    if (cart.getProducts().isEmpty()) {

                        System.out.println("Корзина пуста.");

                    } else {

                        for (Product product : cart.getProducts()) {

                            System.out.println(
                                    "ID: " + product.getId()
                                            + " | "
                                            + product.getName()
                                            + " | "
                                            + product.getPrice()
                                            + " грн"
                            );
                        }

                        System.out.print("Введите ID товара для удаления: ");

                        int removeId = scanner.nextInt();

                        Product productToRemove = null;

                        for (Product product : cart.getProducts()) {

                            if (product.getId() == removeId) {
                                productToRemove = product;
                                break;
                            }
                        }

                        if (productToRemove != null) {

                            cart.removeProduct(productToRemove);

                            System.out.println(
                                    "Товар \""
                                            + productToRemove.getName()
                                            + "\" удалён из корзины."
                            );

                        } else {

                            System.out.println(
                                    "Товар с таким ID не найден в корзине."
                            );
                        }
                    }

                    break;

                // ==================================
                // 5. СОЗДАНИЕ ЗАКАЗА
                // ==================================

                case 5:

                    System.out.println("\n===== ОФОРМЛЕНИЕ ЗАКАЗА =====");

                    if (cart.getProducts().isEmpty()) {

                        System.out.println(
                                "Нельзя оформить заказ. Корзина пуста."
                        );

                    } else {

                        Order order = new Order(cart);

                        orderHistory.add(order);

                        System.out.println("Заказ успешно создан!");
                        System.out.println(
                                "Стоимость заказа: "
                                        + order.getTotalPrice()
                                        + " грн"
                        );

                        System.out.println(
                                "Статус: "
                                        + order.getStatus()
                        );

                        cart.clear();

                        System.out.println(
                                "Корзина очищена."
                        );
                    }

                    break;

                // ==================================
                // 6. ИСТОРИЯ ЗАКАЗОВ
                // ==================================

                case 6:

                    System.out.println("\n===== ИСТОРИЯ ЗАКАЗОВ =====");

                    if (orderHistory.isEmpty()) {

                        System.out.println(
                                "История заказов пуста."
                        );

                    } else {

                        for (int i = 0; i < orderHistory.size(); i++) {

                            Order order = orderHistory.get(i);

                            System.out.println(
                                    "\nЗаказ №" + (i + 1)
                            );

                            System.out.println(
                                    "Статус: "
                                            + order.getStatus()
                            );

                            System.out.println(
                                    "Стоимость: "
                                            + order.getTotalPrice()
                                            + " грн"
                            );

                            System.out.println("Товары:");

                            for (Product product : order.getProducts()) {

                                System.out.println(
                                        "  - "
                                                + product.getName()
                                                + " | "
                                                + product.getPrice()
                                                + " грн"
                                );
                            }
                        }
                    }

                    break;

                // ==================================
                // 7. ПОИСК ТОВАРА
                // ==================================

                case 7:

                    System.out.println("\n===== ПОИСК ТОВАРА =====");
                    System.out.println("1. По названию");
                    System.out.println("2. По категории");
                    System.out.print("Выберите способ поиска: ");

                    int searchType = scanner.nextInt();
                    scanner.nextLine();

                    if (searchType == 1) {

                        System.out.print(
                                "Введите название товара: "
                        );

                        String searchName =
                                scanner.nextLine().toLowerCase();

                        boolean found = false;

                        for (Product product : products) {

                            if (product.getName()
                                    .toLowerCase()
                                    .contains(searchName)) {

                                System.out.println(
                                        "Найден товар: "
                                                + product.getName()
                                                + " | "
                                                + product.getPrice()
                                                + " грн"
                                );

                                found = true;
                            }
                        }

                        if (!found) {
                            System.out.println(
                                    "Товары не найдены."
                            );
                        }

                    } else if (searchType == 2) {

                        System.out.print(
                                "Введите название категории: "
                        );

                        String searchCategory =
                                scanner.nextLine().toLowerCase();

                        boolean found = false;

                        for (Product product : products) {

                            if (product.getCategory()
                                    .getName()
                                    .toLowerCase()
                                    .contains(searchCategory)) {

                                System.out.println(
                                        "Найден товар: "
                                                + product.getName()
                                                + " | Категория: "
                                                + product.getCategory().getName()
                                                + " | Цена: "
                                                + product.getPrice()
                                                + " грн"
                                );

                                found = true;
                            }
                        }

                        if (!found) {
                            System.out.println(
                                    "Товары не найдены."
                            );
                        }

                    } else {

                        System.out.println(
                                "Неверный способ поиска."
                        );
                    }

                    break;

                // ==================================
                // 0. ВЫХОД
                // ==================================

                case 0:

                    System.out.println(
                            "Спасибо за использование магазина!"
                    );

                    scanner.close();

                    return;

                default:

                    System.out.println(
                            "Неверный выбор. Попробуйте снова."
                    );
            }
        }
    }
}