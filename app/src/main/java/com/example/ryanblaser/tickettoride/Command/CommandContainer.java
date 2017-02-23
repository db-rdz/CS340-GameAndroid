package com.example.ryanblaser.tickettoride.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandContainer{

  public List<String> str_type;
  public List<Object> icommand;
  public CommandContainer(){str_type = new ArrayList<>(); icommand = new ArrayList<>();}
  public CommandContainer(List<String> type, List<Object> cmd){
    str_type = type;
    icommand = cmd;}
  
  /**
   * This is used inside the switch statements where we need to create specific commands.
   * @param objects The list of Objects inside the CommandContainer object
   * @return The List<String> version of the List<Object>
   */
	public List<String> convertListObjectToListString(List<Object> objects) {
		List<String> stringVersion = new ArrayList<>(); 
		
		for (int i = 0; i < objects.size(); i++) {
			stringVersion.add((String) objects.get(i));
		}
		return stringVersion;
	}
  }