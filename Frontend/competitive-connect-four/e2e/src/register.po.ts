import { browser, by, element, ElementFinder, ProtractorExpectedConditions, WebElement } from 'protractor';
import { protractor } from 'protractor/built/ptor';

export class RegisterPage {

    private EC: ProtractorExpectedConditions = protractor.ExpectedConditions;

    private registerComponent: ElementFinder;

    private usernameField: ElementFinder;
    private passwordField: ElementFinder;
    private registerButton: ElementFinder;

    constructor(){
        browser.wait(this.EC.visibilityOf(element(by.tagName('app-register'))), 5000);
        this.registerComponent = element(by.tagName('app-register'));
        this.usernameField = this.registerComponent.element(by.id('user'));
        this.passwordField = this.registerComponent.element(by.id('pass'));
        this.registerButton = this.registerComponent.element(by.id('registerBtn'));
    }

    async register(username: string, password: string): Promise<void> {
        browser.wait(this.EC.visibilityOf(this.usernameField), 5000);
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.registerButton.click();
    }
}