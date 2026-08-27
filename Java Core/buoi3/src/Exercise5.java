import java.util.Arrays;
import java.util.Comparator;

public class Exercise5 {

    // Question 1: In thông tin phòng ban thứ 1 (sử dụng toString)
    public static void question1() {
        Department dept1 = new Department(1, "Phòng A");
        System.out.println("Exercise 5 - Q1: " + dept1.toString());
        System.out.println();
    }

    // Question 2: In thông tin tất cả phòng ban (sử dụng toString)
    public static void question2() {
        Department[] departments = {
                new Department(1, "Phòng A"),
                new Department(2, "Phòng B"),
                new Department(3, "Phòng C")
        };
        System.out.println("Exercise 5 - Q2: Danh sách phòng ban:");
        for (Department d : departments) {
            System.out.println(d.toString());
        }
        System.out.println();
    }

    // Question 3: In ra địa chỉ của phòng ban thứ 1
    public static void question3() {
        Department dept1 = new Department(1, "Phòng A");
        System.out.println("Exercise 5 - Q3: Địa chỉ (hashCode): " + dept1.hashCode());
        // Hoặc dùng System.identityHashCode(dept1) để lấy địa chỉ thực
        System.out.println("    IdentityHashCode: " + System.identityHashCode(dept1));
        System.out.println();
    }

    // Question 4: Kiểm tra phòng ban thứ 1 có tên "Phòng A" không?
    public static void question4() {
        Department dept1 = new Department(1, "Phòng A");
        boolean isPhongA = "Phòng A".equals(dept1.departmentName);
        System.out.println("Exercise 5 - Q4: Phòng ban 1 có tên 'Phòng A'? " + isPhongA);
        System.out.println();
    }

    // Question 5: So sánh 2 phòng ban (bằng nhau nếu tên bằng nhau)
    public static void question5() {
        Department dept1 = new Department(1, "Phòng A");
        Department dept2 = new Department(2, "Phòng A"); // cùng tên
        Department dept3 = new Department(3, "Phòng B");

        System.out.println("Exercise 5 - Q5:");
        System.out.println("dept1 và dept2 bằng nhau? " + dept1.equals(dept2)); // true
        System.out.println("dept1 và dept3 bằng nhau? " + dept1.equals(dept3)); // false
        System.out.println();
    }

    // Question 6: Khởi tạo 5 phòng ban, sắp xếp tăng dần theo tên
    public static void question6() {
        Department[] deptArray = {
                new Department(1, "Accounting"),
                new Department(2, "Boss of director"),
                new Department(3, "Marketing"),
                new Department(4, "Sale"),
                new Department(5, "Waiting room")
        };

        // Sắp xếp theo tên (không phân biệt hoa thường)
        Arrays.sort(deptArray, Comparator.comparing(d -> d.departmentName.toLowerCase()));

        System.out.println("Exercise 5 - Q6: Danh sách sắp xếp tăng dần theo tên:");
        for (Department d : deptArray) {
            System.out.println(d.departmentName);
        }
        System.out.println();
    }

    // Question 7: Khởi tạo 5 phòng ban (học sinh), sắp xếp theo tên
    public static void question7() {
        // Đề có vẻ giống Q6, nhưng có thể thay đổi thứ tự ban đầu
        Department[] deptArray = {
                new Department(1, "Accounting"),
                new Department(2, "Boss of director"),
                new Department(3, "Marketing"),
                new Department(4, "waiting room"),  // viết thường
                new Department(5, "Sale")
        };

        Arrays.sort(deptArray, Comparator.comparing(d -> d.departmentName.toLowerCase()));

        System.out.println("Exercise 5 - Q7: Danh sách sắp xếp tăng dần theo tên:");
        for (Department d : deptArray) {
            System.out.println(d.departmentName);
        }
        System.out.println();
    }
}