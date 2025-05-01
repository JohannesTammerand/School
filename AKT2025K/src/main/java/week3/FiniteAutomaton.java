package week3;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class FiniteAutomaton extends AbstractAutomaton {

    Set<Integer> states = new HashSet<>();
    Integer startState;
    Set<Integer> acceptingStates = new HashSet<>();
    Set<Object[]> transitions = new HashSet<>();

    @Override
    public void addState(int state) {
        states.add(state);
    }

    @Override
    public void setStartState(int state) {
        startState = state;
    }

    @Override
    public void addAcceptingState(int state) {
        acceptingStates.add(state);
    }

    @Override
    public void addTransition(int fromState, Character label, int toState) {
        Object[] o = new Object[]{fromState, label, toState};
        transitions.add(o);
    }

    @Override
    public Set<Integer> getStates() {
        return states;
    }

    @Override
    public Integer getStartState() {
        return startState;
    }

    @Override
    public Set<Integer> getAcceptingStates() {
        return acceptingStates;
    }

    @Override
    public Set<Character> getOutgoingLabels(int state) {
        Set<Character> result = new HashSet<>();
        for (Object[] o : transitions){
            if ((int)o[0] == state){
                if (o[1] == null){
                    result.add(null);
                } else {
                    result.add((char)o[1]);
                }
            }
        }
        return result;
    }

    @Override
    public Set<Integer> getDestinations(int state, Character label) {
        Set<Integer> result = new HashSet<>();
        for (Object[] o : transitions){
            if ((int)o[0] == state && Objects.equals(o[1], label)){
                result.add((int)o[2]);
            }
        }
        return result;
    }

    @Override
    public boolean accepts(String input) {

        // Stack holds branching points in processing the input
        // Form: [state, depth, move1, move2, ...]
        Stack<Object[]> branches = new Stack<>();
        Stack<Object[][]> branchMoves = new Stack<>();
        int state = getStartState();
        ArrayList<Object[]> moves = new ArrayList<>();
        int i = 0;
        while (true){
            if (i == input.length()){
                if (getAcceptingStates().contains(state)){
                    return true;
                } else if (branches.isEmpty()){
                    return false;
                } else {
                    Object[] lastBranch = branches.pop();
                    Object[][] lastBranchMoves = branchMoves.pop();
                    state = (int)lastBranch[0];
                    i = (int)lastBranch[1];
                    for (int j = 2; j < lastBranch.length; j++){
                        moves.add(lastBranchMoves[j]);;
                    }
                }
            } else {
                char c = input.charAt(i);
                if (moves.isEmpty()){
                    for (Object[] t : transitions){
                        if (t[2] == null || (int)t[2] == state){
                            moves.add(t);
                        }
                    }
                }
                if (moves.isEmpty()){
                    Object[] lastBranch = branches.pop();
                    Object[][] lastBranchMoves = branchMoves.pop();
                    state = (int)lastBranch[0];
                    i = (int)lastBranch[1];
                    for (int j = 2; j < lastBranch.length; j++){
                        moves.add(lastBranchMoves[j]);;
                    }
                } else if (moves.size() == 1){
                    state = (int)(moves.getFirst()[2]);
                    i++;
                } else {
                    branches.add(new Object[]{state, i});
                    state = (int)(moves.getFirst()[2]);
                    i++;

                }
            }
        }
    }

    /**
     * Seda meetodit ei hinnata ja seda ei pea muutma, aga läbikukkunud testide korral
     * antakse sulle automaadi kirjelduseks just selle meetodi tagastusväärtus.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) throws IOException {
        FiniteAutomaton fa = new FiniteAutomaton();

        fa.addState(0);
        fa.addState(1);
        fa.addState(2);

        fa.addTransition(0, 'b', 0);
        fa.addTransition(0, 'c', 2);
        fa.addTransition(2, 'a', 1);
        fa.addTransition(1, 'd', 0);
        fa.addTransition(0, null, 1);

        fa.setStartState(0);
        fa.addAcceptingState(1);

        System.out.println(fa.accepts("cadbbbca")); // true
        System.out.println(fa.accepts("abc"));      // false
        System.out.println(fa.accepts(""));         // true

        // Pead ise veenduda, et toString töötab...
        System.out.println(fa);
        fa.renderPngFile(Paths.get("graphs", "auto.png"));
    }
}
