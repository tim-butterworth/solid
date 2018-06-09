import D.dependencyInversion.NotInverted;

public class Demonstrator {

    public static void main(String[] args) {
        NotInverted notInverted = new NotInverted();
        System.out.println(notInverted.doStuff());
    }
}
