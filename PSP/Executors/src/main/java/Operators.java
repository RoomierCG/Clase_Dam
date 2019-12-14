public class Operators {

    AplicationCalculation calculation;

    public int square(int number){
        Math.pow(number,2);
        return number;
    }
    public int cube(int number){
        Math.pow(number,3);
        return number;
    }
    public int squareRoot(int number){
        Math.sqrt(number);
        return number;
    }

    public int factorial (int number){
        int initialNumber = 1;

        for (int i = number; i > 0 ; i--) {
            initialNumber *= i;
        }
        return initialNumber;
    }
}
