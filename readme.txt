KFramework README
Design and Programming: Alejandro Vazquez, Ke Li
Programming: Chris Backas, Behaven Patel, Liliana Baron, Marisela Islas, Xavier Gonzalez, Josep Bernal

KFramework 3.0  1MARCH2013
----------------
* FULL XML transport, for any type in object, including binaries, images, lists ect
* Suports multiple PU for domain objects
* JPA build time enhancements
* Demo loading images to objects, no backend code changes needed to add images!
* generic export button
* better scrolling for large large result sets ...  10 ,000+
* transaction control is now user controlable and allows more than one EntityManager at one time and trx
* better handling simultaneous sessions
* redid server session/pom control
* transactions eparated from sessions
* No separate JDBC connections,,,
* added pre post to controller
* added new number format with no formatting for code
* New object to controll dialog lifecycle. Now dialogs an be coded with just a few lines, just pass the screen and the PDC object
KFramework 2.2  Not released
* URGENT Fixed: Fixed issue with sql connections not being closed properly in server
* Fixed: Entity and db connections no longer cached, avoid max connection error, not justified any more. Session will be kept as it is used in many cases.
* Enhanced: Sublog size separated from main log size
* Fixed: Logon denied messasge not clear fixed
* Fixed: Slow applet communication due to multiple unecessary GET xxx.class for each transaction. Added missing lib to applet/jsp.

KFramework 2.1  10DIC2011
----------------
* NEW      ver2: Multiple cluster high availability cluster
* Enhanced ver2: Swing dialog lifecycle redesigned and simplified 
* Enhanced ver2: Data Tables lifecycle redesigned and simplified 
* Enhanced ver2: Main menu coding reduced to 1 line
* Enhanced ver2: All native Database connections shared with JPA
* Enhanced: fixed browser failure handling
* Enhanced: Automatic cursors wait handling
* Enhanced: Full support for international currencies and dates
* Enhanced: Now delivered as MAVEN projects
* FIXED: Issues of flickering for new Swing handling of modal windows
* Enhanced: Log Component
* Enhanced: More examples on server logic
* FIXED: KException gets into infinite loops
* FIXED: table column data type not passed properly to client due to JDBC driver bug
* Enhanced: New manual


KFramework 1.4.9  18AUG2011
----------------
* NEW: Support for automatic optimistic record updating/locking using object version ids. See manual.
* Changed: getEntityTransaction() changed to getEntityManager() for clarity...
* Changed: DropDown will always load all hidden columns be default and clear the combo every time its loaded
* Changed: On materialize and Visualize, the additional component list is now mandatory, to avoid errors.
* Enhanced: Default Date format was changed to transport date _and_ time, no API change. It allows for other calendar/time controls with time.
* Fixed: The mapper will skip unknown controls. The mapper now checks any control, including third partie's for mapping.
* Fixed: DB Changes of one user will delay to be reflected in another user: PersistentObjectManager clears caches on every service.

KFramework 1.4.8B1 11AUG11
----------------
* Fixed: datasource config issue in demo project where glassfish will not reload the server unless undeployed.

KFramework 1.4.8B 8AUG11
----------------
* New: Using checkboxes examples
* New: Added automatic support for radio button groups 
* New: Sending mail example
* Enhanced: dbTransactionClass in client does not require session params any more
* Enhanced: Tree tool API simplified for usage, corresponding manual section redone.
* Fixed: Database dumps and scripts have systemlog message field too short. Field size adjusted.

KFramework 1.4.8A 19JUL11
----------------
* Enhanced: Tweaked some PLAF settings for better window looks
* Enhanced: Browsers now have intercalating colors for each row
* Fixed/Enhanced: POM.execute() had a materializing bug, also now it returns the object received
* Fixed: Repainting tables from background threads repaints UI root: Table's refresh() now only repaints the table

KFramework 1.4.8 13jul11
----------------
* New: New graphics and cosmetics on all dialogs
* New: Example of many to many relationship, more objects, more flows, more dialogs
* New: Added example setting validations, type and size to fields in the sample_facturaClass business object, 
see how widgets automatically use it, don't put business validation on the  screen (UI).
* Mod: ActiveObjectClass moved to KFramework.base package to allow use in client
* Fixed: Concurrent Excep errors on shutdown: Added wait for threads to stop before resource release.
* Fixed: Desktop too slow in Redhat 4-5 browser or applet viewer: Issue in java's nimbus look and feel. Removed Nimbus as default Look and Feel.
* Fixed: Multiple issues with database dumps: Published dumps were incorrect. Sorry, we changed our release quality check.
* Fixed: Some issues materializing currency fields: Changed defaults from [$ 0.00] to [%0.00]
* Fixed: Referential integrity engine infinite loop in some PDC hierarchies:
* Fixed: Tested on more linux distros with firefox

KFramework 1.4.7A 29jun11
----------------
* Fixed: Referential integrity error deleting invoices in the demo, because of field name change

KFramework 1.4.7 28jun11
----------------
* NEW/SIMPLIFIED: Server redesigned to run on only 1 WAR file. No more JEE EAR or EJBs. Server 100% Standards compliant now. Server API not changed, just the container. JPA and persistent model API not changed.
* New: Tutorial now includes batch process, audit trial, Authorization/Authentication
 mailer and table browser reference.

KFramework 1.4.6D 24jun11
----------------
* Fixed incorrectly written auto close cursor function
* Renamed some classes using Capital convention to xxxClass convention
* Moved user's batch processing to POM, added example of batch processes
* Batcher started automatically with listener in WAR
* Reconstructed webservices

KFramework 1.4.6C 8jun2011
----------------
* Fixed release 1.4.6, fixed some build.xml for Netbeans 7.0 released version

KFramework 1.4.6B 7jun2011
----------------
* Fixed release 1.4.6, included demo applet was version 1.4.5 with out table widgets

KFramework 1.4.6 26may2011
---------------
* NEW API to make browsers read/write with combos, calendars and custom cell widgets
* Fixed Max cursors in oracle with people using the browser and reports only
* Enhanced Flush Dangling Cursors function on databaseClass
* Enhanced DesktopInterface function returns JDesktop 
* Fixed execute funtion of POM not working

Tuturial 1.19    11may2011
----------------
* Updated manual: Explained in more detail how the server interacts, plus other details
Always check for the lates manual here: http://sourceforge.net/projects/k-framework/files/tutorial/

KFramework 1.4.5 9may2011
----------------
* API Frozen, efforts now on estability, internationalization and super cluster
* Renamed some printing api methods
* Fixed signer bat to avoid security warnings on startup in browser
* Simplified - Config file of client now is included as resource, parameter in JSP removed.
* Tutorial completed with graphing 
* Tutorial completed report engine
* LIMS data navigator provided with full graphing examples Oracle & Sqlserver
* LIMS reproter sample with dynamic data navigator  Oracle & Sqlserver
* LIMS reproter sample with full report engine samples Oracle & Sqlserver
KFramework 1.4.4 -  not released
* Both ORACLE and SQLSERVER dumps provided with demo data 
* Tested with free Oracle10G Express & free Microsoft SQLSERVER 2005 Express
* Materialization support for BigDecimals, used by NB on entity objects
* Fixed recognize american vs euro date format
* Fixed negative numbers not recognized for some locales: accepted (123) or -123
* Simplified - removed SampleConfig site. SampleServer war now hosts everything
* Simplified - shipped example preconfigured for c:\opt /opt

KFramework 1.4.3 march 14th
----------------
* Full sample tree view implementation
* Added api to tree viewer to set root node label
* Changed TreeView api to allow default popup menu to be more customizable
* Fixed top labels of browsers for proper resizing of internal frames
* Removed default text field max size of 50chars, more troubles than convenience
* Magnus locations not hardcoded
* Server magnus changed location
* Fixed user edit missed isadmin combo
* Sample autocalculation labels from browser data

KFramework 1.4.2 feb21
----------------
* Tutorial: Added clarification on the different kinds of comboboxes
* Reference Manual: Delayed to end of feb

KFramework 1.4.2 feb10
----------------
* Added example for bound combo boxes
* Simplified createNew object is now optional
* Fixed custom renderer in table loosing formatting when using custom hook
Manual and Tutorial
* tutorial/ref Added entry on naming conventions for fields/db columns
* tutorial/ref Explained bound combo boxes
 
KFramework 1.4.1 
----------------
* removed obsolete PDC objects in sample client
* Fixed some spanish in some windows
* Fixed concurrent exception on shutdown
* Added new, edit, delete to selector dialog
* Fixed sample client class pk error, no pk strategy defined in PDC
* Added browser hook demo
* Added editor demo data bound label to different object
* Added selector demo
* Added date picker demo

KFramework 1.4.0 
----------------
* Initial public release with sample project and few examples
