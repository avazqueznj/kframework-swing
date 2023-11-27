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

package Sample1.HumanInterfaceComponent.LIMSDataNavigatorDemo.util;

// rtl
import KFramework30.Widgets.TreeView.KTreeViewerClass.treeNodeClass;
import java.util.*;
import java.awt.*;
import java.awt.print.*;
import java.text.*;
import javax.swing.event.*;
import javax.swing.*;

// jfree



// system


public interface fieldSelectorInterface {

    public abstract java.util.List< treeNodeClass > getSelectedFields();
    
    public abstract void addManyFields();    
    
    public abstract void removeManyFields();        
    
    public abstract void clearFieldSelection();
}

