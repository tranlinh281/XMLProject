/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtt.resolvers;

import java.io.Serializable;

/**
 *
 * @author macos
 */
public class HtmlTag implements Serializable {
    private String tagName;
    private int lineNumber;
    private int columnNumber;

    public HtmlTag(String tagName, int lineNumber, int columnNumber) {
        this.tagName = tagName;
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    public String getTagName() {
        return tagName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    @Override
    public String toString() {
        return "HtmlTagDTO{" +
                "tagName='" + tagName + '\'' +
                ", lineNumber=" + lineNumber +
                ", columnNumber=" + columnNumber +
                '}';
    }
}
