# jsoup-ftl - about this fork

The default FreeMarker template notations (`<#...>`, `<@...>`, `${...}`) and their deviations from normal HTML make them 
problematic for processing through jsoup, as jsoup considers the directives as bogus tags and cleans up expressions 
within tags and attributes to follow HTML naming rules (as in stripping the ${...} bit and cleaning up what's inside).

FreeMarker supports an alternate notation via `[#...]` which would solve some of these problems, but that still prevents
a proper traversal through the tree as you would expect, since all directives would just become normal text nodes.

This fork patches and extends jsoup to provide rudimentary support for FreeMarker templates, allowing you to parse 
and output an .ftl file in the same manner as you would an HTML file, without corrupting the FreeMarker-specific 
contents of it.

jsoup is very goal-oriented at the cost of extensibility, so I do not believe these changes are suitable to be merged 
upstream, as some of the patches tie directly into the reader and writer and can not be toggled off. For that reason 
this fork should be used *only* for processing FreeMarker templates, as it will always treat any input as if it was FTL. 
There is also no automated tests and this was thrown together in a day, so I would not consider it production-ready. 

# jsoup: Java HTML Parser

**jsoup** is a Java library for working with real-world HTML. It provides a very convenient API for fetching URLs and extracting and manipulating data, using the best of HTML5 DOM methods and CSS selectors.

**jsoup** implements the [WHATWG HTML5](https://html.spec.whatwg.org/multipage/) specification, and parses HTML to the same DOM as modern browsers do.

* scrape and [parse](https://jsoup.org/cookbook/input/parse-document-from-string) HTML from a URL, file, or string
* find and [extract data](https://jsoup.org/cookbook/extracting-data/selector-syntax), using DOM traversal or CSS selectors
* manipulate the [HTML elements](https://jsoup.org/cookbook/modifying-data/set-html), attributes, and text
* [clean](https://jsoup.org/cookbook/cleaning-html/safelist-sanitizer) user-submitted content against a safe-list, to prevent XSS attacks
* output tidy HTML

jsoup is designed to deal with all varieties of HTML found in the wild; from pristine and validating, to invalid tag-soup; jsoup will create a sensible parse tree.

See [**jsoup.org**](https://jsoup.org/) for downloads and the full [API documentation](https://jsoup.org/apidocs/).

[![Build Status](https://github.com/jhy/jsoup/workflows/Build/badge.svg)](https://github.com/jhy/jsoup/actions?query=workflow%3ABuild)

## Example
Fetch the [Wikipedia](https://en.wikipedia.org/wiki/Main_Page) homepage, parse it to a [DOM](https://developer.mozilla.org/en-US/docs/Web/API/Document_Object_Model/Introduction), and select the headlines from the *In the News* section into a list of [Elements](https://jsoup.org/apidocs/org/jsoup/select/Elements.html):

```java
Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
log(doc.title());
Elements newsHeadlines = doc.select("#mp-itn b a");
for (Element headline : newsHeadlines) {
  log("%s\n\t%s", 
    headline.attr("title"), headline.absUrl("href"));
}
```
[Online sample](https://try.jsoup.org/~LGB7rk_atM2roavV0d-czMt3J_g), [full source](https://github.com/jhy/jsoup/blob/master/src/main/java/org/jsoup/examples/Wikipedia.java).

## Open source
jsoup is an open source project distributed under the liberal [MIT license](https://jsoup.org/license). The source code is available on [GitHub](https://github.com/jhy/jsoup).

## Getting started
1. [Download](https://jsoup.org/download) the latest jsoup jar (or add it to your Maven/Gradle build)
2. Read the [cookbook](https://jsoup.org/cookbook/)
3. Enjoy!

### Android support
When used in Android projects, [core library desugaring](https://developer.android.com/studio/write/java8-support#library-desugaring) should be enabled to support Java 8+ features.

## Development and support
If you have any questions on how to use jsoup, or have ideas for future development, please get in touch via the [mailing list](https://jsoup.org/discussion).

If you find any issues, please file a [bug](https://jsoup.org/bugs) after checking for duplicates.

The [colophon](https://jsoup.org/colophon) talks about the history of and tools used to build jsoup.

## Status
jsoup is in general, stable release.
