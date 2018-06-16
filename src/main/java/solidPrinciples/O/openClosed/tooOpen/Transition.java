package solidPrinciples.O.openClosed.tooOpen;

import java.util.Map;

public interface Transition {
    Map<String, Object> getState();
    Integer nextIndex(Integer currentIndex);
    TransitionType getTransitionType();
}
