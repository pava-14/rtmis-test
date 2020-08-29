package ru.rtmis.web.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.rtmis.web.pages.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class PortalTest {
    private StartPage startPage;
    private final String startPageUrl = "https://k-vrachu.ru/";
    private final String[] expectedRegions = {"Астраханская область",
            "Вологодская область", "Калужская область", "Пензенская область",
            "Пермский край", "Псковская область",
            "Республика Башкортостан", "Республика Бурятия",
            "Республика Карелия", "Республика Крым", "Республика Хакасия",
            "Свердловская область"};
    private final String expectedRegionAstrakhan = expectedRegions[0];
    private final String expectedRegionKarelija = expectedRegions[8];
    private final String expectedRegionSverdlovsk = expectedRegions[11];
    private final String expectedRegionPerm = expectedRegions[4];
    private final String expectedRegionBashkortostan = expectedRegions[6];
    private final String demoUserFullName = "Демо учетная запись";

    @BeforeEach
    public void openStartPage() {
        startPage = open(startPageUrl, StartPage.class);
    }

    @Test
    public void shouldOpenOrderPage() {
        assertNotNull(startPage);
    }

    @Test
    public void shouldBeVisibleRegions() {
        startPage.openRegionList();
        assertArrayEquals(expectedRegions, startPage.getRegionList());
    }

    @Test
    public void shouldSelectAstrakhanRegionFromList() {
        startPage.openRegionList();
        startPage.selectRegion(expectedRegionAstrakhan);
        assertEquals(expectedRegionAstrakhan, startPage.getCurrentRegion());
    }

    @Test
    public void shouldSelectKarelijaRegionFromList() {
        startPage.openRegionList();
        startPage.selectRegion(expectedRegionKarelija);
        assertEquals(expectedRegionKarelija, startPage.getCurrentRegion());
    }

    @Test
    public void shouldSelectSverdlovskRegionFromList() {
        startPage.openRegionList();
        startPage.selectRegion(expectedRegionSverdlovsk);
        assertEquals(expectedRegionKarelija, startPage.getCurrentRegion());
    }

    @Test
    public void shouldBeShowPermRegionLoginDialogForm() {
        startPage.openRegionList();
        startPage.selectRegion(expectedRegionPerm);
        startPage.showLoginDialog();
    }

    @Test
    public void shouldBeShowBashkotrtostanRegionLoginDialogForm() {
        startPage.openRegionList();
        startPage.selectRegion(expectedRegionBashkortostan);
        startPage.showLoginDialog();
    }

    @Test
    public void shouldBeShowBashkotrtostanRegionDemo() {
        startPage.openRegionList();
        startPage.selectRegion(expectedRegionBashkortostan);
        startPage.enterDemo();
    }

    @Test
    public void shouldBeShowPermRegionDemo() {
        startPage.openRegionList();
        startPage.selectRegion(expectedRegionPerm);
        startPage.enterDemo();
    }

    @Test
    public void shouldBeShowDemoUserFullNameInBashkotrtostanRegionDemo() {
        startPage.openRegionList();
        startPage.selectRegion(expectedRegionBashkortostan);
        startPage.enterDemo();
        startPage.checkDemoUserProfile(demoUserFullName);
    }

    @Test
    public void shouldBeExitFromBashkotrtostanRegionDemo() {
        startPage.openRegionList();
        startPage.selectRegion(expectedRegionBashkortostan);
        startPage.enterDemo();
        startPage.exitDemo();
    }
}
