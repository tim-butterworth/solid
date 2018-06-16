package solidPrinciples.L.liskovSubstitution.shapesExample;

public interface FancyShape {
    void setHeight(Integer height);
    void setWidth(Integer width);
    Integer calculateArea();
}
