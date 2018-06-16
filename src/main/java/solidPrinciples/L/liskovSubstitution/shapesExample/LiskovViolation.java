package solidPrinciples.L.liskovSubstitution.shapesExample;

public class LiskovViolation {
    public Integer getTheArea(FancyShape shape) {
        shape.setHeight(10);
        shape.setWidth(7);

        return shape.calculateArea();
    }
}
