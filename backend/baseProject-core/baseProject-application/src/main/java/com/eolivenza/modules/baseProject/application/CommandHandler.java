package com.eolivenza.modules.baseProject.application;

import java.util.function.Consumer;

/**
 * Generic contract intended to handle commands. The term "command" refers to
 * Bertrand Meyer's meaning in the book "Object Oriented Software Construction".
 * A command only change the state of the system but do not return any value.
 *
 * @param <CommandType> the command object
 * @author David Gracia Celemendi
 */
@FunctionalInterface
public interface CommandHandler<CommandType> extends Consumer<CommandType> {

    default String getName() { return getClass().getName(); }
}
