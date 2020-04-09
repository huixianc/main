package seedu.nova.logic.commands;

import static java.util.Objects.requireNonNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nova.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.nova.commons.core.GuiSettings;
import seedu.nova.logic.commands.abcommands.AbAddCommand;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.AddressBook;
import seedu.nova.model.Mode;
import seedu.nova.model.Model;
import seedu.nova.model.Nova;
import seedu.nova.model.ReadOnlyAddressBook;
import seedu.nova.model.ReadOnlyUserPrefs;
import seedu.nova.model.person.Person;
import seedu.nova.model.plan.StrongTask;
import seedu.nova.model.plan.Task;
import seedu.nova.model.plan.WeakTask;
import seedu.nova.model.progresstracker.ProgressTracker;
import seedu.nova.model.progresstracker.PtTask;
import seedu.nova.model.schedule.event.Event;
import seedu.nova.model.schedule.event.Lesson;
import seedu.nova.model.util.time.slotlist.DateTimeSlotList;
import seedu.nova.testutil.PersonBuilder;

public class AbAddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AbAddCommand(null));
    }

    /*@Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AbAddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AbAddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    } */

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AbAddCommand abAddCommand = new AbAddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AbAddCommand.MESSAGE_DUPLICATE_PERSON, () ->
            abAddCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AbAddCommand addAliceCommand = new AbAddCommand(alice);
        AbAddCommand addBobCommand = new AbAddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AbAddCommand addAliceCommandCopy = new AbAddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getNovaFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Nova getNova() {
            return null;
        }

        @Override
        public void setNovaFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String viewSchedule(LocalDate date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String viewSchedule(int weekNumber) {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isWithinSem(LocalDate date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isWithinSem(int weekNumber) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Mode getMode() {
            return null;
        }

        @Override
        public void addEvent(Event e) {
        }

        @Override
        public void addAllLessons(Lesson l) {
        }

        @Override
        public DateTimeSlotList getFreeSlotOn(LocalDate date) {
            return null;
        }

        @Override
        public String viewFreeSlot(LocalDate date) {
            return null;
        }

        public String deleteEvent(LocalDate date, int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean deleteEvent(Event event) {
            return false;
        }

        @Override
        public String addNote(String desc, LocalDate date, int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetPlan() {

        }

        @Override
        public boolean addRoutineTask(StrongTask st) {
            return false;
        }

        @Override
        public boolean addFlexibleTask(WeakTask wt) {
            return false;
        }

        @Override
        public List<Task> getTaskList() {
            return null;
        }

        @Override
        public Task searchTask(String name) {
            return null;
        }

        @Override
        public boolean deleteTask(Task task) {
            return false;
        }

        @Override
        public Event generateTaskEvent(Task task, LocalDate date) throws Exception {
            return null;
        }

        public ProgressTracker getProgressTracker() {
            return null;
        }

        @Override
        public String listPtTask(String projectName, int weekNum) {
            return "";
        }

        @Override
        public void addPtTask(String projectName, int weekNum, PtTask task) {
        }

        @Override
        public boolean deletePtTask(String projectName, int weekNum, int taskNum) {
            return false;
        }

        @Override
        public boolean editPtTask(String projectName, int weekNum, int taskNum, String taskDesc) {
            return false;
        }

        @Override
        public boolean setDonePtTask(String projectName, int weekNum, int taskNum) {
            return false;
        }

        @Override
        public boolean addPtNote(String projectName, int weekNum, int taskNum, String note) {
            return false;
        }

        @Override
        public boolean deletePtNote(String projectName, int weekNum, int taskNum) {
            return false;
        }

        @Override
        public boolean editPtNote(String projectName, int weekNum, int taskNum, String note) {
            return false;
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
