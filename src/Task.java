import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Task {
    protected final String title;
    protected final String description;
    protected final boolean typeOfTask;
    protected LocalDateTime nextTask;
    protected LocalDateTime firstTime;
    private static int counter = 0;
    public final int id;
    private final LocalDateTime taskDateTime;

    public LocalDateTime getTaskDateTime() {
        return taskDateTime;
    }


    public int getId() {
        return id;
    }

    public Task(String title, String description, boolean typeOfTask, LocalDateTime taskDateTime) {
        this.title = title;
        this.description = description;
        this.typeOfTask = typeOfTask;
        this.taskDateTime = taskDateTime;
        this.id = counter++;
        this.firstTime = LocalDateTime.now();
        this.nextTask = taskDateTime;
        ValidationUtils.Validation(title);
        ValidationUtils.Validation(description);
    }

    public abstract boolean getNextData(LocalDate date);

    public boolean typeOfTask() {
        return typeOfTask;
    }

    public LocalDateTime getNextTask() {
        return nextTask;
    }

    public LocalDateTime wasCreated() {
        return firstTime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean getTypeOfTask() {
        return typeOfTask;
    }


    @Override
    public String toString() {
        return "Задача номер" + id + '\'' +
                "Название задачи='" + title + '\'' +
                ", Описание задачи='" + description + '\'' +
                ", это рабочая задача?'" + typeOfTask +
                ", Дата и время постановки задачи=" + taskDateTime.format(Main.dateFormat1);
    }

}