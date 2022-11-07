import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static List<Task> allTasks = new ArrayList<>();
    public static DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    public static DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                showMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:

                            System.out.print("Введите ID задачи для удаления:");
                            Schedule.deleteTask(scanner.nextInt());

                            break;
                        case 3:
                            System.out.print("Отобразить список дел на выбранное число" +
                                    " в формате dd.MM.yyyy");
                            try {
                                Schedule.getTasksForDay(LocalDate.parse(scanner.next(), Main.dateFormat2));
                            } catch (DateTimeParseException e) {
                                System.out.println("Неверный ввод");
                            }
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) {
        System.out.print("Название задачи: ");
        String taskName = scanner.next();
        System.out.println("Описание задачи: ");
        String taskDescription = scanner.useDelimiter("\n").next();
        System.out.println("Это рабочая задача? <Y-да/N-нет>");
        boolean typeOfTask = true;
        typeOfTask = "y".equals(scanner.next().toLowerCase(Locale.ROOT));
        if (typeOfTask) {
            System.out.println("Рабочая задача");
        } else System.out.println("Личная задача");
        System.out.println("Выберите тип задачи: ");
        System.out.print(" 1 - одноразовая ");
        System.out.print(" 2 - ежедневная ");
        System.out.print(" 3 - еженедельная ");
        System.out.print(" 4 - ежемесячная ");
        System.out.print(" 5 - ежегодная ");
        int repeatability = scanner.nextInt();
        System.out.print("Введите дату начала выполнения задачи в формате Жdd.MM.yyyy HH:mm:ss ");
        String stringData = scanner.next();
        try {
            LocalDateTime ldt = LocalDateTime.parse(stringData, Main.dateFormat1);

            Task task;

            switch (repeatability) {
                case 0:
                    task = new OneTimeTask(taskName, taskDescription, typeOfTask, ldt);
                    break;
                case 1:
                    task = new DailyTask(taskName, taskDescription, typeOfTask, ldt);
                    break;
                case 2:
                    task = new WeeklyTask(taskName, taskDescription, typeOfTask, ldt);
                    break;
                case 3:
                    task = new MonthlyTask(taskName, taskDescription, typeOfTask, ldt);
                    break;
                case 4:
                    task = new AnnualTask(taskName, taskDescription, typeOfTask, ldt);
                    break;
                default:
                    throw new IllegalArgumentException("Некорректный тип задачи");
            }
            Schedule.addTask(task);
            System.out.println("Задача добавлена успешно " + task);
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка, проверьте вводимую информацию");
        }

    }

    private static void showMenu() {
        System.out.println(

                " 1 - Добавить задачу " +
                        " 2 - Удалить задачу" +
                        " 3 - Вывести задачу на указанный день " +
                        " 0 - Выход"

        );
    }
}
