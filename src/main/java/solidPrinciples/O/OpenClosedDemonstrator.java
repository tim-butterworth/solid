package solidPrinciples.O;

import solidPrinciples.O.openClosed.tooClosed.TooClosed;
import solidPrinciples.O.openClosed.tooOpen.Step;
import solidPrinciples.O.openClosed.tooOpen.TooOpen;
import solidPrinciples.O.openClosed.tooOpen.transitions.FinishTransition;
import solidPrinciples.O.openClosed.tooOpen.transitions.GoToTransition;
import solidPrinciples.O.openClosed.tooOpen.transitions.NextLineTransition;

import java.util.HashMap;
import java.util.Map;

public class OpenClosedDemonstrator {
    public static void main(String[] args) {
        TooClosed tooClosed = new TooClosed();
        System.out.println(tooClosed.execute());

        Step[] steps = {
                state -> {
                    //0
                    state.put("key", "fancy value");
                    state.put("index", 0);

                    return new NextLineTransition(state);
                },
                state -> {
                    //1
                    Integer index = pretendNotToCast(state, "index");
                    state.put("index", index + 1);

                    return new NextLineTransition(state);
                },
                state -> {
                    //2
                    Integer index = pretendNotToCast(state, "index");

                    if (index == 10) return new NextLineTransition(state);
                    else return new GoToTransition(state, 1);
                },
                FinishTransition::new
        };

        TooOpen tooOpen = new TooOpen();
        System.out.println(tooOpen.<String>execute(
                new HashMap<>(),
                stateMap -> pretendNotToCast(stateMap, "index"),
                steps
        ));
    }

    @SuppressWarnings("unchecked") // They will never know!
    private static <T> T pretendNotToCast(Map<String, Object> state, String key) {
        return (T) state.get(key);
    }
}
