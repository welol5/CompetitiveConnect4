import { AppPage } from './app.po';
import { browser, by, element, logging } from 'protractor';
import { LoginPage } from './login.po';
import { RegisterPage } from './register.po';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', async () => {
    await page.navigateTo();
    expect(await page.getAppComponent()).toBeTruthy();
  });

  it('should be able to create a new user and login', async() => {
    let username = 'test';
    let password = 'password';
    
    let login : LoginPage = new LoginPage();
    login.register();
    let register: RegisterPage = new RegisterPage();
    register.register(username, password);
    expect(await (await login.isLoggedIn()).getText()).toEqual(username);
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
