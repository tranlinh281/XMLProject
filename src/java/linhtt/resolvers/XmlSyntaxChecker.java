/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhtt.resolvers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import static linhtt.resolvers.SyntaxState.*;

/**
 *
 * @author macos
 */
public class XmlSyntaxChecker implements Serializable {
    private String state;
    private StringBuilder writer;
    private StringBuilder content;
    private StringBuilder openTagName;
    private StringBuilder closeTagName;
    private StringBuilder attributeName;
    private StringBuilder attributeValue;
    private Map<String, String> attributes;
    private Stack<String> stack;
    private char quote;
    private boolean isEmptyTag;
    private boolean isOpenTag;
    private boolean isCloseTag;
    
    public XmlSyntaxChecker() {
        this.state = CONTENT;
        this.writer = new StringBuilder();
        this.content = new StringBuilder();
        this.openTagName = new StringBuilder();
        this.closeTagName = new StringBuilder();
        this.attributeName = new StringBuilder();
        this.attributeValue = new StringBuilder();
        this.attributes = new HashMap<>();
        this.stack = new Stack<>();
        this.isEmptyTag = false;
        this.isOpenTag = false;
        this.isCloseTag = false;
    }

    
    public String check(String source) {
        char[] reader = source.toCharArray();

        for (int i = 0; i < reader.length; ++i) {
            char character = reader[i];

            switch (state) {
                case CONTENT:
                    handleStateContent(character);
                    break;

                case OPEN_BRACKET:
                    handleStateOpenBracket(character);
                    break;

                case OPEN_TAG_NAME:
                    handleStateOpenTagName(character);
                    break;

                case TAG_INNER:
                    handleStateTagInner(character);
                    break;

                case ATTR_NAME:
                    handleStateAttributeName(character);
                    break;

                case EQUAL_WAIT:
                    handleStateEqualWait(character);
                    break;

                case EQUAL:
                    handleStateEqual(character);
                    break;

                case ATTR_VALUE_Q:
                    handleStateAttributeValueWithQuote(character);
                    break;

                case ATTR_VALUE_NQ:
                    handleStateAttributeValueWithoutQuote(character);
                    break;

                case EMPTY_SLASH:
                    handleStateEmptySlash(character);
                    break;

                case CLOSE_TAG_SLASH:
                    handleStateCloseTagSlash(character);
                    break;

                case CLOSE_TAG_NAME:
                    handleStateCloseTagName(character);
                    break;

                case WAIT_END_TAG_CLOSE:
                    handleStateWaitEndTagClose(character);
                    break;

                case CLOSE_BRACKET:
                    handleStateCloseBracket(character);
                    break;
            }
        }

        if (CONTENT.equals(state)) {
            writer.append(content.toString().trim().replace("&", "&amp;"));
        }

        while (!stack.isEmpty()){
            writer.append("<")
                    .append("/")
                    .append(stack.pop())
                    .append(">");
        }

        return writer.toString();
    }
    
    private String convert(Map<String, String> attributes) {
        if (attributes.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            String value = attribute.getValue()
                    .replace("&", "&amp;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&apos;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;");

            builder.append(attribute.getKey())
                    .append("=")
                    .append("\"")
                    .append(value)
                    .append("\"")
                    .append(" ");
        }

        String result = builder.toString().trim();
        if (!result.equals("")) {
            result = " " + result;
        }

        return result;
    }
    
    private void handleStateCloseBracket(char character) {
        if (isOpenTag) {
            String strOpenTagName = openTagName.toString().toLowerCase();

            if (INLINE_TAGS.contains(strOpenTagName)) {
                isEmptyTag = true;
            }

            String tmp = isEmptyTag ? "/" : "";
            writer.append("<")
                    .append(strOpenTagName)
                    .append(convertAttribute(attributes))
                    .append(tmp)
                    .append(">");
            attributes.clear();
            if (!isEmptyTag) {
                stack.push(strOpenTagName);
            }
        } else if (isCloseTag) {
            String strCloseTagName = closeTagName.toString().toLowerCase();
            if (!stack.isEmpty() && stack.contains(strCloseTagName)) {
                while(!stack.isEmpty() && !stack.peek().equals(strCloseTagName)){
                    writer.append("<")
                            .append("/")
                            .append(stack.pop())
                            .append(">");
                }
                if (!stack.isEmpty() && stack.peek().equals(strCloseTagName)){
                    writer.append("<")
                            .append("/")
                            .append(stack.pop())
                            .append(">");
                }
            }
        }

        if (character == '<') {
            state = OPEN_BRACKET;
        } else {
            state = CONTENT;
            content.setLength(0);
            content.append(character);
        }
    }

    private String convertAttribute(Map<String, String> attributes) {
        if (attributes.isEmpty()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            String name = attribute.getKey();
            String value = attribute.getValue()
                    .replace("&", "&amp;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&apos;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;");

            builder.append(name)
                    .append("=")
                    .append("\"")
                    .append(value)
                    .append("\"")
                    .append(" ");
        }

        String result = builder.toString().trim();
        if (!result.equals("")) {
            result = " " + result;
        }

        return result;
    }

    private void handleStateWaitEndTagClose(char character) {
        if (Character.isSpaceChar(character)) {
            /* do nothing */
        } else if (character == '>') {
            state = CLOSE_BRACKET;
        }
    }

    private void handleStateCloseTagName(char character) {
        if (Character.isLetterOrDigit(character) || character == ':' || character == '_') {
            closeTagName.append(character);
        } else if (Character.isSpaceChar(character)) {
            state = WAIT_END_TAG_CLOSE;
        } else if (character == '>') {
            state = CLOSE_BRACKET;
        }
    }

    private void handleStateCloseTagSlash(char character) {
        if (Character.isLetter(character) || character == ':' || character == '_') {
            state = CLOSE_TAG_NAME;
            closeTagName.setLength(0);
            closeTagName.append(character);
        }
    }

    private void handleStateEmptySlash(char character) {
        if (character == '>') {
            state = CLOSE_BRACKET;
            isEmptyTag = true;
        }
    }

    private void handleStateAttributeValueWithoutQuote(char character) {
        if (!Character.isSpaceChar(character) && character != '>') {
            attributeValue.append(character);
        } else if (Character.isSpaceChar(character)) {
            state = TAG_INNER;
            attributes.put(attributeName.toString(), attributeValue.toString());
        } else if (character == '>') {
            state = CLOSE_BRACKET;
            attributes.put(attributeName.toString(), attributeValue.toString());
        }
    }

    private void handleStateAttributeValueWithQuote(char character) {
        if (character != quote) {
            attributeValue.append(character);
        } else if (character == quote) {
            state = TAG_INNER;
            attributes.put(attributeName.toString(), attributeValue.toString());
        }
    }

    private void handleStateEqual(char character) {
        if (Character.isSpaceChar(character)) {
            /* do nothing */
        } else if (character == '"' || character == '\'') {
            state = ATTR_VALUE_Q;
            quote = character;
            attributeValue.setLength(0);
        } else if (!Character.isSpaceChar(character) && character != '>') {
            state = ATTR_VALUE_NQ;
            attributeValue.setLength(0);
            attributeValue.append(character);
        }
    }

    private void handleStateEqualWait(char character) {
        if (character == '=') {
            state = EQUAL;
        } else if (Character.isSpaceChar(character)) {
            /* do nothing */
        } else if (Character.isLetter(character) || character == ':' || character == '_') {
            state = ATTR_NAME;
            attributes.put(attributeName.toString(), "true");

            attributeName.setLength(0);
            attributeName.append(character);
        }
    }

    private void handleStateAttributeName(char character) {
        if (Character.isLetterOrDigit(character) || character == ':' || character == '_') {
            attributeName.append(character);
        } else if (Character.isSpaceChar(character)) {
            state = EQUAL_WAIT;
        } else if (character == '=') {
            state = EQUAL;
        } else if (character == '/') {
            state = EMPTY_SLASH;
            attributes.put(attributeName.toString(), "true");
        } else if (character == '>') {
            state = CLOSE_BRACKET;
            attributes.put(attributeName.toString(), "true");
        }

    }

    private void handleStateTagInner(char character) {
        if (Character.isSpaceChar(character)) {
            /* do nothing */
        } else if (Character.isLetter(character) || character == ':' || character == '_') {
            state = ATTR_NAME;
            attributeName.setLength(0);
            attributeName.append(character);
        } else if (character == '>') {
            state = CLOSE_BRACKET;
        } else if (character == '/') {
            state = EMPTY_SLASH;
        }
    }

    private void handleStateOpenTagName(char character) {
        if (character == '/') {
            state = EMPTY_SLASH;
        } else if (character == '>') {
            state = CLOSE_BRACKET;
        } else if (Character.isSpaceChar(character)) {
            state = TAG_INNER;
            attributes.clear();
        } else if (Character.isLetterOrDigit(character) || character == ':' || character == '_') {
            openTagName.append(character);
        }
    }

    private void handleStateOpenBracket(char character) {
        if (Character.isLetter(character) || character == ':' || character == '_') {
            state = OPEN_TAG_NAME;
            isOpenTag = true;
            isCloseTag = false;
            isEmptyTag = false;
            openTagName.setLength(0);
            openTagName.append(character);
        } else if (character == '/') {
            state = CLOSE_TAG_SLASH;
            isOpenTag = false;
            isCloseTag = true;
            isEmptyTag = false;
        }
    }

    private void handleStateContent(char character) {
        if (character == '<') {
            state = OPEN_BRACKET;
            writer.append(content.toString().trim().replace("&", "&amp;"));
        } else {
            content.append(character);
        }
    }
}
