package ru.white.bot;

/**
 * Enumeration for {@link Command}'s.
 */
public enum CommandName {

    START("/start"),
    HELP("/help"),
    STAT("/stat");


    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
