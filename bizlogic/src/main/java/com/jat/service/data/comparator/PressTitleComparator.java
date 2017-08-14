package com.jat.service.data.comparator;

import java.util.Comparator;

import com.jat.persistence.entity.Press;

public class PressTitleComparator extends AbstractPressComparator implements Comparator<Press> {
	@Override
	public int compare(Press pressA, Press pressB) {
		String aTitle = pressA.getTitle();
		String bTitle = pressB.getTitle();
		
		int numComp = compareNull(aTitle, bTitle);
		if (numComp != NOT_NULL)
			return numComp;
		
		return aTitle.compareTo(bTitle);
	}
}
