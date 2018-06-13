package O.openClosed.tooOpen.transitions;

import java.util.Map;

public class NextLineTransition extends ContinueTransition {

    private final Map<String, Object> state;

    public NextLineTransition(Map<String, Object> state) {
        this.state = state;
    }

    @Override
    public Map<String, Object> getState() {
        return state;
    }

    @Override
    public Integer nextIndex(Integer currentIndex) {
        return currentIndex + 1;
    }
}
