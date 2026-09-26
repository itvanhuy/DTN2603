package backend.service.impl;

import backend.service.IAccountService;
import backend.repository.impl.AccountRepository;
import common.StringCommon;
import entity.Account;
import entity.Department;
import entity.Position;
import entity.PositionName;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AccountService implements IAccountService {
    private static final int MIN_LENGTH = 6;
    private static final int MAX_LENGTH = 100;
    private final AccountRepository accountRepository;

    public AccountService() {
        this.accountRepository = new AccountRepository();
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.getAll();
    }

    @Override
    public Account getAccountById(int id) {
        if (id <= 0) {
            return null;
        }
        return accountRepository.getById(id);
    }

    @Override
    public boolean addAccount(Account account) {
        if (!isValid(account)) {
            return false;
        }
        if (isDuplicateUsername(account.getUsername(), account.getId())) {
            return false;
        }
        if (isDuplicateEmail(account.getEmail(), account.getId())) {
            return false;
        }
        if (!departmentExists(account.getDepartment().getId())) {
            return false;
        }
        if (!positionExists(account.getPosition().getId())) {
            return false;
        }
        return accountRepository.add(account);
    }

    @Override
    public boolean updateAccount(Account account) {
        if (account == null || account.getId() <= 0 || !isValid(account)) {
            return false;
        }
        if (isDuplicateUsername(account.getUsername(), account.getId())) {
            return false;
        }
        if (isDuplicateEmail(account.getEmail(), account.getId())) {
            return false;
        }
        if (!departmentExists(account.getDepartment().getId())) {
            return false;
        }
        if (!positionExists(account.getPosition().getId())) {
            return false;
        }
        if (accountRepository.getById(account.getId()) == null) {
            return false;
        }
        return accountRepository.update(account);
    }

    @Override
    public boolean deleteAccount(int id) {
        if (id <= 0 || accountRepository.getById(id) == null) {
            return false;
        }
        return accountRepository.delete(id);
    }

    @Override
    public String importCSV(String url) {
        if (url == null || url.trim().isEmpty()) {
            return "Đường dẫn file không hợp lệ.";
        }

        Path inputPath = Paths.get(url.trim());
        if (!url.toLowerCase(Locale.ROOT).endsWith(".csv")) {
            return "File không đúng định dạng!";
        }
        if (!Files.exists(inputPath) || !Files.isRegularFile(inputPath)) {
            return "File không tồn tại!";
        }

        List<Account> accounts = new ArrayList<>();
        List<String> listErrors = new ArrayList<>();
        String header;
        Map<String, Integer> headerIndexes;
        Map<String, String> seenSignatures = new HashMap<>();
        int totalRows = 0;
        int validCount = 0;
        int errorCount = 0;

        try (BufferedReader reader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8)) {
            header = reader.readLine();
            if (header == null || header.trim().isEmpty()) {
                return "File CSV không có header!";
            }

            headerIndexes = mapHeaderIndexes(parseCsvLine(header));
            if (!hasRequiredHeaders(headerIndexes)) {
                return "File CSV thiếu các cột bắt buộc: email, username, fullname, department, position.";
            }

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                totalRows++;
                String message = validationAccount(line, accounts, headerIndexes, seenSignatures);
                if (message != null) {
                    listErrors.add(message);
                    errorCount++;
                }
            }
        } catch (IOException e) {
            return "Lỗi khi đọc file CSV: " + e.getMessage();
        }

        for (Account account : accounts) {
            if (!addAccount(account)) {
                listErrors.add(buildErrorRowFromAccount(account, "Username/email trùng hoặc department/position không tồn tại"));
                errorCount++;
            } else {
                validCount++;
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Tổng số dòng: ").append(totalRows)
                .append(" | Thành công: ").append(validCount)
                .append(" | Lỗi: ").append(errorCount);

        if (!listErrors.isEmpty()) {
            Path errorPath = writeErrorFile(inputPath, listErrors, header);
            result.append("\nĐã xuất file lỗi: ").append(errorPath);
        } else {
            result.append("\nImport thành công!");
        }
        return result.toString();
    }

    public String validationAccount(String line, List<Account> accounts, Map<String, Integer> headerIndexes, Map<String, String> seenSignatures) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        List<String> values = parseCsvLine(line);
        if (values.size() <= maxHeaderIndex(headerIndexes)) {
            return line + ",Dữ liệu không đủ cột";
        }

        Account account = buildAccount(values, headerIndexes);
        if (account == null) {
            return line + ",Dữ liệu không hợp lệ";
        }

        String signature = (account.getEmail() + "|" + account.getUsername()).toLowerCase(Locale.ROOT);
        if (seenSignatures.containsKey(signature)) {
            return line + ",Dữ liệu trùng trong file";
        }

        if (!isValid(account)) {
            return line + ",Dữ liệu không hợp lệ theo validate Account";
        }

        if (isDuplicateUsername(account.getUsername(), account.getId()) || isDuplicateEmail(account.getEmail(), account.getId())) {
            return line + ",Username/email trùng";
        }

        if (!departmentExists(account.getDepartment().getId()) || !positionExists(account.getPosition().getId())) {
            return line + ",Department/Position không tồn tại";
        }

        for (Account existing : accounts) {
            if (existing.getUsername().equalsIgnoreCase(account.getUsername())) {
                return line + ",Username trùng trong file";
            }
            if (existing.getEmail().equalsIgnoreCase(account.getEmail())) {
                return line + ",Email trùng trong file";
            }
        }

        seenSignatures.put(signature, signature);
        accounts.add(account);
        return null;
    }

    private boolean hasRequiredHeaders(Map<String, Integer> headerIndexes) {
        return headerIndexes.containsKey("email")
                && headerIndexes.containsKey("username")
                && headerIndexes.containsKey("fullname")
                && headerIndexes.containsKey("department")
                && headerIndexes.containsKey("position");
    }

    private int maxHeaderIndex(Map<String, Integer> headerIndexes) {
        int max = -1;
        for (Integer index : headerIndexes.values()) {
            if (index != null && index > max) {
                max = index;
            }
        }
        return max;
    }

    private List<String> parseCsvLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (ch == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (ch == ',' && !inQuotes) {
                values.add(current.toString().trim());
                current.setLength(0);
            } else {
                current.append(ch);
            }
        }

        values.add(current.toString().trim());
        return values;
    }

    private Map<String, Integer> mapHeaderIndexes(List<String> headers) {
        Map<String, Integer> indexes = new HashMap<>();
        for (int i = 0; i < headers.size(); i++) {
            String normalized = normalizeField(headers.get(i));
            if (normalized.isEmpty()) {
                continue;
            }

            if (normalized.equals("email")) {
                indexes.put("email", i);
            } else if (normalized.equals("username") || normalized.equals("user_name")) {
                indexes.put("username", i);
            } else if (normalized.equals("fullname") || normalized.equals("full_name") || normalized.equals("hoten")) {
                indexes.put("fullname", i);
            } else if (normalized.equals("department") || normalized.equals("departmentid") || normalized.equals("department_id")) {
                indexes.put("department", i);
            } else if (normalized.equals("position") || normalized.equals("positionid") || normalized.equals("position_id")) {
                indexes.put("position", i);
            }
        }
        return indexes;
    }

    private Account buildAccount(List<String> row, Map<String, Integer> headerIndexes) {
        if (row == null || row.isEmpty()) {
            return null;
        }

        String email = normalizeText(getCell(row, headerIndexes, "email"));
        String username = normalizeText(getCell(row, headerIndexes, "username"));
        String fullName = normalizeText(getCell(row, headerIndexes, "fullname"));
        String departmentValue = normalizeText(getCell(row, headerIndexes, "department"));
        String positionValue = normalizeText(getCell(row, headerIndexes, "position"));

        if (email == null || username == null || fullName == null || departmentValue == null || positionValue == null) {
            return null;
        }

        Department department = resolveDepartment(departmentValue);
        Position position = resolvePosition(positionValue);
        if (department == null || position == null) {
            return null;
        }

        Account account = new Account();
        account.setUsername(username);
        account.setEmail(email);
        account.setFullName(fullName);
        account.setDepartment(department);
        account.setPosition(position);
        return account;
    }

    private String getCell(List<String> row, Map<String, Integer> headerIndexes, String key) {
        Integer index = headerIndexes.get(key);
        if (index == null || index >= row.size()) {
            return null;
        }
        String value = row.get(index).trim();
        return value.isEmpty() ? null : value;
    }

    private String normalizeField(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\uFEFF", "").replaceAll("[^a-zA-Z0-9]", "").toLowerCase(Locale.ROOT);
    }

    private String normalizeText(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    private Department resolveDepartment(String rawValue) {
        if (rawValue == null || rawValue.trim().isEmpty()) {
            return null;
        }

        try {
            int departmentId = Integer.parseInt(rawValue.trim());
            return new Department(departmentId, "");
        } catch (NumberFormatException e) {
            String target = rawValue.trim();
            for (Department department : new DepartmentService().getAllDepartments()) {
                if (department.getName() != null && department.getName().equalsIgnoreCase(target)) {
                    return new Department(department.getId(), "");
                }
            }
            return null;
        }
    }

    private Position resolvePosition(String rawValue) {
        if (rawValue == null || rawValue.trim().isEmpty()) {
            return null;
        }

        try {
            int positionId = Integer.parseInt(rawValue.trim());
            return new Position(positionId, PositionName.DEV);
        } catch (NumberFormatException e) {
            String target = rawValue.trim();
            for (Position position : new PositionService().getAllPositions()) {
                if (position.getName() != null && position.getName().name().equalsIgnoreCase(target)) {
                    return new Position(position.getId(), position.getName());
                }
            }
            try {
                return new Position(0, PositionName.valueOf(target.toUpperCase(Locale.ROOT)));
            } catch (IllegalArgumentException ignored) {
                return null;
            }
        }
    }

    private Path writeErrorFile(Path inputPath, List<String> invalidRows, String header) {
        String baseName = inputPath.getFileName().toString().replaceFirst("\\.csv$", "");
        Path errorPath = inputPath.resolveSibling(baseName + "_error.csv");
        try (BufferedWriter writer = Files.newBufferedWriter(errorPath, StandardCharsets.UTF_8)) {
            if (header != null && !header.trim().isEmpty()) {
                writer.write(header + ",error_messages\n");
            } else {
                writer.write("email,username,fullname,department,position,error_messages\n");
            }
            for (String invalidRow : invalidRows) {
                writer.write(invalidRow + "\n");
            }
        } catch (IOException e) {
            return inputPath;
        }
        return errorPath;
    }

    private String buildErrorRowFromAccount(Account account, String errorMessage) {
        String deptValue = "";
        String posValue = "";
        if (account.getDepartment() != null) {
            deptValue = String.valueOf(account.getDepartment().getId());
        }
        if (account.getPosition() != null) {
            if (account.getPosition().getId() > 0) {
                posValue = String.valueOf(account.getPosition().getId());
            } else if (account.getPosition().getName() != null) {
                posValue = account.getPosition().getName().name();
            }
        }
        String email = account.getEmail() == null ? "" : escapeCsv(account.getEmail());
        String username = account.getUsername() == null ? "" : escapeCsv(account.getUsername());
        String fullName = account.getFullName() == null ? "" : escapeCsv(account.getFullName());
        String error = errorMessage == null ? "" : escapeCsv(errorMessage);
        return email + "," + username + "," + fullName + "," + deptValue + "," + posValue + "," + error;
    }

    private String escapeCsv(String value) {
        if (value == null) {
            return "";
        }
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    private boolean isValid(Account account) {
        if (account == null) {
            return false;
        }

        if (!isValidUsername(account.getUsername())) {
            return false;
        }

        if (!isValidEmail(account.getEmail())) {
            return false;
        }

        if (!isValidFullName(account.getFullName())) {
            return false;
        }

        Department department = account.getDepartment();
        Position position = account.getPosition();

        if (department == null || !isValidDepartmentId(department.getId())) {
            return false;
        }

        if (position == null || !isValidPositionId(position.getId())) {
            return false;
        }

        return true;
    }

    private boolean isValidUsername(String username) {
        if (username == null) {
            return false;
        }
        String trimmed = username.trim();
        return trimmed.length() > MIN_LENGTH && trimmed.length() < MAX_LENGTH;
    }

    private boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        String trimmed = email.trim();
        return trimmed.length() > MIN_LENGTH
                && trimmed.length() < MAX_LENGTH
                && trimmed.matches(StringCommon.EMAIL_REGEX);
    }

    private boolean isValidFullName(String fullName) {
        if (fullName == null) {
            return false;
        }
        String trimmed = fullName.trim();
        return trimmed.length() > MIN_LENGTH && trimmed.length() < MAX_LENGTH;
    }

    private boolean isValidDepartmentId(int departmentId) {
        return departmentId > 0;
    }

    private boolean isValidPositionId(int positionId) {
        return positionId > 0;
    }

    private boolean isDuplicateUsername(String username, int ignoreId) {
        if (username == null) {
            return true;
        }
        String target = username.trim();
        List<Account> accounts = accountRepository.getAll();
        for (Account account : accounts) {
            if (account != null
                    && account.getUsername() != null
                    && account.getUsername().trim().equalsIgnoreCase(target)
                    && account.getId() != ignoreId) {
                return true;
            }
        }
        return false;
    }

    private boolean isDuplicateEmail(String email, int ignoreId) {
        if (email == null) {
            return true;
        }
        String target = email.trim();
        List<Account> accounts = accountRepository.getAll();
        for (Account account : accounts) {
            if (account != null
                    && account.getEmail() != null
                    && account.getEmail().trim().equalsIgnoreCase(target)
                    && account.getId() != ignoreId) {
                return true;
            }
        }
        return false;
    }

    private boolean departmentExists(int departmentId) {
        return departmentId > 0 && new DepartmentService().getDepartmentById(departmentId) != null;
    }

    private boolean positionExists(int positionId) {
        return positionId > 0 && new PositionService().getPositionById(positionId) != null;
    }
}
