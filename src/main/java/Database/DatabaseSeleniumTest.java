package Database;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DatabaseSeleniumTest {

    public static void main(String[] args) {
        //Initialize WebDriver for Selenium
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.example.com");

        DatabaseHelper.deleteRow("Test2");

        //Fetch data from the database (Example: Fetch user data)
        DatabaseHelper.fetchData();

        //Insert test result into the database
        String insertQuery = "INSERT INTO seleniumuser (firstname, email) VALUES ('Test4', 'Passed4@gmail.com')";
        DatabaseHelper.insertData(insertQuery); // Insert test result data

        DatabaseHelper.fetchData();

        //Close the browser after the test
        driver.quit();
    }
}
