package ru.rtmis.web.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.rtmis.web.pages.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class PortalTest {
    private final String startPageUrl = "https://k-vrachu.ru/";
    private StartPage startPage;
    private final String[] expectedRegions = {"Астраханская область",
            "Вологодская область", "Калужская область", "Пензенская область",
            "Пермский край", "Псковская область",
            "Республика Башкортостан", "Республика Бурятия",
            "Республика Карелия", "Республика Крым", "Республика Хакасия",
            "Свердловская область"};
    private final String expectedRegionUI3 = expectedRegions[0];
    private final String expectedRegionUI4 = expectedRegions[8];
    private final String expectedRegionUI5 = expectedRegions[11];
    private final String expectedRegionUI6 = expectedRegions[4];

    @BeforeEach
    public void openStartPage() {
        startPage = open(startPageUrl, StartPage.class);
    }

    @Test
    public void shouldOpenOrderPage() {
        assertNotNull(startPage);
    }

    @Test
    public void shouldBeVisibleRegions () {
        startPage.openRegionList();
        assertArrayEquals(expectedRegions, startPage.getGegionList());
    }

    @Test
    public void shouldSelectAstrakhanRegionFromList () {
        startPage.openRegionList();
        startPage.selectRegion(expectedRegionUI3);
        assertEquals(expectedRegionUI3, startPage.getCurrentRegion());
    }

   @Test
    public void shouldSelectKarelijaRegionFromList () {
        startPage.openRegionList();
        startPage.selectRegion(expectedRegionUI4);
        assertEquals(expectedRegionUI4, startPage.getCurrentRegion());
    }

   @Test
    public void shouldSelectSverdlovskRegionFromList () {
        startPage.openRegionList();
        startPage.selectRegion(expectedRegionUI5);
        assertEquals(expectedRegionUI4, startPage.getCurrentRegion());
    }

   @Test
    public void shouldBeShowLoginDialogForm () {
        startPage.openRegionList();
        startPage.selectRegion(expectedRegionUI6);
        startPage.showLoginDialog();
//        assertEquals(expectedRegionUI4, startPage.getCurrentRegion());
    }

//    @Test
//    public void shouldApprovedCreditByCard() {
//        OrderPage orderPage = startPage.selectOrderByCredit();
//        orderPage.setCardFields(CardGenerator.CardInfo.getCardInfoWithApprovedCardNumber());
//        orderPage.sendData();
//        orderPage.waitForApproved();
//    }

}
