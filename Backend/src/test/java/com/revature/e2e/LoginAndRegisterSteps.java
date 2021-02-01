package com.revature.e2e;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.revature.pom.LoginAndRegister;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginAndRegisterSteps {
	
	static {
		File file = new File("src/test/resources/geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
	}
	
	private static WebDriver driver;
	private static LoginAndRegister loginAndRegister;
	
	
	@Given("I am on the home page and not logged in")
	public void i_am_on_the_home_page_and_not_logged_in() {
		newPage();
	    loginAndRegister = new LoginAndRegister(driver);
	    if(loginAndRegister.checkLoggedIn()) {
	    	loginAndRegister.logout();
	    }
	    assertFalse(loginAndRegister.checkLoggedIn());
	}

	@When("I try to login without having an account")
	public void i_try_to_login_without_having_an_account() {
	    loginAndRegister.badLogin("doomedToFail", "password");
	    assertFalse(loginAndRegister.checkLoggedIn());
	}

	@Then("I should be told my login info was invalid")
	public void i_should_be_told_my_login_info_was_invalid() {
		driver.close();
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("I click the register button")
	public void i_click_the_register_button() {
	    loginAndRegister.registerNewAccount();
	    assertTrue(true);
	}
	
	@When("I can type in a new username {string} and password {string}")
	public void i_can_type_in_a_new_username_and_password(String string, String string2) {
	    loginAndRegister.setNewAccountUsernameAndPassword(string, string2);
	}

	@When("I click Register")
	public void i_click_Register() {
	    loginAndRegister.clickRegisterButton();
	}

	@Then("I should be able to login using {string} and {string}")
	public void i_should_be_able_to_login_using_and(String string, String string2) {
	    loginAndRegister.login(string, string2);
	    assertTrue(loginAndRegister.checkLoggedIn());
	}

	@Then("I can logout")
	public void i_can_logout() {
	    assertTrue(loginAndRegister.logout());
	    driver.close();
	}
	
	private void newPage() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:4200");
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("app-root"))));
	}
}
