import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AttachmentsTest {
    private static final String REPO = "evgenikuz/qa_guru_HW_11_Allure";
    private static final Integer ISSUE = 1;

    @Test
    void issueTestWithLambdaStepAndAttachments() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com");
            attachment("Source", webdriver().driver().source());
        });

        step("Ищем репозиторий " + REPO, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(REPO);
            $("#query-builder-test").submit();
        });

        step("Кликаем по ссылке репозитория " + REPO, () -> {
            $(linkText(REPO)).click();
        });

        step("Проверяем наличие вкладки Issue", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issue №" + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    public void testAnnotatedSteps() {
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.takeScreenShot();
        steps.searchForRepository(REPO);
        steps.clickOnRepositoryLink(REPO);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }
}
