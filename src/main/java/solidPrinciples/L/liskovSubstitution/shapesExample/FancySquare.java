package solidPrinciples.L.liskovSubstitution.shapesExample;

public class FancySquare implements FancyShape {
    private Integer sideLength;

    @Override
    public void setHeight(Integer height) {
        this.sideLength = height;
    }

    @Override
    public void setWidth(Integer width) {
        this.sideLength = width;
    }

    @Override
    public Integer calculateArea() {
        return sideLength * sideLength;
    }
}
