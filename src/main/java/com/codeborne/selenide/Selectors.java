package com.codeborne.selenide;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Quotes;

public class Selectors {
  /**
   * Find element CONTAINING given text (as a substring)
   *
   * NB! It seems that Selenium WebDriver does not support i18n characters in XPath :(
   *
   * @param elementText Text to search inside element
   * @return standard selenium By criteria`
   */
  public static By withText(String elementText) {
    return new WithText(elementText);
  }

  /**
   * Find element that has EXACTLY this text
   *
   * NB! It seems that Selenium WebDriver does not support i18n characters in XPath :(
   *
   * @param elementText Text that searched element should have
   * @return standard selenium By criteria
   */
  public static By byText(String elementText) {
    return new ByText(elementText);
  }

  /**
   * Find elements having attribute with given value.
   *
   * Seems to work incorrectly if attribute name contains dash, for example: <option data-mailServerId="123"></option>
   *
   * @param attributeName name of attribute, should not be empty or null
   * @param attributeValue value of attribute, should not contain both apostrophes and quotes
   * @return standard selenium By criteria
   */
  public static By byAttribute(String attributeName, String attributeValue) {
    return By.xpath(".//*[@" + attributeName + '=' + Quotes.escape(attributeValue) + ']');
  }

  /**
   * Synonym for #byAttribute
   *
   * Seems to work incorrectly in HtmlUnit and PhantomJS if attribute name contains dash (e.g. "data-mailServerId")
   */
  public static By by(String attributeName, String attributeValue) {
    return byAttribute(attributeName, attributeValue);
  }

  /**
   * Find element with given title ("title" attribute)
   */
  public static By byTitle(String title) {
    return byAttribute("title", title);
  }

  /**
   * Find input element with given value ("value" attribute)
   */
  public static By byValue(String value) {
    return byAttribute("value", value);
  }

  public static class ByText extends By.ByXPath {
    protected final String elementText;

    public ByText(String elementText) {
      super(".//*/text()[normalize-space(.) = " + Quotes.escape(elementText) + "]/parent::*");
      this.elementText = elementText;
    }

    @Override
    public String toString() {
      return "by text: " + elementText;
    }

    String getXPath() {
      return super.toString().replace("By.xpath: ", "");
    }
  }

  public static class WithText extends By.ByXPath {
    protected final String elementText;

    public WithText(String elementText) {
      super(".//*/text()[contains(normalize-space(.), " + Quotes.escape(elementText) + ")]/parent::*");
      this.elementText = elementText;
    }

    @Override
    public String toString() {
      return "with text: " + elementText;
    }

    String getXPath() {
      return super.toString().replace("By.xpath: ", "");
    }
  }
  /**
   * @see By#name(java.lang.String)
   * @since 3.1
   */
  public static By byName(String name) {
    return By.name(name);
  }

  /**
   * @see By#xpath(java.lang.String)
   * @since 3.1
   */
  public static By byXpath(String xpath) {
    return By.xpath(xpath);
  }

  /**
   * @see By#linkText(java.lang.String)
   * @since 3.1
   */
  public static By byLinkText(String linkText) {
    return By.linkText(linkText);
  }

  /**
   * @see By#partialLinkText(java.lang.String)
   * @since 3.1
   */
  public static By byPartialLinkText(String partialLinkText) {
    return By.partialLinkText(partialLinkText);
  }

  /**
   * @see By#id(java.lang.String)
   * @since 3.1
   */
  public static By byId(String id) {
    return By.id(id);
  }

  /**
   * @see By#cssSelector(java.lang.String)
   * @since 3.8
   */
  public static By byCssSelector(String css) {
    return By.cssSelector(css);
  }

  /**
   * @see By#className(java.lang.String)
   * @since 3.8
   */
  public static By byClassName(String className) {
    return By.className(className);
  }
}
