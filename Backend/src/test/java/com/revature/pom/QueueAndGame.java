package com.revature.pom;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class QueueAndGame {
	private WebDriver driver;
	
	private WebElement gameTable;
	
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
	}
	
	public boolean isInGame() {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("app-game"))));
		gameTable = driver.findElement(By.id("board"));
		
		boolean inGame;
		try {
			driver.findElement(By.tagName("app-game"));
			inGame = true;
		} catch (NoSuchElementException e) {
			inGame = false;
		}
		return inGame;
	}
	
	public void makeMove(int rowNum, int colNum) {
		WebElement row = gameTable.findElements(By.tagName("tr")).get(rowNum);
		WebElement position = row.findElements(By.tagName("td")).get(colNum);
		position.click();
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				//.ignoring(NoSuchElementException.class);
		
		row = gameTable.findElements(By.tagName("tr")).get(rowNum);
		position = row.findElements(By.tagName("td")).get(colNum);
		try {
			wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(position.findElement(By.cssSelector(".player0")))));
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkMoveMade(int rowNum, int colNum) {
		WebElement row = gameTable.findElements(By.tagName("tr")).get(rowNum);
		WebElement position = row.findElements(By.tagName("td")).get(colNum);
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		try {
			wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(position.findElement(By.cssSelector(".player0")))));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
}
