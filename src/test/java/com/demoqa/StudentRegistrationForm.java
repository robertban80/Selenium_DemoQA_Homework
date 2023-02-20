package com.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StudentRegistrationForm {
    private WebDriver driver;
    @BeforeMethod
    private void setUp(){
        //se seteaza driverul
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        //se creaza un nou driver
        driver = new ChromeDriver();
        //se deschide pagina care trebuie testata
        driver.get("https://demoqa.com/automation-practice-form");
        //se maximizeaza fereastra cu pagina deschisa
        driver.manage().window().maximize();
    }

    @Test
    public void register(){
        //enter first name
        WebElement firstnameInput = driver.findElement(By.id("firstName"));
        firstnameInput.sendKeys("Ionica");

        //enter last name
        WebElement lastnameInput = driver.findElement(By.id("lastName"));
        lastnameInput.sendKeys("Popescu");

        //enter user email
        WebElement emailInput = driver.findElement(By.id("userEmail"));
        emailInput.sendKeys("Ionica.Popescu@email.com");

        //gender pick male
        WebElement gender_1 = driver.findElement(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[1]/label"));
        gender_1.click();

        //gender pick femail
        WebElement gender_2 = driver.findElement(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[2]/label"));
        gender_2.click();

        //gender pick other
        WebElement gender_3 = driver.findElement(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[3]/label"));
        gender_3.click();

        //user mobile number
        WebElement mobileNumber = driver.findElement(By.id("userNumber"));
        mobileNumber.sendKeys("0123456789");

        //date fill - date picker activation
        WebElement dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirth.click();

        //Select the month using the "Select" package
        WebElement monthInput = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select"));
        Select monthPicker = new Select(monthInput);
        monthPicker.selectByValue("10"); //10 for November

        //Select the year using the "Select" package
        WebElement yearInput = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select"));
        Select yearPicker = new Select(yearInput);
        yearPicker.selectByValue("1980");

        //day picker by click
        WebElement dayInput = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[2]/div[3]/div[2]"));
        dayInput.click();

        //input subjects
        WebElement subjectInput = driver.findElement(By.xpath("//*[@id=\"subjectsInput\"]"));
        subjectInput.sendKeys("Arts");
        subjectInput.sendKeys(Keys.ENTER);
        subjectInput.sendKeys("Maths");
        subjectInput.sendKeys(Keys.ENTER);

        //hobbies checkboxes
        WebElement checkHobby_1 = driver.findElement(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[1]/label"));
        checkHobby_1.click();
        WebElement checkHobby_2 = driver.findElement(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[2]/label"));
        checkHobby_2.click();
        WebElement checkHobby_3 = driver.findElement(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[3]/label"));;
        checkHobby_3.click();

        //upload picture
        WebElement chooseFile = driver.findElement(By.xpath("//*[@id=\"uploadPicture\"]"));
        chooseFile.sendKeys("C:\\Users\\robert.ban\\IdeaProjects\\Selenium_DemoQA_Homework\\src\\test\\resources\\test.jpg");

        //Address
        WebElement addressFill = driver.findElement(By.xpath("//*[@id=\"currentAddress\"]"));
        addressFill.sendKeys("Current address is the sale as the last one");

        //Select state
        WebElement stateSelect = driver.findElement(By.xpath("//*[@id=\"react-select-3-input\"]"));
        //Select statePicker = new Select(stateSelect);
        //statePicker.selectByValue("NCR");
        stateSelect.sendKeys("NCR");
        stateSelect.sendKeys(Keys.ENTER);

        //Select City
        WebElement citySelect = driver.findElement(By.xpath("//*[@id=\"react-select-4-input\"]"));
        citySelect.sendKeys("Delhi");
        citySelect.sendKeys(Keys.ENTER);
        citySelect.sendKeys(Keys.TAB, Keys.ENTER); //e necesara linia asta, ca sa se activeze butonul de Submit!!!

        //click Select button
        WebElement selectButton = driver.findElement(By.id("submit"));
        //System.out.println(selectButton.getText());
        //selectButton.click();

        //waitForIt(5);

        //verify submitted data
        WebElement windowsModal = driver.findElement(By.id("example-modal-sizes-title-lg"));
        Assert.assertTrue(windowsModal.isDisplayed());
        Assert.assertTrue(windowsModal.getText().contains("Thanks for submitting the form"));

        //close modal
        WebElement closeModal = driver.findElement(By.id("closeLargeModal"));
        Assert.assertTrue(closeModal.isDisplayed());
        waitForIt(1);
        //closeModal.click(); // cu click nu merge!!!
        closeModal.sendKeys(Keys.ENTER);

    }


    @AfterMethod
    private void tearDown(){
        driver.close();
    }
    
    private void waitForIt(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
