package O;

import O.openClosed.tooOpen.Step;
import O.openClosed.tooOpen.TooOpen;
import O.openClosed.tooOpen.transitions.FinishTransition;
import O.openClosed.tooOpen.transitions.GoToTransition;
import O.openClosed.tooOpen.transitions.NextLineTransition;

import java.util.HashMap;
import java.util.Map;

public class OpenClosedDemonstrator {
    public static void main(String[] args) {
        TooOpen tooOpen = new TooOpen();

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

        String result = tooOpen.execute(
                new HashMap<>(),
                stateMap -> pretendNotToCast(stateMap, "index"),
                steps
        );

        System.out.println(result);
    }

    @SuppressWarnings("unchecked") // They will never know!
    private static <T> T pretendNotToCast(Map<String, Object> state, String key) {
        return (T) state.get(key);
    }
}
