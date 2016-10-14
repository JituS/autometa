import dfa.DFA;
import dfa.State;
import dfa.Transitions;
import dfa.Tuple;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DFATest {
  private Transitions transitions = new Transitions();
  private State q1 = new State("q1");
  private State q2 = new State("q2");
  private State q3 = new State("q3");
  private State q4 = new State("q4");
  private State q5 = new State("q5");
  private State q6 = new State("q6");

  @Test
  public void shouldReturnTrueIfLanguageAcceptTheString() throws Exception {
    transitions.setTransition(q1, q2, "0");
    Set<State> states = new HashSet<State>() {{
      add(q1);
      add(q2);
    }};
    ArrayList<String> alphabets = new ArrayList<String>() {{
      add("0");
      add("1");
    }};
    State initialState = q1;
    Set<State> finalStates = new HashSet<State>() {{
      add(q2);
    }};

    Tuple tuple = new Tuple(states, alphabets, transitions, initialState, finalStates);
    ArrayList<String> inputString = new ArrayList<String>() {{
      add("0");
    }};

    DFA dfa = new DFA(tuple, null, null, inputString, null);

    boolean result = dfa.verify(inputString);
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

    Set<State> states = new HashSet<State>() {{
      add(q1);
      add(q2);
      add(q3);
    }};
    ArrayList<String> alphabets = new ArrayList<String>() {{
      add("1");
      add("0");
    }};
    State initialState = q1;
    Set<State> finalStates = new HashSet<State>() {{
      add(q1);
      add(q2);
    }};
    Tuple tuple = new Tuple(states, alphabets, transitions, initialState, finalStates);
    ArrayList<String> inputString = new ArrayList<String>() {{
      add("1");
      add("0");
      add("1");
      add("0");
    }};
    DFA dfa = new DFA(tuple, null, null, inputString, null);

    boolean result = dfa.verify(inputString);
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

    Set<State> states = new HashSet<State>() {{
      add(q1);
      add(q2);
      add(q3);
      add(q4);
      add(q5);
      add(q6);
    }};
    ArrayList<String> alphabets = new ArrayList<String>() {{
      add("1");
      add("0");
    }};
    State initialState = q1;
    Set<State> finalStates = new HashSet<State>() {{
      add(q5);
    }};
    Tuple tuple = new Tuple(states, alphabets, transitions, initialState, finalStates);

    ArrayList<String> inputString = new ArrayList<String>() {{
      add("1");
      add("0");
      add("0");
      add("1");
      add("0");
    }};

    DFA dfa = new DFA(tuple, null, null, inputString, null);

    boolean result = dfa.verify(inputString);
    Assert.assertTrue(result);
  }

}