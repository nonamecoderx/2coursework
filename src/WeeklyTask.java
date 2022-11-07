import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {

    public WeeklyTask(String header, String description, boolean typeTask, LocalDateTime localDateTime) {
        super(header, description, typeTask, localDateTime);
    }

    @Override
    public boolean getNextData(LocalDate date) {
        LocalDate taskCreationDate = getTaskDateTime().toLocalDate();
        return taskCreationDate.equals(date)
                || date.getDayOfWeek().equals(taskCreationDate.getDayOfWeek());
    }
}