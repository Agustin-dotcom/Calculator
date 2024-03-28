package TaylorMethod;

public class Taylor {
    public static double factorial(int n)
    {//Recursion :D
        if(n!=1)
            return n*factorial(n-1);
        else
            return 1;
    }
    public static double calculateTaylor(int numberIterations,double x)
    {
        double summation=0.0;
        for(int i=0;i<numberIterations;++i)
        {
            summation+=(Math.pow(x,2*i+1))*(Math.pow(-1,i)/factorial(2*i+1));
        }
        return summation;
    }
    //Math.toRadian();
    public static void main(String[] args){
        System.out.println("Taylor method:"+calculateTaylor(17  ,Math.toRadians(25))+"\nSin(25):"+Math.sin(Math.toRadians(25)));
        //double v=BabylonianMethod.calculateBaby(2.5,3,9);
        //System.out.println("Babylonian method:"+v);
        System.out.println(Math.pow(2,1));
    }
}
