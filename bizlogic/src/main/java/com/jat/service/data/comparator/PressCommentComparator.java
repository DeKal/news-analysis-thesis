package com.jat.service.data.comparator;

import java.util.Comparator;
import java.util.List;

import com.jat.persistence.entity.Comment;
import com.jat.persistence.entity.Press;

public class PressCommentComparator extends AbstractPressComparator implements Comparator<Press>{
	@Override
	public int compare(Press pressA, Press pressB) {
		
		List<Comment> lcA = pressA.getComment();
		List<Comment> lcB = pressA.getComment();
		
		int numComp = compareNull(lcA, lcB);
		if (numComp != NOT_NULL)
			return numComp;
		
		int numCmtA = lcA.size();
		int numCmtB = lcB.size();
		
		if (numCmtA > numCmtB){
			return GREATER;
		}
		else if (numCmtA < numCmtB){
			return LESS;
		}
		else return EQUAL;
	}
}
