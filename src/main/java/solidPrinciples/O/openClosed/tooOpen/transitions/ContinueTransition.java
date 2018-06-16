package solidPrinciples.O.openClosed.tooOpen.transitions;

import solidPrinciples.O.openClosed.tooOpen.Transition;
import solidPrinciples.O.openClosed.tooOpen.TransitionType;

public abstract class ContinueTransition implements Transition {
    @Override
    public TransitionType getTransitionType() {
        return TransitionType.CONTINUE;
    }
}
