package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import po.GadgetsnowPO;
import po.SummaryPO;
import utils.Summary;

import java.awt.print.Book;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Gadgetsnow {
    WebDriver   driver;
    GadgetsnowPO gadgetsnowPO;
    SummaryPO   summaryPO;
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Dado("que ingreso a gadgetsnow.com")
    public void queIngresoAGadgetsnowCom() {
        driver.get("https://www.gadgetsnow.com/compare-laptops");
        driver.manage().window().maximize();
    }

    @Cuando("selecciono el primer laptop {string}")
    public void seleccionoElPrimerLaptop(String laptop) throws InterruptedException {
        gadgetsnowPO = new GadgetsnowPO(driver);
        gadgetsnowPO.getCompareCard().findElement(By.xpath(".//i")).click();
        gadgetsnowPO.getCompareCard().findElement(By.xpath(".//input")).sendKeys(laptop);
        Thread.sleep(1000);
        gadgetsnowPO.getCompareCard().findElement(By.xpath(".//mark[text()='"+laptop+"']")).click();
        Thread.sleep(1000);
    }

    @Entonces("comparo los productos")
    public void comparoLosProductos() throws IOException {
        gadgetsnowPO = new GadgetsnowPO(driver);
        gadgetsnowPO.getCompareButton().click();
        summaryPO = new SummaryPO(driver);
        writeExcel(summaryPO.getSummaryList(), "Evidencia.xls");
    }

    private void writeExcel(List<WebElement> listElements, String excelFilePath) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        int rowCount = 0;

        for (WebElement element : listElements) {
            Row row = sheet.createRow(++rowCount);
            writeSummary(element, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
    }

    private void writeSummary(WebElement element, Row row) {
        Cell cell = row.createCell(1);
        cell.setCellValue(element.findElement(By.xpath(".//th")).getText());

        cell = row.createCell(2);
        cell.setCellValue(element.findElement(By.xpath(".//td[1]")).getText());

        cell = row.createCell(3);
        cell.setCellValue(element.findElement(By.xpath(".//td[2]")).getText());

        cell = row.createCell(4);
        cell.setCellValue(element.findElement(By.xpath(".//td[3]")).getText());

        cell = row.createCell(5);
        cell.setCellValue(element.findElement(By.xpath(".//td[4]")).getText());
    }
}
