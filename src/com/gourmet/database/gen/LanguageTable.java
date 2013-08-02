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



public class LanguageTable extends DBTable { 


	public static final String NAME = "Language"; 


	public static class LanguageColumns extends ColumnModel { 

		//tables column names
		public static String _ID = "_id"; 
		public static String DESCRIPTION = "Description"; 

		public LanguageColumns() {
			setColumnsNames(Arrays.asList(_ID, DESCRIPTION)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("_id"))
)); 
		}
	}

	public LanguageTable(){
		super (NAME, new LanguageColumns()); 

	}

}
