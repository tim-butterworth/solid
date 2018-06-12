package L.liskovSubstitution;

public class FancyDirectLiskovViolationShape implements FancyShape {

    private Integer width;

    @Override
    public void setHeight(Integer height) {
        throw new RuntimeException("shouldn't have called this method...");
    }

    @Override
    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public Integer calculateArea() {
        return 8 * width;
    }
}
