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


package Sample1;

// rtl

// uitls
import KFramework30.Base.*;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

// system

public interface desktopAccessInterface {

    public boolean enableNew( boolean state );
    public boolean enableEdit( boolean state );
    public boolean enableDelete( boolean state );    
    public boolean enableSort( boolean state );
    public boolean enableFilter( boolean state );
    public boolean enableRefresh( boolean state );    
    public boolean enablePrint( boolean state );
    public boolean enableExport( boolean state );
                       
    public java.awt.Window getDesktopsWindow();
    public javax.swing.JDesktopPane getDesktop();
       
    public JInternalFrame openInternalFrame( 
            Class< ? extends JInternalFrame > internalFrameClass, String internalFrameTitle 
            );

}

