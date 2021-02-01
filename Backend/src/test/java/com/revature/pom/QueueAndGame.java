package com.revature.pom;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class QueueAndGame {
	private WebDriver driver;
	
	public QueueAndGame(WebDriver driver) {
		this.driver = driver;
	}
	
	public void queueUp() {
		WebElement queueButton = driver.findElement(By.id("queueBtn"));
		queueButton.click();
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("dequeueBtn"))));
	}
	
	public void dequeue() {
		WebElement dequeueButton = driver.findElement(By.id("dequeueBtn"));
		dequeueButton.click();
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("queueBtn"))));
	}
	
	public void queueUpForGame() {
		WebElement queueButton = driver.findElement(By.id("queueBtn"));
		queueButton.click();
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("app-game"))));
	}
	
	public boolean isInGame() {
		boolean inGame;
		try {
			driver.findElement(By.tagName("app-game"));
			inGame = true;
		} catch (NoSuchElementException e) {
			inGame = false;
		}
		return inGame;
	}
}
