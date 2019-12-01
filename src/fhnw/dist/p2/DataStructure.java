package fhnw.dist.p2;

import java.util.ArrayList;
import java.util.List;

public class DataStructure {
	
	List<String> strings;

	public DataStructure() {
		strings = new ArrayList<>();
	}
	
	public boolean insertString(String s) {
		return strings.add(s);
	}
	
	public boolean containsString(String s) {
		return strings.contains(s);
	}
}
