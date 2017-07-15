package jutil;


// updated to use generics

import java.util.regex.*;
import java.util.*;

/**
 * Lexical analysis utilities.
 */
public class Lex {
	/**
	 * This regular expression is matched by any sequence of digits
	 * possibly containing a decimal point.
	 */
	public static final String NUMBER = "\\d*\\.?\\d+";
	/**
	 * This regular expression is matched by common arithmetic
	 * operators.
	 *
	 *<pre> +, *, -, %, =, <, > </pre>
	 */
	public static final String OPERATOR = 
		"\\+|\\*|-|/|=|<|>|%";
	/**
	 * This regular expression is matched by any alphanumeric
	 * sequence beginning with a letter.
	 */
	public static final String IDENTIFIER = "[a-zA-Z][a-zA-Z0-9]*";
   	/**
   	 * This regular expression is matched by parenthesis, curly
   	 * braces, braces, and the semicolon.
   	 */
   	public static final String PUNCTUATION =
   		";|\\(|\\.|\\)|\\]|\\[|\\}|\\{";

	/**
	 * A utility for joining two patterns.
	 * @param pattern1   a regular expression
	 * @param pattern2   another regular expression
	 * @return  pattern1 | pattern2
	 */
	public static String join(String pattern1, String pattern2) {
		return pattern1 + "|" + pattern2;
	}
    /**
     * Breaks a string into a list of tokens. Whitespaces are ignored.
     * @param regExp   a regular expression
     * @param phrase   any string
     * @return  A list of tokens appearing in phrase.
     * @throws  AppError if any illegal tokens are found.
     */
	public static List<String> scan(String regExp, String phrase) throws AppError {
		List<String> tokens = new ArrayList<String>();
		Pattern p = Pattern.compile(regExp, Pattern.COMMENTS);
		Matcher m = p.matcher(phrase);
		int lastIndex = 0;
		while(m.find()) {
			if (lastIndex < m.start()) {
				String irritant = phrase.substring(lastIndex, m.start());
				if (!Pattern.matches("\\s+", irritant)) {
					throw new AppError("Illegal tokens: " + irritant);
				}
			}
			lastIndex = m.end();
			String token = m.group();
			tokens.add(token);
		}

		if (lastIndex < phrase.length()) {
			String irritant = phrase.substring(lastIndex, phrase.length());
			if (!Pattern.matches("\\s+", irritant)) {
				throw new AppError("Illegal tokens: " + irritant);
			}
		}
		return tokens;
	}
}
