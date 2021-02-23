Overview


Your objective is to create a GUI that accesses chat log files.
This lab is a prerequisite to the lab "Inheritance and interfaces".

Requirements

The collections and IO classes from the previous lab are to be used. If you have designed the classes successfully no changes needs to be made to these classes.

GUI design

Interface prototype

The File menu contains the option Exit.
The checkmark on the "Private chat" menu item indicates that you can select a friend in the friends list and the corresponding chat is show. (You may use radiobuttons instead of checkboxes. If so, the application should default to one of the two chat modes.)
The components of the main window should follow resizes smoothly.
The horizontal size of the friends list should be determined by the longest nickname.
The size of the chat text component resizes with the main window.

Layout managers

There are a few ways to accomplish this layout. The by far easiest way is to have the main window layout calculated by a BorderLayout.
A BorderLayout requires that only one component is added to each location. This is however not a problem since several components can be added to a panel
and the panel is added to the BorderLayout location. The panels need to have a layout assigned to them also.
A proper choice of layout manager will make resizing of the main behave in a smooth and natural fashion.

Event handling

When the public chat menu option is selected, clicking on the names in the friends list has no effect.
When the private chat menu option is selected, clicking on a name shows the corresponding chat text, or "Not found".
The exit option in the File menu terminates the application.

Class design

It is a good idea to create a separate class for both the chat text panel as well as the friends list panel.
Both of these classes should inherit JPanel or a similar class.
Note that, to be able to listen for events concerning the selection of friends,
you need to add methods to register the corresponding listeners in the friends list class. Provide a method similar to existing event producers.
i.e.  void addListSelectionListener(ListSelectionListener listener).
