import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class LabelsTest {
    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Owner("KharitonovaES")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Создание теста для авторизованного пользователя")
    public void testStaticLables() {
        open("https://github.com");
    }

    @Test
    public void testDynamicLables() {
        Allure.getLifecycle().updateTestCase(
                t -> t.setName("Создание теста для авторизованного пользователя")
        );
        Allure.feature("Issue в репозитории");
        Allure.story("Создание Issue");
        Allure.label("owner", "KharitonovaES");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.link("Testing", "https://testing.github.com");

        open("https://github.com");
    }

}
