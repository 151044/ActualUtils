package com.freemyip.nopersonalinfo.discord.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A list of commands for a specific prefix.
 */
public class CommandList {
    private Map<String,Command> commandMap;
    private Map<List<String>,String> aliasMap;
    public CommandList() {
        commandMap = new HashMap<>();
        aliasMap = new HashMap<>();
    }

    /**
     * Tries to get a command if it exists by its alias or name.
     * @param byName The name of the command, or its alias, to search for
     * @return An optional containing the requested command, or an empty optional if it cannot be found
     */
    public Optional<Command> tryGet(String byName){
        if(commandMap.containsKey(byName)){
            return Optional.of(commandMap.get(byName));
        }
        if(aliasMap.keySet().stream().flatMap(list -> list.stream()).anyMatch(str -> str.equals(byName))){
            return Optional.of(commandMap.get(aliasMap.entrySet().stream().filter(ent -> ent.getKey().contains(byName)).findFirst().map(ent -> ent.getValue()).get()));
        }else{
            return Optional.empty();
        }
    }

}
