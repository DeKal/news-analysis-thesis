package com.jat.service.data.comparator;

import java.util.Comparator;

import com.jat.persistence.entity.Press;

public class PressSentiCommentComparator extends AbstractPressComparator implements Comparator<Press> {

	@Override
	public int compare(Press pressA, Press pressB) {
		
		int posCmtA = pressA.getPosCmt();
		int posCmtB = pressB.getPosCmt();
		
		return compareNum(posCmtA, posCmtB);
	}

}
