package ru.rtmis.web.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StartPage {
    private final String portalCaption = "Региональный портал медицинских услуг";
    private final String regionListSelector = ".cap .region li";
    private final String enterCaption = "Вход";
    private final String esiaLinkCaption = "Войти через портал Госуслуг РФ (ЕСИА)";
    private final SelenideElement wrapperSection = $(".cap");
    private final SelenideElement captionField = $(".cap .name h1");
    private final SelenideElement selectedRegion = $(".cap .region");
    private final SelenideElement enterBoxModal = $(".enterBoxModal");
    private final SelenideElement loginField = $(".enterBoxModal [name=username]");
    private final SelenideElement passwordField = $(".enterBoxModal [name=password]");
    private final SelenideElement rememberCheckbox = $(".enterBoxModal [name=remember]");
    private final SelenideElement forgotPassLink = $(".enterBoxModal .forgot_pass_link");
    private final SelenideElement buttonLoginSubmit = $(".enterBoxModal [name=login-submit]");


//    private final String creditCaption = "Кредит по данным карты";
//    private final String headerCaption = "Путешествие дня";
//    private final String buttonOrderCaption = "Купить";
//    private final String buttonCreditCaption = "Купить в кредит";
//    private final SelenideElement buttonCredit = $(withText(buttonCreditCaption));
//    private final SelenideElement buttonOrder = $(byText(buttonOrderCaption));

    public StartPage() {
        captionField.shouldBe(visible);
    }

    public void openRegionList() {
        selectedRegion.click();

    }

    public String getCurrentRegion() {
        return selectedRegion.getText();
    }

    public String[] getRegionList() {
        List<SelenideElement> regionList = $$(regionListSelector);
        String[] regions = new String[regionList.size()];

        for (int index = 0; index < regionList.size();index++) {
            regions[index] = regionList.get(index).getText();
        }
        return regions;
    }

    public void selectRegion(String regionName) {
        $(withText(regionName)).click();
    }

    public void showLoginDialog() {
        wrapperSection.$(withText(enterCaption)).waitUntil(visible, 15000);
        wrapperSection.$(withText(enterCaption)).click();
        loginField.shouldBe(visible);
        passwordField.shouldBe(visible);
        rememberCheckbox.shouldBe(visible);
        buttonLoginSubmit.shouldBe(visible);
        enterBoxModal.$(withText(esiaLinkCaption)).shouldBe(visible);
    }

//        $(withText("Свердловская область1")).waitUntil(visible, 15000);

//    public OrderPage selectOrderByCard() {
//        buttonOrder.click();
//        $(withText(orderCaption)).waitUntil(visible, 5000);
//        return Selenide.page(OrderPage.class);
//    }
//
//    public OrderPage selectOrderByCredit() {
//        buttonCredit.click();
//        $(withText(creditCaption)).waitUntil(visible, 5000);
//        return Selenide.page(OrderPage.class);
//    }
}
