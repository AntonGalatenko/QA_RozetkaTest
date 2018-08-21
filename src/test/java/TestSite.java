import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class TestSite {

    @BeforeTest
    public void setUp(){
        ChromeDriverManager.getInstance().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "950x1030";


//        open("https://rozetka.com.ua");
    }

    @Test
    public void testCanOpenSite() {
        $("title")
                .shouldHave(attribute("text", "Интернет-магазин ROZETKA™: " +
                        "фототехника, видеотехника, аудиотехника, компьютеры и компьютерные комплектующие"));
    }

    @Test
    public void testCanClickOnPhone() {
        $(By.xpath("//a[contains(@href, '/telefony-tv-i-ehlektronika/')]")).click();
        $(By.xpath("//a[contains(@href, '/telefony/')]")).click();
        $(By.xpath("//a[contains(@href, '/mobile-phones/')]"))
                .shouldNotHave(cssClass(".m-cat-subl-i-link"))
                .click();

        $("title")
                .shouldHave(attribute("text", "Смартфоны - " +
                        "Rozetka.ua | Купить смартфон в Киеве: цена, отзывы, продажа"));
    }

    @Test
    public void testCountPhoneSize() {
        open("https://rozetka.com.ua/mobile-phones/c80003/preset=smartfon/");

        RozetkaPage.doClick("//div[@class='g-i-tile g-i-tile-catalog preloader-trigger']");
        RozetkaPage.doClick("//div[@class='g-i-tile g-i-tile-catalog preloader-trigger']");

        $$(By.xpath("//div[@class='g-i-tile g-i-tile-catalog']")).shouldHave(size(96));
    }

    @Test
    public void testCountPopularityPhonesSize() {
        open("https://rozetka.com.ua/mobile-phones/c80003/preset=smartfon/");

        RozetkaPage.doClick("//div[@class='g-i-tile g-i-tile-catalog preloader-trigger']");
        RozetkaPage.doClick("//div[@class='g-i-tile g-i-tile-catalog preloader-trigger']");

        List<String> phoneNameList =
        $$(By.xpath("//i[@class='g-tag g-tag-icon-middle-popularity sprite']" +
                "/parent::div/parent::div/following::div[@class='g-i-tile-i-title clearfix'][1]/a"))
                .texts();
        List<String> phonaPriceList =
        $$(By.xpath("//i[@class='g-tag g-tag-icon-middle-popularity sprite']/parent::div" +
                "/parent::div/following::div[@name='prices_active_element_original'][1]//div[@class='g-price-uah']"))
                .texts();

        for(int i = 0; i < phoneNameList.size(); i++)
            System.out.println(phoneNameList.get(i) + "     " + phonaPriceList.get(i));

        $$(By.xpath("//i[@class='g-tag g-tag-icon-middle-popularity sprite']")).shouldHave(size(5));
    }
}
