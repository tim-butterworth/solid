package L;

import L.liskovSubstitution.LiskovViolation;
import L.liskovSubstitution.shapes.FancyDirectLiskovViolationShape;
import L.liskovSubstitution.shapes.FancyRectangle;
import L.liskovSubstitution.shapes.FancySquare;
import L.liskovSubstitution.shapes.FancyTriangle;

public class LiskovDemonstrator {
    public static void main(String[] args) {
        LiskovViolation liskovViolation = new LiskovViolation();

        System.out.println(liskovViolation.getTheArea(new FancyRectangle()));
        System.out.println(liskovViolation.getTheArea(new FancyTriangle()));
        System.out.println(liskovViolation.getTheArea(new FancyDirectLiskovViolationShape()));

        // subtle liskov violation
        System.out.println(liskovViolation.getTheArea(new FancySquare()));
    }
}
