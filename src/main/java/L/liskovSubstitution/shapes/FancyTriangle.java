package L.liskovSubstitution.shapes;

import L.liskovSubstitution.FancyShape;

public class FancyTriangle implements FancyShape {

    private Integer height;
    private Integer width;

    @Override
    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public Integer calculateArea() {
        return height * width / 2;
    }
}
