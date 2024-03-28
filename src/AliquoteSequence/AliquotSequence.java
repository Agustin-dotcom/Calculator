package AliquoteSequence;



public class AliquotSequence {

    int n; // the starting number

    public AliquotSequence(int n) {
        this.n = n;
    }

    public String  calculate() {
        StringBuilder sb = new StringBuilder();
        sb.append(n).append(" ");
        int count = 1;
        while (n != 1 && count <= 5) {
            int sum = 0;
            for (int i = 1; i <= n/2; i++) {
                if (n % i == 0) {
                    sum += i; // add the divisor to the sum
                }
            }
            n = sum; // set the new number to the sum of the divisors
            sb.append(n).append(" ");
            count++;
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        AliquotSequence aliquotSequence = new AliquotSequence(10);
        System.out.println(aliquotSequence.calculate());
    }
}

