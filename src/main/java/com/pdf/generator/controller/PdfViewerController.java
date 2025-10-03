package com.pdf.generator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pdf.generator.service.PdfService;

@Controller
public class PdfViewerController
{

	@Autowired
	private PdfService pdfService;

	private static Logger log = LoggerFactory.getLogger(PdfViewerController.class);

//	@PostMapping("/pdfViewer")
//	public String pdfViewer(@RequestParam String first_name, @RequestParam String mobile_num,
//			@RequestParam String emailid, RedirectAttributes redirectAttributes)
//	{
//		log.info("inside controller");
//		pdfService.pdfGenerator(first_name, emailid, emailid);
//
//		redirectAttributes.addFlashAttribute("msg", "Okay");
//
//		return "redirect:/hello";
//	}

//	@PostMapping("/pdfViewer")
//	public ResponseEntity<InputStreamResource> pdfViewer(@RequestParam String first_name,
//			@RequestParam String mobile_num, @RequestParam String emailid) throws IOException
//	{
//		log.info("inside controller");
//		File pdfFile = pdfService.pdfGenerator(first_name, emailid, emailid);
//
//		InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));
//
////		redirectAttributes.addFlashAttribute("msg", "Okay");
//
//		ResponseEntity<InputStreamResource> response = ResponseEntity.ok()
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + pdfFile.getName())
//				.contentType(MediaType.APPLICATION_PDF).contentLength(pdfFile.length()).body(resource);
//		pdfFile.delete();
//		return response;
//	}

	@PostMapping("/pdfViewer")
	public ResponseEntity<ByteArrayResource> pdfViewer(@RequestParam String first_name, @RequestParam String mobile_num,
//	public String pdfViewer(@RequestParam String first_name, @RequestParam String mobile_num,
			@RequestParam String emailid)
	{
		byte[] pdfBytes = pdfService.pdfGenerator(first_name, mobile_num, emailid);

//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=user.pdf")
//				.contentType(MediaType.APPLICATION_PDF).contentLength(pdfBytes.length)
//				.body(new ByteArrayResource(pdfBytes));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=user.pdf")
				.contentType(MediaType.APPLICATION_PDF).contentLength(pdfBytes.length)
				.body(new ByteArrayResource(pdfBytes));

//		return "redirect:/";
	}

	@GetMapping("/hello")
	public String helloPage()
	{
		return "hello";
	}

}
