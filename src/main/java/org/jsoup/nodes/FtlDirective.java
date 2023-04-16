package org.jsoup.nodes;

import org.jsoup.parser.Tag;

import javax.annotation.Nullable;
import java.io.IOException;

public class FtlDirective extends Element {
    @Nullable private String expression;

    public FtlDirective(Tag tag, @Nullable String expression) {
        super(tag, null);
        this.expression = expression;
    }

    public FtlDirective(String directive, @Nullable String expression) {
        super(directive);
        this.expression = expression;
    }

    @Nullable
    public String expression() {
        return expression;
    }

    public FtlDirective expression(String expression) {
        this.expression = expression;
        return this;
    }

    @Override
    void outerHtmlHead(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        if (shouldIndent(out)) {
            if (accum instanceof StringBuilder) {
                if (((StringBuilder) accum).length() > 0)
                    indent(accum, depth, out);
            } else {
                indent(accum, depth, out);
            }
        }
        accum.append("<#").append(tagName());
        if (expression != null && !expression.trim().isEmpty()) accum.append(' ').append(expression);

        if (childNodes.isEmpty() && tag().isSelfClosing()) {
            if (out.syntax() == Document.OutputSettings.Syntax.html && tag().isEmpty())
                accum.append('>');
            else
                accum.append(" />"); // <img> in html, <img /> in xml
        }
        else
            accum.append('>');
    }

    @Override
    void outerHtmlTail(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        if (!(childNodes.isEmpty() && tag().isSelfClosing())) {
            if (out.prettyPrint() && (!childNodes.isEmpty() && (
                    (tag().formatAsBlock() && !preserveWhitespace(parentNode)) ||
                            (out.outline() && (childNodes.size()>1 || (childNodes.size()==1 && (childNodes.get(0) instanceof Element))))
            )))
                indent(accum, depth, out);
            accum.append("</#").append(tagName()).append('>');
        }
    }

    @Override
    public FtlDirective clone() {
        return (FtlDirective) super.clone();
    }
}
