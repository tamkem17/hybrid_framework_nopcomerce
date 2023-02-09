package commons;

import java.awt.Desktop.Action;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
// viet cac ham dung chung
	
	public void openUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refeshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	public Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, 30).until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptToAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void cancelToAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public void sendKeyToAlert(WebDriver driver, String valueToSendKey) {
		waitForAlertPresence(driver).sendKeys(valueToSendKey);
	}
	
	public String getTextInAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
	
	public void switchToWindownByID(WebDriver driver, String parentID) {
		//Lay tat ca id cua tab windown dang mo
		Set<String> allWindownIDs = driver.getWindowHandles();
		for(String id : allWindownIDs) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	public void switchToWindownByTitle(WebDriver driver, String expectedPageTitle) {
		//Lay tat ca id cua tab windown dang mo
		Set<String> allWindownIDs = driver.getWindowHandles();
		for(String id : allWindownIDs) {
				driver.switchTo().window(id);
				String currentPageTitle = driver.getTitle();
				System.out.print(currentPageTitle);
				if(!currentPageTitle.equals(expectedPageTitle)) {
					break;
			}
		}
	}
	
	public void closeAllWindownWithoutParent(WebDriver driver, String parentId) {
		Set<String> allWindownIDs = driver.getWindowHandles();
		for(String id : allWindownIDs) {
			if(!id.equals(parentId)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentId);
	
}
	
	public By getXpath(String locator) {
		return By.xpath(locator);
	}
	
	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getXpath(locator));
	}
	
	public List<WebElement> getWebListElement(WebDriver driver, String locator) {
		return driver.findElements(getXpath(locator));
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}
	
	public void sendKeyToElement(WebDriver driver, String locator, String valueToSendKey) {
	getWebElement(driver, locator).clear();
	getWebElement(driver, locator).sendKeys(valueToSendKey);
	}
	
	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}
	
	public void selectItemInDropdown(WebDriver driver, String locator, String itemValue) {
	 new Select(getWebElement(driver, locator)).selectByVisibleText(itemValue);
	}
	
	public String getSelectItemInDropdown(WebDriver driver, String locator) {
		return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
		}
	
	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		return new Select(getWebElement(driver, locator)).isMultiple();
		}
	
	public void sleepInSecond(long timeInSecond){
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}
	
	public String getAttributeValue(WebDriver driver, String locator, String atributeName) {
		return getWebElement(driver, locator).getAttribute(atributeName);
	}
	
	public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}
	
    public String getHexaColorByRgbaColor(String RgbaColor) {
    	return Color.fromString(RgbaColor).asHex();
    }
    
    public int getElementsSize(WebDriver driver, String locator) {
		return driver.findElements(getXpath(locator)).size();
	}
    
    public void checkToCheckBox(WebDriver driver, String locator) {
    	if(!getWebElement(driver, locator).isSelected()) {
    		getWebElement(driver, locator).click();
    	}
    }
    
    public void unCheckToCheckBox(WebDriver driver, String locator) {
    	if(getWebElement(driver, locator).isSelected()) {
    		getWebElement(driver, locator).click();
    	}
    }
    
    public boolean isElementDisplayed(WebDriver driver, String locator) {
    	return getWebElement(driver, locator).isDisplayed();
    }
    
    public boolean isElementSelected(WebDriver driver, String locator) {
    	return getWebElement(driver, locator).isSelected();
    }
    public boolean isElementEnabled(WebDriver driver, String locator) {
    	return getWebElement(driver, locator).isEnabled();
    }
    
    public void switchToFrame(WebDriver driver, String locator) {
    	driver.switchTo().frame(getWebElement(driver, locator));
    }
    public void switchToDefautContent(WebDriver driver) {
    	driver.switchTo().defaultContent();
    }
    public void moveToElement(WebDriver driver, String locator) {
    	new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();;
    }
    
    public void doupleClickToElement(WebDriver driver, String locator) {
    	new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }
    
    public void rightClickToElement(WebDriver driver, String locator) {
    	new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }
    
    public void dragDropToElement(WebDriver driver, String sourceLocator, String targetLocator) {
    	new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
    }
    
    public void sendKeysBroadToElement(WebDriver driver, String locator, Keys key) {
    	new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
}
    
    public Object executeForBrowser(WebDriver driver, String javaScript) {
		return ((JavascriptExecutor) driver).executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(getXpath(locator)));
	}
	public void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(getXpath(locator)));
	}
	public void waitForElementInvisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(getXpath(locator)));
	}
	
	public void waitForElementPresence(WebDriver driver, String locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(getXpath(locator)));
	}
	
	
	public void waitForListElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getXpath(locator)));
	}
	
	public void waitForListElementInvisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfAllElements(getWebListElement(driver, locator)));
	}
	public void waitForListElementPresence(WebDriver driver, String locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getXpath(locator)));
	}
	

}
