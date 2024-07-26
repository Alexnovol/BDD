package stepdefinitions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import utilities.ScenarioContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static asserts.GuiAssert.shouldBeEquals;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static utilities.Locators.*;

@AllArgsConstructor
public class CatalogStep {

    private ScenarioContext scenarioContext;

    @Then("Открывается страница с каталогом найденных товаров, на которой присутствует текст {string}")
    public void checkCatalogTitle(String expCatalogTitle) {
        $(CLASS_TITLE_RESULTS).shouldHave(text(expCatalogTitle));
    }

    @And("Первый фильтр среди примененных фильтров {string}")
    public void checkFirstFilter(String expFilter) {
        $x(XPATH_FIRST_FILTER).shouldHave(text(expFilter));
    }

    @And("Второй фильтр {string}")
    public void checkSecondFilter(String expFilter) {
        $x(XPATH_SECOND_FILTER).shouldHave(text(expFilter));
    }

    @And("У первого устройства из списка бренд {string}")
    public void checkBrandFirstProduct(String expBrand) {
        $x(XPATH_BRAND_FIRST_PRODUCT).shouldHave(text(expBrand));
    }

    @When("Пользователь нажимает на крестик в поисковой строке")
    public void clearSearchField() {
        $(CSS_BUTTON_CLEAR_SEARCH_FIELD).click();
    }

    @Then("Поисковая строка очищается и становится пустой")
    public void checkSearchField() {
        scenarioContext.getContextEl("searchField").shouldHave(value(""));
    }

    @Then("Происходит переход на страницу каталога товаров, на которой отображается категория {string}")
    public void checkCategory(String expCategory) {
        SelenideElement catalogTitle = $(CLASS_CATALOG_TITLE);
        catalogTitle.shouldHave(text(expCategory));
    }

    @And("Путь фильтра {string}")
    public void checkFilters(String expFilters) {
        List<String> actualFilters = new ArrayList<>();
        $$(CLASS_FILTERS_ON_PAGE).stream().forEach(filter -> actualFilters.add(filter.$("span").text()));

        shouldBeEquals(Arrays.asList(expFilters.split(", ")), actualFilters);
    }

    @When("Пользователь нажимает на кнопку В корзину")
    public void clickButtonInCart() {
        SelenideElement firstProduct = $x(XPATH_FIRST_PRODUCT);
        scenarioContext.setContextText("nameProduct", firstProduct.$x(XPATH_NAME_FIRST_PRODUCT).text());
        scenarioContext.setContextText("priceProduct", firstProduct.$x(XPATH_PRICE_FIRST_PRODUCT).text());
        firstProduct.$x(XPATH_IN_CART_BUTTON).scrollTo().click();


    }

    @Then("В правом верхнем углу над логотипом Корзина появляется красная цифра {string}")
    public void checkCountProductsInCart(String expCount) {
        $(CLASS_COUNT_PRODUCTS_CART).shouldHave(text(expCount));
    }

    @When("Пользователь нажимает на Корзина")
    public void clickButtonCart() {
        $x(XPATH_CART_BUTTON).click();
    }

    @When("Пользователь нажимает на кнопку Все фильтры")
    public void clickButtonAllFilters() {
        $(CSS_BUTTON_ALL_FILTERS).click();
    }

    @When("Пользователь применяет фильтр цены от {string} до {string}")
    public void enterPrice(String priceFrom, String priceTo) {
        SelenideElement priceFromEl = $x(XPATH_FILTER_PRICE_FROM);
        priceFromEl.clear();
        priceFromEl.setValue(priceFrom);

        SelenideElement priceToEl = $x(XPATH_FILTER_PRICE_TO);
        priceToEl.clear();
        priceToEl.setValue(priceTo);

        scenarioContext.setContextText("priceFrom", priceFrom);
        scenarioContext.setContextText("priceTo", priceTo);
    }

    @When("Пользователь применяет фильтр бренда {string}")
    public void selectBrand(String brand) {
        $x(XPATH_FILTER_BRAND.replace("insert brand", brand)).click();

        scenarioContext.setContextText("brand", brand);
    }

    @When("Пользователь применяет фильтр диагонали {string}")
    public void selectDiagonal(String diagonal) {
        $x(XPATH_FILTER_DIAGONAL.replace("insert diagonal", diagonal)).click();

        scenarioContext.setContextText("diagonal", diagonal);

        Selenide.sleep(2000);

        String countGoodsInFilters = $(CLASS_COUNT_FILTERED_GOODS_IN_FILTERS).text()
                .replaceAll("\\D+", "");
        scenarioContext.setContextText("countGoodsInFilters", countGoodsInFilters);
    }

    @When("Пользователь нажимает на кнопку Показать")
    public void clickButtonShowing() {
        $(CSS_BUTTON_SHOWING).click();
    }

    @Then("На странице с каталогом товаров отображаются примененные фильтры")
    public void checkFiltersApplied() {
        String expPriceFrom = scenarioContext.getContextText("priceFrom");
        String expPriceTo = scenarioContext.getContextText("priceTo");
        String expBrand = scenarioContext.getContextText("brand");
        String expDiagonal = scenarioContext.getContextText("diagonal");

        $x(XPATH_SELECTED_FILTER_PRICE).shouldHave(text(String.format("от %s до %s", expPriceFrom, expPriceTo)));
        $x(XPATH_SELECTED_FILTER_BRAND).shouldHave(text(expBrand));
        $x(XPATH_SELECTED_FILTER_DIAGONAL).shouldHave(text(expDiagonal));
    }

    @And("Количество товара на странице = количеству товара, отображенного в фильтрах после применения фильтров")
    public void checkCountFilteredGoodsOnPage() {
        String countFilteredGoodsOnPage = $x(XPATH_COUNT_FILTERED_GOODS_ON_PAGE).text()
                .replace(" ", "");
        String countFilteredGoodsInFilters = scenarioContext.getContextText("countGoodsInFilters");

        shouldBeEquals(countFilteredGoodsInFilters, countFilteredGoodsOnPage);
    }

    @And("На странице с каталогом товаров появилась кнопка Сбросить все")
    public void checkButtonReset() {
        $x(XPATH_BUTTON_RESET).shouldBe(enabled);
    }
}
