public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String languages[] = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n = n * 3;
        n = n + 0b10101;
        n = n + 0xFF;
        n = n * 6;
        int result = 0;
        while (n > 0) {
            result = result + n % 10;
            n = n / 10;
        }
        while (result > 9) {
            int aux = 0;
            while (result > 0) {
                aux = aux + result % 10;
                result = result / 10;
            }
            result = aux;
        }
        System.out.println(result);
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}

