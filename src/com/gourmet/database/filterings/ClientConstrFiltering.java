package com.gourmet.database.filterings;

import org.dynamicschema.reification.Table;
import org.dynamicschema.sql.RelationCondition;
import org.dynamicschema.sql.SqlCondition;

import com.gourmet.database.gen.RequirementTable.RequirementColumns;

public class ClientConstrFiltering extends RelationCondition {

	private int idClient;
	
	public ClientConstrFiltering(int clientID) {
		this.idClient = clientID;
	}

	/* (non-Javadoc)
	 * @see org.dynamicschema.sql.RelationCondition#eval(org.dynamicschema.reification.Table[])
	 */
	@Override
	public SqlCondition eval(Table... tables) {

		SqlCondition finalCond = new SqlCondition();
		SqlCondition locCond = null;

		for (int i = 0; i < tables.length; i++) {
			locCond = new SqlCondition().lEq(tables[i].col(RequirementColumns.ID_CLIENT), this.idClient);
			finalCond.and(locCond.toString());
		}

		return finalCond;	
	}

}
