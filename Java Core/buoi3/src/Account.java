import java.time.LocalDate;

public class Account {
    int id;
    String email;
    String username;
    String fullName;
    public Department department; // Tối ưu: Sử dụng đối tượng thay vì ID
    public Position position;     // Tối ưu: Sử dụng đối tượng thay vì ID
    LocalDate createDate;

    Group[] groups;// ds casc group ma account nay tham gia
}