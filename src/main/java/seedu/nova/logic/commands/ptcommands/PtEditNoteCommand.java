package seedu.nova.logic.commands.ptcommands;

import static java.util.Objects.requireNonNull;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.Model;

/**
 * Edits task to specified week
 */
public class PtEditNoteCommand extends Command {
    public static final String COMMAND_WORD = "editNote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits note in specified task in the "
            + "project in the specified week. "
            + "Parameters: "
            + PREFIX_PROJECT + "PROJECT "
            + PREFIX_WEEK + "WEEK "
            + PREFIX_TASK + "TASK "
            + PREFIX_DESC + "NOTE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PROJECT + "Ip "
            + PREFIX_WEEK + "2"
            + PREFIX_TASK + "1"
            + PREFIX_DESC + "take note to do by 2359 Friday";

    public static final String MESSAGE_NOWEEK = "No week beyond week 13";
    public static final String MESSAGE_FAILURE = "Command failed. Please check that there is a task "
            + " note in the specified index";

    private int weekNum;
    private int taskNum;
    private String project;
    private String note;

    public PtEditNoteCommand(int weekNum, int taskNum, String project, String note) {
        this.weekNum = weekNum;
        this.taskNum = taskNum;
        this.project = project.trim().toLowerCase();
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isOver13 = weekNum > 13;

        if (isOver13) {
            throw new CommandException(MESSAGE_NOWEEK);
        } else {
            boolean isEditNoteSuccess = model.editPtNote(this.project, weekNum, taskNum, note);

            if (!isEditNoteSuccess) {
                throw new CommandException(MESSAGE_FAILURE);
            }

            String projectName = this.project.toUpperCase();
            String result = "Edited note to task " + taskNum + " in week " + weekNum + " of " + projectName;

            return new CommandResult(result, false, false);
        }
    }
}
