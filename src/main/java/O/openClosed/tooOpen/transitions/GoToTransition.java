package O.openClosed.tooOpen.transitions;

import java.util.Map;

public class GoToTransition extends ContinueTransition {

    private final Map<String, Object> state;
    private Integer goToIndex;

    public GoToTransition(Map<String, Object> state, Integer goToIndex) {
        this.state = state;
        this.goToIndex = goToIndex;
    }

    @Override
    public Map<String, Object> getState() {
        return state;
    }

    @Override
    public Integer nextIndex(Integer currentIndex) {
        return goToIndex;
    }

}
