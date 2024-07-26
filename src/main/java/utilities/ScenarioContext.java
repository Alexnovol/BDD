package utilities;

import com.codeborne.selenide.SelenideElement;
import lombok.NoArgsConstructor;

import java.util.HashMap;

public class ScenarioContext {
    private HashMap<String, SelenideElement> contextEl;
    private HashMap<String, String> contextText;

    public ScenarioContext() {
        this.contextEl = new HashMap<>();
        this.contextText = new HashMap<>();
    }

    public void setContextEl(String key, SelenideElement value) {
        contextEl.put(key, value);
    }

    public void setContextText(String key, String value) {
        contextText.put(key, value);
    }

    public SelenideElement getContextEl(String key) {

        return contextEl.get(key);

    }


    public String getContextText(String key) {

        return contextText.get(key);

    }
}
