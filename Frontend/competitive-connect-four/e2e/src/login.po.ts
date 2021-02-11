import { browser, by, element, WebElement } from 'protractor';

export class LoginPage {

    private usernameField: WebElement;
    private passwordField: WebElement;
    private loginButton: WebElement;
    private registerLink: WebElement;

    constructor() {
        this.usernameField = element(by.id('user'));
        this.passwordField = element(by.id('pass'));
        this.loginButton = element(by.id('loginBtn'));
        this.registerLink = element(by.linkText('register'));
    }

    async getLoginButton(): Promise<WebElement> {
        return this.loginButton;
    }

    async login(username: string, password: string): Promise<void> {
        this.usernameField.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.loginButton.click();
    }

    async register(): Promise<void> {
        this.registerLink.click();
    }

    async isLoggedIn(): Promise<WebElement>{
        return element(by.linkText('profile'));
    }
}
