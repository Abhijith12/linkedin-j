
package com.google.code.linkedinapi.schema.dom;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.code.linkedinapi.schema.Update;
import com.google.code.linkedinapi.schema.Updates;

public class UpdatesImpl
    extends BaseSchemaEntity
    implements Updates
{

    protected List<Update> update;
    protected Long total;

    public List<Update> getUpdate() {
        if (update == null) {
            update = new ArrayList<Update>();
        }
        return this.update;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long value) {
        this.total = value;
    }

	@Override
	public void init(Element element) {
		setTotal(Long.valueOf(element.getAttribute("total")));
		List<Element> updateElems = DomUtils.getChildElementsByLocalName(element, "update");
		for (Element updateElem : updateElems) {
			UpdateImpl updateImpl = new UpdateImpl();
			updateImpl.init(updateElem);
			getUpdate().add(updateImpl);
		}
	}

	@Override
	public Element toXml(Document document) {
		Element element = document.createElement("updates");
		element.setAttribute("total", String.valueOf(getTotal()));
		for (Update update : getUpdate()) {
			element.appendChild(((UpdateImpl) update).toXml(document));
		}
		return element;
	}
}