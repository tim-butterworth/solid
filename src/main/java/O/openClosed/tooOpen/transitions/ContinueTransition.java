package O.openClosed.tooOpen.transitions;

import O.openClosed.tooOpen.Transition;
import O.openClosed.tooOpen.TransitionType;

public abstract class ContinueTransition implements Transition {
    @Override
    public TransitionType getTransitionType() {
        return TransitionType.CONTINUE;
    }
}
