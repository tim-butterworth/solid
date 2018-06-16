package solidPrinciples.O.openClosed.tooOpen;

import java.util.Map;
import java.util.function.Function;

public class TooOpen {
    public <T> T execute(
            Map<String, Object> initialState,
            Function<Map<String, Object>, T> returnFunction,
            Step... steps
    ) {
        Map<String, Object> state = initialState;

        int stepIndex = 0;
        boolean keepGoing = true;

        while (keepGoing) {
            Transition transition = steps[stepIndex].doStep(state);

            state = transition.getState();

            if (transition.getTransitionType() == TransitionType.CONTINUE) {
                stepIndex = transition.nextIndex(stepIndex);
            } else {
                keepGoing = false;
            }
        }

        return returnFunction.apply(state);
    }
}
