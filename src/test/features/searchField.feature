Feature: Работа поисковой строки

  Scenario: Поиск товара "iPhone 13" через поисковую строку
    Given Пользователь на главной странице сайта "https://www.wildberries.ru/"
    When Пользователь вводит "iPhone 13" в поисковую строку и нажимает Enter
    Then Открывается страница с каталогом найденных товаров, на которой присутствует текст "По запросу 'iPhone 13' найдено"
    And Первый фильтр среди примененных фильтров "iPhone 13"
    And Второй фильтр "По популярности"
    And У первого устройства из списка бренд "Apple"
    When Пользователь нажимает на крестик в поисковой строке
    Then Поисковая строка очищается и становится пустой