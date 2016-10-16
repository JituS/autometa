package utils;

import dfa.State;
import dfa.Transitions;
import dfa.Tuple;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GenericMapper {
  public static List<String> mapObjectListToStringList(Object states) {
    List strings = (List) states;
    ArrayList<String> mappedStrings = new ArrayList<>();
    for (Object string : strings) {
      mappedStrings.add((String) string);
    }
    return mappedStrings;
  }

  public static Transitions mapToDFATransitions(Map<String, Map<String, String>> transitions) {
    Transitions mappedTransition = new Transitions();
    transitions.entrySet().stream().forEach(m -> {
      State state = new State(m.getKey());
      Map<String, String> transitionsOfState = m.getValue();
      transitionsOfState.forEach((alphabet, nextState) -> mappedTransition.setTransition(state, new State(nextState), alphabet));
    });
    return mappedTransition;
  }

  public static Transitions mapToNFATransitions(Map<String, Map<String, ArrayList<String>>> transitions) {
    Transitions mappedTransition = new Transitions();
    transitions.entrySet().stream().forEach(m -> {
      State state = new State(m.getKey());
      Map<String, ArrayList<String>> transitionsOfState = m.getValue();
      transitionsOfState.forEach((alphabet, nextState) ->
        nextState.forEach(eachState -> mappedTransition.setTransition(state, new State(eachState), alphabet)));
    });
    return mappedTransition;
  }

  public static Tuple mapToFATuple(JSONObject tuple, Transitions allTransitions) {
    Set<State> states = mapObjectListToStringList(tuple.get("states")).stream().map(State::new).collect(Collectors.toSet());
    List<String> alphabets = mapObjectListToStringList(tuple.get("alphabets"));
    State initialState = new State((String) tuple.get("start-state"));
    Set<State> finalStates = mapObjectListToStringList(tuple.get("final-states")).stream().map(State::new).collect(Collectors.toSet());
    return new Tuple(states, alphabets, allTransitions, initialState, finalStates);
  }
}