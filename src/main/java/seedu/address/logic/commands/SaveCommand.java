package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SaveCommand extends Command {
  public final static String COMMAND_WORD = "save";

  @Override
  public CommandResult execute(Model model) throws CommandException {
    Path desiredPath = model.getAddressBookFilePath();
    ObjectMapper mapper = new ObjectMapper();
    ReadOnlyAddressBook book = model.getAddressBook();
    try {
      mapper.writeValue(new File(desiredPath.toString()), book);
      return new CommandResult("Successfully saved your address book!");
    } catch (IOException e) {
      System.out.println(e);
      return new CommandResult("Encountered an error while trying to save your address book");
    }
  }
}
