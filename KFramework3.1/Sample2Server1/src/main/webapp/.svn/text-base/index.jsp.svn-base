<!--
/*
* This source code is part of the KFRAMEWORK  (http://k-framework.sourceforge.net/)
* Copyright (C) 2001  Alejandro Vazquez, Ke Li
* Feedback / Bug Reports vmaxxed@users.sourceforge.net
* 
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
* 
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
* 
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
-->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
              
  <head>
    <title>KFramework Application</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  
<Script Language="JavaScript">


//************************************************************
//************************************************************
//************************************************************

var remote1=null;

function closeWindow() {    
	netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserWrite");    
	window.open('','_self');    
	window.close();
}

function IEbugfix(){
	now = new Date();
	random = '&ran=';
	random += now.getMinutes() + '-';
	random += now.getSeconds();	
	return( random );
}

function randomString(){
	now = new Date();
	random = '_';
	random += now.getMinutes();
	random += now.getSeconds();	
	return( random );
}

function openManual() {
    
	remote1 = window.open('/SampleServer2/UserManual.pdf','Help','width=800,height=600,resizable=no,scrollbars=no,status=0,toolbar=yes');
         
	if (remote1 != null) {
		remote1.focus();
		if (remote1.opener == null )
		remote1.opener = self;
	}
                
}  

</Script>  
  
  
  <body> 

<OBJECT 
	classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"
	width="100%" height="100%" align="baseline" 
	border=0 width=100% vspace=0 hsapce=0  mayscript=true
>

	    <PARAM NAME="type" VALUE="application/x-java-applet;version=1.6.0">        
	    <PARAM NAME="scriptable" VALUE="true">
	    <PARAM NAME="mayscript" VALUE="mayscript" >                	
	    <PARAM NAME="CODEBASE" VALUE=".">
	    <PARAM NAME="CODE" VALUE="Sample1/KFrameWorkDesktopClass.class">	
	    <PARAM NAME="ARCHIVE" VALUE= " /Sample2Server1/KFrameworkBase.jar , /Sample2Server1/KFrameworkClient.jar , /Sample2Server1/KFrameworkServer.jar , /Sample2Server1/Sample2ProblemDomainComponent.jar ,  /Sample2Server1/Sample2Client1.jar,  /Sample2Server1/activation-1.1.jar , /Sample2Server1/jcalendar-1.3.2.jar , /Sample2Server1/jcommon-1.0.0.jar,  /Sample2Server1/jfreechart-1.0.0.jar ,/Sample2Server1/swing-layout-1.0.3.jar , /Sample2Server1/javax.persistence.jar , /Sample2Server1/xmlpull-1.1.3.1.jar ,/Sample2Server1/xpp3_min-1.1.4c.jar , /Sample2Server1/xstream-1.4.3.jar  " >

	    <comment>
    
	<EMBED
		TYPE ="application/x-java-applet;version=1.6" 	
		NAME ="KFramework Application"
		BORDER =" 0"
		WIDTH =" 100%"
		HEIGHT =" 100%"	
		ARCHIVE =   " /Sample2Server1/KFrameworkBase.jar , /Sample2Server1/KFrameworkClient.jar , /Sample2Server1/KFrameworkServer.jar , /Sample2Server1/Sample2ProblemDomainComponent.jar ,  /Sample2Server1/Sample2Client1.jar,  /Sample2Server1/activation-1.1.jar , /Sample2Server1/jcalendar-1.3.2.jar , /Sample2Server1/jcommon-1.0.0.jar,  /Sample2Server1/jfreechart-1.0.0.jar ,/Sample2Server1/swing-layout-1.0.3.jar , /Sample2Server1/javax.persistence.jar , /Sample2Server1/xmlpull-1.1.3.1.jar ,/Sample2Server1/xpp3_min-1.1.4c.jar , /Sample2Server1/xstream-1.4.3.jar  "
		CODE ="Sample1/KFrameWorkDesktopClass.class"
		MAYSCRIPT=true
	>
	
	</EMBED>
	
    <!-------------------------------------------------------------------------------------------------->		
	<center>
		
      <br>      
      <br>
	      <font face="verdana" size=-1 ><p>
	      	Can not launch application. The Java plugin is noy installed or not working.
          	Please install a JAVA plugin > 1.6 and retry.
      	  </p></font>  
	  <br>
	  <br>	
         
  	</center>    
    <!-------------------------------------------------------------------------------------------------->      
	
</OBJECT>
        
  </body>
</html>


