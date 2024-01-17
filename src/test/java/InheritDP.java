import org.testng.annotations.Test;

public class InheritDP {
  @Test(dataProvider = "dpdemo", dataProviderClass = DataProviderTest.class)
  
  public void f1(String str) {
	  System.out.println("data "+str);
  }
}
