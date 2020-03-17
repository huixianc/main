package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Collection;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AbEditCommand;
import seedu.address.logic.commands.AbEditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.category.Category;

/**
 * Parses input arguments and creates a new AbEditCommand object
 */
public class AbEditCommandParser implements Parser<AbEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AbEditCommand
     * and returns an AbEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AbEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_CATEGORY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AbEditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        //parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setCategories);

        if (argMultimap.getAllValues(PREFIX_CATEGORY).size() == 1) {
            editPersonDescriptor.setCategories(ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_CATEGORY)));
        } else if (argMultimap.getAllValues(PREFIX_CATEGORY).size() > 1) {
            throw new ParseException("Please only provide 1 category");
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(AbEditCommand.MESSAGE_NOT_EDITED);
        }

        return new AbEditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Category>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Category>} containing zero tags.
     */
    /*private Optional<Set<Category>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    } */

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Category>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Category>} containing zero tags.
     */
    private Set<Category> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        //Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        Collection<String> tagSet = tags;
        return ParserUtil.parseTags(tagSet);
    }

}
