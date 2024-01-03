import data.FirstPageData;
import org.testng.Assert;
import util.BaseUtility;
import org.testng.annotations.Test;

public class FirstPageTest extends BaseUtility implements FirstPageData {

    @Test(description = "Testing process")
    public void testingTest() {
        firstPageSteps.inputSearchData("Hello23425425");
        Assert.assertEquals(expectedURL, driver.getCurrentUrl());
    }

    @Test
    public void trustMe() {
        firstPageSteps.inputSearchData("Trust me");
    }


}
