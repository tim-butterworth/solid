package solidPrinciples.I.InterfaceSegregation.externalExamples.wrappers;

import solidPrinciples.I.InterfaceSegregation.externalClassWeCanNotChange.TooBigClass;
import solidPrinciples.I.InterfaceSegregation.externalExamples.Commands;

public class CommandWrapper implements Commands {

    private final TooBigClass tooBigClass;

    public CommandWrapper(TooBigClass tooBigClass) {
        this.tooBigClass = tooBigClass;
    }

    @Override
    public void command1() {
        tooBigClass.command1();
    }

    @Override
    public void command2() {
        tooBigClass.command2();
    }

    @Override
    public void command3() {
        tooBigClass.command3();
    }
}
