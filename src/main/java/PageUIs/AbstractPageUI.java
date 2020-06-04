package PageUIs;

public class AbstractPageUI {

  public static final String DYNAMIC_LINK = "//ul[@class='menusubnav']//a[text()='%s']";
  public static final String DYNAMIC_TABLE_INFOR = "//td[text()='%s']//following-sibling::td";
  public static final String DYNAMIC_TEXTBOX_TEXTAREA = "(//textarea | // input) [@name='%s']";
  public static final String DYNAMIC_TEXT_AREA_WITH_INFO = "//textarea[@name='%s' and text()='%s']";
  public static final String DYNAMIC_RADIO_BUTTON = "//input[@value='%s']";
  public static final String DYNAMIC_BUTTON = "//input[@name='%s']";
  public static final String DYNAMIC_DROPDOWN = "//select[@name='%s']";
  public static final String DYNAMIC_VALIDATE_MESSAGE = "//td[contains(text(),'%s')]//following-sibling::td/label";
  public static final String DYNAMIC_PAGE_TITLE = "//p[@class='heading3' and text()='%s']";
  public static final String DYNAMIC_AGENT = "//a[contains(text(),'%s')]/ancestor::div//input";
  public static final String DYNAMIC_TENBAOCAO_TABLEDATA = "//table//td/a[contains(text(),'%s')]";
}
