package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.AllArgsConstructor;
import utilities.ScenarioContext;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static utilities.Locators.*;
@AllArgsConstructor
public class CartStep {

    private ScenarioContext scenarioContext;

    @Then("Происходит переход в Корзину, в которой название товара соответствует названию товара из каталога товаров")
    public void checkNameProduct() {
        String expNameProduct = scenarioContext.getContextText("nameProduct").replace("/ ", "");
        $(CLASS_NAME_PRODUCT).shouldHave(text(expNameProduct));
    }

    @And("Цена товара соответствует цене товара из каталога товаров")
    public void checkPriceProduct() {
        $(CLASS_PRICE_PRODUCT).shouldHave(text(scenarioContext.getContextText("priceProduct")));
    }

    @And("Итого = сумме товара")
    public void checkTotalCost() {
        $x(XPATH_PRICE_TOTAL).shouldHave(text(scenarioContext.getContextText("priceProduct")));
    }

    @And("Кнопка Заказать активна для нажатия")
    public void checkButtonOrder() {
        $(CSS_ORDER_BUTTON).shouldBe(enabled);
    }
}
