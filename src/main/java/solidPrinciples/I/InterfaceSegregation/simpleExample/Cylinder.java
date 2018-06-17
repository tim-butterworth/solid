package solidPrinciples.I.InterfaceSegregation.simpleExample;

public class Cylinder implements CircleShape, RectangleShape {

    private final Double height;
    private final Double radius;

    public Cylinder(Double height, Double radius) {
        this.height = height;
        this.radius = radius;
    }

    @Override
    public Double getHeight() {
        return height;
    }

    @Override
    public Double getRadius() {
        return radius;
    }

    @Override
    public Double getWidth() {
        return radius * 2;
    }
}
