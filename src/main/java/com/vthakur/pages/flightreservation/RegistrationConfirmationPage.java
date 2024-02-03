package com.vthakur.pages.flightreservation;

import com.vthakur.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
//Page#2 Author: Vinay Thakur || Tutor: Vinod Selvaraj
public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id= "go-to-flights-search")
    private WebElement goToFlightSearchButton;

    @FindBy(css = "#registration-confirmation-section p b")
    private WebElement firstNameElement;

    // create constructor
    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightSearchButton));
        return this.goToFlightSearchButton.isDisplayed();
    }

    public  void goToFlightSearch(){
        this.goToFlightSearchButton.click();
    }

    public String getFirstName(){
        return this.firstNameElement.getText();
    }


}
