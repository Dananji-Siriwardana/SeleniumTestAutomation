package AllureTest;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestCase3 {
  @Test
  public void testMethod3() {
	  throw new SkipException("M3 will be skipped");
  }
  
  @Test
  public void testMethod4() {
	  throw new SkipException("M4 will be skipped");
  }
}
