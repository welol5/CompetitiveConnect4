package com.revature.pom;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class LoginAndRegister {
	
	private static final long TIMEOUT = 10L;

	private WebDriver driver;

	@FindBy(xpath="//a[@href='/profile']")
	private WebElement profileLink;

	public LoginAndRegister(WebDriver driver) {
		this.driver = driver;
	}

	public boolean login(String username, String password) {
		WebElement usernameField = driver.findElement(By.id("user"));
		WebElement passwordField = driver.findElement(By.id("pass"));
		WebElement loginButton = driver.findElement(By.id("loginBtn"));

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(TIMEOUT, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);


		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@href='/profile']"))));
			return true;
		} catch (TimeoutException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void badLogin(String username, String password) {
		WebElement usernameField = driver.findElement(By.id("user"));
		WebElement passwordField = driver.findElement(By.id("pass"));
		WebElement loginButton = driver.findElement(By.id("loginBtn"));

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		loginButton.click();

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(TIMEOUT, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);


		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@href='/profile']"))));
	}

	public boolean checkLoggedIn() {
		try {
			driver.findElement(By.xpath("//a[@href='/profile']"));
			return true;
		} catch (NoSuchElementException e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	public boolean checkLoggedIn(String username) {
		try {
			String usernameLink = driver.findElement(By.xpath("//div/a[@href='/profile']")).getText();
			if(usernameLink.trim().equals(username)) {
				return true;
			} else {
				return false;
			}
		} catch (NoSuchElementException e) {
			//e.printStackTrace();
			return false;
		}
	}

	public boolean logout() {
		WebElement logoutButton = driver.findElement(By.id("loginBtn"));
		if(logoutButton.getText().equals("Log Out")) {
			logoutButton.click();
			return true;
		} else {
			return false;
		}
	}

	public void registerNewAccount() {
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(TIMEOUT, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//app-login/a[@href='/register']")));
		WebElement registerLink = driver.findElement(By.xpath("//app-login/a[@href='/register']"));
		registerLink.click();
	}

	public void setNewAccountUsernameAndPassword(String username, String password) {
		WebElement usernameField = driver.findElement(By.xpath("//form/input[@id='user']"));
		WebElement passwordField = driver.findElement(By.xpath("//form/input[@id='pass']"));
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
	}

	public void clickRegisterButton() {
		WebElement registerButton = driver.findElement(By.xpath("//form/button[@id='registerBtn']"));
		registerButton.click();
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(TIMEOUT, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='loginBtn']")));
	}
}
