import com.thoughtworks.testrunner.FiniteAutomata;
import commons.Builder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DFAIntegrationTest {
  @Test
  public void shouldReturnTrueIfStringIsAccepted() throws Exception {
    String jsonString = "{\"name\":\"odd number of zeroes\"," +
      "\"type\":\"finiteAutomata\"," +
      "\"tuple\":{\"states\":[\"q1\",\"q2\"]," +
      "\"alphabets\":[\"1\",\"0\"]," +
      "\"delta\":{\"q1\":{\"0\":\"q2\",\"1\":\"q1\"},\"q2\":{\"0\":\"q1\",\"1\":\"q2\"}}," +
      "\"start-state\":\"q1\"," +
      "\"final-states\":[\"q2\"]}," +
      "\"pass-cases\":[\"0\",\"000\",\"00000\",\"10\",\"101010\",\"010101\"]," +
      "\"fail-cases\":[\"00\",\"0000\",\"1001\",\"1010\",\"001100\"]}";

    JSONObject FAJson = (JSONObject) new JSONParser().parse(jsonString);
    Builder builder = new Builder(FAJson);

    FiniteAutomata finiteAutomata = builder.buildFA();
    List<String> passCases = builder.getPassCases();
    List<String> failCases = builder.getFailCases();
    testCases(finiteAutomata, passCases, failCases);
  }

  private void testCases(FiniteAutomata dfa, List<String> passCases, List<String> failCases) {
    for (String passCase : passCases) {
      Assert.assertTrue(dfa.verify(passCase));
    }
    for (String failCase : failCases) {
      Assert.assertFalse(dfa.verify(failCase));
    }
  }
}
