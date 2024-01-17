import org.testng.annotations.Test;
import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

public class MethodParam {
	
  @Test(dataProvider="dp")
  public void sum(int a, int b, int result) {
	  int sum=a+b;
	  Assert.assertEquals(result, sum);
  }
  @Test(dataProvider="dp")
  public void diff(int a, int b, int result) {
	  int diff=a-b;
	  Assert.assertEquals(result, diff);
  }

  @DataProvider(name="dp")
  public Object[][] dp(Method m) {
    switch(m.getName()) {
    case "sum":
    	return new Object[][] {{1,2,3},{1,3,4}};
    	
    
  case "diff":
  	return new Object[][] {{1,2,3},{1,3,4}};
  	
  
  }
    return null;
  }
  
  

  
}