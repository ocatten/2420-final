package lab12;

import java.util.Comparator;

public class ReverseStringComparator implements Comparator<Integer> {
	    public int compare(Integer arg0, Integer arg1) {
	    	return arg1.compareTo(arg0);
	    }
	}
