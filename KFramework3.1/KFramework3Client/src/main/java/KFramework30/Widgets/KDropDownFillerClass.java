/*
This source code is part of the KFRAMEWORK  (http://k-framework.sourceforge.net/)
Copyright (C) 2001  Alejandro Vazquez, Ke Li
Feedback / Bug Reports vmaxxed@users.sourceforge.net

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package KFramework30.Widgets;


// utilities
import KFramework30.Communication.dbTransactionClientClass;
import KFramework30.Base.*;
import KFramework30.Base.KConfigurationClass;
import KFramework30.Base.KExceptionClass;
import KFramework30.Base.KLogClass;
import KFramework30.Base.KMetaUtilsClass;
import java.util.*;
import javax.swing.*;

public class KDropDownFillerClass 
extends java.lang.Object 
implements KCustomWidgetIntegrationIntrerface
{

    //Constants
    private static final int MAX_DATA_WIDTH_OF_COMBOBOX = 60;
    
    // uses
    private KConfigurationClass configuration;    
    private KLogClass log;   
    public JComboBox dropDownBox;
    
    // has - defaulted
    private dbTransactionClientClass    dbTransactionClient;    
    private String                      SQL;            //SQL query clause 
    private String                      keyField;       //key field such as OID, maybe empty.
                                                        //In case of GROUP BY, OID must not be selected.
    private List                         keyValueList;   //key field value related with 
                                                        //ComboBox data by the same index value
                                                        //If keyField is empty, keyValueList is empty too.
    private int                         maxDataSize;    //maximum data width in combo box
    private String                      name;           //dropDownFiller name used as field name
    private List                        wholeTable;     //In addition to ComboBox data, all table cells info 
                                                         //are kept in the list by the selected columns order.
                                                         //Vectors in the list are rows.
                                                         //Data in ComboBox must be FIRST selected column
    
    private boolean                     enabled = true;
    
 
   //----------------------------------------------------------------------
   // PRIVATE INTERFACE    

        
    /** Creates new dropDownFillerClass */
   public KDropDownFillerClass( KConfigurationClass configurationParam, KLogClass logParam, 
                                String SQLstring, String keyFieldParam, JComboBox dropDownParam,
                                String nameParam )
    throws KExceptionClass
    {
        // inherits
        super();

        // uses
        configuration = configurationParam;
        log = logParam;
        dropDownBox = dropDownParam;
        
        // has - defaulted 
        maxDataSize = MAX_DATA_WIDTH_OF_COMBOBOX;
        SQL = SQLstring;
        keyField = keyFieldParam;
        keyValueList = new ArrayList();
        name = nameParam;
        wholeTable = new ArrayList(); //in table rows
        
        dbTransactionClient = new dbTransactionClientClass( configuration, log );
        //Prepares the SQL string
        dbTransactionClient.prepare( SQL );
        
        log.log( this, "Constructed successfully." );
    }
    
    /** Creates new dropDownFillerClass */
    public KDropDownFillerClass( KConfigurationClass configurationParam, KLogClass logParam, 
                                String SQLstring, String keyFieldParam, JComboBox dropDownParam
                              )
    throws KExceptionClass
    {
        this( configurationParam, logParam, SQLstring, keyFieldParam, dropDownParam, "" );
    }
    
    @Override
    public void disable(){        
        dropDownBox.setEnabled( false );
        enabled = false;
    }
    
    @Override
    public void enable(){        
        dropDownBox.setEnabled( true );
        enabled = true;        
    }
    
    public void addItem( String key, String value ){
        
        keyValueList.add( key );
        dropDownBox.addItem( value );
    }

    public void removeItemByID( String keyParam ) throws KExceptionClass{
        
        Iterator IDs = keyValueList.iterator();
       
        int index = 0;
        while( IDs.hasNext() ){
            
            if( ( (String) IDs.next() ).equals( keyParam ) ){
                
                keyValueList.remove( keyParam );
                
                dropDownBox.remove( index );
                
                return;
            }
            
            index++;
            
        }
        
        throw new KExceptionClass( "Could not remove id from list " + name + " value " + keyParam + " is not in the list. "  , null );
    }
    
    
    
    /**
            NAME
            Filler name used by serializableObject interface
    */
    @Override
    public String getName(){ return( name ); }
        
    /**
            BINDING
            Parameters can be reassigned at any moment 
            and may be reset only partially				
    */
    public void bind( String parameterName, Object parameterValue )
    throws KExceptionClass
    {    
        dbTransactionClient.dynamicBind( parameterName, parameterValue );
    }
    

    
    /**   Set maximum data width in combo box */
    public void setMaxDataWidth( int dataWidth )
    {
        maxDataSize = dataWidth;
    }
    
    
    /**   Execute the query and get the results.  */
    public void load()
    throws KExceptionClass
    {
        log.log( this, "Loading combo " + name );
        
        // clear the combo
        dropDownBox.removeAllItems();
                
        //Query to DB
        dbTransactionClient.executeQuery( 0, dbTransactionClientClass.DEFAULT_MAXROWS );  
        
        if( !keyField.isEmpty() && !dbTransactionClient.columnNamesList.contains(keyField) ) {
            throw new KExceptionClass( "*** SQL Query error **** \n" +
                        "Key field, [" + keyField + "] not found in db Query! for drop "  + name , null   );
        }
                
        //Load rows into the drop down box and the key value list
        //key field value related with the drop down box data by the same index value
        boolean noData = true;
        while( dbTransactionClient.fetch() ) {
            
            noData = false;
            
            Iterator columnNames = dbTransactionClient.columnNamesList.iterator();
            String rowLabel = "";  
            Vector record = new Vector<String>();
            String logMessage = "";
            
            // for each column
            int columnIndex = 0;
            while( columnNames.hasNext() ){
                
                String column_name = ( String ) columnNames.next();
                
                // Save key
                if( !keyField.isEmpty() && column_name.equals(keyField) ){
                    keyValueList.add( dbTransactionClient.getProperty(column_name) );
                }
                    
                // save label, always following field
                if( columnIndex == 1 ){
                    rowLabel += dbTransactionClient.getProperty( column_name );
                }                        
                    
                // store all data                   
                record.add(dbTransactionClient.getProperty( column_name ));
                
                logMessage += dbTransactionClient.getProperty( column_name ) + "|";
                
                columnIndex++;
            }                       

            //keep the data within the maximum length
            if ( rowLabel.length() > maxDataSize )
                rowLabel = rowLabel.substring( 0, maxDataSize );
                
            dropDownBox.addItem( rowLabel );
            wholeTable.add(record);
            
            log.log( this, "Loading combo {" + logMessage + "}" );
        }    
        
        
        if( noData ) throw new KExceptionClass( 
            "***Could not setup combobox ***\n" +
            "No data for [" + name + "]  " , null 
            );
        
        if( !enabled ){
            dropDownBox.setEnabled( false );            
        }else{
            dropDownBox.setEnabled( true );            
        }
           
    }
    
    /**
     * Calling function - public List getAllRequested(), 
     * will get list of rows as Vector containing entire SQL request result set. 
     * The list index lines up with combo box index. 
     * The Vector index lines up with SELECT columns order.
    */

    public List getAllItems() { return wholeTable; }    
    
 
    
    /**   Return the key field value of current selected item.  */
    public long SelectedItem()
    throws KExceptionClass
    {
        int index = dropDownBox.getSelectedIndex();
        if( index == -1 ) {
            throw new KExceptionClass( "*** Combo Box error **** \n" +
                        "No valid selected item in the model list!"  , null );
        }
            
        return KMetaUtilsClass.getIntegralNumericValueFromString( (String)keyValueList.get( index ) );
    }

    /**   Return the key field value of current selected item.  */
    public String SelectedItemLabel()
    throws KExceptionClass
    {
        return( ( String ) dropDownBox.getSelectedItem() );
    }

    
    /**   Set selected item via key value.  */
    public void setSelectedItem( long id )
    throws KExceptionClass
    {
        //if key value list is empty.
        if( keyValueList.size() == 0 ) {
            throw new KExceptionClass( "*** Combo Box error **** \n" +
                    "Can not set selected value for drop down " + name + "\n" +
                    "Drop down list is empty." , null 
                    );
        }
        
        String key = KMetaUtilsClass.toDecimalString( id );
        Iterator keyValues = keyValueList.iterator();     
        int index = 0;
        
        //find key value index in the list.
        while( keyValues.hasNext() ){

            String nextKey = ( String ) keyValues.next();
            if( key.equals( nextKey ) )
                break;
            index++;
        }            
        
        //if key value is not in the list.
        if( index >= keyValueList.size() ) {
            throw new KExceptionClass( "*** Combo Box error **** \n" +
                    "Invalid [" + key + "] value for drop down " + name  , null );
        }
         
        //Set the related item in the Combo box as selected.
        dropDownBox.setSelectedIndex( index );

    }

    @Override
    public void setValue(String newValue) throws KExceptionClass {
        setSelectedItem( KMetaUtilsClass.getIntegralNumericValueFromString(newValue) );
    }

    @Override
    public String getValue() throws KExceptionClass {
        return "" + SelectedItem();
    }
    
}
