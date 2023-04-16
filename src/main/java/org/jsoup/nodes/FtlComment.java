package org.jsoup.nodes;

import java.io.IOException;

public class FtlComment extends LeafNode {
    /**
     Create a new comment node.
     @param data The contents of the comment
     */
    public FtlComment(String data) {
        value = data;
    }

    public String nodeName() {
        return "#ftlcomment";
    }

    /**
     Get the contents of the comment.
     @return comment content
     */
    public String getData() {
        return coreValue();
    }

    public FtlComment setData(String data) {
        coreValue(data);
        return this;
    }

    @Override
    void outerHtmlHead(Appendable accum, int depth, Document.OutputSettings out) throws IOException {
        if (out.prettyPrint() && ((isEffectivelyFirst() && parentNode instanceof Element && ((Element) parentNode).tag().formatAsBlock()) || (out.outline() )))
            indent(accum, depth, out);
        accum
                .append("<#--")
                .append(getData())
                .append("-->");
    }

    @Override
    void outerHtmlTail(Appendable accum, int depth, Document.OutputSettings out) {}

    @Override
    public String toString() {
        return outerHtml();
    }

    @Override
    public FtlComment clone() {
        return (FtlComment) super.clone();
    }
}
