package com.vthakur.pages.flightreservation;

import com.vthakur.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//Page#4 Author: Vinay Thakur || Tutor: Vinod Selvaraj
public class FlightsSelectionPage extends AbstractPage {

    //added logs to debug failure, it can be removed later
    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);


    @FindBy(name = "departure-flight")
        private List<WebElement> departureFlightsOptions;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlightsOptions;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightsButton;



    public FlightsSelectionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightsButton));
        return this.confirmFlightsButton.isDisplayed();
    }

    public void selectFlights(){
        int random = ThreadLocalRandom.current().nextInt(0, departureFlightsOptions.size());
        log.info("random# : {}", random);
        this.departureFlightsOptions.get(random).click();

        this.arrivalFlightsOptions.get(random).click();
    }

    public void confirmFlights(){
        this.confirmFlightsButton.click();
    }
}
