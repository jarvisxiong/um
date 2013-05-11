package com.intelitune.control;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.click.Context;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Decorator;
import net.sf.click.control.Table;
import net.sf.click.util.ClickUtils;
import net.sf.click.util.HtmlStringBuffer;
import net.sf.click.util.PropertyUtils;

import org.apache.commons.lang.math.NumberUtils;

public class MyColumn extends Column implements Serializable {

    private static final long serialVersionUID = 1L;

    // ----------------------------------------------------- Instance Variables

    /** The Column attributes Map. */
    protected Map attributes;

    /**
     * The automatically hyperlink column URL and email address values flag,
     * default value is <tt>false</tt>.
     */
    protected boolean autolink;

    /** The column table data &lt;td&gt; CSS class attribute. */
    protected String dataClass;

    /** The Map of column table data &lt;td&gt; CSS style attributes. */
    protected Map dataStyles;

    /** The column row decorator. */
    protected Decorator decorator;

    /** The escape HTML characters flag. The default value is true. */
    protected boolean escapeHtml = true;

    /** The column message format pattern. */
    protected String format;

    /** The CSS class attribute of the column header. */
    protected String headerClass;

    /** The Map of column table header &lt;th&gt; CSS style attributes. */
    protected Map headerStyles;

    /** The title of the column header. */
    protected String headerTitle;

    /**
     * The maximum column length. If maxLength is greater than 0 and the column
     * data string length is greater than maxLength, the rendered value will be
     * truncated with an eclipse(...).
     * <p/>
     * Autolinked email or URL values will not be constrained.
     * <p/>
     * The default value is 0.
     */
    protected int maxLength;

    /**
     * The optional MessageFormat used to render the column table cell value.
     */
    protected MessageFormat messageFormat;

    /** The property name of the row object to render. */
    protected String name;

    /** The column render id attribute status. The default value is false. */
    protected Boolean renderId;

    /** The method cached for rendering column values. */
    protected transient Map methodCache;

    /** The column sortable status. The default value is false. */
    protected Boolean sortable;

    /** The parent Table. */
    protected Table table;

    /**
     * The property name used to populate the &lt;td&gt; "title" attribute.
     * The default value is null.
     */
    protected String titleProperty;

    /** The column HTML &lt;td&gt; width attribute. */
    protected String width;

    /** The column comparator object, which is used to sort column row values. */
    Comparator comparator;

    // ----------------------------------------------------------- Constructors

    /**
     * Create a table column with the given property name. The table header
     * title will be set as the capitalized property name.
     *
     * @param name the name of the property to render
     */
    public MyColumn(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Null name parameter");
        }
        this.name = name;
    }

    /**
     * Create a table column with the given property name and header title.
     *
     * @param name the name of the property to render
     * @param title the column header title to render
     */
    public MyColumn(String name, String title) {
        if (name == null) {
            throw new IllegalArgumentException("Null name parameter");
        }
        if (title == null) {
            throw new IllegalArgumentException("Null title parameter");
        }
        this.name = name;
        this.headerTitle = title;
    }

    /**
     * Create a Column with no name defined.
     * <p/>
     * <b>Please note</b> the control's name must be defined before it is valid.
     */
    public MyColumn() {
    }

    // ------------------------------------------------------ Public Properties

    /**
     * Return the Column HTML attribute with the given name, or null if the
     * attribute does not exist.
     *
     * @param name the name of column HTML attribute
     * @return the Column HTML attribute
     */
    public String getAttribute(String name) {
        return (String) getAttributes().get(name);
    }

    /**
     * Set the Column with the given HTML attribute name and value. These
     * attributes will be rendered as HTML attributes, for example:
     * <p/>
     * If there is an existing named attribute in the Column it will be replaced
     * with the new value. If the given attribute value is null, any existing
     * attribute will be removed.
     *
     * @param name the name of the column HTML attribute
     * @param value the value of the column HTML attribute
     * @throws IllegalArgumentException if attribute name is null
     */
    public void setAttribute(String name, String value) {
        if (name == null) {
            throw new IllegalArgumentException("Null name parameter");
        }

        if (value != null) {
            getAttributes().put(name, value);
        } else {
            getAttributes().remove(name);
        }
    }

    /**
     * Return the Column attributes Map.
     *
     * @return the column attributes Map.
     */
    public Map getAttributes() {
        if (attributes == null) {
            attributes = new HashMap();
        }
        return attributes;
    }

    /**
     * Return true if the Column has attributes or false otherwise.
     *
     * @return true if the column has attributes on false otherwise
     */
    public boolean hasAttributes() {
        return (attributes != null && !getAttributes().isEmpty());
    }

    /**
     * Return the flag to automatically render HTML hyperlinks for column URL
     * and email addresses values.
     *
     * @return automatically hyperlink column URL and email addresses flag
     */
    public boolean getAutolink() {
        return autolink;
    }

    /**
     * Set the flag to automatically render HTML hyperlinks for column URL
     * and email addresses values.
     *
     * @param autolink the flag to automatically hyperlink column URL and
     * email addresses flag
     */
    public void setAutolink(boolean autolink) {
        this.autolink = autolink;
    }

    /**
     * Return the column comparator object, which is used to sort column row
     * values.
     *
     * @return the column row data sorting comparator object.
     */
    public Comparator getComparator() {
        if (comparator == null) {
            comparator = new ColumnComparator(this);
        }
        return comparator;
    }

    /**
     * Set the column comparator object, which is used to sort column row
     * values.
     *
     * @param comparator the column row data sorting comparator object
     */
    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    /**
     * Return the table data &lt;td&gt; CSS class.
     *
     * @return the table data CSS class
     */
    public String getDataClass() {
        return dataClass;
    }

    /**
     * Set the table data &lt;td&gt; CSS class.
     *
     * @param dataClass the table data CSS class
     */
    public void setDataClass(String dataClass) {
        this.dataClass = dataClass;
    }

    /**
     * Return the table data &lt;td&gt; CSS style.
     *
     * @param name the CSS style name
     * @return the table data CSS style for the given name
     */
    public String getDataStyle(String name) {
        if (hasDataStyles()) {
            return (String) getDataStyles().get(name);

        } else {
            return null;
        }
    }

    /**
     * Set the table data &lt;td&gt; CSS style name and value pair.
     *
     * @param name the CSS style name
     * @param value the CSS style value
     */
    public void setDataStyle(String name, String value) {
        if (name == null) {
            throw new IllegalArgumentException("Null name parameter");
        }

        if (value != null) {
            getDataStyles().put(name, value);
        } else {
            getDataStyles().remove(name);
        }
    }

    /**
     * Return true if table data &lt;td&gt; CSS styles are defined.
     *
     * @return true if table data &lt;td&gt; CSS styles are defined
     */
    public boolean hasDataStyles() {
        return (dataStyles != null && !dataStyles.isEmpty());
    }

    /**
     * Return the Map of table data &lt;td&gt; CSS styles.
     *
     * @return the Map of table data &lt;td&gt; CSS styles
     */
    public Map getDataStyles() {
        if (dataStyles == null) {
            dataStyles = new HashMap();
        }
        return dataStyles;
    }

    /**
     * Return the row column &lt;td&gt; decorator.
     *
     * @return the row column &lt;td&gt; decorator
     */
    public Decorator getDecorator() {
        return decorator;
    }

    /**
     * Set the row column &lt;td&gt; decorator.
     *
     * @param decorator the row column &lt;td&gt; decorator
     */
    public void setDecorator(Decorator decorator) {
        this.decorator = decorator;
    }

    /**
     * Return true if the HTML characters will be escaped when rendering the
     * column data. By default this method returns true.
     *
     * @return true if the HTML characters will be escaped when rendered
     */
    public boolean getEscapeHtml() {
        return escapeHtml;
    }

    /**
     * Set the escape HTML characters when rendering column data flag.
     *
     * @param escape the flag to escape HTML characters
     */
    public void setEscapeHtml(boolean escape) {
        this.escapeHtml = escape;
    }

    /**
     * Return the row column message format pattern.
     *
     * @return the message row column message format pattern
     */
    public String getFormat() {
        return format;
    }

    public void setFormat(String pattern) {
        this.format = pattern;
    }

    /**
     * The maximum column length. If maxLength is greater than 0 and the column
     * data string length is greater than maxLength, the rendered value will be
     * truncated with an eclipse(...).
     *
     * @return the maximum column length, or 0 if not defined
     */
    public int getMaxLength() {
        return maxLength;
    }

    /**
     * Set the maximum column length. If maxLength is greater than 0 and the
     * column data string length is greater than maxLength, the rendered value
     * will be truncated with an eclipse(...).
     *
     * @param value the maximum column length
     */
    public void setMaxLength(int value) {
        maxLength = value;
    }

    /**
     * Return the MessageFormat instance used to format the table cell value.
     *
     * @return the MessageFormat instance used to format the table cell value
     */
    public MessageFormat getMessageFormat() {
        return messageFormat;
    }

    /**
     * Set the MessageFormat instance used to format the table cell value.
     *
     * @param messageFormat the MessageFormat used to format the table cell
     *  value
     */
    public void setMessageFormat(MessageFormat messageFormat) {
        this.messageFormat = messageFormat;
    }

    /**
     * Return the property name.
     *
     * @return the name of the property
     */
    public String getName() {
        return name;
    }

    /**
     * Set the property name.
     *
     * @param name the property name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the table header &lt;th&gt; CSS class.
     *
     * @return the table header CSS class
     */
    public String getHeaderClass() {
        return headerClass;
    }

    /**
     * Set the table header &lt;th&gt; CSS class.
     *
     * @param headerClass the table header CSS class
     */
    public void setHeaderClass(String headerClass) {
        this.headerClass = headerClass;
    }

    /**
     * Return the table header &lt;th&gt; CSS style.
     *
     * @param name the CSS style name
     * @return the table header CSS style value for the given name
     */
    public String getHeaderStyle(String name) {
        if (hasHeaderStyles()) {
            return (String) getHeaderStyles().get(name);

        } else {
            return null;
        }
    }

    /**
     * Set the table header &lt;th&gt; CSS style name and value pair.
     *
     * @param name the CSS style name
     * @param value the CSS style value
     */
    public void setHeaderStyle(String name, String value) {
        if (name == null) {
            throw new IllegalArgumentException("Null name parameter");
        }

        if (value != null) {
            getHeaderStyles().put(name, value);
        } else {
            getHeaderStyles().remove(name);
        }
    }

    /**
     * Return true if table header &lt;th&gt; CSS styles are defined.
     *
     * @return true if table header &lt;th&gt; CSS styles are defined
     */
    public boolean hasHeaderStyles() {
        return (headerStyles != null && !headerStyles.isEmpty());
    }

    /**
     * Return the Map of table header &lt;th&gt; CSS styles.
     *
     * @return the Map of table header &lt;th&gt; CSS styles
     */
    public Map getHeaderStyles() {
        if (headerStyles == null) {
            headerStyles = new HashMap();
        }
        return headerStyles;
    }

    /**
     * Return the table header &lt;th&gt; title.
     * <p/>
     * If the header title value is null, this method will attempt to find a
     * localized message in the parent messages using the key:
     * <blockquote>
     * <tt>getName() + ".headerTitle"</tt>
     * </blockquote>
     * If not found then the message will be looked up in the
     * <tt>/click-control.properties</tt> file using the same key.
     * If a value still cannot be found then the Column name will be converted
     * into a header title using the method: {@link ClickUtils#toLabel(String)}
     * <p/>
     *
     * @return the table header title
     */
    public String getHeaderTitle() {
        if (headerTitle == null) {
            headerTitle = getTable().getMessage(getName() + ".headerTitle");
        }
        if (headerTitle == null) {
            headerTitle = ClickUtils.toLabel(getName());
        }
        return headerTitle;
    }

    /**
     * Set the table header &lt;th&gt; title.
     *
     * @param title the table header title
     */
    public void setHeaderTitle(String title) {
        headerTitle = title;
    }

    /**
     * Return the Table and Column id appended: &nbsp; "<tt>table-column</tt>"
     * <p/>
     * Use the field the "id" attribute value if defined, or the name otherwise.
     *
     * @return HTML element identifier attribute "id" value
     */
    public String getId() {
        if (hasAttributes() && getAttributes().containsKey("id")) {
            return getAttribute("id");

        } else {
            String tableId = (getTable() != null)
                                ? getTable().getId() + "-" : "";

            String id = tableId + getName();

            if (id.indexOf('/') != -1) {
                id = id.replace('/', '_');
            }
            if (id.indexOf(' ') != -1) {
                id = id.replace(' ', '_');
            }

            return id;
        }
    }

    /**
     * Return the column sortable status. If the column sortable status is not
     * defined the value will be inherited from the
     * {@link Table#sortable} property.
     *
     * @return the column sortable status
     */
    public boolean getSortable() {
        if (sortable == null) {
            if (getTable() != null) {
                return getTable().getSortable();
            } else {
                return false;
            }

        } else {
            return sortable.booleanValue();
        }
    }

    /**
     * Set the column render id attribute status.
     *
     * @param value set the column render id attribute status
     */
    public void setRenderId(boolean value) {
        renderId = Boolean.valueOf(value);
    }

    /**
     * Returns the column render id attribute status. If the column render id
     * status is not defined the value will be inherited from the
     * {@link Table#renderId} property.
     *
     * @return the column render id attribute status
     */
    public boolean getRenderId() {
        if (renderId == null) {
            if (getTable() != null) {
                return getTable().getRenderId();
            } else {
                return false;
            }

        } else {
            return renderId.booleanValue();
        }
    }

    /**
     * Set the column sortable status.
     *
     * @param value the column sortable status
     */
    public void setSortable(boolean value) {
        sortable = Boolean.valueOf(value);
    }

    /**
     * Return the parent Table containing the Column.
     *
     * @return the parent Table containing the Column
     */
    public Table getTable() {
        return table;
    }

    /**
     * Set the Column's the parent <tt>Table</tt>.
     *
     * @param table Column's parent <tt>Table</tt>
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * Set the column CSS "text-align" style for the header &lt;th&gt; and
     * data &lt;td&gt; elements.  Valid values include:
     * <tt>[left, right, center]</tt>
     *
     * @param align the CSS "text-align" value: <tt>[left, right, center]</tt>
     */
    public void setTextAlign(String align) {
        if (align != null && "middle".equalsIgnoreCase(align)) {
            String msg =
                "\"middle\" is not a valid CSS \"text-align\" "
                + "value, use \"center\" instead";
            throw new IllegalArgumentException(msg);
        }
        if (!getSortable()) {
            setHeaderStyle("text-align", align);
        }
        setDataStyle("text-align", align);
    }

    /**
     * Return the property name used to populate the &lt;td&gt; "title" attribute.
     *
     * @return the property name used to populate the &lt;td&gt; "title" attribute
     */
    public String getTitleProperty() {
       return titleProperty;
    }

    /**
     * Set the property name used to populate the &lt;td&gt; "title" attribute.
     *
     * @param property the property name used to populate the &lt;td&gt; "title" attribute
     */
    public void setTitleProperty(String property) {
       titleProperty = property;
    }

    /**
     * Set the column CSS "vertical-align" style for the header &lt;th&gt; and
     * data &lt;td&gt; elements. Valid values include:
     * <tt>[baseline | sub | super | top | text-top | middle | bottom |
     * text-bottom | &lt;percentage&gt; | &lt;length&gt; | inherit]</tt>
     *
     * @param align the CSS "vertical-align" value
     */
    public void setVerticalAlign(String align) {
        if (align != null && "center".equalsIgnoreCase(align)) {
            String msg =
                "\"center\" is not a valid CSS \"vertical-align\" "
                + "value, use \"middle\" instead";
            throw new IllegalArgumentException(msg);
        }
        setHeaderStyle("vertical-align", align);
        setDataStyle("vertical-align", align);
    }

    /**
     * Return the column HTML &lt;td&gt; width attribute.
     *
     * @return the column HTML &lt;td&gt; width attribute
     */
    public String getWidth() {
        return width;
    }

    /**
     * Set the column HTML &lt;td&gt; width attribute.
     *
     * @param value the column HTML &lt;td&gt; width attribute
     */
    public void setWidth(String value) {
        width = value;
    }

    // --------------------------------------------------------- Public Methods

    /**
     * Render the column table data &lt;td&gt; element to the given buffer using
     * the passed row object.
     *
     * @param row the row object to render
     * @param buffer the string buffer to render to
     * @param context the request context
     * @param rowIndex the index of the current row within the parent table
     */
    public void renderTableData(Object row, HtmlStringBuffer buffer,
            Context context, int rowIndex) {

        if (getMessageFormat() == null && getFormat() != null) {
            Locale locale = context.getLocale();
            setMessageFormat(new MessageFormat(getFormat(), locale));
        }

        buffer.elementStart("td");
        if (getRenderId()) {
        	//modified by success 2008-11-15
            buffer.appendAttribute("id", getId() + "_" + rowIndex + "_" + getTdValue(row, buffer, context, rowIndex));
        }
        buffer.appendAttribute("class", getDataClass());

        if (getTitleProperty() != null) {
            Object titleValue = getProperty(getTitleProperty(), row);
            buffer.appendAttributeEscaped("title", titleValue);
        }
        if (hasAttributes()) {
            buffer.appendAttributes(getAttributes());
        }
        if (hasDataStyles()) {
            buffer.appendStyleAttributes(getDataStyles());
        }
        buffer.appendAttribute("width", getWidth());
        buffer.closeTag();

        renderTableDataContent(row, buffer, context, rowIndex);

        buffer.elementEnd("td");
    }

    /**
     * Render the column table header &lt;tr&gt; element to the given buffer.
     *
     * @param buffer the string buffer to render to
     * @param context the request context
     */
    public void renderTableHeader(HtmlStringBuffer buffer, Context context) {
        buffer.elementStart("th");

        boolean isSortable = getSortable() && !getTable().getRowList().isEmpty();
        boolean sortedColumn = getName().equals(getTable().getSortedColumn());
        boolean ascending = getTable().isSortedAscending();

        if (isSortable) {
            String classValue =
                (getHeaderClass() != null) ? getHeaderClass() + " " : "";

            if (sortedColumn) {
                classValue += (ascending) ? "ascending" : "descending";
            } else {
                classValue += "sortable";
            }
            buffer.appendAttribute("class", classValue);

        } else {
            buffer.appendAttribute("class", getHeaderClass());
        }

        if (hasHeaderStyles()) {
            buffer.appendStyleAttributes(getHeaderStyles());
        }

        if (hasAttributes()) {
            buffer.appendAttributes(getAttributes());
        }

        buffer.closeTag();

        if (isSortable) {
            ActionLink controlLink = getTable().getControlLink();

            controlLink.setId("control-" + getName());
            controlLink.setParameter(Table.COLUMN, getName());
            controlLink.setParameter(Table.PAGE, String.valueOf(getTable().getPageNumber()));

            if (sortedColumn) {
                controlLink.setParameter(Table.ASCENDING, String.valueOf(ascending));
                controlLink.setParameter(Table.SORT, "true");

            } else {
                controlLink.setParameter(Table.ASCENDING, null);
                controlLink.setParameter(Table.SORT, null);
            }

            // Provide spacer for sorting icon
            controlLink.setLabel(getHeaderTitle() + "&nbsp;&nbsp;&nbsp;");

            controlLink.render(buffer);

        } else {
            if (getEscapeHtml()) {
                buffer.appendEscaped(getHeaderTitle());
            } else {
                buffer.append(getHeaderTitle());
            }
        }

        if(getName().equals("bkt_select")) {
        	buffer.append("<input type=\"checkbox\" name=\"cb_all\" id=\"cb_all\" onclick=\"selectAll()\" />");
        }
        
        buffer.elementEnd("th");
    }

    /**
     * Return the column name property value from the given row object.
     * <p/>
     * If the row object is a <tt>Map</tt> this method will attempt to return
     * the map value for the column name.
     * <p/>
     * The row map lookup will be performed using the property name,
     * if a value is not found the property name in uppercase will be used,
     * if a value is still not found the property name in lowercase will be used.
     * If a map value is still not found then this method will return null.
     * <p/>
     * Object property values can also be specified using an path expression.
     *
     * @param row the row object to obtain the property from
     * @return the named row object property value
     * @throws RuntimeException if an error occurred obtaining the property
     */
    public Object getProperty(Object row) {
        return getProperty(getName(), row);
    }

    /**
     * Return the column property value from the given row object and property name.
     * <p/>
     * If the row object is a <tt>Map</tt> this method will attempt to return
     * the map value for the column.
     * <p/>
     * The row map lookup will be performed using the property name,
     * if a value is not found the property name in uppercase will be used,
     * if a value is still not found the property name in lowercase will be used.
     * If a map value is still not found then this method will return null.
     * <p/>
     * Object property values can also be specified using an path expression.
     *
     * @param name the name of the property
     * @param row the row object to obtain the property from
     * @return the named row object property value
     * @throws RuntimeException if an error occurred obtaining the property
     */
    public Object getProperty(String name, Object row) {
        if (row instanceof Map) {
            Map map = (Map) row;

            Object object = map.get(name);
            if (object != null) {
                return object;
            }

            String upperCaseName = name.toUpperCase();
            object = map.get(upperCaseName);
            if (object != null) {
                return object;
            }

            String lowerCaseName = name.toLowerCase();
            object = map.get(lowerCaseName);
            if (object != null) {
                return object;
            }

            return null;

        } else {
            if (methodCache == null) {
                methodCache = new HashMap();
            }

            return PropertyUtils.getValue(row, name, methodCache);
        }
    }

    // ------------------------------------------------------ Protected Methods

    //added by success for getColumnValue in <td>
    protected String getTdValue(Object row, HtmlStringBuffer buffer,
            Context context, int rowIndex) {
    	String result = "";
    	Object columnValue = getProperty(row);
    	if (columnValue != null) {
    		result = columnValue.toString();
    	}
    	return result;
    }
    
    /**
     * Render the content within the column table data &lt;td&gt; element.
     *
     * @param row the row object to render
     * @param buffer the string buffer to render to
     * @param context the request context
     * @param rowIndex the index of the current row within the parent table
     */
    protected void renderTableDataContent(Object row, HtmlStringBuffer buffer,
            Context context, int rowIndex) {

        if (getDecorator() != null) {
            Object value = getDecorator().render(row, context);
            if (value != null) {
                buffer.append(value);
            }

        } else {
            Object columnValue = getProperty(row);
            if (columnValue != null) {
                if (getAutolink() && renderLink(columnValue, buffer)) {
                    // Has been rendered

                } else if (getMessageFormat() != null) {
                    Object[] args = new Object[] { columnValue };

                    String value = getMessageFormat().format(args);

                    if (getMaxLength() > 0) {
                        value = ClickUtils.limitLength(value, getMaxLength());
                    }

                    if (getEscapeHtml()) {
                        buffer.appendEscaped(value);
                    } else {
                        buffer.append(value);
                    }

                } else {
                    String value = columnValue.toString();

                    if (getMaxLength() > 0) {
                        value = ClickUtils.limitLength(value, getMaxLength());
                    }

                    if (getEscapeHtml()) {
                        buffer.appendEscaped(value);
                    } else {
                        buffer.append(value);
                    }
                }
            }
        }
    }

    /**
     * Render the given table cell value to the buffer as a <tt>mailto:</tt>
     * or <tt>http:</tt> hyperlink, or as an ordinary string if the value is
     * determined not be linkable.
     *
     * @param value the table cell value to render
     * @param buffer the StringBuffer to render to
     * @return a rendered email or web hyperlink if applicable
     */
    protected boolean renderLink(Object value, HtmlStringBuffer buffer) {
        if (value != null) {
            String valueStr = value.toString();

            // If email
            if (valueStr.indexOf('@') != -1
                && !valueStr.startsWith("@")
                && !valueStr.endsWith("@")) {

                buffer.append("<a href=\"mailto:");
                buffer.append(valueStr);
                buffer.append("\">");
                buffer.append(valueStr);
                buffer.append("</a>");
                return true;

            } else if (valueStr.startsWith("http")) {
                int index = valueStr.indexOf("//");
                if (index != -1) {
                    index += 2;
                } else {
                    index = 0;
                }
                buffer.append("<a href=\"");
                buffer.append(valueStr);
                buffer.append("\">");
                buffer.append(valueStr.substring(index));
                buffer.append("</a>");
                return true;

            } else if (valueStr.startsWith("www")) {
                buffer.append("<a href=\"http://");
                buffer.append(valueStr);
                buffer.append("\">");
                buffer.append(valueStr);
                buffer.append("</a>");
                return true;
            }
        }
        return false;
    }

    // ---------------------------------------------------------- Inner Classes

    /**
     * Provides a table Column comparator for sorting table rows.
     *
     * @see net.sf.click.control.Column
     * @see net.sf.click.control.Table
     *
     * @author Malcolm Edgar
     */
    static class ColumnComparator implements Comparator {

        /** The sort ascending flag. */
        protected int ascendingSort;

        /** The column to sort on. */
        protected final MyColumn column;

        /**
         * Create a new Column based row comparator.
         *
         * @param column the colum to sort on
         */
        public ColumnComparator(MyColumn column) {
            this.column = column;
        }

        /**
         * @see java.util.Comparator#compare(Object, Object)
         *
         * @param row1 the first row to compare
         * @param row2 the second row to compare
         * @return the comparison result
         */
        public int compare(Object row1, Object row2) {

            this.ascendingSort = column.getTable().isSortedAscending() ? 1 : -1;

            Object value1 = column.getProperty(row1);
            Object value2 = column.getProperty(row2);

            if (value1 instanceof Comparable && value2 instanceof Comparable) {

                if (value1 instanceof String || value2 instanceof String) {
                    return stringCompare(value1, value2)  * ascendingSort;

                } else {

                    return ((Comparable) value1).compareTo(value2) * ascendingSort;
                }

            } else if (value1 != null && value2 != null) {

                return value1.toString().compareToIgnoreCase(value2.toString())
                    * ascendingSort;

            } else if (value1 != null && value2 == null) {

                return +1 * ascendingSort;

            } else if (value1 == null && value2 != null) {

                return -1 * ascendingSort;

            } else {
                return 0;
            }
        }

        // ------------------------------------------------------ Protected Methods

        /**
         * Perform a comparison on the given string values.
         *
         * @param value1 the first value to compare
         * @param value2 the second value to compare
         * @return the string comparison result
         */
        protected int stringCompare(Object value1, Object value2) {
            String string1 = value1.toString().trim();
            String string2 = value2.toString().trim();

            StringTokenizer st1 = new StringTokenizer(string1);
            StringTokenizer st2 = new StringTokenizer(string2);

            String token1 = null;
            String token2 = null;

            while (st1.hasMoreTokens()) {
                token1 = st1.nextToken();

                if (st2.hasMoreTokens()) {
                    token2 = st2.nextToken();

                    int comp = 0;

                    if (useNumericSort(token1, token2)) {
                        comp = numericCompare(token1, token2);

                    } else {
                        comp = token1.compareToIgnoreCase(token2);
                    }

                    if (comp != 0) {
                        return comp;
                    }

                } else {
                    return -1;
                }
            }

            return 0;
        }

        /**
         * Return true if a numeric sort should be used.
         *
         * @param value1 the first value to test
         * @param value2 the second value to test
         * @return true if a numeric sort should be used
         */
        protected boolean useNumericSort(String value1, String value2) {
            return NumberUtils.isNumber(value1) && NumberUtils.isNumber(value2);
        }

        /**
         * Perform a numeric compare on the given string values.
         *
         * @param string1 the first string value to compare
         * @param string2 the second string value to compare
         * @return the numeric comparison result
         */
        protected int numericCompare(String string1, String string2) {
            if (string1.length() > 0 && string2.length() > 0) {
                Double double1 = Double.valueOf(string1);
                Double double2 = Double.valueOf(string2);

                return double1.compareTo(double2);

            } else if (string1.length() > 0) {
                return 1;

            } else if (string2.length() > 0) {
                return -1;

            } else {
                return 0;
            }
        }

    }

}
