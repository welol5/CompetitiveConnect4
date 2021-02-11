import { browser, by, element, WebElement } from 'protractor';

export class AppPage {
  async navigateTo(): Promise<unknown> {
    return browser.get(browser.baseUrl);
  }

  async getAppComponent(): Promise<WebElement> {
    return element(by.css('app-root'));
  }
}
