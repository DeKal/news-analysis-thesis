package com.jat.service.data.comparator;

import java.util.List;

import com.jat.persistence.entity.Comment;
import com.jat.persistence.entity.Press;

public abstract class AbstractPressComparator {
	public final static int EQUAL = 0;
	public final static int LESS = -1;
	public final static int GREATER = 1;
	public final static int NOT_NULL = 1;

	protected final int compareNull(Object A, Object B) {

		if (A == null && B == null)
			return EQUAL;
		if (A == null)
			return LESS;

		if (B == null)
			return GREATER;

		return NOT_NULL;
	}

	protected final int compareNum(int a, int b) {
		if (a == b)
			return EQUAL;
		else if (a < b)
			return LESS;
		else 
			return GREATER;
	}
}
