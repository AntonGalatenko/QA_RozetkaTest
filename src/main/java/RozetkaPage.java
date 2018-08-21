import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RozetkaPage {

    public static void doClick(String s) {
        if($(By.xpath("//span[@class='exponea-close-cross']")).is(visible)){
            $(By.xpath("//span[@class='exponea-close-cross']")).click();
            System.out.println("------------------- close banner");
        }
        $(By.xpath(s)).click();
    }

}
