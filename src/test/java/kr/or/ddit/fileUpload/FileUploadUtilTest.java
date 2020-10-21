package kr.or.ddit.fileUpload;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileUploadUtilTest {

	@Test
	public void getFilenameTest() {
		/***Given***/
		String contentDisposition = "form-data; name=\"img\"; filename=\"moo.jpg\"";

		/***When***/
		String fileName = FileUploadUtil.getFilename(contentDisposition);

		/***Then***/
		assertEquals("moo.jpg", fileName);

	}

}
