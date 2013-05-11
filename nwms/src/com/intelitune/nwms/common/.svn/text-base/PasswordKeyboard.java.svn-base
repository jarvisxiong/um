package com.intelitune.nwms.common;

import javax.servlet.ServletContext;

import net.sf.click.control.TextField;
import net.sf.click.util.ClickUtils;

public class PasswordKeyboard extends TextField {

    private static final long serialVersionUID = 1L;
    private static boolean passwordOrNot;

     /** The HTML import statements. */
    public static final String HTML_IMPORTS =
          "<script type=\"text/javascript\">var keyboard_png_path=\"{0}/click/keyboard{1}.png\";</script>\n"
        + "<script type=\"text/javascript\" src=\"{0}/click/keyboard{1}.js\" charset=\"UTF-8\"></script>\n"
        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"{0}/click/keyboard{1}.css\"/>\n";

    // ----------------------------------------------------------- Constructors

    /**
     * Constructs a new VirtualKeyboard Field object with no name defined.
     * <p/>
     * <b>Please note</b> the control's name must be defined before it is valid.
     */
    public PasswordKeyboard(boolean PasswordOrNot) {
        super();
        addStyleClass("keyboardInput");
        passwordOrNot = PasswordOrNot;
    }

    /**
     * Constructs the VirtualKeyboard Field with the given name.
     *
     * @param name the name of the VirtualKeyboard field
     */
    public PasswordKeyboard(String name, boolean PasswordOrNot) {
        super(name);
        addStyleClass("keyboardInput");
        passwordOrNot = PasswordOrNot;
    }

    /**
     * Constructs the VirtualKeyboard Field with the given name and label.
     *
     * @param name the name of the VirtualKeyboard field
     * @param label the label of the VirtualKeyboard field
     */
    public PasswordKeyboard(String name, String label, boolean PasswordOrNot) {
        super(name, label);
        addStyleClass("keyboardInput");
        passwordOrNot = PasswordOrNot;
    }

    // --------------------------------------------------------- Public Methods

    /**
     * Returns the HTML head import statements for the JavaScript
     * (<tt>click/keyboard.js</tt>) and CSS (<tt>click/keyboard.css</tt>) files.
     *
     * @see net.sf.click.Control#getHtmlImports()
     *
     * @return the HTML head import statements for the JavaScript and CSS files
     */
    public String getHtmlImports() {
        return ClickUtils.createHtmlImport(HTML_IMPORTS, getContext());
    }

    /**
     * Deploy the static resource files in the VirtualKeyboard control.
     *
     * @see net.sf.click.control.Field#onDeploy(javax.servlet.ServletContext)
     *
     * @param servletContext the ServletContext
     */
    public void onDeploy(ServletContext servletContext) {
        ClickUtils.deployFiles(servletContext,
                new String[]{
                        "/net/sf/click/extras/control/keyboard.css",
                        "/net/sf/click/extras/control/keyboard.js",
                        "/net/sf/click/extras/control/keyboard.png"},
                "click");
    }
    
    /**
     * Return the input type: '<tt>password</tt>'.
     *
     * @return the input type: '<tt>password</tt>'
     */
    public String getType() {
    	if(passwordOrNot == true) {
    		return "password";
    	}
    	else {
    		return null;
    	}
    }
}
