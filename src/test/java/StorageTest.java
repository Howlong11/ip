package oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
public class StorageTest {

    @Test
    public void stringToList_givenString_success(){
        ArrayList<Task> tasks = new ArrayList<>();
        String input = "[T] / [ ] / vitamin c";
        Storage storage = new Storage("data/lemona.txt");
        tasks = storage.stringToList(tasks, input);
        assertEquals("[T][ ] vitamin c", tasks.get(0).print());
    }

    @Test void stringToList_dateTimeParse_exceptionThrown() {
        ArrayList<Task> tasks = new ArrayList<>();
        String input = "[D] / [ ] / vitamin c / 1/1/1";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Storage storage = new Storage("data/lemona.txt");
        tasks = storage.stringToList(tasks, input);
        System.setOut(System.out);
        String actualOutput = outContent.toString().trim();
        String output = "\t______________________________________________________"
                + "\n\t I think you haven't had enough vitamin C."
                + "\n\t Your time format should be :"
                + "\n\t\t { dd/MM/yyyy HHmm }"
                + "\n\t I suggest you take some LEMONA."
                + "\n\t______________________________________________________";

        assertEquals(output.trim(), actualOutput.replace("\r\n", "\n"));
    }
}
