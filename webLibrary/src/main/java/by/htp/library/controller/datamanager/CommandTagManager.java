package by.htp.library.controller.datamanager;

/**
 * Enum of XML tags
 * @author Mashkouski Andrei
 * @version 1.0 
 */
public enum CommandTagManager {
	COMMANDS, COMMAND, NAME, REALIZATION;
	public static CommandTagManager getElementTagName(String element) {
		switch (element) {
		case "commands":
			return COMMANDS;
		case "command":
			return COMMAND;
		case "name":
			return NAME;
		case "realization":
			return REALIZATION;
		default:
			throw new RuntimeException(element);

		}

	}

}