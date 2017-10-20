package by.htp.library.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.library.controller.command.CommandName;
import by.htp.library.controller.command.impl.AddBook;
import by.htp.library.controller.command.impl.BookIn;
import by.htp.library.controller.command.impl.DelBook;
import by.htp.library.controller.command.impl.EditBook;
import by.htp.library.controller.command.impl.EditUserProfile;
import by.htp.library.controller.command.impl.SearchByTitle;
import by.htp.library.controller.command.impl.SaveUser;
import by.htp.library.controller.command.impl.SearchByAuthor;
import by.htp.library.controller.command.impl.SearchByContext;
import by.htp.library.controller.command.impl.ShowBook;
import by.htp.library.controller.command.impl.ListBook;
import by.htp.library.controller.command.impl.Registration;
import by.htp.library.controller.command.impl.SaveBook;
import by.htp.library.controller.command.impl.SingIn;
import by.htp.library.controller.command.impl.SingOut;
import by.htp.library.controller.command.impl.WrongRequest;

public class CommandProvider {
	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {
		repository.put(CommandName.ADDBOOK, new AddBook());
		repository.put(CommandName.EDITBOOK, new EditBook());
		repository.put(CommandName.SEARCHBYTITLE, new SearchByTitle());
		repository.put(CommandName.SEARCHBYAUTHOR, new SearchByAuthor());
		repository.put(CommandName.SEARCHBYCONTEXT, new SearchByContext());
		repository.put(CommandName.DELBOOK, new DelBook());
		repository.put(CommandName.SAVEBOOK, new SaveBook());
		repository.put(CommandName.SHOWBOOK, new ShowBook());
		repository.put(CommandName.LISTBOOK, new ListBook());
		repository.put(CommandName.SINGIN, new SingIn());
		repository.put(CommandName.SINGOUT, new SingOut());
		repository.put(CommandName.WRONGREQUEST, new WrongRequest());
		repository.put(CommandName.EDITUSERPROFILE, new EditUserProfile());
		repository.put(CommandName.SAVEUSER, new SaveUser());
		repository.put(CommandName.REGISTRATION, new Registration());
		repository.put(CommandName.BOOKIN, new BookIn());
		
		

	}

	public Command getCommand(String commandName) {
		
		Command command = null;
		try {
			commandName = commandName.toUpperCase();
			command = repository.get(CommandName.valueOf(commandName));
		} catch (IllegalArgumentException | NullPointerException e) {
			command = repository.get(CommandName.WRONGREQUEST);
		}
		return command;
	}
	
}
