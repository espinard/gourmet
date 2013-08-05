package com.gourmet.database.filterings;

import java.util.List;

import org.dynamicschema.reification.Table;
import org.dynamicschema.sql.RelationCondition;
import org.dynamicschema.sql.SqlCondition;
/*
 * Set of filterings to be applied on a table
 */
public class NestedFiltering extends RelationCondition {
	
	private List<RelationCondition> filterings;
	
	public NestedFiltering(List<RelationCondition> filterings) {
		this.filterings = filterings;
	}

	/* (non-Javadoc)
	 * @see org.dynamicschema.sql.RelationCondition#eval(org.dynamicschema.reification.Table[])
	 */
	@Override
	public SqlCondition eval(Table... tables) {
		SqlCondition finalCond = new SqlCondition("");
		
		for (RelationCondition cond : this.filterings) {
			finalCond.and(cond.eval(tables).toString());
		}
	
		return finalCond;
	}
	
	

}
