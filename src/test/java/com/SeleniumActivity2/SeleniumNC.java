package com.SeleniumActivity2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SeleniumNC {

    public WebDriver driver;
    static String emailUsed;
    static String passwodUsed;
    static String nicknameUsed;

    @Before
    public void launchBrowser(){
        emailUsed = "fdc.armanarco+" + generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 8) + "@gmail.com";
        passwodUsed = generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 8);
        nicknameUsed = generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 8);

        System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver.exe");


        ChromeOptions options = new ChromeOptions();

        Map<String, Object> preference = new HashMap<String, Object>();
        preference.put("profile.content_settings.exceptions.media_stream_camera.https://english.fdc-inc.com.setting","1");
        preference.put("profile.content_settings.exceptions.media_stream_mic.https://english.fdc-inc.com.setting","1");
        preference.put("credentials_enable_service", false);
        options.setExperimentalOption("prefs", preference);
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--use-fake-ui-for-media-stream=1");
        driver = new ChromeDriver(options);
    }

//    @After
    public void afterTest() {
        driver.quit();
    }


    public static String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }
        return sb.toString();
    }



    @Test
    public void Registration() throws InterruptedException {
        driver.get("https://english.fdc-inc.com/login");
        Thread.sleep(2000);


        //register button
        driver.findElement(By.xpath("//*[@id=\"header\"]//div/ul/li[5]/a")).click();

        driver.findElement(By.id("UserEmail")).sendKeys(emailUsed);
        driver.findElement(By.id("UserEmailConfirm")).sendKeys(emailUsed);
        driver.findElement(By.id("UserPassword")).sendKeys(passwodUsed);
        driver.findElement(By.id("UserPasswordConfirm")).sendKeys(passwodUsed);
        driver.findElement(By.xpath("//*[@id=\"UserCreateAccountForm\"]//ul/li/button")).click();

        driver.findElement(By.id("UserNickname")).sendKeys(nicknameUsed);

        Select selectYear = new Select(driver.findElement(By.id("UserByear")));
        selectYear.selectByVisibleText("1992");

        Select selectMonth = new Select(driver.findElement(By.id("UserBmonth")));
        selectMonth.selectByVisibleText("6");

        Select selectByDay = new Select(driver.findElement(By.id("UserBday")));
        selectByDay.selectByVisibleText("8");

        driver.findElement(By.xpath("//*[@id=\"UserProfileInputForm\"]//label[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"UserProfileInputForm\"]//button")).click();

        driver.findElement(By.xpath("//*[@id=\"credit_form_wrap_btn\"]")).click();
        driver.findElement(By.id("zeus_token_card_number")).sendKeys("4574933171272280");
        Select selectYearExpire = new Select(driver.findElement(By.id("zeus_token_card_expires_year")));
        selectYearExpire.selectByValue("2029");
        Select selectMonthExpire = new Select(driver.findElement(By.id("zeus_token_card_expires_month")));
        selectMonthExpire.selectByValue("08");
        driver.findElement(By.id("zeus_token_card_name")).sendKeys(nicknameUsed);
        driver.findElement(By.xpath("//*[@id=\"credit_form_wrap\"]//label")).click();
        driver.findElement(By.id("btn_go_to_confirm_page")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("notice_complete")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(@for,'quest_job3')]")).click();
        driver.findElement(By.xpath("//*[contains(@for,'quest_trigger2')]")).click();
        driver.findElement(By.xpath("//*[contains(@for,'quest_reason6')]")).click();

        driver.findElement(By.xpath("//*[contains(@name,'data[User][quest_reason_text]')]"))
                .sendKeys("test test");
        driver.findElement(By.xpath("//*[contains(@name,'data[User][questant_box]')]"))
                .sendKeys("test me not");
        driver.findElement(By.xpath("//*[@id=\"UserQuestionnaireForm\"]//button")).click();
        driver.findElement(By.xpath("//*[contains(@href,'/mypage')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("btn_connect_test_first")).click();

        // Store the current window handle
        String winHandleBefore = driver.getWindowHandle();

        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(500);
        driver.findElement(By.id("btn_check-system_browser")).click();
        Thread.sleep(500);

        driver.findElement(By.id("btn_check-device_camera")).click();
//        driver.findElement(By.id("btn_check-manual_camera_ignore")).click();

        Thread.sleep(3000);

//        driver.findElement(By.xpath("/html/body/div[4]/div[2]")).click();

//        Thread.sleep(2000);
        driver.findElement(By.id("btn_check-device_mic")).click();
        Thread.sleep(500);

        driver.findElement(By.id("btn_check-manual_video")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[contains(@for,'radio_testing_video_1')]")).click();
        Thread.sleep(500);
        driver.findElement(By.id("btn_check-manual_mic")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[contains(@for,'radio_testing_mic_1')]")).click();
        Thread.sleep(500);
        driver.findElement(By.id("btn_check-manual_speaker")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[contains(@for,'radio_testing_speaker_1')]")).click();
        //*[@id="testing_area"]//div[2]/label[1]
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"testing_area\"]/div/ul/li/a")).click();
        Thread.sleep(1000);
        driver.switchTo().window(winHandleBefore);
        driver.findElement(By.id("btn_connect_test_next")).click();

        // Store the current window handle
        winHandleBefore = driver.getWindowHandle();

        driver.get("https://english.fdc-inc.com/admin/login");
        Thread.sleep(1000);
        driver.findElement(By.id("AdminLoginId")).sendKeys("FDC-Tester-Arman");
        driver.findElement(By.id("AdminPassword")).sendKeys("admin123");
        driver.findElement(By.id("AdminRememberMe")).click();
        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(3000);
        for(String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        //*[@id="sidebar-wrapper"]/ul/li[19]/a


    }

}

