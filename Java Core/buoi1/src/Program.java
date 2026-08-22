import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
        // ===== Department =====
        Department dept1 = new Department();
        dept1.departmentId = 1;
        dept1.departmentName = "Sales";

        Department dept2 = new Department();
        dept2.departmentId = 2;
        dept2.departmentName = "Marketing";

        Department dept3 = new Department();
        dept3.departmentId = 3;
        dept3.departmentName = "IT";

        System.out.println("=== Departments ===");
        System.out.println(dept1.departmentId + " - " + dept1.departmentName);
        System.out.println(dept2.departmentId + " - " + dept2.departmentName);
        System.out.println(dept3.departmentId + " - " + dept3.departmentName);

        // ===== Position =====
        Position pos1 = new Position();
        pos1.positionId = 1;
        pos1.positionName = PositionName.DEV;

        Position pos2 = new Position();
        pos2.positionId = 2;
        pos2.positionName = PositionName.TEST;

        Position pos3 = new Position();
        pos3.positionId = 3;
        pos3.positionName = PositionName.PM;

        System.out.println("\n=== Positions ===");
        System.out.println(pos1.positionId + " - " + pos1.positionName);
        System.out.println(pos2.positionId + " - " + pos2.positionName);
        System.out.println(pos3.positionId + " - " + pos3.positionName);

        // ===== Account =====
        Account acc1 = new Account();
        acc1.accountId = 1;
        acc1.email = "a1@mail.com";
        acc1.username = "user1";
        acc1.fullName = "Tran Van Suong";
        acc1.departmentId = 1;
        acc1.positionId = 1;
        acc1.createDate = LocalDate.of(2026, 8, 1);

        Account acc2 = new Account();
        acc2.accountId = 2;
        acc2.email = "a2@mail.com";
        acc2.username = "user2";
        acc2.fullName = "Tran Thi Buoi";
        acc2.departmentId = 2;
        acc2.positionId = 2;
        acc2.createDate = LocalDate.of(2026, 8, 2);

        Account acc3 = new Account();
        acc3.accountId = 3;
        acc3.email = "a3@mail.com";
        acc3.username = "user3";
        acc3.fullName = "Le Van Cuong";
        acc3.departmentId = 3;
        acc3.positionId = 3;
        acc3.createDate = LocalDate.of(2026, 8, 3);

        System.out.println("\n=== Accounts ===");
        System.out.println(acc1.accountId + " - " + acc1.email + " - " + acc1.fullName);
        System.out.println(acc2.accountId + " - " + acc2.email + " - " + acc2.fullName);
        System.out.println(acc3.accountId + " - " + acc3.email + " - " + acc3.fullName);

        // ===== Group =====
        Group g1 = new Group();
        g1.groupId = 1;
        g1.groupName = "Group A";
        g1.creatorId = 1;
        g1.createDate = LocalDate.of(2026, 2, 1);

        Group g2 = new Group();
        g2.groupId = 2;
        g2.groupName = "Group B";
        g2.creatorId = 2;
        g2.createDate = LocalDate.of(2026, 2, 2);

        Group g3 = new Group();
        g3.groupId = 3;
        g3.groupName = "Group C";
        g3.creatorId = 3;
        g3.createDate = LocalDate.of(2026, 2, 3);

        System.out.println("\n=== Groups ===");
        System.out.println(g1.groupId + " - " + g1.groupName + " (creator: " + g1.creatorId + ")");
        System.out.println(g2.groupId + " - " + g2.groupName + " (creator: " + g2.creatorId + ")");
        System.out.println(g3.groupId + " - " + g3.groupName + " (creator: " + g3.creatorId + ")");

        // ===== GroupAccount =====
        GroupAccount ga1 = new GroupAccount();
        ga1.groupId = 1;
        ga1.accountId = 1;
        ga1.joinDate = LocalDate.of(2026, 2, 10);

        GroupAccount ga2 = new GroupAccount();
        ga2.groupId = 2;
        ga2.accountId = 2;
        ga2.joinDate = LocalDate.of(2026, 2, 11);

        GroupAccount ga3 = new GroupAccount();
        ga3.groupId = 3;
        ga3.accountId = 3;
        ga3.joinDate = LocalDate.of(2026, 2, 12);

        System.out.println("\n=== GroupAccounts ===");
        System.out.println("Group " + ga1.groupId + " - Account " + ga1.accountId);
        System.out.println("Group " + ga2.groupId + " - Account " + ga2.accountId);
        System.out.println("Group " + ga3.groupId + " - Account " + ga3.accountId);

        // ===== TypeQuestion =====
        TypeQuestion tq1 = new TypeQuestion();
        tq1.typeId = 1;
        tq1.typeName = TypeName.ESSAY;

        TypeQuestion tq2 = new TypeQuestion();
        tq2.typeId = 2;
        tq2.typeName = TypeName.MULTIPLE_CHOICE;

        TypeQuestion tq3 = new TypeQuestion();
        tq3.typeId = 3;
        tq3.typeName = TypeName.ESSAY;

        System.out.println("\n=== TypeQuestions ===");
        System.out.println(tq1.typeId + " - " + tq1.typeName);
        System.out.println(tq2.typeId + " - " + tq2.typeName);
        System.out.println(tq3.typeId + " - " + tq3.typeName);

        // ===== CategoryQuestion =====
        CategoryQuestion cq1 = new CategoryQuestion();
        cq1.categoryId = 1;
        cq1.categoryName = "Java";

        CategoryQuestion cq2 = new CategoryQuestion();
        cq2.categoryId = 2;
        cq2.categoryName = ".NET";

        CategoryQuestion cq3 = new CategoryQuestion();
        cq3.categoryId = 3;
        cq3.categoryName = "SQL";

        System.out.println("\n=== CategoryQuestions ===");
        System.out.println(cq1.categoryId + " - " + cq1.categoryName);
        System.out.println(cq2.categoryId + " - " + cq2.categoryName);
        System.out.println(cq3.categoryId + " - " + cq3.categoryName);

        // ===== Question =====
        Question q1 = new Question();
        q1.questionId = 1;
        q1.content = "What is Java?";
        q1.categoryId = 1;
        q1.typeId = 1;
        q1.creatorId = 1;
        q1.createDate = LocalDate.of(2026, 8, 1);

        Question q2 = new Question();
        q2.questionId = 2;
        q2.content = "Explain OOP.";
        q2.categoryId = 1;
        q2.typeId = 1;
        q2.creatorId = 2;
        q2.createDate = LocalDate.of(2026, 8, 2);

        Question q3 = new Question();
        q3.questionId = 3;
        q3.content = "SQL JOIN?";
        q3.categoryId = 3;
        q3.typeId = 2;
        q3.creatorId = 3;
        q3.createDate = LocalDate.of(2026, 8, 3);

        System.out.println("\n=== Questions ===");
        System.out.println(q1.questionId + ": " + q1.content);
        System.out.println(q2.questionId + ": " + q2.content);
        System.out.println(q3.questionId + ": " + q3.content);

        // ===== Answer =====
        Answer a1 = new Answer();
        a1.answerId = 1;
        a1.content = "Java is a language";
        a1.questionId = 1;
        a1.isCorrect = true;

        Answer a2 = new Answer();
        a2.answerId = 2;
        a2.content = "Object-oriented programming";
        a2.questionId = 2;
        a2.isCorrect = true;

        Answer a3 = new Answer();
        a3.answerId = 3;
        a3.content = "INNER JOIN";
        a3.questionId = 3;
        a3.isCorrect = true;

        System.out.println("\n=== Answers ===");
        System.out.println(a1.answerId + ": " + a1.content + " (correct: " + a1.isCorrect + ")");
        System.out.println(a2.answerId + ": " + a2.content + " (correct: " + a2.isCorrect + ")");
        System.out.println(a3.answerId + ": " + a3.content + " (correct: " + a3.isCorrect + ")");

        // ===== Exam =====
        Exam e1 = new Exam();
        e1.examId = 1;
        e1.code = "EX001";
        e1.title = "Java Test";
        e1.categoryId = 1;
        e1.duration = 60;
        e1.creatorId = 1;
        e1.createDate = LocalDate.of(2026, 4, 1);

        Exam e2 = new Exam();
        e2.examId = 2;
        e2.code = "EX002";
        e2.title = ".NET Test";
        e2.categoryId = 2;
        e2.duration = 60;
        e2.creatorId = 2;
        e2.createDate = LocalDate.of(2026, 4, 2);

        Exam e3 = new Exam();
        e3.examId = 3;
        e3.code = "EX003";
        e3.title = "SQL Test";
        e3.categoryId = 3;
        e3.duration = 90;
        e3.creatorId = 3;
        e3.createDate = LocalDate.of(2026, 4, 3);

        System.out.println("\n=== Exams ===");
        System.out.println(e1.examId + " - " + e1.code + ": " + e1.title);
        System.out.println(e2.examId + " - " + e2.code + ": " + e2.title);
        System.out.println(e3.examId + " - " + e3.code + ": " + e3.title);

        // ===== ExamQuestion =====
        ExamQuestion eq1 = new ExamQuestion();
        eq1.examId = 1;
        eq1.questionId = 1;

        ExamQuestion eq2 = new ExamQuestion();
        eq2.examId = 2;
        eq2.questionId = 2;

        ExamQuestion eq3 = new ExamQuestion();
        eq3.examId = 3;
        eq3.questionId = 3;

        System.out.println("\n=== ExamQuestions ===");
        System.out.println("Exam " + eq1.examId + " - Question " + eq1.questionId);
        System.out.println("Exam " + eq2.examId + " - Question " + eq2.questionId);
        System.out.println("Exam " + eq3.examId + " - Question " + eq3.questionId);
    }
}