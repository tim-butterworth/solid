package L;

import L.liskovSubstitution.*;

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
