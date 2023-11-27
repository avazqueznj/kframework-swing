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



package KFramework30.Server;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Administrator
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultSetClass", propOrder = {
    "session_id",
    "status",
    "desc",
    "start",    
    "lengh",
    "column_names",
    "column_types",    
    "records"
})
public class ResultSetClass {

    @XmlElement(required = true)
    String session_id;
    
    @XmlElement(required = true)
    String status;
    
    @XmlElement(required = true)
    String desc;

    @XmlElement(required = true)
    int start = 0;    
    
    @XmlElement(required = true)
    int lengh = 0;
    
    @XmlElement(required = true)
    ArrayList<String> column_names;
    
    @XmlElement(required = true)
    ArrayList<Integer> column_types;
    
    @XmlElement(required = true)
    ArrayList<RecordClass> records;
    
    public ResultSetClass() {
        status = "";
        desc = "";  
        session_id = "";
        
        start = 0;
        lengh = 0;
        
        column_names = new ArrayList<String>();
        column_types = new ArrayList<Integer>();
        records = new ArrayList<RecordClass>();
    }
       
}