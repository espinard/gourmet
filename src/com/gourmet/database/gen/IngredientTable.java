package com.gourmet.database.gen; 


import org.dynamicschema.annotation.Role; 
import org.dynamicschema.reification.ColumnModel; 
import org.dynamicschema.reification.Relation; 
import org.dynamicschema.reification.columnconstraint.ForeignKey; 
import org.dynamicschema.reification.Occurrence; 
import org.dynamicschema.sql.SqlCondition; 
import org.dynamicschema.reification.Table; 
import java.util.Arrays; 
import java.util.ArrayList; 
import org.dynamicschema.reification.RelationMember; 
import org.dynamicschema.sql.RelationCondition; 
import org.dynamicschema.reification.columnconstraint.ColumnConstraint; 
import org.dynamicschema.reification.Column; 
import java.util.List; 
import org.dynamicschema.reification.ContextedTable; 
import org.dynamicschema.reification.Schema; 
import com.github.dynamicschema.android.sql.EmptyFilteringCondition; 
import org.dynamicschema.reification.RelationModel; 
import org.dynamicschema.reification.columnconstraint.PrimaryKey; 
import org.dynamicschema.reification.DBTable; 
//import static android.provider.BaseColumns._ID; 



public class IngredientTable extends DBTable { 


	public static final String NAME = "Ingredient"; 


	public static class IngredientColumns extends ColumnModel { 

		//tables column names
		public static String _ID = "_id"; 
		public static String UNIT = "Unit"; 
		public static String ORIGIN = "Origin"; 
		public static String EXPIRATION_DATE = "Expiration_date"; 

		public IngredientColumns() {
			setColumnsNames(Arrays.asList(_ID, UNIT, ORIGIN, EXPIRATION_DATE)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("_id"))
)); 
		}
	}

	public IngredientTable(){
		super (NAME, new IngredientColumns()); 

	}

}
