public class Department {
    int departmentId;
    String departmentName;

    // Constructor mặc định
    public Department() {
    }

    // Constructor có tham số
    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    // Override toString để in thông tin đẹp
    @Override
    public String toString() {
        return "Department{id=" + departmentId + ", name='" + departmentName + "'}";
    }

    // Override equals để so sánh theo tên
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Department that = (Department) obj;
        return this.departmentName != null && this.departmentName.equals(that.departmentName);
    }

    // Override hashCode để hỗ trợ equals
    @Override
    public int hashCode() {
        return departmentName != null ? departmentName.hashCode() : 0;
    }
}