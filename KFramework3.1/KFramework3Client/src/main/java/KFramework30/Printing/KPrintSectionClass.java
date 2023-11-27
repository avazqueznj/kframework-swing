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

package KFramework30.Printing;

// rtl
import java.util.*;
import java.awt.image.*;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

// barcoder
//import MSBBarCode.*;

// ale utils

import KFramework30.Base.*;
import org.jfree.chart.JFreeChart;


public class KPrintSectionClass{

    //Constants
    public static final int LEFT = 0;
    public static final int RIGHT = 1;   
    public static final int CENTER = 2;
     
    // uses
    private KConfigurationClass              configuration;
    private KLogClass                        log;
        
    // has - defaulted   
    private java.util.List                  printableObjectList;
    private int                             sectionWidth;
    private int                             sectionHeight;
    public  Font                            currentFont;
    
//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------    
//Printable inner classes        

    
                class printableTextClass implements printableInterface {              
                    // uses
                    private String      text;       //the text to be printed.
                    private int         x;          //section offset
                    private int         y;          //section offset

                    public printableTextClass( String textParam, int xParam, int yParam ) 
                    throws KExceptionClass
                    {

                        text = textParam;
                        x = xParam;
                        y = yParam;
                    }

                    public void draw( Graphics graphics, int pageX, int pageY ){             
                        FontMetrics metrics =  graphics.getFontMetrics( );
                        int maxAscent = metrics.getMaxAscent();


                        //Caculate alignment.
                        int finalX = pageX + x;

                        graphics.drawString( text, finalX, pageY + y + maxAscent );


                        //For test            
                        //graphics.drawLine(pageX+x, pageY, pageX +x+ fieldWidth, pageY );  
                        //graphics.drawLine(pageX+x, pageY +sectionHeight-1, pageX +x+ fieldWidth, pageY + sectionHeight-1 );        
                    }


                }


                class printableGraphicJFreeChartClass implements printableInterface {              
                    // uses
                    private JFreeChart  jfreeChart;
                    private int         widht;
                    private int         height;
                    private int         x;
                    private int         y;

                    public printableGraphicJFreeChartClass( JFreeChart chart, int widhtParam, int heightParam, int xParam, int yParam ) 
                    throws KExceptionClass
                    {

                        jfreeChart = chart;
                        widht = widhtParam;
                        height = heightParam;
                        x = xParam;
                        y = yParam;
                    }

                    public void draw( Graphics graphics, int pageX, int pageY ){             
                        
                        graphics.drawImage( jfreeChart.createBufferedImage(widht, height), pageX + x, pageY + y, null );

                    }


                }
                
                
            //------------------------------------------------------------------------------------    

                class printableFieldClass implements printableInterface {
                    // uses
                    private String      text;   //the text to be printed.
                    private int         x;      //section offset
                    private int         y;      //section offset
                    private int         fieldWidth;
                    private int         alignment;

                    public printableFieldClass( String textParam, int xParam, int yParam, 
                                                int fieldWidthParam ) 
                    throws KExceptionClass
                    {
                        this(textParam, xParam, yParam, fieldWidthParam, LEFT);
                    }

                    public printableFieldClass( String textParam, int xParam, int yParam, 
                                                int fieldWidthParam, int alignmentParam ) 
                    throws KExceptionClass
                    {

                        text = textParam;
                        x = xParam;
                        y = yParam;
                        fieldWidth = fieldWidthParam;
                        alignment = alignmentParam;
                    }        

                    public void draw( Graphics graphics, int pageX, int pageY ) {

                        FontMetrics metrics =  graphics.getFontMetrics( );
                        int maxAscent = metrics.getMaxAscent();

                        graphics.setClip( pageX + x, pageY + y, fieldWidth, sectionHeight );

                        //Caculate alignment.
                        int finalX = pageX + x;

                        if( alignment == RIGHT ) {             
                            finalX += fieldWidth - metrics.stringWidth( text );
                            finalX -= 5;
                        }                

                        if( alignment == CENTER ) {             
                            finalX += ( fieldWidth - metrics.stringWidth( text ) ) / 2;
                            finalX -= 5;
                        }                
                        
                        graphics.drawString( text, finalX, pageY + y + maxAscent );



            //For test            
            //graphics.drawLine(pageX+x, pageY, pageX +x+ fieldWidth, pageY );  
            //graphics.drawLine(pageX+x, pageY +sectionHeight-1, pageX +x+ fieldWidth, pageY + sectionHeight-1 );        
                    }
                }    


            //------------------------------------------------------------------------------------    

                class printableHeaderLabelClass implements printableInterface {
                    
                    // uses
                    private String      text;   //the text to be printed.
                    private int         x;      //section offset
                    private int         y;      //section offset
                    private int         fieldWidth;

                    // has - defauted

                    
                    public printableHeaderLabelClass( 
                        String textParam, int xParam, int yParam, int fieldWidthParam ) 
                        throws KExceptionClass
                    {

                        text = textParam;
                        x = xParam;
                        y = yParam;
                        fieldWidth = fieldWidthParam;
                                                
                    }        

                    public void draw( Graphics graphics, int pageX, int pageY ) 
                    throws KExceptionClass
                    {                        

                    //********************                        
                                                                       
                        FontMetrics metrics =  graphics.getFontMetrics( );                                                
                                                
                    // split text in underscores
                        
                        Vector headerLines = new Vector();                                                                    
                        
                        Vector tokens = new Vector();
                        StringTokenizer tokenizer = new StringTokenizer( text, " _-:.", true );
                        while( tokenizer.hasMoreTokens() ) tokens.add( tokenizer.nextToken() );
                        if( tokens.size() == 0 ) 
                                throw new KExceptionClass(  
                                    "*** Could not print header ***\nCould not split header text." , null 
                                );

                    // start assembly
                        
                        {


                            // for each word                       
                            Iterator token = tokens.iterator();
                            String currentLine = (String) token.next();                        
                            while( token.hasNext() ){

                                String nextToken = (String) token.next();

                                // over limit?
                                double newLineWidth = 
                                    metrics.getStringBounds( 
                                        currentLine + nextToken, graphics ).getWidth();

                                if( newLineWidth > fieldWidth ){

                                    // store previous line
                                    headerLines.insertElementAt( currentLine, 0 );

                                    // and get new one
                                    currentLine = nextToken;

                                }else{

                                    currentLine += nextToken;
                                }

                            } // next word
                            
                            
                            // add last 
                            headerLines.insertElementAt( currentLine, 0 );                            

                        }                        
                        
                        
                        
                    // print lines
                        
                        int maxAscent = metrics.getMaxAscent();                                                

                        int row = 0;
                        Iterator headerLinesIterator = headerLines.iterator();
                        
                        
                        // FOR LINE
                        while( headerLinesIterator.hasNext() ){

                            String currentLine = (String) headerLinesIterator.next();
                           
                         
                        // ------------------------------
                        
                        
//                        // box  -
//                        graphics.drawLine( 
//                            pageX + x, 
//                            pageY, // pageY + y + row,
//                            
//                            pageX + x + fieldWidth + 5, 
//                            pageY );                           
//                            
//                        // box  |
//                        graphics.drawLine( 
//                            pageX + x + fieldWidth, 
//                            pageY , // pageY + y + row,
//                            
//                            pageX + x + fieldWidth, 
//                            pageY + y + 10 + row + 1 );                             
                        
                        // ------------------------------

                            graphics.setClip( 
                                pageX + x, 
                                pageY + y + row, 

                                pageX + x + fieldWidth, 
                                pageY + y + maxAscent + row );                        

                            graphics.drawString( 
                                currentLine, 
                                pageX + x, 
                                pageY + y + maxAscent + row );   
                            

                            row -= maxAscent;                                
                            
                        }  // next token 
                                                
                    
                    } // draw
                    
                    
                } // class



            //------------------------------------------------------------------------------------      

                class printableBarcodeClass 
                implements printableInterface {

                    // uses
                    private String      data;   //the data to be encoded
                    private int         x;      //section offset
                    private int         y;      //section offset

                    private int         width;      // barcode width
                    private int         height;     // barcode height

                    // has

                    public printableBarcodeClass( 
                        String dataParam,
                        int xParam, int yParam,
                        int widthParam, int heightParam
                    ) 
                    throws KExceptionClass
                    {

                        data = dataParam;
                        x = xParam;
                        y = yParam;            
                        width = widthParam;
                        height = heightParam;
                    }

                    public void draw( Graphics graphics, int pageX, int pageY ){

                        // get an encoder
                        BufferedImage barCodeImage = new BufferedImage( 
                            width , height , 
                            BufferedImage.TYPE_INT_RGB );            

                        // configure encoding
                 
/*                        
                        BarCode encoder = new BarCode();           
                        encoder.code = data;
                        encoder.barType = BarCode.EAN13;
                        encoder.checkCharacter = true;                                   
                        encoder.setSize( width, height );            
                        encoder.barHeightCM = 1;
                         

                        // paint code in image
                        encoder.paint( barCodeImage.getGraphics() );
*/                      

                        // print image
                        graphics.drawImage( barCodeImage, pageX + x, pageY + y, null );
                    }
                }    


            //------------------------------------------------------------------------------------


                class printableLineClass implements printableInterface {
                    // uses
                    private int         x1;      //section offset of the first point's x
                    private int         y1;      //section offset of the first point's y
                    private int         x2;      //section offset of the second point's x
                    private int         y2;      //section offset of the second point's y

                    public printableLineClass( int x1Param, int y1Param, int x2Param, int y2Param ) 
                    throws KExceptionClass
                    {

                        x1 = x1Param;
                        y1 = y1Param;
                        x2 = x2Param;
                        y2 = y2Param;            
                    }

                    public void draw( Graphics graphics, int pageX, int pageY ) {
                        graphics.drawLine( pageX + x1, pageY + y1, pageX + x2, pageY + y2);            
                    }

                }


            //------------------------------------------------------------------------------------

                class printableImageClass implements printableInterface {
                    // uses
                    private Image       image;   //the image to be printed.
                    private int         x;      //section offset
                    private int         y;      //section offset

                    public printableImageClass( Image imageParam, int xParam, int yParam ) 
                    throws KExceptionClass
                    {

                        image= imageParam;
                        x = xParam;
                        y = yParam;
                    }

                    public void draw( Graphics graphics, int pageX, int pageY ) {

                        ImageIcon newImage = new ImageIcon( image );            
                        graphics.drawImage( newImage.getImage(), pageX + x, pageY + y, null );
                    }
                }    


            //------------------------------------------------------------------------------------


                class  printableObjectFontClass implements printableInterface {
                    // uses
                    private Font        font;   //the font to be used in printing.

                    public printableObjectFontClass( Font fontParam ) 
                    {
                        font = fontParam;
                    }

                    public void draw( Graphics graphics, int x, int y ) {
                        if( font != null )
                            graphics.setFont( font );           
                    }
                }    


            //------------------------------------------------------------------------------------


                class  printableObjectColorClass implements printableInterface {
                    // uses
                    private Color        color;   //the font to be used in printing.

                    public printableObjectColorClass( Color colorParam ) 
                    {
                        color = colorParam;
                    }

                    public void draw( Graphics graphics, int x, int y ) {
                        if( color != null ) graphics.setColor( color );           
                    }
                }  
    
//Printable inner classes
//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------    
    
    /** Creates new KPrintSectionClass */
    public KPrintSectionClass( KConfigurationClass configurationParam, KLogClass logParam,
                              int width, int height ){
        //uses
        configuration = configurationParam;
        log = logParam;
        
        // has - defaulted   
        printableObjectList = new ArrayList();
        sectionWidth = width;
        sectionHeight = height;
        currentFont = null;
        
        log.log( this, "printSectionClass is constructed." );
    }
    

    public int getHeight() {
        return sectionHeight;
    }
  
    public int getWidth() {
        return sectionWidth;
    }
    
    public void clearSection()
    {
        printableObjectList.clear();    
    }
    
     public void setFont( Font fontParam ) {
        //create a printable object
        printableInterface object = new printableObjectFontClass( fontParam );
        //add the printable object to the list
        printableObjectList.add( object );
        
        // save for defaulting
        currentFont = fontParam;
    }
    
    public void setColor( Color colorParam ) {
        //create a printable object
        printableInterface object = new printableObjectColorClass( colorParam );
        //add the printable object to the list
        printableObjectList.add( object );
    }
    
    
    public void printText( String text, int x, int y )
    throws KExceptionClass
    {
        //create a printable object
        printableInterface object = new printableTextClass( text, x, y );
        //add the printable object to the list
        printableObjectList.add( object );
    }
    
    public void printGraphicJFreeChart( JFreeChart chart, int width, int height, int x, int y )
    throws KExceptionClass
    {
        //create a printable object
        printableInterface object = new printableGraphicJFreeChartClass( chart, width, height, x, y );
        //add the printable object to the list
        printableObjectList.add( object );

    }
    //((
    public void printField( String text, int x, int y, int fieldWidth, int alignment ) 
    throws KExceptionClass
    {
        //create a printable object
        printableInterface object = new printableFieldClass( text, x, y,        
                                                 fieldWidth, alignment );
        //add the printable object to the list
        printableObjectList.add( object );
    }
    
    public void printField( String text, int x, int y, int fieldWidth ) 
    throws KExceptionClass
    {
        printField( text, x, y, fieldWidth, LEFT );
    }    
    
    public void printField( long numberString, int x, int y, int fieldWidth ) 
    throws KExceptionClass
    {
        printField( String.valueOf( numberString ), x, y, fieldWidth, RIGHT );
    }        

    
    
    public void printHeaderLabel( String text, int x, int y, int fieldWidth ) 
    throws KExceptionClass
    {
                
        //create a printable object
        printableInterface object = 
            new printableHeaderLabelClass( text, x, y, fieldWidth );
        
        //add the printable object to the list
        printableObjectList.add( object );

    }        
    
    
    
    public void printLine( int x1, int y1, int x2, int y2 )
    throws KExceptionClass
    {
        //create a printable object
        printableInterface object = new printableLineClass( x1, y1, x2, y2 );
        //add the printable object to the list
        printableObjectList.add( object );             
    }
    
    public void printImage( Image image, int x, int y )
    throws KExceptionClass
    {
        //create a printable object
        printableInterface object = new printableImageClass( image, x, y );
        //add the printable object to the list
        printableObjectList.add( object );        
    }
    

    public void printBarCode(  
            String dataParam,
            int xParam, int yParam,
            int widthParam, int heightParam    
    )
    throws KExceptionClass
    {
        //create a printable object
        printableInterface object = new printableBarcodeClass(  
            dataParam,
            xParam, yParam, widthParam, heightParam                
        );
        
        //add the printable object to the list
        printableObjectList.add( object );        
    }
    
    
    // -----------------------------------------------------------------
    // -----------------------------------------------------------------    
    // -----------------------------------------------------------------        
    
    public void print( Graphics graphics, int x, int y, int MAX_X, int MAX_Y )
    throws KExceptionClass
    {
        log.log( this, "Printing section elements..." );
                
        Iterator printableObjects = printableObjectList.iterator();     
        
        while( printableObjects.hasNext() ){
            
            printableInterface currentElement = ( printableInterface ) printableObjects.next();
            
            String message = "Printing [" + currentElement.toString() + "]";        
            log.log( this, message );
            
            try{
                
                // java bugofix, dont clear clip with NULL
                graphics.setClip( 0, 0, MAX_X, MAX_Y );
                // java bugofix
                
                ( currentElement ).draw( graphics, x, y );                                    
                
            // catch java bug                
            }catch( NullPointerException error ){

                log.log( this, "   <<<<<<<<<<<<<<<<Error" );
                log.log( this, KMetaUtilsClass.getStackTrace( error ) );                
            };
        };
        
        log.log( this, "Printing section elements...done!" );        
    }
   
    // -----------------------------------------------------------------    
    // -----------------------------------------------------------------    
    // -----------------------------------------------------------------        
    
    public void export( persistentTextClass exportFile )
    {
        log.log( this, "Printing section elements..." );
                
        Iterator printableObjects = printableObjectList.iterator();     

        int currentLineY = -1;
        String currentLine = "";
        while( printableObjects.hasNext() ){
            
            printableInterface currentElement = ( printableInterface ) printableObjects.next();

            String message = ">>Printing [" + currentElement.toString() + "]";        
            log.log( this, message );
                        
            if( currentElement instanceof printableTextClass ){

                // break line?                
                if( ( currentLineY != -1 ) && ( currentLineY != ( (printableTextClass) currentElement).y ) ){
                    exportFile.add( currentLine );
                    currentLine = "";
                    currentLineY = ( (printableTextClass) currentElement).y;                                    
                };
                
                currentLine += ( (printableTextClass) currentElement).text + ",";
                currentLineY = ( (printableTextClass) currentElement).y;                                                    
                
            }

            if( currentElement instanceof printableFieldClass ){
                
                // break line?                
                if( ( currentLineY != -1 ) && ( currentLineY != ( (printableFieldClass) currentElement).y ) ){
                    exportFile.add( currentLine );
                    currentLine = "";
                    currentLineY = ( (printableFieldClass) currentElement).y;                                    
                };
                
                currentLine += ( (printableFieldClass) currentElement).text + ",";
                currentLineY = ( (printableFieldClass) currentElement).y;                                                    
            }                        

            if( currentElement instanceof printableHeaderLabelClass ){
                
                // break line?                
                if( ( currentLineY != -1 ) && ( currentLineY != ( (printableHeaderLabelClass) currentElement).y ) ){
                    exportFile.add( currentLine );
                    currentLine = "";
                    currentLineY = ( (printableHeaderLabelClass) currentElement).y;                                    
                };
                
                currentLine += ( (printableHeaderLabelClass) currentElement).text + ",";
                currentLineY = ( (printableHeaderLabelClass) currentElement).y;                                                    
            }                        
            
            
        };
        
        // last line
        exportFile.add( currentLine );
        
        log.log( this, "Printing section elements...done!" );        
    }
   
    // -----------------------------------------------------------------    
    // -----------------------------------------------------------------    
    // -----------------------------------------------------------------        
    
}
