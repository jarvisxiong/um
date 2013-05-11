package com.intelitune.control;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import net.sf.click.control.Table;
import net.sf.click.util.ClickUtils;

public class TableEx extends Table {
	
	private String path;
	
    private String TABLE_IMPORTS_LIGHTT =
    	"<link type=\"text/css\" rel=\"stylesheet\" href=\"{0}/click/table{1}.css\"/>\n"
        + "<style type=\"text/css\"> th.sortable a '{'background: url({0}/click/column-sortable-light{1}.gif) center right no-repeat;'}' th.ascending a '{'background: url({0}/click/column-ascending-light{1}.gif) center right no-repeat;'}' th.descending a '{'background: url({0}/click/column-descending-light{1}.gif) center right no-repeat;'}' </style>\n";
       
    private String TABLE_IMPORTS_DARKK =
    	"<link type=\"text/css\" rel=\"stylesheet\" href=\"{0}/click/table{1}.css\"/>\n"
        + "<style type=\"text/css\"> th.sortable a '{'background: url({0}/click/column-sortable-dark{1}.gif) center right no-repeat;'}' th.ascending a '{'background: url({0}/click/column-ascending-dark{1}.gif) center right no-repeat;'}' th.descending a '{'background: url({0}/click/column-descending-dark{1}.gif) center right no-repeat;'}' </style>\n";
    
    private static final Set DARK_STYLES;
    
    private String style;

    static {
        DARK_STYLES = new HashSet();
        DARK_STYLES.add("isi");
        DARK_STYLES.add("nocol");
        DARK_STYLES.add("report");
    }
    public TableEx()
    {
    	super.hoverRows=true;
    	
    	this.setPath("../templates/wms/css/table.css");
		this.setStyle("hmgWhite");
    }
    public TableEx(String s)
    {
    	super.hoverRows=true;
    	super.setName(s);
    	this.setPath("../templates/wms/css/table.css");
		this.setStyle("hmgWhite");
    }
    public String getHtmlImports() {

        // Flag indicating which import style to return
        boolean useDarkStyle = false;

        if (hasAttribute("class")) {

            String styleClasses = getAttribute("class");

            StringTokenizer tokens = new StringTokenizer(styleClasses, " ");
            while (tokens.hasMoreTokens()) {
                String token = tokens.nextToken();
                if (DARK_STYLES.contains(token)) {
                    useDarkStyle = true;
                    break;
                }
            }
        }

        if (useDarkStyle) {
            return ClickUtils.createHtmlImport(TABLE_IMPORTS_DARKK,
                getContext());

        } else {
            return ClickUtils.createHtmlImport(TABLE_IMPORTS_LIGHTT,
                getContext());
        }
    }

	public String getPath() {
		
		return path;
	}

	public void setPath(String path) {		
		this.path = path;
		
		TABLE_IMPORTS_LIGHTT = "<link type=\"text/css\" rel=\"stylesheet\" href=\""+path+"\"/>\n"
        + "<style type=\"text/css\"> th.sortable a '{'background: url({0}/click/column-sortable-light{1}.gif) center right no-repeat;'}' th.ascending a '{'background: url({0}/click/column-ascending-light{1}.gif) center right no-repeat;'}' th.descending a '{'background: url({0}/click/column-descending-light{1}.gif) center right no-repeat;'}' </style>\n";
		
		TABLE_IMPORTS_DARKK = "<link type=\"text/css\" rel=\"stylesheet\" href=\""+path+"\"/>\n"
	        + "<style type=\"text/css\"> th.sortable a '{'background: url({0}/click/column-sortable-dark{1}.gif) center right no-repeat;'}' th.ascending a '{'background: url({0}/click/column-ascending-dark{1}.gif) center right no-repeat;'}' th.descending a '{'background: url({0}/click/column-descending-dark{1}.gif) center right no-repeat;'}' </style>\n";
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		
		this.style = style;
		this.setClass(style);
	}
}
