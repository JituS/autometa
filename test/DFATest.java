import commons.*;
import finiteAutomata.DFA;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DFATest {
  private ITransition<State> transitions = new DFATransition();
  private State q1 = new State("q1");
  private State q2 = new State("q2");
  private State q3 = new State("q3");
  private State q4 = new State("q4");
  private State q5 = new State("q5");
  private State q6 = new State("q6");

  @Test
  public void shouldReturnTrueIfLanguageAcceptTheString() throws Exception {
    transitions.setTransition(q1, q2, "0");
    States states = new States();
    states.add(q1);
    states.add(q2);

    List<String> alphabets = new ArrayList<String>() {{
      add("0");
      add("1");
    }};
    State initialState = q1;
    States finalStates = new States();
    finalStates.add(q2);

    Tuple<State> tuple = new Tuple<>(states, alphabets, transitions, initialState, finalStates);

    DFA dfa = new DFA(tuple, null);

    boolean result = dfa.verify("0");
    Assert.assertTrue(result);
  }

  @Test
  public void shouldReturnTrueIfLanguageAcceptTheStringContainsOneOnOddPlaces() throws Exception {
    transitions.setTransition(q1, q2, "1");
    transitions.setTransition(q1, q3, "0");
    transitions.setTransition(q2, q1, "0");
    transitions.setTransition(q2, q1, "1");
    transitions.setTransition(q3, q3, "1");
    transitions.setTransition(q3, q3, "0");

    States states = new States();
    states.add(q1);
    states.add(q2);
    states.add(q3);

    List<String> alphabets = new ArrayList<String>() {{
      add("1");
      add("0");
    }};
    State initialState = q1;
    States finalStates = new States();
    finalStates.add(q1);
    finalStates.add(q2);
    Tuple<State> tuple = new Tuple<>(states, alphabets, transitions, initialState, finalStates);
    DFA dfa = new DFA(tuple, null);

    boolean result = dfa.verify("1010");
    Assert.assertTrue(result);
  }

  @Test
  public void AllStringsThatBeginWithOneAndContainTheString001() throws Exception {
    transitions.setTransition(q1, q2, "1");
    transitions.setTransition(q1, q6, "0");
    transitions.setTransition(q2, q2, "1");
    transitions.setTransition(q2, q3, "0");
    transitions.setTransition(q3, q2, "1");
    transitions.setTransition(q3, q4, "0");
    transitions.setTransition(q4, q4, "0");
    transitions.setTransition(q4, q5, "1");
    transitions.setTransition(q5, q5, "1");
    transitions.setTransition(q5, q5, "0");
    transitions.setTransition(q6, q6, "0");
    transitions.setTransition(q6, q6, "1");

    States states = new States();
    states.add(q1);
    states.add(q2);
    states.add(q3);
    states.add(q4);
    states.add(q5);
    states.add(q6);

    List<String> alphabets = new ArrayList<String>() {{
      add("1");
      add("0");
    }};

    State initialState = q1;
    States finalStates = new States();
    finalStates.add(q5);

    Tuple<State> tuple = new Tuple<>(states, alphabets, transitions, initialState, finalStates);

    DFA dfa = new DFA(tuple, null);

    boolean result = dfa.verify("10010");
    Assert.assertTrue(result);
  }

}