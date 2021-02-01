package com.revature.e2e;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.revature.pom.LoginAndRegister;
import com.revature.pom.QueueAndGame;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GameTester {
	
	static {
		File file = new File("src/test/resources/geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
	}
	
	private static WebDriver driver;
	private static WebDriver driver2;
	private static LoginAndRegister loginAndRegister;
	private static LoginAndRegister loginAndRegister2;
	private static QueueAndGame queueAndGame;
	private static QueueAndGame queueAndGame2;
	
	@Given("two players are logged in")
	public void two_players_are_logged_in() {
		driver = new FirefoxDriver();
		driver2 = new FirefoxDriver();
		driver.get("http://localhost:4200");
	    driver2.get("http://localhost:4200");
	    loginAndRegister = new LoginAndRegister(driver);
	    loginAndRegister2 = new LoginAndRegister(driver2);
	    
	    if(loginAndRegister.checkLoggedIn()) {
	    	loginAndRegister.logout();
	    }
	    
	    if(loginAndRegister2.checkLoggedIn()) {
	    	loginAndRegister2.logout();
	    }
	    
	    loginAndRegister.login("test", "password");
	    loginAndRegister2.login("test2", "password");
	    assertTrue(loginAndRegister.checkLoggedIn("test"));
	    assertTrue(loginAndRegister2.checkLoggedIn("test2"));
	}
	
	@Given("a player is logged in")
	public void a_player_is_logged_in() {
	    driver = new FirefoxDriver();
	    driver.get("http://localhost:4200");
	    loginAndRegister = new LoginAndRegister(driver);
	    if(loginAndRegister.checkLoggedIn()) {
	    	loginAndRegister.logout();
	    }
	    loginAndRegister.login("test", "password");
	    assertTrue(loginAndRegister.checkLoggedIn("test"));
	}
	
	@When("they press the queue button")
	public void they_press_the_queue_button() {
		queueAndGame = new QueueAndGame(driver);
		queueAndGame.queueUp();
	}
	
	@Then("they will see the queue screen")
	public void they_will_see_the_queue_screen() {
		boolean queued;
		try {
			driver.findElement(By.id("dequeueBtn"));
			queued = true;
		} catch (NoSuchElementException e) {
			queued = false;
		}
		assertTrue(queued);
	}
	
	@Then("they should be able to leave the queue")
	public void they_should_be_able_to_leave_the_queue() {
	    queueAndGame.dequeue();
	    boolean dequeued;
		try {
			driver.findElement(By.id("queueBtn"));
			dequeued = true;
		} catch (NoSuchElementException e) {
			dequeued = false;
		}
		assertTrue(dequeued);
		driver.close();
	}
	
	@When("they both press the queue button")
	public void they_both_press_the_queue_button() {
		queueAndGame = new QueueAndGame(driver);
		queueAndGame2 = new QueueAndGame(driver2);
		queueAndGame.queueUpForGame();
		queueAndGame2.queueUpForGame();
	}
	
	@Then("they will be put into a game")
	public void they_will_be_put_into_a_game() {
	    assertTrue(queueAndGame.isInGame());
	    assertTrue(queueAndGame2.isInGame());
	}
}
