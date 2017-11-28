package by.htp.library.controller.command;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.library.controller.datamanager.CommandTagManager;
import by.htp.library.controller.exception.ControllerException;

/**
 * Class serves to store command-objects in a collection from XML
 * @author Mashkouski Andrei
 * @version 1.0
 */
public class CommandProviderXML {
	private static final Logger log = LogManager.getRootLogger();
	private final static String PATH = "/commands.xml";
	private Map<String, Command> commands;

	public CommandProviderXML() {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			InputStream input = classLoader.getResourceAsStream(PATH);
			XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
			commands = getCommandsMap(reader);
		} catch (Exception e) {
			log.error(e);
			throw new ControllerException("fail in CommandProviderXML()", e);
		}
	}

	public Command getCommand(String commandName) {
		Command command = null;
		try {
			commandName = commandName.toUpperCase();
			command = commands.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			command = commands.get("WRONGREQUEST");
		}
		return command;
	}

	private static Map<String, Command> getCommandsMap(XMLStreamReader reader) throws XMLStreamException {
		Map<String, Command> commands = new HashMap<>();
		CommandTagManager elementName = null;
		String key = null;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = CommandTagManager.getElementTagName(reader.getLocalName());
				switch (elementName) {
				case COMMAND:
					break;
				default:
					break;
				}
				break;
			case XMLStreamConstants.CHARACTERS:
				String text = reader.getText().trim();
				if (text.isEmpty()) {
					break;
				}
				switch (elementName) {
				case NAME:
					key = text;
					break;
				case REALIZATION:
					Command command = commandCreator(text);
					commands.put(key, command);
					break;
				default:
					break;
				}
				break;
			}
		}
		return commands;
	}

	private static Command commandCreator(String text) {
		Command command = null;
		try {
			Class<?> c = Class.forName(text);
			command = (Command) c.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			log.error(e);
			throw new ControllerException("fail in commandCreator(String text)", e);
		}
		return command;
	}
}