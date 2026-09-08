import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        // ========== SETUP DATA ==========
        // Tạo 3 account thủ công
        Account acc1 = new Account();
        acc1.accountId  = 1;
        acc1.email      = "a1@mail.com";
        acc1.username   = "user1";
        acc1.fullName   = "Tran Van Suong";
        acc1.positionId = 1; // 1 = DEV
        acc1.createDate = LocalDate.of(2026, 8, 1);

        Account acc2 = new Account();
        acc2.accountId  = 2;
        acc2.email      = "a2@mail.com";
        acc2.username   = "user2";
        acc2.fullName   = "Tran Thi Buoi";
        acc2.positionId = 2; // 2 = TEST
        acc2.createDate = LocalDate.of(2026, 8, 2);

        Account acc3 = new Account();
        acc3.accountId  = 3;
        acc3.email      = "a3@mail.com";
        acc3.username   = "user3";
        acc3.fullName   = "Le Van Cuong";
        acc3.positionId = 3; // 3 = PM
        acc3.createDate = LocalDate.of(2026, 8, 3);

        // Lưu vào mảng
        Account[] accounts = { acc1, acc2, acc3 };

        // Tạo 3 department
        Department dept1 = new Department();
        dept1.departmentId   = 1;
        dept1.departmentName = "Sales";

        Department dept2 = new Department();
        dept2.departmentId   = 2;
        dept2.departmentName = "Marketing";

        Department dept3 = new Department();
        dept3.departmentId   = 3;
        dept3.departmentName = "IT";

        Department[] departments = { dept1, dept2, dept3 };

        
        acc1.departmentId = 1; 
        acc2.departmentId = 2; 
        acc3.departmentId = 3; 

        int acc2GroupCount = 2;

        // Tạo Exam
        Exam exam1 = new Exam();
        exam1.examId     = 1;
        exam1.code       = "EX001";
        exam1.title      = "Java Test";
        exam1.createDate = LocalDate.of(2026, 4, 1);


        // ============================================================
        // EXERCISE 1: Flow Control
        // ============================================================
        System.out.println("========== EXERCISE 1: Flow Control ==========");

        // --- IF ---

        // Question 1: Kiểm tra account thứ 2
        // Nếu không có phòng ban (departmentId == 0) thì in ra "Nhân viên này chưa có phòng ban"
        // Nếu có thì in ra "Phòng ban của nhân viên này là ..."
        System.out.println("\n--- Q1 (IF) ---");
        if (acc2.departmentId == 0) {
            System.out.println("Nhân viên này chưa có phòng ban");
        } else {
            System.out.println("Phòng ban của nhân viên này là " + dept2.departmentName);
        }

        // Question 2: Kiểm tra account thứ 2
        // Nếu không có group thì in ra "Nhân viên này chưa có group"
        // Nếu có 1 hoặc 2 group thì in ra "Group của nhân viên này là Java Fresher, C# Fresher"
        // Nếu có đúng 3 group thì in ra "Nhân viên này là người quan trọng, tham gia nhiều group"
        // Nếu có từ 4 group trở lên thì in ra "Nhân viên này là người hóng chuyện, tham gia tất cả các group"
        System.out.println("\n--- Q2 (IF) ---");
        if (acc2GroupCount == 0) {
            System.out.println("Nhân viên này chưa có group");
        } else if (acc2GroupCount <= 2) {
            System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
        } else if (acc2GroupCount == 3) {
            System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
        } else {
            System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
        }

        // Question 3: Sử dụng toán tử ternary để làm lại Question 1
        System.out.println("\n--- Q3 (Ternary) ---");
        System.out.println(acc2.departmentId == 0
                ? "Nhân viên này chưa có phòng ban"
                : "Phòng ban của nhân viên này là " + dept2.departmentName);

        // Question 4: Sử dụng toán tử ternary để kiểm tra Position của account thứ 1
        // Nếu Position = DEV thì in ra "Đây là Developer"
        // Nếu không phải thì in ra "Người này không phải là Developer"
        System.out.println("\n--- Q4 (Ternary) ---");
        System.out.println(acc1.positionId == 1
                ? "Đây là Developer"
                : "Người này không phải là Developer");

        // --- SWITCH CASE ---

        // Question 5: Lấy ra số lượng account trong nhóm thứ 1 và in ra theo format:
        // Nếu số lượng = 1 thì in ra "Nhóm có một thành viên"
        // Nếu số lượng = 2 thì in ra "Nhóm có hai thành viên"
        // Nếu số lượng = 3 thì in ra "Nhóm có ba thành viên"
        // Còn lại in ra "Nhóm có nhiều thành viên"
        System.out.println("\n--- Q5 (Switch Case) ---");
        int groupOneCount = 2; // nhóm 1 có 2 người
        switch (groupOneCount) {
            case 1:  System.out.println("Nhóm có một thành viên"); break;
            case 2:  System.out.println("Nhóm có hai thành viên"); break;
            case 3:  System.out.println("Nhóm có ba thành viên");  break;
            default: System.out.println("Nhóm có nhiều thành viên");
        }

        // Question 6: Sử dụng switch case để làm lại Question 2
        System.out.println("\n--- Q6 (Switch Case) ---");
        switch (acc2GroupCount) {
            case 0:
                System.out.println("Nhân viên này chưa có group");
                break;
            case 1:
            case 2:
                System.out.println("Group của nhân viên này là Java Fresher, C# Fresher");
                break;
            case 3:
                System.out.println("Nhân viên này là người quan trọng, tham gia nhiều group");
                break;
            default:
                System.out.println("Nhân viên này là người hóng chuyện, tham gia tất cả các group");
        }

        // Question 7: Sử dụng switch case để làm lại Question 4
        System.out.println("\n--- Q7 (Switch Case) ---");
        switch (acc1.positionId) {
            case 1:
                System.out.println("Đây là Developer");
                break;
            default:
                System.out.println("Người này không phải là Developer");
        }

        // --- FOREACH ---

        // Question 8: In ra thông tin các account bao gồm Email, FullName và tên phòng ban của họ
        System.out.println("\n--- Q8 (Foreach) ---");
        for (Account a : accounts) {
            // tìm tên phòng ban theo departmentId
            String deptName = "";
            for (Department d : departments) {
                if (d.departmentId == a.departmentId) {
                    deptName = d.departmentName;
                    break;
                }
            }
            System.out.println("Email: " + a.email + " | Full name: " + a.fullName + " | Phòng ban: " + deptName);
        }

        // Question 9: In ra thông tin các phòng ban bao gồm id và name
        System.out.println("\n--- Q9 (Foreach) ---");
        for (Department d : departments) {
            System.out.println("Id: " + d.departmentId + " | Name: " + d.departmentName);
        }

        // --- FOR ---

        // Question 10: In ra thông tin các account bao gồm Email, FullName và tên phòng ban
        // theo định dạng:
        //   Thông tin account thứ 1 là:
        //     Email: NguyenVanA@gmail.com
        //     Full name: Nguyễn Văn A
        //     Phòng ban: Sale
        System.out.println("\n--- Q10 (For) ---");
        for (int i = 0; i < accounts.length; i++) {
            String deptName = "";
            for (Department d : departments) {
                if (d.departmentId == accounts[i].departmentId) {
                    deptName = d.departmentName;
                    break;
                }
            }
            System.out.println("Thông tin account thứ " + (i + 1) + " là:");
            System.out.println("  Email: "    + accounts[i].email);
            System.out.println("  Full name: " + accounts[i].fullName);
            System.out.println("  Phòng ban: " + deptName);
        }

        // Question 11: In ra thông tin các phòng ban bao gồm id và name theo định dạng:
        //   Thông tin department thứ 1 là:
        //     Id: 1
        //     Name: Sale
        System.out.println("\n--- Q11 (For) ---");
        for (int i = 0; i < departments.length; i++) {
            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
            System.out.println("  Id: "   + departments[i].departmentId);
            System.out.println("  Name: " + departments[i].departmentName);
        }

        // Question 12: Chỉ in ra thông tin 2 department đầu tiên theo định dạng như Question 11
        System.out.println("\n--- Q12 (For) ---");
        for (int i = 0; i < departments.length; i++) {
            if (i == 2) break;
            System.out.println("Thông tin department thứ " + (i + 1) + " là:");
            System.out.println("  Id: "   + departments[i].departmentId);
            System.out.println("  Name: " + departments[i].departmentName);
        }

        // Question 13: In ra thông tin tất cả các account ngoại trừ account thứ 2
        System.out.println("\n--- Q13 (For) ---");
        for (int i = 0; i < accounts.length; i++) {
            if (i == 1) continue;
            System.out.println("Email: " + accounts[i].email + " | Full name: " + accounts[i].fullName);
        }

        // Question 14: In ra thông tin tất cả các account có id < 4
        System.out.println("\n--- Q14 (For) ---");
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].accountId < 4) {
                System.out.println("Email: " + accounts[i].email + " | Full name: " + accounts[i].fullName);
            }
        }

        // Question 15: In ra các số chẵn nhỏ hơn hoặc bằng 20
        System.out.println("\n--- Q15 (For) ---");
        for (int i = 2; i <= 20; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();

        // --- WHILE ---

        // Question 16: Làm lại các Question ở phần FOR bằng cách sử dụng WHILE kết hợp với lệnh break, continue
        System.out.println("\n--- Q16 (While) ---");

        // lại Q10
        int i = 0;
        while (i < accounts.length) {
            String deptName = "";
            for (Department d : departments) {
                if (d.departmentId == accounts[i].departmentId) {
                    deptName = d.departmentName;
                    break;
                }
            }
            System.out.println("Thông tin account thứ " + (i + 1) + " là:");
            System.out.println("  Email: "    + accounts[i].email);
            System.out.println("  Full name: " + accounts[i].fullName);
            System.out.println("  Phòng ban: " + deptName);
            i++;
        }

        // lại Q11
        int j = 0;
        while (j < departments.length) {
            System.out.println("Thông tin department thứ " + (j + 1) + " là:");
            System.out.println("  Id: "   + departments[j].departmentId);
            System.out.println("  Name: " + departments[j].departmentName);
            j++;
        }

        // lại Q12 - chỉ 2 dept đầu (dùng break)
        int k = 0;
        while (k < departments.length) {
            if (k == 2) break;
            System.out.println("Thông tin department thứ " + (k + 1) + " là:");
            System.out.println("  Id: "   + departments[k].departmentId);
            System.out.println("  Name: " + departments[k].departmentName);
            k++;
        }

        // lại Q13 - bỏ account thứ 2 (dùng continue)
        int m = 0;
        while (m < accounts.length) {
            if (m == 1) { m++; continue; }
            System.out.println("Email: " + accounts[m].email + " | Full name: " + accounts[m].fullName);
            m++;
        }

        // lại Q14 - account có id < 4
        int n = 0;
        while (n < accounts.length) {
            if (accounts[n].accountId < 4) {
                System.out.println("Email: " + accounts[n].email + " | Full name: " + accounts[n].fullName);
            }
            n++;
        }

        // lại Q15 - số chẵn <= 20
        int num = 2;
        while (num <= 20) {
            System.out.print(num + " ");
            num += 2;
        }
        System.out.println();

        // --- DO-WHILE ---

        // Question 17: Làm lại các Question ở phần FOR bằng cách sử dụng DO-WHILE kết hợp với lệnh break, continue
        System.out.println("\n--- Q17 (Do-While) ---");

        // lại Q10
        int a1 = 0;
        do {
            String deptName = "";
            for (Department d : departments) {
                if (d.departmentId == accounts[a1].departmentId) {
                    deptName = d.departmentName;
                    break;
                }
            }
            System.out.println("Thông tin account thứ " + (a1 + 1) + " là:");
            System.out.println("  Email: "    + accounts[a1].email);
            System.out.println("  Full name: " + accounts[a1].fullName);
            System.out.println("  Phòng ban: " + deptName);
            a1++;
        } while (a1 < accounts.length);

        // lại Q11
        int d1 = 0;
        do {
            System.out.println("Thông tin department thứ " + (d1 + 1) + " là:");
            System.out.println("  Id: "   + departments[d1].departmentId);
            System.out.println("  Name: " + departments[d1].departmentName);
            d1++;
        } while (d1 < departments.length);

        // lại Q12 - chỉ 2 dept đầu (dùng break)
        int d2 = 0;
        do {
            if (d2 == 2) break;
            System.out.println("Thông tin department thứ " + (d2 + 1) + " là:");
            System.out.println("  Id: "   + departments[d2].departmentId);
            System.out.println("  Name: " + departments[d2].departmentName);
            d2++;
        } while (d2 < departments.length);

        // lại Q13 - bỏ account thứ 2 (dùng continue)
        int a2 = 0;
        do {
            if (a2 == 1) { a2++; continue; }
            System.out.println("Email: " + accounts[a2].email + " | Full name: " + accounts[a2].fullName);
            a2++;
        } while (a2 < accounts.length);

        // lại Q14 - account có id < 4
        int a3 = 0;
        do {
            if (accounts[a3].accountId < 4) {
                System.out.println("Email: " + accounts[a3].email + " | Full name: " + accounts[a3].fullName);
            }
            a3++;
        } while (a3 < accounts.length);

        // lại Q15 - số chẵn <= 20
        int num2 = 2;
        do {
            System.out.print(num2 + " ");
            num2 += 2;
        } while (num2 <= 20);
        System.out.println();


        // ============================================================
        // EXERCISE 2: System.out.printf
        // ============================================================
        System.out.println("\n========== EXERCISE 2: System.out.printf ==========");

        // Question 1: Khai báo 1 số nguyên = 5 và sử dụng System.out.printf để in ra số nguyên đó
        System.out.println("\n--- Q1 ---");
        int soNguyen = 5;
        System.out.printf("Số nguyên: %d%n", soNguyen);

        // Question 2: Khai báo 1 số nguyên = 100.000.000 và sử dụng System.out.printf để in ra theo định dạng: 100,000,000
        System.out.println("\n--- Q2 ---");
        int soLon = 100_000_000;
        System.out.printf("Số nguyên: %,d%n", soLon);

        // Question 3: Khai báo 1 số thực = 5,567098 và sử dụng System.out.printf để in ra chỉ bao gồm 4 chữ số sau dấu phẩy
        System.out.println("\n--- Q3 ---");
        double soThuc = 5.567098;
        System.out.printf("Số thực: %.4f%n", soThuc);

        // Question 4: Khai báo họ và tên của 1 học sinh và in ra theo định dạng:
        // Tên tôi là "Nguyễn Văn A" và tôi đang độc thân.
        System.out.println("\n--- Q4 ---");
        String hoTen = "Nguyễn Văn A";
        System.out.printf("Tên tôi là \"%s\" và tôi đang độc thân.%n", hoTen);

        // Question 5: Lấy thời gian hiện tại và in ra theo định dạng: 24/04/2020 11h:16p:20s
        System.out.println("\n--- Q5 ---");
        LocalDateTime now = LocalDateTime.now();
        System.out.printf("%02d/%02d/%04d %02dh:%02dp:%02ds%n",
                now.getDayOfMonth(), now.getMonthValue(), now.getYear(),
                now.getHour(), now.getMinute(), now.getSecond());

        // Question 6: In ra thông tin account (như Question 8 phần FOREACH) theo định dạng table (giống trong Database)
        System.out.println("\n--- Q6 ---");
        System.out.printf("%-5s %-20s %-25s %-15s%n", "ID", "Full Name", "Email", "Department");
        System.out.println("-".repeat(70));
        for (Account a : accounts) {
            String deptName = "";
            for (Department d : departments) {
                if (d.departmentId == a.departmentId) {
                    deptName = d.departmentName;
                    break;
                }
            }
            System.out.printf("%-5d %-20s %-25s %-15s%n",
                    a.accountId, a.fullName, a.email, deptName);
        }


        // ============================================================
        // EXERCISE 3: Date Format
        // ============================================================
        System.out.println("\n========== EXERCISE 3: Date Format ==========");

        // Question 1: In ra thông tin Exam thứ 1, property createDate được format theo định dạng tiếng Việt
        System.out.println("\n--- Q1 ---");
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd 'tháng' MM 'năm' yyyy");
        System.out.println("Exam: " + exam1.code + " - " + exam1.title);
        System.out.println("Ngày tạo: " + exam1.createDate.format(fmt1));

        // Question 2: In ra thông tin Exam đã tạo ngày nào theo định dạng: Năm – tháng – ngày – giờ – phút – giây
        System.out.println("\n--- Q2 ---");
        LocalDateTime examDateTime = exam1.createDate.atStartOfDay();
        DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("yyyy - MM - dd - HH - mm - ss");
        System.out.println("Ngày tạo: " + examDateTime.format(fmt2));

        // Question 3: Chỉ in ra năm của createDate trong Question 2
        System.out.println("\n--- Q3 ---");
        System.out.println("Năm: " + exam1.createDate.getYear());

        // Question 4: Chỉ in ra tháng và năm của createDate trong Question 2
        System.out.println("\n--- Q4 ---");
        DateTimeFormatter fmt4 = DateTimeFormatter.ofPattern("MM/yyyy");
        System.out.println("Tháng/Năm: " + exam1.createDate.format(fmt4));

        // Question 5: Chỉ in ra "MM-DD" của createDate trong Question 2
        System.out.println("\n--- Q5 ---");
        DateTimeFormatter fmt5 = DateTimeFormatter.ofPattern("MM-dd");
        System.out.println("MM-DD: " + exam1.createDate.format(fmt5));


        // ============================================================
        // EXERCISE 4: Random Number
        // ============================================================
        System.out.println("\n========== EXERCISE 4: Random Number ==========");
        Random rand = new Random();

        // Question 1: In ngẫu nhiên ra 1 số nguyên
        System.out.println("\n--- Q1 ---");
        System.out.println("Số nguyên ngẫu nhiên: " + rand.nextInt());

        // Question 2: In ngẫu nhiên ra 1 số thực
        System.out.println("\n--- Q2 ---");
        System.out.println("Số thực ngẫu nhiên: " + rand.nextDouble());

        // Question 3: Khai báo 1 array bao gồm các tên của các bạn trong lớp, sau đó in ngẫu nhiên ra tên của 1 bạn
        System.out.println("\n--- Q3 ---");
        String[] tenLop = { "An", "Binh", "Cuong", "Dung", "Phong", "Giang", "Huy" };
        int viTri = rand.nextInt(tenLop.length);
        System.out.println("Tên ngẫu nhiên: " + tenLop[viTri]);

        // Question 4: Lấy ngẫu nhiên 1 ngày trong khoảng thời gian từ 24-07-1995 đến 20-12-1995
        System.out.println("\n--- Q4 ---");
        LocalDate ngayBatDau = LocalDate.of(1995, 7, 24);
        // khoảng cách = 149 ngày
        int soNgay4 = rand.nextInt(150);
        LocalDate ngayNgauNhien4 = ngayBatDau.plusDays(soNgay4);
        System.out.println("Ngày ngẫu nhiên: " + ngayNgauNhien4);

        // Question 5: Lấy ngẫu nhiên 1 ngày trong khoảng thời gian 1 năm trở lại đây
        System.out.println("\n--- Q5 ---");
        LocalDate homNay = LocalDate.now();
        LocalDate motNamTruoc = homNay.minusYears(1);
        int soNgay5 = rand.nextInt(366);
        LocalDate ngayNgauNhien5 = motNamTruoc.plusDays(soNgay5);
        System.out.println("Ngày ngẫu nhiên (1 năm trở lại): " + ngayNgauNhien5);

        // Question 6: Lấy ngẫu nhiên 1 ngày trong quá khứ
        System.out.println("\n--- Q6 ---");
        int soNgay6 = rand.nextInt(36500); // khoảng 100 năm
        LocalDate ngayNgauNhien6 = homNay.minusDays(soNgay6);
        System.out.println("Ngày ngẫu nhiên trong quá khứ: " + ngayNgauNhien6);

        // Question 7: Lấy ngẫu nhiên 1 số có 3 chữ số
        System.out.println("\n--- Q7 ---");
        int so3ChuSo = 100 + rand.nextInt(900);
        System.out.println("Số có 3 chữ số: " + so3ChuSo);


        // ============================================================
        // EXERCISE 5: Input from console
        // ============================================================
        System.out.println("\n========== EXERCISE 5: Input from console ==========");
        Scanner sc = new Scanner(System.in);

        // Question 1: Viết lệnh cho phép người dùng nhập 3 số nguyên vào chương trình
        System.out.println("\n--- Q1 ---");
        System.out.print("Nhập số nguyên 1: "); int so1 = sc.nextInt();
        System.out.print("Nhập số nguyên 2: "); int so2 = sc.nextInt();
        System.out.print("Nhập số nguyên 3: "); int so3 = sc.nextInt();
        System.out.println("Bạn đã nhập: " + so1 + ", " + so2 + ", " + so3);

        // Question 2: Viết lệnh cho phép người dùng nhập 2 số thực vào chương trình
        System.out.println("\n--- Q2 ---");
        System.out.print("Nhập số thực 1: "); double thuc1 = sc.nextDouble();
        System.out.print("Nhập số thực 2: "); double thuc2 = sc.nextDouble();
        System.out.println("Bạn đã nhập: " + thuc1 + ", " + thuc2);

        // Question 3: Viết lệnh cho phép người dùng nhập họ và tên
        System.out.println("\n--- Q3 ---");
        sc.nextLine();
        System.out.print("Nhập họ và tên: ");
        String hoTenNhap = sc.nextLine();
        System.out.println("Họ và tên: " + hoTenNhap);

        // Question 4: Viết lệnh cho phép người dùng nhập vào ngày sinh nhật của họ
        System.out.println("\n--- Q4 ---");
        System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
        String ngaySinhStr = sc.nextLine();
        LocalDate ngaySinh = LocalDate.parse(ngaySinhStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Ngày sinh: " + ngaySinh);

        // Question 5: Viết lệnh cho phép người dùng tạo account (viết thành method)
        // Đối với property Position, người dùng nhập 1 2 3 4 và chương trình sẽ chuyển thành DEV, TEST, SCRUM_MASTER, PM
        System.out.println("\n--- Q5 ---");
        Account accMoi = new Account();
        System.out.print("Nhập email: ");       accMoi.email    = sc.nextLine();
        System.out.print("Nhập username: ");    accMoi.username = sc.nextLine();
        System.out.print("Nhập fullName: ");    accMoi.fullName = sc.nextLine();
        System.out.print("Nhập positionId (1=DEV, 2=TEST, 3=PM, 4=SCRUM_MASTER): ");
        accMoi.positionId = Integer.parseInt(sc.nextLine().trim());
        accMoi.createDate = LocalDate.now();
        System.out.println("Đã tạo: " + accMoi.username + " - " + accMoi.fullName);

        // Question 6: Viết lệnh cho phép người dùng tạo department (viết thành method)
        System.out.println("\n--- Q6 ---");
        Department deptMoi = new Department();
        System.out.print("Nhập departmentId: ");   deptMoi.departmentId   = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Nhập departmentName: "); deptMoi.departmentName = sc.nextLine();
        System.out.println("Đã tạo: " + deptMoi.departmentId + " - " + deptMoi.departmentName);

        // Question 7: Nhập số chẵn từ console
        System.out.println("\n--- Q7 ---");
        int soNhap;
        do {
            System.out.print("Nhập vào một số chẵn: ");
            soNhap = sc.nextInt();
            if (soNhap % 2 != 0) {
                System.out.println("Đây không phải số chẵn, vui lòng nhập lại.");
            }
        } while (soNhap % 2 != 0);
        System.out.println("Số chẵn bạn nhập: " + soNhap);

        // Question 8: Chương trình in ra "Mời bạn nhập vào chức năng muốn sử dụng"
        // Nhập 1 → tạo account | Nhập 2 → tạo department | Nhập khác → in "Mời bạn nhập lại" và quay lại
        // Question 9: Viết method cho phép người dùng thêm group vào account
        // Question 10: Bổ sung vào Question 8: nhập 3 → thêm group vào account; sau khi xong hỏi có muốn tiếp tục không
        // Question 11: Bổ sung vào Question 10: nhập 4 → thêm account vào 1 nhóm ngẫu nhiên
        System.out.println("\n--- Q8/Q9/Q10/Q11 ---");
        sc.nextLine();
        while (true) {
            System.out.println("Mời bạn nhập vào chức năng muốn sử dụng:");
            System.out.println("  1. Tạo account");
            System.out.println("  2. Tạo department");
            System.out.print("Lựa chọn: ");
            String luaChon = sc.nextLine().trim();

            if (luaChon.equals("1")) {
                Account a = new Account();
                System.out.print("Email: ");    a.email    = sc.nextLine();
                System.out.print("Username: "); a.username = sc.nextLine();
                System.out.print("FullName: "); a.fullName = sc.nextLine();
                a.createDate = LocalDate.now();
                System.out.println("Đã tạo account: " + a.username);
            } else if (luaChon.equals("2")) {
                Department d = new Department();
                System.out.print("DepartmentId: ");   d.departmentId   = Integer.parseInt(sc.nextLine().trim());
                System.out.print("DepartmentName: "); d.departmentName = sc.nextLine();
                System.out.println("Đã tạo department: " + d.departmentName);
            } else {
                System.out.println("Mời bạn nhập lại");
                continue;
            }

            System.out.print("Bạn có muốn thực hiện chức năng khác không? (co/khong): ");
            String tiepTuc = sc.nextLine().trim();
            if (tiepTuc.equals("khong")) {
                System.out.println("Kết thúc chương trình.");
                return;
            }
        }
    }


    // ============================================================
    // EXERCISE 6: Method
    // ============================================================

    // Question 1: Tạo method để in ra các số chẵn nguyên dương nhỏ hơn 10
    static void inSoChan() {
        for (int i = 2; i < 10; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Question 2: Tạo method để in thông tin các account
    static void inThongTinAccount(Account[] accounts) {
        for (Account a : accounts) {
            System.out.println("ID: " + a.accountId + " | Username: " + a.username + " | Email: " + a.email);
        }
    }

    // Question 3: Tạo method để in ra các số nguyên dương nhỏ hơn 10
    static void inSoNguyenDuong() {
        for (int i = 1; i < 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
