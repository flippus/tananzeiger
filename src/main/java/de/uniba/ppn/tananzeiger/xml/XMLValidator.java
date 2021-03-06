package de.uniba.ppn.tananzeiger.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Philipp Neugebauer
 */
public class XMLValidator {

	private Validator validator;

	{
		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = null;
		try {
			schema = schemaFactory
					.newSchema(getStreamSourceOfSchema("tanspeicher.xsd"));
		} catch (SAXException e) {
			e.printStackTrace();
		}
		validator = schema.newValidator();
	}

	public void validateXmlFile(File file) throws SAXException, IOException {
		validator.validate(new StreamSource(file));
	}

	private StreamSource getStreamSourceOfSchema(String schemaName) {
		return new StreamSource(this.getClass().getResourceAsStream(
				"/" + schemaName));
	}
}
