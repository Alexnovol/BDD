package stepdefinitions;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import utilities.ScenarioContext;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static utilities.Locators.*;

@AllArgsConstructor
public class GeoStep {

    private ScenarioContext scenarioContext;

    @When("Пользователь вводит в поисковую строку город {string}")
    public void enterCity(String city) {
        SelenideElement searchFieldCity = $x(XPATH_ADDRESS_ENTRY);
        searchFieldCity.click();
        searchFieldCity.setValue(city);

        $x(XPATH_BUTTON_FIND).click();

        $x(XPATH_ANY_DELIVERY_POINT.replace("city", city)).shouldBe(enabled);
    }

    @When("Пользователь выбирает первый адрес из списка адресов")
    public void selectFirstAddress() {
        SelenideElement firstAddress = $x(XPATH_FIRST_DELIVERY_POINT);
        scenarioContext.setContextText("firstAddress", firstAddress.$x(XPATH_ADDRESS_FIRST_DELIVERY_POINT).text());
        firstAddress.click();
    }

    @Then("Открывается информация о центре выдачи")
    public void checkInfoDeliveryPoint() {
        SelenideElement infoDeliveryPoint = $(CLASS_INFO_DELIVERY_POINT);
        infoDeliveryPoint.shouldBe(visible);

        scenarioContext.setContextEl("infoDeliveryPoint", infoDeliveryPoint);
    }

    @And("Адрес пункта выдачи совпадает с тем адресом, что был предложен в списке адресов")
    public void checkAddressDeliveryPoint() {
        $(CLASS_ADDRESS_DELIVERY_POINT).shouldHave(text(scenarioContext.getContextText("firstAddress")));
    }

    @When("Пользователь нажимает на кнопку Выбрать")
    public void clickButtonSelect() {
        $(CSS_BUTTON_SELECT_DELIVERY_POINT).click();
    }
}
