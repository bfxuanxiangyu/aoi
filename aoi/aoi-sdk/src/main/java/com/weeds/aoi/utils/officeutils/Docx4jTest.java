package com.weeds.aoi.utils.officeutils;

import java.io.File;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;

public class Docx4jTest {
	
	
	public static void main(String[] args) {
		String docFileName = "D:\\opt\\word\\test.doc";
		createNewDoc(docFileName);
	}
	
	private static void createNewDoc(String path){
		try {
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
			wordMLPackage.getMainDocumentPart().addParagraphOfText("Hello Word!");
			ObjectFactory factory = new ObjectFactory();  
	        P paragraph = factory.createP();  
	        R run = factory.createR();
	        wordMLPackage.getMainDocumentPart().addObject(paragraph);
			wordMLPackage.save(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
