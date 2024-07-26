package utilities;

import static com.codeborne.selenide.Selenide.*;
import static utilities.Locators.CSS_CATEGORY_MENU;

public class CommonActions {

    public static void hoverToCategoryMenu() {
        $(CSS_CATEGORY_MENU).hover();
    }
}
