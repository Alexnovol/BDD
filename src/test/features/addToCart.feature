Feature: Добавление товара в корзину

  Scenario: Добавление товара в корзину в категории Роботы-пылесосы
    Given Пользователь на главной странице сайта "https://www.wildberries.ru/"
    When Пользователь нажимает на кнопку Фильтры
    When Пользователь выбирает Бытовая техника - Техника для дома - Пылесосы и пароочистители - Роботы-Пылесосы
    Then Происходит переход на страницу каталога товаров, на которой отображается категория "Роботы-пылесосы"
    And Путь фильтра "Главная, Бытовая техника, Техника для дома, Пылесосы и пароочистители, Роботы-пылесосы"
    When Пользователь нажимает на кнопку В корзину
    Then В правом верхнем углу над логотипом Корзина появляется красная цифра "1"
    When Пользователь нажимает на Корзина
    Then Происходит переход в Корзину, в которой название товара соответствует названию товара из каталога товаров
    And Цена товара соответствует цене товара из каталога товаров
    And Итого = сумме товара
    And Кнопка Заказать активна для нажатия