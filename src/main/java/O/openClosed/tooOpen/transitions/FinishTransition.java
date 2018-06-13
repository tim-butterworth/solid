package O.openClosed.tooOpen.transitions;

import O.openClosed.tooOpen.Transition;
import O.openClosed.tooOpen.TransitionType;

import java.util.Map;

public class FinishTransition implements Transition {

    private Map<String, Object> state;

    public FinishTransition(Map<String, Object> state) {
        this.state = state;
    }

    @Override
    public Map<String, Object> getState() {
        return state;
    }

    @Override
    public Integer nextIndex(Integer currentIndex) {
        return null;
    }

    @Override
    public TransitionType getTransitionType() {
        return TransitionType.FINISHED;
    }
}
