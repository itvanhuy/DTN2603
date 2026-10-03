package frontend;

import backend.controller.ManufacturerController;
import backend.controller.ProductController;
import common.EmailValidator;
import entity.Category;
import entity.Manufacturer;
import entity.Product;

import java.util.Scanner;

public class Function {
    private final Scanner scanner = new Scanner(System.in);
    private final ProductController productController = new ProductController();
    private final ManufacturerController manufacturerController = new ManufacturerController();

    public void menu() {
        while (true) {
            System.out.println("\n=== QUẢN LÝ SẢN PHẨM ===");
            System.out.println("1. Tìm nhà sản xuất theo ID");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Xóa sản phẩm theo ID");
            System.out.println("4. Cập nhật tên sản phẩm");
            System.out.println("5. Thêm sản phẩm");
            System.out.println("6. Kiểm tra email");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            switch (scanner.nextLine().trim()) {
                case "1":
                    findManufacturer();
                    break;
                case "2":
                    productController.displayProducts();
                    break;
                case "3":
                    deleteProduct();
                    break;
                case "4":
                    updateProductName();
                    break;
                case "5":
                    addProduct();
                    break;
                case "6":
                    checkEmail();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    public static boolean isValidEmail(String email) {
        return EmailValidator.isValid(email);
    }

    private void findManufacturer() {
        int id = readPositiveId("Nhập ID nhà sản xuất: ");
        Manufacturer manufacturer = manufacturerController.getManufacturerById(id);
        if (manufacturer == null) {
            System.out.println("Không tìm thấy nhà sản xuất.");
        } else {
            System.out.println("ID: " + manufacturer.getId() + " | Tên: " + manufacturer.getName());
        }
    }

    private void deleteProduct() {
        int id = readPositiveId("Nhập ID sản phẩm cần xóa: ");
        if (productController.deleteProduct(id)) {
            System.out.println("Xóa sản phẩm thành công.");
        } else {
            System.out.println("Không thể xóa sản phẩm (ID không tồn tại hoặc lỗi cơ sở dữ liệu).");
        }
    }

    private void updateProductName() {
        int id = readPositiveId("Nhập ID sản phẩm cần cập nhật: ");
        String name = readRequiredText("Tên sản phẩm mới: ");
        if (productController.updateProductName(id, name)) {
            System.out.println("Cập nhật tên sản phẩm thành công.");
        } else {
            System.out.println("Không thể cập nhật (ID không tồn tại, tên bị trùng hoặc không hợp lệ).");
        }
    }

    private void addProduct() {
        Product product = new Product();
        product.setName(readRequiredText("Tên sản phẩm: "));
        product.setPrice(readRequiredText("Giá: "));
        product.setInfo(readRequiredText("Thông tin ngắn: "));
        product.setDetail(readOptionalText("Chi tiết sản phẩm (Enter để bỏ qua): "));
        product.setRatingStar(readRating());
        product.setImageName(readRequiredText("Tên file ảnh: "));
        product.setManufacturer(new Manufacturer(readPositiveId("ID nhà sản xuất: "), null));
        product.setCategory(new Category(readPositiveId("ID danh mục: "), null));

        if (productController.addProduct(product)) {
            System.out.println("Thêm sản phẩm thành công.");
        } else {
            System.out.println("Không thể thêm: tên có thể bị trùng, dữ liệu không hợp lệ, hoặc ID nhà sản xuất/danh mục không tồn tại.");
        }
    }

    private void checkEmail() {
        System.out.print("Nhập email: ");
        String email = scanner.nextLine().trim();
        System.out.println(isValidEmail(email) ? "Email hợp lệ." : "Email không hợp lệ.");
    }

    private int readPositiveId(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int id = Integer.parseInt(scanner.nextLine().trim());
                if (id > 0) {
                    return id;
                }
            } catch (NumberFormatException ignored) {
                // Ask again until a positive numeric ID is entered.
            }
            System.out.println("ID phải là số nguyên dương.");
        }
    }

    private String readRequiredText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Thông tin không được để trống.");
        }
    }

    private String readOptionalText(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine().trim();
        return value.isEmpty() ? null : value;
    }

    private Integer readRating() {
        while (true) {
            System.out.print("Số sao đánh giá (0-5, Enter để bỏ qua): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                int rating = Integer.parseInt(input);
                if (rating >= 0 && rating <= 5) {
                    return rating;
                }
            } catch (NumberFormatException ignored) {
                // Ask again until a valid rating is entered.
            }
            System.out.println("Số sao phải nằm trong khoảng 0 đến 5.");
        }
    }
}
