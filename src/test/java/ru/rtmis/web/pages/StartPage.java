package ru.rtmis.web.pages;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StartPage {
    private final String regionListSelector = ".cap .region li";
    private final String enterCaption = "Вход";
    private final String enterDemoCaption = "Демо-режим";
    private final String enterDemoButtonCaption = "Включить демо-режим";
    private final String enterDemoLabel = "В демо-режиме можно ознакомиться со всеми возможностями " +
            "портала без регистрации. Изменения сохраняться не будут.";
    private final String demoAlertCaption = "Включен ознакомительный режим. Запись в базу данных не " +
            "производится.";
    private final String exitDemoButtonCaption = "Выключить";

    private final String rememberCaption = "Запомнить меня";
    private final String esiaLinkCaption = "Войти через портал Госуслуг РФ (ЕСИА)";

    private final SelenideElement topAlertSection = $(".top-alert");
    private final SelenideElement wrapperSection = $(".cap");
    private final SelenideElement captionField = $(".cap .name h1");
    private final SelenideElement captionPerson = $(".cap .person");
    private final SelenideElement selectedRegion = $(".cap .region");

    private final SelenideElement enterBoxModal = $(".enterBoxModal");
    private final SelenideElement loginField = $(".enterBoxModal [name=username]");
    private final SelenideElement passwordField = $(".enterBoxModal [name=password]");
    private final SelenideElement forgotPassLink = $(".enterBoxModal .forgot_pass_link");
    private final SelenideElement buttonLoginSubmit = $(".enterBoxModal [name=login-submit]");

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

        for (int index = 0; index < regionList.size(); index++) {
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
        buttonLoginSubmit.shouldBe(visible);
        forgotPassLink.shouldBe(visible);
        enterBoxModal.$(withText(rememberCaption)).shouldBe(visible);
        enterBoxModal.$(withText(esiaLinkCaption)).shouldBe(visible);
    }

    public void enterDemo() {
        wrapperSection.$(withText(enterDemoCaption)).hover();
        $(withText(enterDemoLabel)).waitUntil(visible, 15000);
        $(withText(enterDemoButtonCaption)).click();
        topAlertSection.$(withText(demoAlertCaption)).waitUntil(visible, 15000);
        topAlertSection.$(withText(exitDemoButtonCaption)).waitUntil(visible, 15000);
    }

    public void checkDemoUserProfile(String demoUserFullName) {
        captionPerson.$(withText(demoUserFullName)).waitUntil(visible, 15000);
    }

    public void exitDemo() {
        topAlertSection.$(withText(exitDemoButtonCaption)).click();
        $(withText(enterDemoCaption)).waitUntil(visible, 15000);
    }
}
