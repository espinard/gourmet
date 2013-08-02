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



public class ClientTable extends DBTable { 


	public static final String NAME = "Client"; 


	public static class ClientColumns extends ColumnModel { 

		//tables column names
		public static String _ID = "_id"; 
		public static String CLI_NAME = "Cli_Name"; 
		public static String CLI_SURNAME = "Cli_Surname"; 
		public static String LOC_LATITUDE = "Loc_latitude"; 
		public static String LOC_LONGITUDE = "Loc_longitude"; 
		public static String AGE = "Age"; 
		public static String COUNTRY = "Country"; 
		public static String ID_LANGUAGE = "id_language"; 

		public ClientColumns() {
			setColumnsNames(Arrays.asList(_ID, CLI_NAME, CLI_SURNAME, LOC_LATITUDE, LOC_LONGITUDE, AGE, COUNTRY, ID_LANGUAGE)); 
			setColumnsConstraints(Arrays.asList((ColumnConstraint) new PrimaryKey(Arrays.asList("_id")),
 (ColumnConstraint)new ForeignKey(Arrays.asList("id_language"),
"Language",
Arrays.asList("_id")
)
)); 
		}
	}

	public ClientTable(){
		super (NAME, new ClientColumns()); 

	}

}
