package solidPrinciples.O.openClosed.tooOpen;

import java.util.Map;

public interface Step {
    Transition doStep(Map<String, Object> state);
}
