public class Program {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2};
        int[] brr = new int[3];
        brr = arr.clone();
        System.out.println(brr.length);
    }
}
