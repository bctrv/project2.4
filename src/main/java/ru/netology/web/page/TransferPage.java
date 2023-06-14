package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement amount = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private final SelenideElement errorMessage = $("[data-test-id=error-notification]");
    private final SelenideElement heading = $(byText("Пополнение карты"));

    public TransferPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage validTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amount.setValue(amountToTransfer);
        from.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void error() {
        errorMessage.shouldHave(exactText("Ошибка!"), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
